import java.io.*

fun main(args: Array<String>) {

    testSerialization("A.zip")
}

fun testSerialization(inputPath: String) {
    val compressedPath = "compressed_$inputPath"
    val resultPath = "result_$inputPath"
    val huffman = Huffman()
    huffman.encode(FileInputStream(inputPath), FileOutputStream(compressedPath))
    huffman.decode(FileInputStream(compressedPath), FileOutputStream(resultPath))
}
