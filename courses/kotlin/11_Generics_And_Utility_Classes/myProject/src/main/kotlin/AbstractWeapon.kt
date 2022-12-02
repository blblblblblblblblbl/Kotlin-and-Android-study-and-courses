abstract class AbstractWeapon {
    abstract val maxBullets:Int
    abstract val fireType:FireType
    abstract var bulletMagazine:Stack<Ammo>
    abstract var isEmpty:Boolean
    fun makeAmmo(ammo: Ammo){
        bulletMagazine.push(ammo)
    }
    fun reload(ammo: Ammo){
        bulletMagazine = Stack<Ammo>()
        for (i in 1..maxBullets) makeAmmo(ammo)
        isEmpty = false
    }
    fun getAmmo(fireType: FireType):MutableList<Ammo>{
        var ammo = mutableListOf<Ammo>()
        when(fireType){
            is BurstShooting -> {
                if (bulletMagazine.stack.size>=fireType.burstSize) {
                    for (i in 1..fireType.burstSize){
                        ammo.add(bulletMagazine.pop())
                    }
                }
                else isEmpty = true
            }
            is SingleShot -> {
                if (!isEmpty) ammo.add(bulletMagazine.pop())
                isEmpty= bulletMagazine.isEmpty()
            }
        }
        return ammo
    }
}