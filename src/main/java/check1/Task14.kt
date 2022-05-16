package check1

import tests
import java.io.PrintWriter
import java.util.*


// реализация быстрой сортировки с разбиением Хоара
fun quickSort(arr: LongArray, left: Int = 0, right: Int = arr.size - 1) {
    if (left < right) {
        val p = partition(arr, left, right)
        quickSort(arr, left, p)
        quickSort(arr, p + 1, right)
    }
}

fun partition(arr: LongArray, left: Int, right: Int): Int {
    var l = left
    var r = right
    val p = arr[(l + r) / 2]
    while (true) {
        // ищем 2 элемента такие, что левый меньше р, а правый больше р и меняем их местами
        while (arr[l] < p) l++
        while (arr[r] > p) r--
        if (l >= r) return r
        // swap
        val old = arr[l]
        arr[l] = arr[r]
        arr[r] = old
        l++
        r--
    }
}

fun main() {

    val solution = { scanner: Scanner, writer: PrintWriter ->
        val n = scanner.nextInt()
        val k = scanner.nextLong()
        val m = scanner.nextLong()
        val l = scanner.nextLong()
        val arr = LongArray(n)
        arr[0] = k // Заполнение последовательности
        for (i in 1 until arr.size) {
            arr[i] = (arr[i - 1] * m and 0xffffffffL) % l
        }
        // выполнили быструю сортировку
        quickSort(arr)
        var sum: Long = 0
        var i = 0
        // просуммировали
        while (i < arr.size) {
            sum = (sum + arr[i]) % l
            i += 2
        }
        writer.println(sum)
    }
    // запуск тестов
    tests(solution, List(10) { "z14_${it + 1}.txt" })
}

