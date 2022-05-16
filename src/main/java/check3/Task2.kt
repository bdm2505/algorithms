package check3

import tests.Algorithm
import tests.Tester
import java.io.PrintWriter
import java.util.*
import kotlin.random.Random

data class R(val id:Int, val start: Int, val end: Int)

class Task2 : Algorithm {
    override fun run(scanner: Scanner, writer: PrintWriter) {
        val N = scanner.nextInt()
        val M = scanner.nextInt()
        val K = scanner.nextInt()
        val P = scanner.nextInt()

        val list = mutableListOf<R>()
        for(i in 1..K){
            list += R(i, scanner.nextInt(), scanner.nextInt())
        }
        list.sortWith { o1, o2 -> if (o1.start == o2.start) o1.end.compareTo(o2.end) else o1.start.compareTo(o2.start) }

        val good = mutableListOf<R>()
        val bus = TreeSet<R>{ o1, o2 -> if (o1.end == o2.end) o1.id.compareTo(o2.id) else o1.end.compareTo(o2.end) }

        for(r in list){
            while(!bus.isEmpty() && bus.first().end <= r.start) {
                val f = bus.first()
                bus.remove(f)
                good.add(f)
            }

            bus.add(r)

            if(bus.size > M){
                val m = bus.last()
                bus.remove(m)
            }
        }

        good.addAll(bus)
        writer.println(good.size * P)
        writer.println(good.joinToString(" ") { it.id.toString() })


    }

    override fun inputFolder(): String {
        return "input/check_3_task_2"
    }
}

fun main(){
    generate(2)
    generate(1000)
    generate(10)
    generate(100)
    generate(9)
    generate(8)
    generate(80)
    generate(800)
    generate(30)
    generate(350)
    Tester.run(Task2())
}

fun generate(i: Int){

    val n = Random.nextInt(1000000) + 1
    val m = Random.nextInt(1000) + 1
    val u = Random.nextInt(1000) + 1

    val pr = PrintWriter("input/check_3_task_2/$i.txt")
    pr.println("$n $m $i $u")
    for(j in 1..i){
        val r1 = Random.nextInt(n - 2) + 1
        val r2 = Random.nextInt(n - 1 - r1) + r1 + 1
        pr.println("$r1 $r2")
    }

    pr.close()
}