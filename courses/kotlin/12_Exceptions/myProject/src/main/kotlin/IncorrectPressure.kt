class IncorrectPressure:Throwable() {
    override val message: String?
        get() = "impossible pressure for wheel"
}