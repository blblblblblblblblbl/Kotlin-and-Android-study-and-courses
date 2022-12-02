import kotlin.random.Random

class Bird(energy: Int, weight: Int, maxAge: Int, name: String) : Animal(energy, weight, maxAge, name) {
    override fun move() {
        super.move()
        println("flying")
    }

    override fun reproduction(): Bird {
        return Bird(Random.nextInt(1,10), Random.nextInt(1,5),maxAge,name)
    }
}