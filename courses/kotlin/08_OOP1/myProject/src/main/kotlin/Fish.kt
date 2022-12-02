import kotlin.random.Random

class Fish(energy: Int, weight: Int, maxAge: Int, name: String) : Animal(energy, weight, maxAge, name) {
    override fun move() {
        super.move()
        println("swimming")
    }
    override fun reproduction(): Fish {
        return Fish(Random.nextInt(1,10), Random.nextInt(1,5),maxAge,name)
    }
}