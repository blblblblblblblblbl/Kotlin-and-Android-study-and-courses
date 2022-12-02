import java.util.*

class Card() {
    val lines = 3
    val elsInLine = 9
    val numsInLine = 5
    val values = generate()
    private fun generate(): MutableList<Int?> {
        val stack = Stack<Int?>()
        val output = mutableListOf<Int?>()
        for (i in 1..90) stack.add(i)
        stack.shuffle()
        for (i in 1..lines) {
            val line = mutableListOf<Int?>()
            for (i in 1..elsInLine - numsInLine) {
                line.add(null)
            }
            for (i in 1..numsInLine) {
                line.add(stack.pop())
            }
            line.shuffle()
            output.addAll(line)
        }
        return output
    }

    fun checkNumber(number: Int): Boolean {
        return values.contains(number)
    }

    fun removeNumber(number: Int): Boolean {
        for (i in values.indices) if (values[i] == number) values[i] = null
        return values.filter { it != null }.isEmpty()
    }

    fun info(): String {
        var str = ""
        for (i in values.indices) {
            str += "${values[i]} "
            if ((i + 1) % elsInLine == 0) str += "\n"
        }
        return str
    }

    companion object {
        fun makeCard(): Card {
            return Card()
        }
    }
}