package work1

import kotlin.math.pow
import kotlin.math.sqrt

data class Point(val x: Double, val y: Double)

fun Point.distance(p: Point) =
    sqrt((x - p.x).pow(2) + (y - p.y).pow(2))

fun main(){

    println("Введите количество точек:")
    val N = readLine()!!.toInt()
    if (N < 2)
        return
    println("Введите координаты точек\nx1 y1\nx2 y2\n...")
    val points = Array(N){
        val nums = readLine()!!.split(" ").map { it.toDouble() }
        Point(nums[0], nums[1])
    }
    val sumD = Array(N){ 0.0 } // массив для сумм расcтояний

    for(i in 0 until N)
        for(j in 0 until N){
            sumD[i] += points[i].distance(points[j])
        }
    val minDistance = sumD.minOrNull()
    val indexMin = sumD.indexOf(minDistance)

    println("минимальная сумма расстояний: $minDistance")
    println("координаты точки: ${points[indexMin]}")

}