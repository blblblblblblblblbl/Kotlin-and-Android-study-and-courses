import java.nio.channels.spi.AbstractInterruptibleChannel

class TV(val brand:String,val model:String,val diagonal:Double) {

    var isTurnedOn: Boolean = false
        private set
    private var volume = 0
    private var currentChannel = 0
    private val channelList = Channels.getRandomChannels()


    fun turnOn() {
        isTurnedOn = true
        println("TV has been turned on")
    }

    fun turnOff() {
        isTurnedOn = false
        println("TV has been turned off")
    }

    fun volumeTurnUp(value: Int) {
        if (!isTurnedOn) {
            println("tv is off")
            return
        }
        volume += value
        if (volume > maxVolume) volume = maxVolume
        println("volume $volume")
    }

    fun volumeTurnDown(value: Int) {
        if (!isTurnedOn) {
            println("tv is off")
            return
        }
        volume -= value
        if (volume < 0) volume = 0
        println("volume $volume")
    }

    fun setChannel(channel: Int) {
        if (!isTurnedOn) turnOn()
        if (channel !in channelList.indices) {
            println("this channel doesn't exist")
            return
        }
        currentChannel = channel
        println("current channel $currentChannel")
    }

    fun channelUp() {
        if (!isTurnedOn) turnOn()
        if (currentChannel < channelList.size - 1) currentChannel++
        else currentChannel = 0
        println("current channel $currentChannel")
    }

    fun channelDown() {
        if (!isTurnedOn) turnOn()
        if (currentChannel > 0) currentChannel--
        else currentChannel = channelList.size-1
        println("current channel $currentChannel")
    }
    fun showChannelList(){
        for (i in channelList.indices){
            println("channel $i - ${channelList[i]}")
        }
    }
    companion object {
        const val maxVolume = 100
    }
}