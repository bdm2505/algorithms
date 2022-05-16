package check2

import tests
import tests.Algorithm
import tests.Tester
import java.io.PrintWriter
import java.util.*
import kotlin.random.Random

// реализация кучи
class Heap {
    var root: Node? = null

    // добавить элемент в кучу
    fun add(value: Int) {
        root?.let { addIn(it, Node(value)) } ?: run { root = Node(value, null) }
    }
    // рекурсивное добавление
    private fun addIn(start: Node, add: Node) {
        if (start.left == null) {
            start.left = add.apply { parent = start }
            balanceUp(start.left!!)
        } else if (start.right == null) {
            start.right = add.apply { parent = start }
            balanceUp(start.right!!)
        } else {
            if (Random.nextBoolean())
                addIn(start.left!!, add)
            else
                addIn(start.right!!, add)
        }
    }
    // поднятие минимального элемента в корень кучи
    private tailrec fun balanceUp(node: Node) {
        if (node.parent != null && node.parent!!.value > node.value) {
            node.parent!! swap node
            balanceUp(node.parent!!)
        }
    }

    fun isEmpty() = root == null

    // удалить корень кучи
    // возвращает минимальный элемент
    fun poll(): Int {
        root?.let { node ->
            val res = node.value
            val next = findList(node)
            node.value = next.value
            next.parent?.let { parent ->
                if(parent.left == next)
                    parent.left = null
                else
                    parent.right = null
                balanceDown(node)
            } ?: run {
                root = null
            }
            return res
        } ?: throw Exception("heap is empty")
    }
    private fun findList(node: Node): Node {
        return node.right?.let { findList(it) } ?: node.left?.let { findList(it) } ?: node
    }


    // поднятие минимального элемента вверх
    private fun balanceDown(node: Node) {
        node.left?.let { left ->
            node.right?.let { right ->
                val min = if (left.value < right.value) left else right
                balanceSwap(node, min)
            } ?: run {
                balanceSwap(node, left)
            }
        } ?: run {
            node.right?.let { right ->
                balanceSwap(node, right)
            }
        }
    }
    // Рекурсивный swap с поднятием элемента
    private fun balanceSwap(node: Node, next: Node){
        if (node.value > next.value) {
            node swap next
            balanceDown(next)
        }
    }

    fun dump(start: Node? = root, gl: Int = 0) {
        start?.let {
            println("--".repeat(gl) + ">${it.value}")
            dump(it.left, gl + 1)
            dump(it.right, gl + 1)
        }
    }

}

data class Node(var value: Int, var parent: Node? = null, var left: Node? = null, var right: Node? = null) {

    infix fun swap(other: Node) {
        val temp = value
        value = other.value
        other.value = temp
    }
}


fun main() {

    Tester.run(object : Algorithm {

        override fun run(scanner: Scanner, writer: PrintWriter) {
            val n = scanner.nextInt()
            val heap = Heap()
            // вместо кучи можно использовать стандартную из java
            //val heap = PriorityQueue<Int>(n)

            for(i in 1..n)
                heap.add(scanner.nextInt())

            var res = 0L
            while (true){
                // пока куча не пустая вытаскиваем два элемента и складываем
                val x = heap.poll() + heap.poll()
                res += x
                if (heap.isEmpty())
                    break
                heap.add(x)
            }
            // выводим результат с точностью .00
            writer.println("${res / 100}.${res%100}")
        }

        override fun inputFolder(): String {
            return "input/check_2_task_2"
        }

    })

}