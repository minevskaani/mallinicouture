import java.io.FileInputStream
import java.io.FileOutputStream

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val rsa = RSA()
        println(rsa.toString())

        val path = "A.zip"
        testFile(rsa, path)
    }
    fun testFile(rsa: RSA, path: String) {
        val encPath = "encrypted_$path"
        val decPath = "decrypted_$path"

        println("Encrypting...")
        rsa.encrypt(FileInputStream(path), FileOutputStream(encPath))
        println("Decrypting...")
        rsa.decrypt(FileInputStream(encPath), FileOutputStream(decPath))
        println("Done.")
    }
}


