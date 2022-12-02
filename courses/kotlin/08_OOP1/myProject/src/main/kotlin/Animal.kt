import kotlin.random.Random
import kotlin.random.nextInt

open class Animal(var energy: Int, var weight: Int, val maxAge: Int, val name: String) {
    var currentAge: Int = 0
    init {
        println("$name was born")
    }
    fun isTooOld(): Boolean {
        return (currentAge >= maxAge)
    }

    fun sleep() {
        energy += 5
        currentAge++
        println("$name is sleeping")
    }

    fun eat() {
        energy+=3
        weight++
        tryIncrementAge()
        println("$name is eating")
    }
    open fun move() {
        energy-=5
        weight--
        tryIncrementAge()
        println("$name is moving")
    }
    private fun tryIncrementAge(){
        if (Random.nextBoolean()) currentAge++
    }
    open fun reproduction():Animal{
        return Animal(Random.nextInt(1,10),Random.nextInt(1,5),maxAge,name)
    }
}