@file:Suppress("UNCHECKED_CAST")

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*

class CompressedSerializer @ExperimentalUnsignedTypes constructor() {
    var freq = HashMap<Byte, Int>()
    var compressed: BitSet = BitSet()

    constructor(freq: HashMap<Byte, Int>, compressed: BitSet) : this() {
        this.freq = freq
        this.compressed = compressed
    }

    fun read(fis: FileInputStream) {
        val reader = ObjectInputStream(fis)

        val table: Array<Pair<Byte, Int>> = reader.readObject() as Array<Pair<Byte, Int>>
        val data = fis.readBytes()

        table.forEach { freq.put(it.first, it.second) }

        compressed = BitSet.valueOf(data)
    }

    fun write(fos: FileOutputStream) {
        val writer = ObjectOutputStream(fos)

        val table = freq.toList().toTypedArray()
        writer.writeObject(table)

        fos.write(compressed.toByteArray())
    }

    /*
    fun read(fis: FileInputStream) {
        val reader = ObjectInputStream(fis)

        val table: Array<Pair<Byte, Int>> = reader.readObject() as Array<Pair<Byte, Int>>
        val data: ByteArray = reader.readObject() as ByteArray

        table.forEach { freq.put(it.first, it.second) }

        compressed = BitSet.valueOf(data)

    }

    fun write(fos: FileOutputStream) {
        val writer = ObjectOutputStream(fos)

        val table = freq.toList().toTypedArray()
        writer.writeObject(table)

        val data = compressed.toByteArray()
        writer.writeObject(data)
    }

     */
}