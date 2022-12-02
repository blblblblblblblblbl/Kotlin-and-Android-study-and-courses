class Player(val cards: MutableList<Card>) {
    var isWin = false
    fun checkCards(number: Int): Boolean {
        var output: Boolean = false
        cards.forEach {
            output = output || it.removeNumber(number)
        }
        if (output) isWin = true
        return output
    }
}