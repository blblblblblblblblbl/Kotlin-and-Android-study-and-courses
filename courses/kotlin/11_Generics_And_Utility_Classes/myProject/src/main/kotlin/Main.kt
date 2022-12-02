import kotlin.random.Random

fun main(args: Array<String>) {
    var team1 = Team()
    var team2 = Team()
    for (i in 0..10){
        team1.addWarrior()
        team2.addWarrior()
    }
    println(team1.teamInfo())
    println(team2.teamInfo())
    var battle = Battle(team1, team2)
    while (battle.checkState() is BattleState.Progress){
        println(battle.checkState().str)
        battle.battleIteration()
    }
    println(battle.checkState().str)
}

