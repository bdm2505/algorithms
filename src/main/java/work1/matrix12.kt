package work1

import java.util.*

fun main(){
    val scanner = Scanner(System.`in`)
    val M = scanner.nextInt()
    val N = scanner.nextInt()
    val matrix = Array(M){ Array(N) { scanner.nextInt() } }

    for(i in 0 until N){
        if (i % 2 == 0)
            for(j in 0 until M)
                print("${matrix[j][i]} ")
        else
            for(j in M - 1 downTo 0)
                print("${matrix[j][i]} ")
    }
}