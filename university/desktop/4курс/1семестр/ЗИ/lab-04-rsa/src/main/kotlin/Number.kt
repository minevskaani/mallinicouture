import java.math.BigInteger

fun generatePrimeRandom(a: Long, b: Long): Long {
    // Решето Эратосфена
    val simple = Array<Boolean>(b.toInt() + 1) { true }
    simple[0] = false
    simple[1] = false
    for (p in 2 until Math.sqrt(b.toDouble()).toInt()) {
        if (simple[p]) {
            for (i in p until simple.size step p) {
                simple[i] = false
            }
        }
    }

    // Filtered numbers
    val numbers = arrayListOf<Long>();
    for (n in a..b) {
        if (simple[n.toInt()]) {
            numbers.add(n)
        }
    }

    // Choose one randomly
    return numbers.random()
}

fun Long.isPrime(): Boolean {
    if (this < 2) return false

    for (i in 2..this / 2) {
        if (this % i == 0L) {
            return false
        }
    }

    return true
}

// Greatest Common Divisor
fun gcd(a: BigInteger, b: BigInteger): BigInteger {
    var _a = a
    var _b = b

    // Алгоритм Евклида
    while (_b.compareTo(BigInteger.ZERO) != 0) {
        val tmp = _b
        _b = _a % _b
        _a = tmp
    }

    return _a
}

/**
 * @param a first number for gcd
 * @param b second number for gcd
 * @return (d,x,y), where d - gcd of a and b. x and y are integer numbers, where ax + by = d
 * 1 ^ k = 1
 * m ^ phi == 1 (mod n)
 * (m ^ phi) ^ k = 1 (mod n)
 * m ^ (k * phi) = 1 (mod n)
 *
 * 1 * m = m
 * m ^ (k * phi) * m = m (mod n)
 * m ^ (k * phi + 1) = m (mod n)
 *  ecли m ^ (e * d) = m (mod n)
 *
 *  => пучаем уравнение k * phi + 1 = e * d
 *  k * phi - e * d = -1
 *  -k * phi + e * d = 1
 *  s * phi + e * d = 1
 *
 * e*d + s*phi = 1
 */
fun gcdEx(a: Long, b: Long): Triple<Long, Long, Long> {
    if (b == 0L) {
        return Triple(a,1, 0)
    }

    val (d, x, y) = gcdEx(b, a % b)
    return Triple(d, y, x - y * (Math.floorDiv(a, b)))
}