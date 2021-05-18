
import java.io.File


// Создание раудовых ключей
// Начальная перестановка В
// C(0)
val K1P = arrayOf(
    57, 49, 41, 33, 25, 17,  9,  1, 58, 50, 42, 34, 26, 18,
    10,  2, 59, 51, 43, 35, 27, 19, 11,  3, 60, 52, 44, 36
)

// D(0)
val K2P = arrayOf(
    63, 55, 47, 39, 31, 23, 15,  7, 62, 54, 46, 38, 30, 22,
    14,  6, 61, 53, 45, 37, 29, 21, 13,  5, 28, 20, 12,  4
)

// Сдвиг Si
// TODO:

// Сжимающая перестановка CP(56->48)
val CP = arrayOf(
    14, 17, 11, 24,  1,  5,  3, 28, 15,  6, 21, 10,
    23, 19, 12,  4, 26,  8, 16,  7, 27, 20, 13,  2,
    41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48,
    44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32
)

// Шифрование/расщифровка
// S-блоки
val SBox = arrayOf<Array<Array<UByte>>>(
    // 1-й блок
    arrayOf(
        arrayOf<UByte>(14u,  4u, 13u,  1u,  2u, 15u, 11u,  8u,  3u, 10u,  6u, 12u,  5u,  9u,  0u,  7u),
        arrayOf<UByte>( 0u, 15u,  7u,  4u, 14u,  2u, 13u,  1u, 10u,  6u, 12u, 11u,  9u,  5u,  3u,  8u),
        arrayOf<UByte>( 4u,  1u, 14u,  8u, 13u,  6u,  2u, 11u, 15u, 12u,  9u,  7u,  3u, 10u,  5u,  0u),
        arrayOf<UByte>(15u, 12u,  8u,  2u,  4u,  9u,  1u,  7u,  5u, 11u,  3u, 14u, 10u,  0u,  6u, 13u)
    ),
    // 2-й блок
    arrayOf(
        arrayOf<UByte>(15u,  1u,  8u, 14u,  6u, 11u,  3u,  4u,  9u,  7u,  2u, 13u, 12u,  0u,  5u, 10u),
        arrayOf<UByte>( 3u, 13u,  4u,  7u, 15u,  2u,  8u, 14u, 12u,  0u,  1u, 10u,  6u,  9u, 11u,  5u),
        arrayOf<UByte>( 0u, 14u,  7u, 11u, 10u,  4u, 13u,  1u,  5u,  8u, 12u,  6u,  9u,  3u,  2u, 15u),
        arrayOf<UByte>(13u,  8u, 10u,  1u,  3u, 15u,  4u,  2u, 11u,  6u,  7u, 12u,  0u,  5u, 14u,  9u)
    ),
    // 3-й блок
    arrayOf(
        arrayOf<UByte>(10u,  0u,  9u, 14u,  6u,  3u, 15u,  5u,  1u, 13u, 12u,  7u, 11u,  4u,  2u,  8u),
        arrayOf<UByte>(13u,  7u,  0u,  9u,  3u,  4u,  6u, 10u,  2u,  8u,  5u, 14u, 12u, 11u, 15u,  1u),
        arrayOf<UByte>(13u,  6u,  4u,  9u,  8u, 15u,  3u,  0u, 11u,  1u,  2u, 12u,  5u, 10u, 14u,  7u),
        arrayOf<UByte>( 1u, 10u, 13u,  0u,  6u,  9u,  8u,  7u,  4u, 15u, 14u,  3u, 11u,  5u,  2u, 12u)
    ),
    // 4-й блок
    arrayOf(
        arrayOf<UByte>( 7u, 13u, 14u,  3u,  0u,  6u,  9u, 10u,  1u,  2u,  8u,  5u, 11u, 12u,  4u, 15u),
        arrayOf<UByte>(13u,  8u, 11u,  5u,  6u, 15u,  0u,  3u,  4u,  7u,  2u, 12u,  1u, 10u, 14u,  9u),
        arrayOf<UByte>(10u,  6u,  9u,  0u, 12u, 11u,  7u, 13u, 15u,  1u,  3u, 14u,  5u,  2u,  8u,  4u),
        arrayOf<UByte>( 3u, 15u,  0u,  6u, 10u,  1u, 13u,  8u,  9u,  4u,  5u, 11u, 12u,  7u,  2u, 14u)
    ),
    // 5-й блок
    arrayOf(
        arrayOf<UByte>( 2u, 12u,  4u,  1u,  7u, 10u, 11u,  6u,  8u,  5u,  3u, 15u, 13u,  0u, 14u,  9u),
        arrayOf<UByte>(14u, 11u,  2u, 12u,  4u,  7u, 13u,  1u,  5u,  0u, 15u, 10u,  3u,  9u,  8u,  6u),
        arrayOf<UByte>( 4u,  2u,  1u, 11u, 10u, 13u,  7u,  8u, 15u,  9u, 12u,  5u,  6u,  3u,  0u, 14u),
        arrayOf<UByte>(11u,  8u, 12u,  7u,  1u, 14u,  2u, 13u,  6u, 15u,  0u,  9u, 10u,  4u,  5u,  3u)
    ),
    // 6-й блок
    arrayOf(
        arrayOf<UByte>(12u,  1u, 10u, 15u,  9u,  2u,  6u,  8u,  0u, 13u,  3u,  4u, 14u,  7u,  5u, 11u),
        arrayOf<UByte>(10u, 15u,  4u,  2u,  7u, 12u,  9u,  5u,  6u,  1u, 13u, 14u,  0u, 11u,  3u,  8u),
        arrayOf<UByte>( 9u, 14u, 15u,  5u,  2u,  8u, 12u,  3u,  7u,  0u,  4u, 10u,  1u, 13u, 11u,  6u),
        arrayOf<UByte>( 4u,  3u,  2u, 12u,  9u,  5u, 15u, 10u, 11u, 14u,  1u,  7u,  6u,  0u,  8u, 13u)
    ),
    // 7-й блок
    arrayOf(
        arrayOf<UByte>( 4u, 11u,  2u, 14u, 15u,  0u,  8u, 13u,  3u, 12u,  9u,  7u,  5u, 10u,  6u,  1u),
        arrayOf<UByte>(13u,  0u, 11u,  7u,  4u,  9u,  1u, 10u, 14u,  3u,  5u, 12u,  2u, 15u,  8u,  6u),
        arrayOf<UByte>( 1u,  4u, 11u, 13u, 12u,  3u,  7u, 14u, 10u, 15u,  6u,  8u,  0u,  5u,  9u,  2u),
        arrayOf<UByte>( 6u, 11u, 13u,  8u,  1u,  4u, 10u,  7u,  9u,  5u,  0u, 15u, 14u,  2u,  3u, 12u)
    ),
    // 8-й блок
    arrayOf(
        arrayOf<UByte>(13u,  2u,  8u,  4u,  6u, 15u, 11u,  1u, 10u,  9u,  3u, 14u,  5u,  0u, 12u,  7u),
        arrayOf<UByte>( 1u, 15u, 13u,  8u, 10u,  3u,  7u,  4u, 12u,  5u,  6u, 11u,  0u, 14u,  9u,  2u),
        arrayOf<UByte>( 7u, 11u,  4u,  1u,  9u, 12u, 14u,  2u,  0u,  6u, 10u, 13u, 15u,  3u,  5u,  8u),
        arrayOf<UByte>( 2u,  1u, 14u,  7u,  4u, 10u,  8u, 13u, 15u, 12u,  9u,  0u,  3u,  5u,  6u, 11u)
    )
)

// Начальная перестановка IP
val IP = arrayOf(
    58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4,
    62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8,
    57, 49, 41, 33, 25, 17,  9, 1, 59, 51, 43, 35, 27, 19, 11, 3,
    61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7
)

// Конечноя перестановка FP
val FP = arrayOf(
    40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31,
    38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29,
    36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27,
    34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41,  9, 49, 17, 57, 25

)

// Расширяющая перестановка E
val EP = arrayOf(
    32,  1,  2,  3,  4,  5,  4,  5,  6,  7,  8,  9,
    8,  9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17,
    16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25,
    24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32,  1
)

// Завершающая перестановка в функции шифрования P
val P = arrayOf(
    16,  7, 20, 21, 29, 12, 28, 17,  1, 15, 23, 26,  5, 18, 31, 10,
    2,  8, 24, 14, 32, 27,  3,  9, 19, 13, 30,  6, 22, 11,  4, 25
)

fun writeBytes(path: String, bytes: ByteArray) {
    val file = File(path)
    file.createNewFile()
    file.writeBytes(bytes)
}

fun main() {
    val path = "image.png"
    val fileData = File(path).readBytes()

    val buffer = fileData.map { it.toUByte() }.toTypedArray()
    val encrypted = Array<UByte>(buffer.size + 64) {0u}
    val decrypted = Array<UByte>(buffer.size + 64) {0u}

    val keys8b = arrayOf<UByte>(123u, 134u, 234u, 1u, 2u, 9u, 0u, 3u) // Size 8

    val length = DES(encrypted, true, keys8b, buffer, buffer.size)
    writeBytes("encrypted_$path", encrypted.map { it.toByte() }.toByteArray())

    DES(decrypted, false, keys8b, encrypted, length)
    writeBytes("decrypted_$path", decrypted.sliceArray(buffer.indices).map { it.toByte() }.toByteArray())
}

fun DES(to: Array<UByte>, isEncryption: Boolean, keys8b: Array<UByte>, _from: Array<UByte>, _length: Int): Int {
    val length = if (_length % 8 == 0) _length else _length + (8 - (_length % 8))
    var from = _from
    if (length != from.size) {
        // We need more space
        from = Array(length) { i ->  if (i < from.size) from[i] else 0u }

    }

    val keys48b = Array<ULong>(16) {0u}

    keyExpansion(join8bitsTo64bits(keys8b), keys48b)

    for (i in 0 until length step 8) {
        var (N1, N2) = split64bitsTo32bits(initialPermutation(join8bitsTo64bits(from.sliceArray(i until i + 8))))
        val pair = feistelCipher(isEncryption, N1, N2, keys48b)
        N1 = pair.first
        N2 = pair.second
        split64bitsTo8bits(finalPermutation(join32bitsTo64Bits(N1, N2))).forEachIndexed { index, el ->
            to[i  + index] = el
        }
    }

    return length
}

fun feistelCipher(isEncryption: Boolean, _N1: UInt, _N2: UInt, keys48b: Array<ULong>): Pair<UInt, UInt> {
    var N1 = _N1
    var N2 = _N2

    if (isEncryption) {
        for (round in 0 until 16) {
            val (__N1, __N2) = roundFeistelCipher(N1, N2, keys48b[round])
            N1 = __N1
            N2 = __N2
        }
    } else {
        for (round in 15 downTo  0) {
            val (__N1, __N2) = roundFeistelCipher(N1, N2, keys48b[round])
            N1 = __N1
            N2 = __N2
        }
    }

    // swap
    return Pair(N2, N1)
}

fun roundFeistelCipher(N1: UInt, N2: UInt, key48b: ULong)
        = Pair(N2, funcF(N2, key48b) xor N1)

fun funcF(block32b: UInt, key48b: ULong): UInt {
    val block48b = expansionPermutation(block32b) xor key48b
    return permutation(substitutions(block48b))
}

fun expansionPermutation(block32b: UInt): ULong {
    var block48b = 0uL
    for (i in 0 until 48) {
        block48b = block48b or (((block32b shr (32 - EP[i])) and 1u).toULong() shl (63 - i))
    }

    return block48b
}

fun substitutions(block48b: ULong): UInt {
    val blocks4b = Array<UByte>(4) { 0u } // It is like 8 blocks with 4 bits
    val blocks6b = Array<UByte>(8) { 0u }

    split48bitsTo6bits(block48b, blocks6b)
    substitution6bitsTo4Bits(blocks6b, blocks4b)

    return join4bitsto32bits(blocks4b)
}

fun substitution6bitsTo4Bits(blocks6b: Array<UByte>, blocks4b: Array<UByte>) {
    var block2b: UByte
    var block4b: UByte

    for ((j, i) in (0 until 8 step 2).withIndex()) {
        block2b = extremeBits(blocks6b[i]) // Нужнык крайные 2 бита (левый и правый)
        block4b = middleBits(blocks6b[i]) // Осталные 4 бита в середине
        blocks4b[j] = SBox[i][block2b.toInt()][block4b.toInt()]

        block2b = extremeBits(blocks6b[i + 1])
        block4b = middleBits(blocks6b[i + 1])
        blocks4b[j] = (blocks4b[j].toUInt() shl 4).toUByte() or SBox[i + 1][block2b.toInt()][block4b.toInt()]// TODO: check!
    }
}

fun extremeBits(block6b: UByte)
        = (((block6b.toUInt() shr 6) and 2u) or (block6b.toUInt() shr 2) and 1u).toUByte()

fun middleBits(block6b: UByte)
        = (block6b.toUInt() shr 3).toUByte() and 0xFu

fun permutation(block32b: UInt): UInt {
    var newBlock32b: UInt = 0u
    for (i in 0 until 32) {
        newBlock32b = newBlock32b or (((block32b shr (32 - P[i])) and 1u) shl (31 - i))
    }

    return newBlock32b
}

fun initialPermutation(block64b: ULong): ULong {
    var result = 0uL
    for (i in 0 until 64) {
        result = result or (((block64b shr (64 - IP[i])) and 1u) shl (63 - i))
    }
    return result
}

fun finalPermutation(block64b: ULong): ULong {
    var result = 0uL
    for (i in 0 until 64) {
        result = result or (((block64b shr (64 - FP[i])) and 1u) shl (63 - i))
    }
    return result
}

// Key operations
fun keyExpansion(key64b: ULong, keys48b: Array<ULong>) {
    val (K1, K2) = keyPermutation56bitsTo28bits(key64b) // Divide into 2 blocks with 28bits
    keyExpansionTo48bits(K1, K2, keys48b)
}

fun keyPermutation56bitsTo28bits(block56b: ULong): Pair<UInt, UInt> {
    var block28b_1: UInt = 0u
    var block28b_2: UInt = 0u

    for (i in 0 until 28) {
        block28b_1 = block28b_1 or (((block56b shr (64 - K1P[i])) and 1u) shl (31 - i)).toUInt()
        block28b_2 = block28b_2 or (((block56b shr (64 - K2P[i])) and 1u) shl (31 - i)).toUInt()
    }

    return Pair(block28b_1, block28b_2)
}

fun keyExpansionTo48bits(_block28b_1: UInt, _block28b_2: UInt, keys48b: Array<ULong>) {
    var block56b: ULong
    var block28b_1 = _block28b_1
    var block28b_2 = _block28b_2

    for (i in 0 until 16) {
        val n = when(i) {
            0, 1, 8, 15 -> 1
            else -> 2
        }

        block28b_1 = LSHIFT_28BIT(block28b_1, n)
        block28b_2 = LSHIFT_28BIT(block28b_2, n)

        block56b = join28bitsTo56bits(block28b_1, block28b_2)

        keys48b[i] = keyContractionPermutation(block56b)
    }
}

fun keyContractionPermutation(block56b: ULong): ULong {
    var block48b: ULong = 0u
    for (i in 0 until 48) {
        block48b = block48b or (((block56b shr (64 - CP[i])) and 1u) shl (63 - i))
    }

    return block48b
}

fun LSHIFT_28BIT(num: UInt, L: Int)
        = ((((num) shl (L)) or ((num) shr (-(L) and 27))) and (((1u).toUInt() shl 32) - 1u))

// Splits
fun split64bitsTo32bits(block64b: ULong)
        = Pair((block64b shr 32).toUInt(), block64b.toUInt())

fun split64bitsTo8bits(block64b: ULong): Array<UByte> {
    val blocks8b = Array<UByte>(8) {0u}

    for (i in 0 until 8) {
        blocks8b[i] = (block64b shr ((7 - i) * 8)).toUByte()
    }
    return blocks8b
}

fun split48bitsTo6bits(block48b: ULong, blocks6b: Array<UByte>) {
    for (i in 0 until 8) {
        blocks6b[i] = ((block48b shr (58 - (i * 6))) shl 2).toUByte()
    }
}

// Joins
fun join32bitsTo64Bits(block32b_1: UInt, block32b_2: UInt)
        = (block32b_1.toULong() shl 32) or block32b_2.toULong()

fun join28bitsTo56bits(block28b_1: UInt, block28b_2: UInt)
        = (((block28b_1 shr 4).toULong() shl 32) or block28b_2.toULong()) shl 4

fun join8bitsTo64bits(blocks8b: Array<UByte>): ULong {
    var block64bits: ULong = 0u
    for (i in 0 until 8) {
        block64bits = (block64bits shl 8) or blocks8b[i].toULong()
    }

    return block64bits
}

fun join4bitsto32bits(blocks4b: Array<UByte>): UInt {
    var block32b: UInt = 0u
    for (i in 0 until 4) {
        block32b = (block32b shl 8) or blocks4b[i].toUInt()
    }

    return block32b
}