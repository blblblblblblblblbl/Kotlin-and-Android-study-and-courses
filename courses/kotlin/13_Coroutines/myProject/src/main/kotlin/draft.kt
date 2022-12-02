/*suspend fun func(n:Int):BigInteger? = coroutineScope {
    var a:BigInteger? = null
    val job = launch{
        try {
            a= Fibonacci.take(n)
        }
        catch (exception:Throwable){
            println(exception.message)
        }
    }
    job.join()
    return@coroutineScope a
}*/

/*fun indicateCoroutine() = runBlocking {
    var val1:BigInteger=BigInteger.valueOf(0)
    var val2:BigInteger=BigInteger.valueOf(0)
    var val3:BigInteger=BigInteger.valueOf(0)
    val job = launch {
        launch {val1=func(1) }
        //1000000000
        launch { val2=func(5) }
        launch { val3=func(10) }
    }
    job.join()
    println(val1)
    println(val2)
    println(val3)
}
fun func(n:Int):BigInteger = runBlocking {
    var a:BigInteger =BigInteger.valueOf(-1)
    val job = launch{
         delay(2000)
         try {
             a= Fibonacci.take(n)
             //println(Fibonacci.take(n))
         }
         catch (exception:Throwable){
             println(exception.message)
         }
        }
    job.join()
    return@runBlocking a
}*/