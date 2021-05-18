import java.io.File
import java.io.FileInputStream
import java.security.KeyStore
import java.security.PrivateKey
import java.security.Signature
import java.security.cert.Certificate


val filePath = "A.zip"
val signaturePath = "digitalSignature.bin"

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val privateKey =  loadPrivateKey(
            "sender_keystore.p12",
            "crg02asd94jfh31fhks82hdfb1ubfbcdu2jaq2",
            "senderKeyPair"
        )
        val certificate = loadCertificate(
            "receiver_keystore.p12",
            "crg02asd94jfh31fhks82hdfb1ubfbcdu2jaq2",
            "receiverKeyPair"
        )

        sign(privateKey)
        if (verify(certificate)) {
            println("Verification passed.")
        } else {
            System.err.println("Verification failed!")
        }
    }

    fun loadPrivateKey(keyStorePath: String, pass: String, alias: String): PrivateKey {
        val keyStore = KeyStore.getInstance("PKCS12")
        keyStore.load(FileInputStream(keyStorePath), pass.toCharArray())

        return keyStore.getKey(alias, pass.toCharArray()) as PrivateKey
    }

    fun loadCertificate(keyStorePath: String, pass: String, alias: String): Certificate {
        val keyStore = KeyStore.getInstance("PKCS12")
        keyStore.load(FileInputStream(keyStorePath), pass.toCharArray())

        return keyStore.getCertificate(alias)
    }

    fun sign(privateKey: PrivateKey) {
        val signature = Signature.getInstance("SHA512withRSA")
        signature.initSign(privateKey)

        val file = File(filePath)
        val messageBytes = file.readBytes()

        signature.update(messageBytes)

        val digitalSignature = signature.sign()
        File(signaturePath).writeBytes(digitalSignature)
    }

    fun verify(publicKey: Certificate): Boolean {
        val signature = Signature.getInstance("SHA512withRSA")
        signature.initVerify(publicKey)

        val messageBytes = File(filePath).readBytes()
        val receivedSignature = File(signaturePath).readBytes()

        signature.update(messageBytes)

        return signature.verify(receivedSignature)
    }
}