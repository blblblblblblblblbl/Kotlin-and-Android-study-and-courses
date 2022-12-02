import java.math.BigInteger

fun main(args: Array<String>) {
    var n:Int = 0
    while (n<=0){
        println("значение n>0")
        n = readln().toInt()
    }
    println(fibonacciLoop(n))
    //работает правильно для int только для n<48, а иначе там уже значения больше чем Int.MAX_VALUE появляются
}

fun fibonacci(n:Int):Int{
    if( n==1) return 0
    if( n==2) return 1
    return fibonacci(n-1)+fibonacci(n-2)
}
fun fibonacciLoop(n:Int):BigInteger{
    if( n==1) return BigInteger.ZERO
    if( n==2) return BigInteger.ONE
    var nm1:BigInteger = BigInteger.ONE
    var nm2 = BigInteger.ZERO
    var output = BigInteger.ZERO
    for (i in 3..n){
        output = nm1+nm2
        nm2 = nm1
        nm1 = output
    }
    return output
}