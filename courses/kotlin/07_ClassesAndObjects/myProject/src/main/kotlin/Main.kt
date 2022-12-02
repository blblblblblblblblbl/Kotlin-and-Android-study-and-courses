fun main(args: Array<String>) {
    val tvs = mutableListOf<TV>()
    for (i in 0..3){
        tvs.add(TV("brand$i","model$i", 27.0+7.0*i))
        tvInfo(tvs[i])
    }
    tvs[0].volumeTurnUp(5)
    tvs[0].volumeTurnUp(500)
    tvs[0].volumeTurnDown(5)
    tvs[0].volumeTurnDown(500)
    for (i in 0..51){
        tvs[0].channelUp()
    }
    for (i in 0..51){
        tvs[0].channelDown()
    }
    tvs[0].turnOn()
    tvs[0].setChannel(4)
    tvs[0].setChannel(55)
    tvs[0].setChannel(-55)
    tvs[0].turnOff()
    tvs[0].volumeTurnUp(5)
    tvs[0].volumeTurnUp(500)
}
fun tvInfo(tv:TV){
    println("brand:${tv.brand} model:${tv.model} diagonal:${tv.diagonal}")
    tv.showChannelList()
}