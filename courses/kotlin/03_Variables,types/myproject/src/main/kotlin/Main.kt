fun main(args: Array<String>) {
    val firstName:String = "Kirill"
    val secondName:String = "Tolmachev"
    var height:Double = 1.78
    var weight:Float = 80f
    var isChild:Boolean = ((height<1.50)||(weight<40))
    var info:String = "$firstName $secondName $height $weight $isChild"
    println(info)
    height = 1.0
    isChild = ((height<1.50)||(weight<40))
    info = "$firstName $secondName $height $weight $isChild"
    println(info)
}