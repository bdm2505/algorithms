package check2

import tests
import tests.Algorithm
import tests.Tester
import java.io.PrintWriter
import java.util.*
// структура для хранения строки на основе HashMap
class SortString(ss: String) {
    val map = mutableMapOf<Char, Int>()

    fun add(ch: Char){
        if (map.containsKey(ch))
            map[ch] = map[ch]!! + 1
        else
            map[ch] = 1
    }

    init {
        for (ch in ss)
            add(ch)
    }
    override fun hashCode(): Int {
        return map.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SortString

        if (map != other.map) return false

        return true
    }


}

class Task14 : Algorithm {
    override fun run(scanner: Scanner, writer: PrintWriter) {
        val n = scanner.nextInt()
        // множество отсортированных строк
        val set = mutableSetOf<SortString>()
        for(i in 1..n){
            val str = scanner.next()
            // отсортировать строку
            val sorted = SortString(str)
            // добавить в множество
            set.add(sorted)
        }
        //writer.println(set)

        writer.println(set.size)
    }

    override fun inputFolder(): String  = "input/check_2_task_14"

}

fun main(){
    Tester.run(Task14())
}
