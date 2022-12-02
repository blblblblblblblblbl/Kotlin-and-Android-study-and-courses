object Weapons {
    fun createWeapon1():AbstractWeapon{
        val weapon = object : AbstractWeapon() {
            override val maxBullets: Int = 5
            override val fireType: FireType = SingleShot
            override var bulletMagazine: Stack<Ammo> = Stack<Ammo>()
            override var isEmpty: Boolean = true
        }
        return weapon
    }
    fun createWeapon2():AbstractWeapon{
        val weapon = object : AbstractWeapon() {
            override val maxBullets: Int = 15
            override val fireType: FireType = BurstShooting(3)
            override var bulletMagazine: Stack<Ammo> = Stack<Ammo>()
            override var isEmpty: Boolean = true
        }
        return weapon
    }
    fun createWeapon3():AbstractWeapon{
        val weapon = object : AbstractWeapon() {
            override val maxBullets: Int = 10
            override val fireType: FireType = SingleShot
            override var bulletMagazine: Stack<Ammo> = Stack<Ammo>()
            override var isEmpty: Boolean = true
        }
        return weapon
    }
    fun createWeapon4():AbstractWeapon{
        val weapon = object : AbstractWeapon() {
            override val maxBullets: Int = 50
            override val fireType: FireType = BurstShooting(5)
            override var bulletMagazine: Stack<Ammo> = Stack<Ammo>()
            override var isEmpty: Boolean = true
        }
        return weapon
    }
}