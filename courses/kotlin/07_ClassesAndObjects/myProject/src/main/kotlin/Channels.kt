import java.util.Random

object Channels {
    val channelList = listOf<String>("channel 0",
        "channel 1",        "channel 2",        "channel 3",
        "channel 4",        "channel 5",        "channel 6",
        "channel 7",        "channel 8",        "channel 9",
        "channel 10",        "channel 11",        "channel 12",
        "channel 13",        "channel 14",        "channel 15",
        "channel 16",        "channel 17",        "channel 18",
        "channel 19",        "channel 20",        "channel 21",
        "channel 22",        "channel 23",        "channel 24",
        "channel 25",        "channel 26",        "channel 27",
        "channel 28",        "channel 29",        "channel 30",
        "channel 31",        "channel 32",        "channel 33",
        "channel 34",        "channel 35",        "channel 36",
        "channel 37",        "channel 38",        "channel 39",
        "channel 40",        "channel 41",        "channel 42",
        "channel 43",        "channel 44",        "channel 45",
        "channel 46",        "channel 47",        "channel 48",
        "channel 49",        "channel 50",
        )
    fun getRandomChannels():List<String>{
        return  channelList.shuffled().subList(10, Random().nextInt(10,50))
    }
}