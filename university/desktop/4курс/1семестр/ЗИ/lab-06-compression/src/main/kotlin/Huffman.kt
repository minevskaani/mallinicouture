import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashMap

class Huffman {
    val stringPrefixMap = HashMap<Byte, String>()
    val bitPrefixMap = HashMap<Byte, BitSet>()
    var root: HuffmanNode? = null

    private fun buildThree(freq: Map<Byte, Int>): HuffmanNode {
        val priorityQueue = PriorityQueue<HuffmanNode>()

        for(byte in freq.keys) {
            val node = HuffmanNode(data = byte, frequency = freq.getValue(byte))
            priorityQueue.offer(node)
        }
        assert(priorityQueue.size > 0)

        while (priorityQueue.size > 1) {
            val x = priorityQueue.peek()
            priorityQueue.poll()

            val y = priorityQueue.peek()
            priorityQueue.poll()

            val sum = HuffmanNode(
                frequency = x.frequency + y.frequency,
                left = x,
                right = y
            )

            priorityQueue.offer(sum)
        }

        return priorityQueue.poll()
    }

    private fun setPreffixCodes(node: HuffmanNode?, prefix: StringBuilder) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                stringPrefixMap.put(node.data!!, prefix.toString())
            } else {
                prefix.append('0')
                setPreffixCodes(node.left, prefix)
                prefix.deleteCharAt(prefix.length - 1)

                prefix.append('1')
                setPreffixCodes(node.right, prefix)
                prefix.deleteCharAt(prefix.length - 1)
            }
        }
    }

    private fun setBitPrefixCodes() {
        stringPrefixMap.forEach { (byte, str) ->
            val bits = BitSet()
            str.forEachIndexed { index, chr -> bits.set(index, chr == '1') }
            bitPrefixMap[byte] = bits
        }
    }

    fun encode(fis: FileInputStream, fos: FileOutputStream) {
        val input = fis.readAllBytes()

        val freq = HashMap<Byte, Int>()

        for (i in input.indices) {
            freq[input[i]] = (freq[input[i]] ?: 0) + 1
        }

        println("Character Frequency Map = $freq")

        root = buildThree(freq)

        setPreffixCodes(root, StringBuilder())
        setBitPrefixCodes()
        println("Character Prefix Map = $stringPrefixMap")
        println("Bit Prefix Map = $bitPrefixMap")
        val bits = BitsetBuilder()

        for (i in input.indices) {
            bits.append(stringPrefixMap[input[i]]!!)
        }

        val serializer = CompressedSerializer(freq, bits.bitSet)
        serializer.write(fos)
    }

    fun decode(fis: FileInputStream, fos: FileOutputStream) {
        // Read data
        val serializer = CompressedSerializer()
        serializer.read(fis)

        val freq = serializer.freq
        val input = serializer.compressed
        // Build tree
        root = buildThree(freq)

        val result = mutableListOf<Byte>()

        var temp = root

        for (i in 0..input.length()) {
            val j = input[i]

            if (!j) {
                temp = temp!!.left
                if (temp?.left == null && temp?.right == null) {
                    result.add(temp!!.data!!)
                    temp = root
                }
            } else {
                temp = temp!!.right
                if (temp?.left == null && temp?.right == null) {
                    result.add(temp!!.data!!)
                    temp = root
                }
            }
        }

        fos.write(result.toByteArray())
    }
}