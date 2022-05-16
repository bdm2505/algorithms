package check1

import tests
import java.io.PrintWriter
import java.util.Scanner



fun main(){
    val solution = {scanner: Scanner, writer: PrintWriter ->
        val n = scanner.nextInt()
        val k = scanner.nextInt()
        val m = scanner.nextInt()
        val friends = mutableMapOf<Int, MutableList<Int>>()
        for(i in 1..n){
            friends[i] = mutableListOf()
        }
        for(i in 1..m){
           val a = scanner.nextInt()
           val b = scanner.nextInt()

            friends[a]!!.add(b)
            friends[b]!!.add(a)
        }

        fun countFriends(set: Set<Int>): Int{
            var res = 0
            for(i in set){
                for(j in friends[i]!!){
                    if (set.contains(j))
                       res++
                }
            }
            return res
        }

        fun otherCommand(set: Set<Int>): Set<Int>{
            return friends.keys.filter { !set.contains(it) }.toSet()
        }

        var max = 0
        var command = setOf<Int>()

        fun updateMax(set:Set<Int>){
            val sum = countFriends(set) + countFriends(otherCommand(set))
            if (sum > max){
                max = sum
                command = set.toMutableSet()
            }
        }

        fun comb(set: MutableSet<Int>, num:Int = 1){
            if (set.size >= k) {
                updateMax(set)
                return
            }
            if (num > n)
                return

            comb(set, num + 1)
            set += num
            comb(set, num + 1)
            set -= num
        }

        comb(mutableSetOf())

        val oldSet= setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 16, 18, 20, 22, 24)




        for (item in command){
            writer.print(item)
            writer.print(" ")
        }
    }
    tests(solution, listOf("z9_1.txt", "z9_2.txt", "z9_3.txt"))
}