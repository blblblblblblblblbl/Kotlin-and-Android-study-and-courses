import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.yield
import java.math.BigInteger

object Fibonacci {

    suspend fun take(n:Int): BigInteger {

        if( n==1) return BigInteger.ZERO
        if( n==2) return BigInteger.ONE
        var nm1: BigInteger = BigInteger.ONE
        var nm2 = BigInteger.ZERO
        var output = BigInteger.ZERO
        for (i in 3..n){
            yield()
            output = nm1+nm2
            nm2 = nm1
            nm1 = output
        }
        return output
    }
}