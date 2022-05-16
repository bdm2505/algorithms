package check1

import tests
import java.io.PrintWriter
import java.util.*

fun main() {
    val solution = {scanner: Scanner, writer: PrintWriter ->
        val n = scanner.nextInt() // длина последовательности
        val m = scanner.nextInt() // количество вопрросов
        // словарь для ответов на вопросы в нем для каждого числа (б) будет хранится ответ на вопрос
        val parity = mutableMapOf<Int, Boolean>()
        // словарь для пранения предыдущих значений (а) для вопроса (а) (б)
        val prev = mutableMapOf<Int, Int>()

        // проверяет верен ли вопрос a b par с учетом предыдущих вопросов
        // возвращает true если верен
        // par == true если число едениц нечетно
        fun check(a: Int, b: Int, par: Boolean): Boolean {
            // если словарь не содержит (б), то запоминаем его
            if (!parity.containsKey(b)) {
                parity[b] = par
                prev[b] = a
                return true
            }
            // получаем предыдущие значения из словарей
            val pr = prev[b]!!
            val oldPar = parity[b]!!
            // если уже был подобный запрос проверяем на равенство четности
            if (pr == a) {
                return par == oldPar
            }
            // вызываем рекурсивно
            // в зависимости от порядка pr и a
            if (pr < a) {
                return check(pr, a - 1, par != oldPar)
            }

            return check(a, pr - 1, par != oldPar)
        }
        // был ли выведен номер строки с ошибкой
        var printed = false
        // пройти циклом по всем вопросам
        for (i in 1..m) {
            val a = scanner.nextInt()
            val b = scanner.nextInt()
            val par = scanner.next() == "odd"
            // проверить вопрос на наличие противоречий с предыдущими
            if (!check(a, b, par)) {
                writer.println(i - 1)
                printed = true
                break
            }
        }
        // если не было вывода значит все вопросы верные
        if (!printed){
            writer.println(m)
        }
    }
    // запуск тестирования
    tests(solution, List(10) { "z31_${it + 1}.txt" })

}
