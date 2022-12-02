import kotlin.random.Random

fun main(args: Array<String>) {
    val natureReserve = NatureReserve()
    val N = 20
    for (i in 1..N) {
        for (j in 0..natureReserve.animals.lastIndex) {
            when (Random.nextInt(0, 4)) {
                0 -> natureReserve.animals[j].eat()
                1 -> natureReserve.animals[j].sleep()
                2 -> natureReserve.animals[j].move()
                3 -> natureReserve.animals.add(natureReserve.animals[j].reproduction())
            }
        }
        natureReserve.animals = natureReserve.animals.filter { !it.isTooOld() }.toMutableList()
        if (natureReserve.animals.isEmpty()) {
            println("all animals died")
            return
        }
    }
    println("${natureReserve.animals.size} are left")
}