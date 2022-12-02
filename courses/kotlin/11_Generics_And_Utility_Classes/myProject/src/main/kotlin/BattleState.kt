sealed class BattleState(var str: String) {
    object FirstTeamWin:BattleState("первая команда выиграла")
    object SecondTeamWin:BattleState("вторая команда выиграла")
    object Draw:BattleState("ничья")
    class Progress(str: String):BattleState(str)

}