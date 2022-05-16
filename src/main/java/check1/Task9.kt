package check1

import tests
import java.io.PrintWriter
import java.util.*


fun main() {
    val solution = { scanner: Scanner, writer: PrintWriter ->
        // N – общее число зарегистрированных участников,
        // K – требуемое количество человек в первой команде,
        // M – количество пар участников, знакомых друг с другом.

        val N = scanner.nextInt()
        val K = scanner.nextInt()
        val M = scanner.nextInt()

        // каждому участнику ставим в соответствие всех его знакомых
        val friends = mutableMapOf<Int, MutableList<Int>>()
        for (i in 1..N) {
            friends[i] = mutableListOf()
        }
        for (i in 1..M) {
            val a = scanner.nextInt()
            val b = scanner.nextInt()
            friends[a]!!.add(b)
            friends[b]!!.add(a)
        }

        // возвращает множество всех знакомых с start и тех кто знаком со знакомыми (dfs)
        fun allFriends(start: Int): Set<Int> {
            val queue = ArrayDeque<Int>()
            val result = mutableSetOf<Int>()
            queue.add(start)
            // пока есть знакомые которых не проверили
            // добовляем всех знакомых в result
            while (!queue.isEmpty()) {
                val elem = queue.poll()
                if (result.contains(elem))
                    continue
                if (friends.containsKey(elem)) {
                    result.add(elem)
                    queue.addAll(friends[elem]!!)
                }
            }
            return result
        }

        // количество свободных мест в первой команде 
        var k = K
        // количество свободных мест в первой команде
        var l = N - K
        // множество участников в первой команде
        val firstCommand = mutableSetOf<Int>()
        while (k > 0 && l > 0) {
            // выбираем область связанности с максимальным количеством элементов

            var maxSet = allFriends(1)

            for (i in 2..N) {
                val currSet = allFriends(i)
                if (currSet.size > maxSet.size) {
                    maxSet = currSet
                }
            }
            // из двух команд выбрать ту, где больше свободных мест.
            if (k >= l) {
                for (elem in maxSet) {
                    if (k > 0) {
                        firstCommand.add(elem)
                        k--
                        friends.remove(elem)
                    } else
                        break
                }
            } else {
                for (elem in maxSet) {
                    if (l > 0) {
                        l--
                        friends.remove(elem)
                    } else
                        break
                }
            }
        }
        // если в первой команде нет свободных мест
        if (k == 0) {
            // выводим всю команду
            for (elem in firstCommand) {
                writer.print("$elem ")
            }
        } else {
            // если есть свободный места заполняем из оставшихся
            for (elem in firstCommand) {
                writer.print("$elem ")
            }
            for (elem in friends.keys) {
                writer.print("$elem ")
            }
        }
    }
    // запуск тестирования
    tests(solution, List(10) {"z9_${it + 1}.txt"})
}
