package check3

import tests.Algorithm
import java.io.PrintWriter
import java.util.*

class Task19 : Algorithm {
    override fun run(scanner: Scanner, writer: PrintWriter) {

        val arr = Array(82) { 0 }
        //arr[0] = 1
        val narr = Array(82) { 0 }




    }

    private fun alg1(arr: Array<Int>) {
        for (i in 1..999) {
            val s = summ(i)
            arr[s] += 1
        }
    }
    private fun alg2(arr: Array<Int>, narr: Array<Int>) {
        for (i in 1..999) {
            val s = summ(i)
            narr[s] += 1
            for (j in arr.indices) {
                
            }
        }
    }

    override fun inputFolder(): String = "input/check_3_task_19"

    fun summ(k: Int): Int {
        return k % 10 + k / 10 % 10 + k / 100 % 10 + k / 1000 % 10 + k / 10000 % 10 + k / 100000 % 10 + k / 1000000 % 10 + k / 10000000 % 10 + k / 100000000 % 10 + k / 1000000000 % 10
    }
}

fun main() {

    Task19().run(Scanner(System.`in`), PrintWriter(System.out))
}