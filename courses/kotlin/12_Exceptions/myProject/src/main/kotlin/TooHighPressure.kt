class TooHighPressure: Throwable() {
    override val message: String?
        get() = "to high pressure for normal exploitation"
}