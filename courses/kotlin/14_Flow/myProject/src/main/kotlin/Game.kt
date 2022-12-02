import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class Game(val numOfPlayers: Int, val numOfCards: Int) {
    val players = mutableListOf<Player>()
    val values = (1..90).shuffled()
    init {
        for (i in 1..numOfPlayers){
            val cards = mutableListOf<Card>()
            for (i in 1..numOfCards) cards.add(Card())
            players.add(Player(cards))
        }

    }
    fun playersCheck(number: Int):Boolean{
        var output:Boolean = false
        players.forEach{
            output = output || it.checkCards(number)
        }
        return output
    }

    suspend fun play(){
            coroutineScope {
                players.forEachIndexed { index,player->
                    launch {
                        generator().collect() { value ->
                            if (player.checkCards(value)) {
                                throw object : Throwable(){
                                    override val message: String?="game end"
                                }
                            }

                        }
                    }
            }
        }
    }
    fun result(){
        players.forEachIndexed { index, player ->
            println("player $index ${if (player.isWin) "won" else ""}")
            player.cards.forEach {
                    card-> println(card.info()) }
        }
    }
    fun generator(): Flow<Int> = flow {
        for (i in values.indices) {
            delay(100)
            emit(values[i])
        }
    }
}