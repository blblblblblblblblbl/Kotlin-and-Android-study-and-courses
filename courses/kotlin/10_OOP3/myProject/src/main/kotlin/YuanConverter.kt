class YuanConverter:CurrencyConverter {
    override var currencyCode: String = "Yuan"
    override fun convertToRub(value: Double): Double {
        return value*8
    }
}