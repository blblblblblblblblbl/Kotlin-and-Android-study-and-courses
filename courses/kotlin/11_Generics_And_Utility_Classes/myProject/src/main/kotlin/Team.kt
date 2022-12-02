import warriors.AbstractWarrior
import warriors.WarriorSoldier
import warriors.WarriorsCaptain
import warriors.WarriorsGeneral
import kotlin.random.Random

class Team {
    var warriors = mutableListOf<AbstractWarrior>()
    fun addWarrior(){
        val type:AbstractWarrior
        if (Random.nextInt(0,100)<10) warriors.add(WarriorsGeneral())
        else if (Random.nextInt(0,100)<40 ) warriors.add(WarriorsCaptain())
        else warriors.add(WarriorSoldier())
    }
    fun teamInfo():String{
        var sumHP = 0
        warriors.forEach{
            sumHP+=it.hp
        }
        return "осталось воинов ${warriors.size},суммарное HP $sumHP"
    }
}