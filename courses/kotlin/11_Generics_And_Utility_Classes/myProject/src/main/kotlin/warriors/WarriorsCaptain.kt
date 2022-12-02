package warriors

import AbstractWeapon
import Ammo

class WarriorsCaptain : AbstractWarrior() {
    override var isKilled: Boolean = false
    override var accuracy: Int = 60
    override var hp: Int = 150
    override var maxHP: Int = 150
    override var missChance: Int=20
    override var weapon: AbstractWeapon = Weapons.createWeapon2()
    override var ammoType: Ammo = Ammo.TYPE2
}