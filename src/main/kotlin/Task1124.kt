
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StreamTokenizer


val tokenazer = StreamTokenizer(BufferedReader(InputStreamReader(System.`in`)))

fun nextInt(): Int {
    tokenazer.nextToken()
    return tokenazer.nval.toInt()
}


fun main(){
    val m = nextInt()
    val n = nextInt()
    val graf = Array(m) { mutableListOf<Int>() }
    val rgraf = Array(m) { mutableListOf<Int>() }
    var colors = 0

    for(i in 0 until m){
        for(j in 0 until n){
            val v = nextInt() - 1
            if (i != v) {
                colors++
                graf[i].add(v)
                rgraf[v].add(i)
            }
        }
    }
    val used = BooleanArray(m) { false }
    val order = ArrayList<Int>(m)

    fun dfs(node: Int) {
        used[node] = true
        for(i in graf[node]){
            if(!used[i])
                dfs(i)
        }
        order.add(node)
    }

    for(i in 0 until m){
        if(!used[i])
            dfs(i)
    }

    val rused = BooleanArray(m) { false }
    fun rdfs(node: Int){
        rused[node] = true
        for(i in rgraf[node])
            if(!rused[i])
                rdfs(i)
    }
    var count = 0

    for(i in m - 1 downTo 0){
        val node = order[i]
        if(!rused[node]){
            count++
            rdfs(node)
        }
    }
    println(colors + maxOf(count - 1, 0))
}