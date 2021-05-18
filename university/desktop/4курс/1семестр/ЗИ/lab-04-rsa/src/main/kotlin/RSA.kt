import java.io.FileInputStream
import java.io.FileOutputStream
import java.math.BigInteger
import java.security.SecureRandom

class RSA() {

    private val BIT_LENGTH: Int
    var p: BigInteger
    var q: BigInteger
    var n: BigInteger
    var phi: BigInteger
    var e: BigInteger // открытый ключ
    var d: BigInteger // закрытый ключ
    var clearTextSize: Int
    var cipherTextSize: Int

    init {
        BIT_LENGTH = 2048
        val rand: java.util.Random = SecureRandom()
        do {
            p = BigInteger.probablePrime(BIT_LENGTH / 2, rand)
            q = BigInteger.probablePrime(BIT_LENGTH / 2, rand)
            //p = BigInteger.valueOf(generatePrimeRandom(100, 999))
            //q = BigInteger.valueOf(generatePrimeRandom(100, 999))
        } while (p.compareTo(q) == 0)

        n = p * q
        phi = (p - BigInteger.ONE) * (q - BigInteger.ONE)
        // generateE
        /*
         * е - число не имеещее общие делители с phi
         */
        do {
            e = BigInteger(phi.bitLength(), rand);
            //e = BigInteger.valueOf(Random.nextLong(100, 999))// 2 until 255
        } while (e.compareTo(BigInteger.ONE) <= 0
                || e.compareTo(phi) >= 0
                || !gcd(e, phi).equals(BigInteger.ONE)
                //|| phi.gcd(e).equals(BigInteger.ONE)
        )

        // generateD
        /**
         * e * d == 1 (mod phi)
         */
        d = e.modInverse(phi)
        val keySize = n.bitLength() // In bits

        clearTextSize = Math.min((keySize - 1) / 8, 256) // In bytes
        cipherTextSize = 1 + (keySize - 1) / 8 // in bytes
    }



    //private fun generateD() {
    //    val (_, k1, _) = gcdEx(e, phi)
    //    this.d = k1
    //}

    /*
    private fun crypt(a: ByteArray, key: Long): ByteArray {
        val bi = BigInteger(a)
            .pow(key.toInt())
            .remainder(BigDecimal(n).unscaledValue())
        //println("a ${a.contentToString()} bi $bi key $key")
        println("Crypt")
        return bi
            .toByteArray()
            .removeFirsZeros()
    }

    fun encrypt(a: ByteArray)
        = a.flatMap { crypt(byteArrayOf(it), e).fillWithZerosUntilSize(blockSize).asIterable() }.toByteArray()

    fun decrypt(a: ByteArray) = a.split(blockSize).flatMap { crypt(it, d).asIterable() }.toByteArray()
     */
    private fun crypt(from: ByteArray, to: ByteArray, key: BigInteger) {
        val clearText = BigInteger(1, from)
        val cipherText = clearText.modPow(key, n)

        val cipherTextData = cipherText.toByteArray()

        to.putBytesBlock(cipherTextData)
    }

    fun encrypt(fis: FileInputStream, fos: FileOutputStream) {
        val clearTextBlock = ByteArray(clearTextSize)
        val cipherTextBlock = ByteArray(cipherTextSize)

        var blocks = 0L
        var dataSize = fis.read(clearTextBlock)
        var isPadded = false

        while (dataSize > 0) {
            blocks++
            if (dataSize < clearTextSize) {
                clearTextBlock.padBytesBlock(dataSize)
                isPadded = true
            }
            crypt(clearTextBlock, cipherTextBlock, e)

            fos.write(cipherTextBlock)

            dataSize = fis.read(clearTextBlock)
        }

        // Adding a full padding block, if needed
        if (!isPadded) {
            blocks++
            clearTextBlock.padBytesBlock(0)

            crypt(clearTextBlock, cipherTextBlock, e)

            fos.write(cipherTextBlock)
        }

        fis.close()
        fos.close()

        println("Encryption block count: $blocks")
    }

    fun decrypt(fis: FileInputStream, fos: FileOutputStream) {
        val clearTextBlock = ByteArray(clearTextSize)
        val cipherTextBlock = ByteArray(cipherTextSize)

        var blocks = 0L
        var dataSize = fis.read(cipherTextBlock)

        while (dataSize > 0) {
            blocks++

            crypt(cipherTextBlock, clearTextBlock, d)

            dataSize = fis.read(cipherTextBlock)

            if (dataSize > 0) {
                fos.write(clearTextBlock)
            }
        }

        val lastBlock = clearTextBlock.removePadding()
        if (lastBlock.isNotEmpty()) {
            fos.write(lastBlock)
        }

        println("Decryption block count: $blocks")
    }

    override fun toString(): String {
        return "RSA(BIT_LENGTH=$BIT_LENGTH, p=$p, q=$q, n=$n, phi=$phi, e=$e, d=$d, clearTextSize=$clearTextSize, cipherTextSize=$cipherTextSize)"
    }
}

fun ByteArray.padBytesBlock(dataSize: Int) {
    val bSize: Int = this.size
    val padSize = bSize - dataSize
    val padValue = padSize % bSize

    for (i in 0 until padSize) {
        this[bSize - i - 1] = padValue.toByte()
    }
    println("padValue $padValue")
}

fun ByteArray.removePadding(): ByteArray {
    val bSize: Int = this.size
    var padSize: Int = this[bSize - 1] % bSize
    if (padSize < 0) {
        padSize += Byte.MAX_VALUE - Byte.MIN_VALUE + 1
    }
    println("padSize $padSize lastArr ${this.contentToString()}")

    return this.sliceArray(0 until bSize - padSize)
}

fun ByteArray.putBytesBlock(data: ByteArray) {
    val bSize = this.size
    val dSize = data.size

    var i = 0
    while (i < dSize && i < bSize) {
        this[bSize - i - 1] = data[dSize - i - 1]
        i++
    }

    while (i < bSize) {
        this[bSize - i - 1] = 0
        i++
    }
}