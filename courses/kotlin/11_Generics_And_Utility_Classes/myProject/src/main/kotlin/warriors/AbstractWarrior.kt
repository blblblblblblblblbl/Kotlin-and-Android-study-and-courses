package warriors

import AbstractWeapon
import Ammo
import Chance

abstract class AbstractWarrior() : Warrior {

    abstract var maxHP: Int
    abstract var accuracy: Int
    abstract var weapon: AbstractWeapon
    abstract var hp: Int
    abstract var ammoType :Ammo

    override var isKilled: Boolean = false
    override fun attack(warrior: Warrior) {
        if (weapon.isEmpty) weapon.reload(ammoType)
        else {
            val ammo = weapon.getAmmo(weapon.fireType)
            var damage = 0
            ammo.forEach { damage += it.currentDamage() * (if (!warrior.missChance.Chance() && accuracy.Chance()) 1 else 0) }
            warrior.takeDamage(damage)
        }
    }

    override fun takeDamage(damage: Int) {
        hp -= damage
        if (hp<=0) isKilled= true
    }
}
