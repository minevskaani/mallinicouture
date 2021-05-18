import java.util.*

class BitsetBuilder() {
    val bitSet = BitSet()
    private var index: Int = 0

    fun append(value: Boolean) {
        bitSet.set(index, value)
        index++
    }

    fun append(value: String) {
        value.forEach {
            if (it == '0') {
                append(false)
            } else if (it == '1'){
                append(true)
            }
        }
    }
}