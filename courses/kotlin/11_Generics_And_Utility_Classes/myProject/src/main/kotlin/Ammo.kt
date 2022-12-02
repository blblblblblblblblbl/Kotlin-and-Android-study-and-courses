import kotlin.random.Random

enum class Ammo(val damage:Int, val criticalChance:Int, val criticalCoefficient: Int) {
    TYPE1(10,50,5),
    TYPE2(15,20,10),
    TYPE3(5,5,100);
    fun currentDamage():Int{
        return damage*(if (criticalChance.Chance()) criticalCoefficient else 1)
    }
}

fun Int.Chance():Boolean{
    return this>= Random.nextInt(0,100)
}
