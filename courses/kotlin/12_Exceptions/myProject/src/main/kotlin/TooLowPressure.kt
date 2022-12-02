class TooLowPressure:Throwable() {
    override val message: String?
        get() = "to low pressure for normal exploitation"
}