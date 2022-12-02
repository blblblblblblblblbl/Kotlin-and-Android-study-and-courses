import kotlin.random.Random

class Dog(energy: Int, weight: Int, maxAge: Int, name: String) : Animal(energy, weight, maxAge, name) {
    override fun move() {
        super.move()
        println("running")
    }
    override fun reproduction(): Dog {
        return Dog(Random.nextInt(1,10), Random.nextInt(1,5),maxAge,name)
    }
}