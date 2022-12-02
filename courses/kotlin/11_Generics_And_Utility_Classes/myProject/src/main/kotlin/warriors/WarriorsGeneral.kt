package warriors

import AbstractWeapon
import Ammo

class WarriorsGeneral : AbstractWarrior() {
    override var isKilled: Boolean = false
    override var accuracy: Int = 75
    override var hp: Int = 200
    override var maxHP: Int = 200
    override var missChance: Int=25
    override var weapon: AbstractWeapon = Weapons.createWeapon3()
    override var ammoType: Ammo = Ammo.TYPE3
}