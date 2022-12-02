class Battle(var team1:Team,var team2: Team) {

    var isEnded:Boolean = false
    var battleState = checkState()

    fun checkState():BattleState{
        return if(team1.warriors.isEmpty()&&team2.warriors.isEmpty()) BattleState.Draw
        else if (team1.warriors.isEmpty()) BattleState.SecondTeamWin
        else if (team2.warriors.isEmpty()) BattleState.FirstTeamWin
        else BattleState.Progress("первая команда "+team1.teamInfo()+ "\n" + "вторая команда "+team2.teamInfo()+"\n")
    }

    fun battleIteration(){
        team1.warriors.shuffle()
        team2.warriors.shuffle()
        if (team1.warriors.size>team2.warriors.size) fight(team1,team2)
        else fight(team2,team1)
        battleState = checkState()

    }
    private fun fight(t1:Team,t2:Team){
        for (i in t2.warriors.indices){
            t1.warriors[i].attack(t2.warriors[i])
            t2.warriors[i].attack(t1.warriors[i])
        }
        t1.warriors= t1.warriors.filter { !it.isKilled }.toMutableList()
        t2.warriors= t2.warriors.filter { !it.isKilled }.toMutableList()
    }

}