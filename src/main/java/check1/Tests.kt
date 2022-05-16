import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.PrintWriter
import java.util.*
import kotlin.math.roundToInt


fun tests(funs: (Scanner, PrintWriter) -> Unit, inputFiles: List<String>) {
    for (i in inputFiles.indices) {
        test(funs, inputFiles[i], i + 1)
    }
}

private fun test(funs: (Scanner, PrintWriter) -> Unit, input: String, numTest: Int) {
    val output = "output/output$numTest.txt"
    try {
        println("Тест №$numTest")
        val scanner = Scanner(FileInputStream(input))
        val out = PrintWriter(FileOutputStream(output))
        System.gc()
        val startTime = System.currentTimeMillis()
        funs(scanner, out)
        val endTime = System.currentTimeMillis()
        val memory = memoryMb()
        scanner.close()
        out.close()

        println("---------------------Входные данные----------------------")
        printFile(input)
        println("---------------------------------------------------------\n")
        println("--------------------Выходные данные----------------------")
        printFile(output)
        println("---------------------------------------------------------\n")
        val time = (endTime - startTime)
        println("Затраченное время: $time милисекунд")
        println("Затраченная память: $memory Mb")
        println()
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    }
}


private fun printFile(fileName: String) {
    val scanner = Scanner(FileInputStream(fileName))
    var count = 20
    while (scanner.hasNext() && count >= 0) {
        if (count > 0) {
            count --
            println(scanner.nextLine())
        } else if (count == 0){
            println("...")
            count --
        }
    }
    scanner.close()
}

private fun memoryMb(): Double = ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
    .freeMemory()) / 1048.576).roundToInt() / 1000.0
