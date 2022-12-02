package warriors

import AbstractWeapon
import Ammo
import Weapons

class WarriorSoldier : AbstractWarrior() {
    override var isKilled: Boolean = false
    override var accuracy: Int = 50
    override var hp: Int = 100
    override var maxHP: Int = 100
    override var missChance: Int=10
    override var weapon: AbstractWeapon = Weapons.createWeapon1()
    override var ammoType: Ammo = Ammo.TYPE1
}