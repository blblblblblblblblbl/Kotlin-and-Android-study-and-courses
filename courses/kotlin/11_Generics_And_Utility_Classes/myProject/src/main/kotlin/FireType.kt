sealed class FireType
object SingleShot: FireType()
data class BurstShooting(val burstSize:Int):FireType()
