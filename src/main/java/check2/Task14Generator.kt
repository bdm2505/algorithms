package check2

import java.io.PrintWriter

fun main(){
    val n = 1
    val l = 3
    f(1, 3, 1)
    f(1, 100, 2)
    f(3, 10, 3)
    f(5, 5, 4)
    f(7, 3, 5)
    f(8, 10, 6)
    f(9, 7, 7)
    f(11, 6, 8)
    f(100, 10000, 9)
    f(10000, 10000, 10)
}

private fun f(n: Int, l: Int, nun: Int) {
    val ss = "qwertyuiopasdfghjklzxcvbnm".uppercase()

    val wr = PrintWriter("input/check_2_task_14/$nun.txt")
    wr.println(n)
    for (i in 1..n) {
        for (j in 1..l)
            wr.print(ss.random())
        wr.println()
    }
    wr.close()
}