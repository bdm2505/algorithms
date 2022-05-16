package work1

fun f(x: Double) =
    if (x < 0) 0
    else if (x % 2 < 1.0) 1
    else -1

fun main() {
    val num = readLine()!!.toDouble()
    println("result:${f(num)}")
}