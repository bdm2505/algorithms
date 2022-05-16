package check3

open class N(val num: Int) {
    val ns = mutableSetOf<N>()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as N

        if (num != other.num) return false

        return true
    }

    override fun hashCode(): Int {
        return num
    }

    fun add(node: N) {
        ns.add(node)
    }

    override fun toString(): String {
        return "[$num](${ns.map { it.num }})"
    }

}
typealias Graf<DATA> = MutableMap<Int, DATA>

fun <NODE> grafOf(range: IntRange, init: (Int) -> NODE): Graf<NODE> =
    Array(range.last - range.first + 1) { range.first + it to init(range.first + it) }.toMap().toMutableMap()


fun readInt() = readLine()!!.toInt()
fun readInts() = readLine()!!.split(" ").map { it.toInt() }

operator fun <NODE> Graf<NODE>.invoke(index: Int): NODE = this[index]!!

fun <NODE : N> Graf<NODE>.connect(i1: Int, i2: Int) {
    this(i1).add(this(i2))
    this(i2).add(this(i1))
}

open class DfsNode(num: Int) : N(num) {
    var dfsBe: Boolean = false
}

fun <NODE : DfsNode> Graf<NODE>.dfs(start: Int, go: (NODE?, NODE) -> Unit, endGo: (NODE) -> Unit, old: NODE? = null) {
    val node = this(start)
    go(old, node)
    if (node.dfsBe) {
        endGo(node)
        return
    }
    node.dfsBe = true
    for (n in node.ns) {
        this.dfs(n.num, go, endGo, node)
    }
    endGo(node)
}


class Data(num: Int) : DfsNode(num) {
    var score = -1

    override fun toString(): String {
        return "$score " + super.toString() + " $dfsBe"
    }
}

operator fun String.times(i: Int): String {
    val sb = StringBuilder()
    for (j in 1..i) {
        sb.append(this)
    }
    return sb.toString()
}

fun main() {

    val n = readInt()
    val graf = grafOf(1..n) { Data(it) }
    for (i in 1..n) {
        val ints = readInts()
        for (j in 0..(ints.size - 2)) {
            graf(i).add(graf(ints[j]))
        }
    }
    var good = true
    for (i in graf.keys) {
        if (graf(i).dfsBe)
            continue
        graf.dfs(i, { old, node ->
            val nscore = if (old != null) old.score + 1 else 0
            if (nscore > node.score){
                node.score = nscore
                node.dfsBe = false
            }

        }, {})

    }
    for(node in graf.values.sortedBy { it.score }){
        print("${node.num} ")
    }


}