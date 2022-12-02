import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.util.Stack

fun main(args: Array<String>) {
    val game = Game(3,2)
    game.result()
    runBlocking {
        try {
            game.play()
        }
        catch (e:Throwable) {
            println(e.message)
        }
    }
    game.result()


}







