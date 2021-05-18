class HuffmanNode(
    var data: Byte? = null,
    var frequency: Int = 0,
    var left: HuffmanNode? = null,
    var right: HuffmanNode? = null
): Comparable<HuffmanNode> {
    override fun compareTo(other: HuffmanNode) = frequency!! - other.frequency!!
}