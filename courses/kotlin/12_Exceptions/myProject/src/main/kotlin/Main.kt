fun main(args: Array<String>) {
    val wheel = Wheel(0.0)
    pump(wheel,-5.0)
    pump(wheel,11.0)
    pump(wheel,6.0)
    pump(wheel,1.0)
    pump(wheel,2.0)
}
fun pump(wheel:Wheel,level: Double){
    try {
        print("При накачке $level атмосфер ")
        wheel.pumpUp(level)
        print("процедура удалась ")
        check(wheel)
    }
    catch (exception:Throwable){
        println("процедура не удалась")
        print("причина: ")
        println(exception.message)
    }
}
fun check(wheel: Wheel){
    try {
        wheel.checkLevel()
        println("Эксплуатация возможна")
    }
    catch (exception:Throwable){
        println("Эксплуатация невозможна")
        print("причина: ")
        println(exception.message)
    }
}