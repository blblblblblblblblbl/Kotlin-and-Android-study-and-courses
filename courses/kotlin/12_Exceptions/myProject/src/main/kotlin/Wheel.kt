class Wheel(var currentPreassure:Double) {
    fun pumpUp(level: Double){
        if (level in 0.0..10.0) currentPreassure = level
        else throw IncorrectPressure()
    }
    fun checkLevel(){
        if (currentPreassure<1.6) throw TooLowPressure()
        if (currentPreassure>2.5) throw TooHighPressure()
    }
}