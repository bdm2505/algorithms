package emaxx

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StreamTokenizer


class Graf(val arr: MutableList<MutableList<Int>>) {

    constructor(numberVertex: Int) : this(MutableList(numberVertex) { mutableListOf() })

    val size
        get() = arr.size

    operator fun get(vertex: Int) = arr[vertex]

    fun connect(start: Int, end: Int) {
        arr[start].add(end)
    }
}

open class Dfs(val graf: Graf) {
    val used = MutableList(graf.size) { false }

    open fun run() {
        dfsForAll()
    }

    fun dfsForAll() {
        for (i in 0 until graf.size) {
            checkDfsAndRun(i)
        }
    }

    fun checkDfsAndRun(i: Int) {
        if (!used[i]) {
            startNewComponent(i)
            dfs(i)
            endComponent(i)
        }
    }

    open fun startNewComponent(v: Int) {}
    open fun endComponent(v: Int) {}

    fun dfs(v: Int) {
        open(v)
        used[v] = true;
        for (i in graf[v]) {
            if (!used[i]) {
                dfs(i)
            } else {
                noEnter(i)
            }
        }
        close(v)
    }

    open fun open(v: Int) {}
    open fun noEnter(v: Int) {}
    open fun close(v: Int) {}
}

open class TopologicalSort(graf: Graf) : Dfs(graf) {
    val result: MutableList<Int> = ArrayList(graf.size)
    override fun close(v: Int) {
        result.add(v)
    }

    override fun run() {
        result.clear()
        dfsForAll()
        result.reverse()
    }
}


fun main() {
    val graf = Graf(nextInt())
    val rgraf = Graf(graf.size)
    val m = nextInt()
    for (i in 0 until m) {
        val k1 = nextInt()
        val k2 = nextInt()
        graf.connect(k1, k2)
        rgraf.connect(k2, k1)
    }
    val sorted = TopologicalSort(graf).apply { run() }.result.apply { reverse() }

    object : Dfs(rgraf) {
        var component = mutableListOf<Int>()
        val components = mutableListOf<MutableList<Int>>()
        override fun open(v: Int) {
            component.add(v)
        }

        override fun endComponent(v: Int) {
            components.add(component)
            component = mutableListOf()
        }
    }.apply {
        for(v in sorted){
            checkDfsAndRun(v)
        }
        println(components.size)
        for(com in components){
            println(com.size)
            println(com.joinToString(" ") { "${it + 1}" })
        }
    }



}

val tokenazer = StreamTokenizer(BufferedReader(InputStreamReader(System.`in`)))

fun nextDouble(): Double {
    tokenazer.nextToken()
    if (tokenazer.ttype != StreamTokenizer.TT_NUMBER)
        throw Exception("not number")
    return tokenazer.nval
}

fun nextInt(): Int = nextDouble().toInt()

fun nextWord(): String {
    tokenazer.nextToken()
    if (tokenazer.ttype != StreamTokenizer.TT_WORD)
        throw Exception("not number")
    return tokenazer.sval
}