package check3

data class Path( var distanse: Int = 0, val parentVertices: MutableList<Int> = mutableListOf())

class Graph{

    val labels = mutableMapOf<String, Int>()
    val vertexsInTree = mutableListOf<Boolean>()
    val relation = mutableListOf<MutableList<Pair<Int,Int>>>()

    var distancies = mutableListOf<Int>()

    val countOfVertices
        get() = labels.size
    var countOfVertexInTree = 0

    val shortestPaths = mutableListOf<Path>()
    var currentVertex = 0
    var startToCurrent = 0

    fun addVertex(label: String){
        vertexsInTree.add(false)
        relation.add(mutableListOf())
        labels[label] = countOfVertices
    }

    fun addEdge(startLabel: String, endLabel: String, weight: Int){
        val start = labels[startLabel]!!
        val end = labels[endLabel]!!
        relation[start].add(end to weight)
    }
    fun calculatePaths(start: Int){
        distancies = MutableList(countOfVertices) { Int.MAX_VALUE }

        for((v, dis) in relation[start]){
            distancies[v] = dis
        }
    }

    fun getMin():Int {
        var minDist = Int.MAX_VALUE
        var indexMin = 0
        for(i in 0 until distancies.size){

        }
        return 0
    }


}