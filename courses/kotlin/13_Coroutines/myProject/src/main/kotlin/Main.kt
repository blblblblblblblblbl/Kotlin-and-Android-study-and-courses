import kotlinx.coroutines.*
import java.math.BigInteger

fun main(args: Array<String>)  {
    indicateCoroutine()
}

fun indicateCoroutine() = runBlocking {
    var n :Int = 1
    for (i in 0..9){
        launch{
            try {
                withTimeout(3000){
                    val a = Fibonacci.take(n)
                    println(a)
                }
            }
            catch (exception:Throwable){
                println("Timed out")
            }
        }
        launch { n*=10 }.join()
    }

}

fun indicateCoroutineAdd() = runBlocking {
    var vals = mutableListOf<BigInteger?>()
    for (i in 1..10) vals.add(null)
    var n :Int = 1
    val job = launch {
        for (i in 0..9){
            launch{
                try {
                    withTimeout(3000){
                        vals[i]= Fibonacci.take(n)
                    }
                }
                catch (exception:Throwable){
                    //println(exception.message)
                }
            }
            launch { n*=10 }.join()
        }
    }
    job.join()
    vals.forEach{
        if (it!=null) println(it)
        else println("Timed out")
    }
}

