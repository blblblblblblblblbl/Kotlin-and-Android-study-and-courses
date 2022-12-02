package warriors

interface Warrior {
    var isKilled:Boolean
    var missChance:Int
    fun attack(warrior: Warrior)
    fun takeDamage(damage:Int)
}