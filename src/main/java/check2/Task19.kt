package check2

import tests.Algorithm
import tests.Tester
import java.io.PrintWriter
import java.math.BigInteger
import java.util.*
import kotlin.random.Random

class Task19 : Algorithm {
    override fun run(scanner: Scanner, writer: PrintWriter) {
        val n = scanner.nextInt()
        for (i in 0 until n) {
            val str = scanner.next()
            // для больших чисел
            val big = BigInteger(str)
            // извлечь корень и возвести в квадрат
            val next = big.sqrt().pow(2)
            if (big == next)
                writer.println(i + 1)
        }
    }

    override fun inputFolder(): String = "input/check_2_task_19"

}

fun main() {
    //generator()
    Tester.run(Task19())
}

fun generator(){
    f(5, "a")
    f(6, "b")
    f(7, "c")
    f(8, "d")
    f(10, "e")
    f(100, "f")
    f(1000, "g")
    f(10000, "h")
    f(100000, "k")
    f(1000000, "l")
}

private fun f(n: Int, num: String) {

    val wr = PrintWriter("input/check_2_task_19/$num.txt")
    wr.println(n)
    for (i in 1..n) {
        for (j in 1..Random.nextInt(95) + 5)
            wr.print(Random.nextInt(10))
        wr.println()
    }
    wr.close()
}
