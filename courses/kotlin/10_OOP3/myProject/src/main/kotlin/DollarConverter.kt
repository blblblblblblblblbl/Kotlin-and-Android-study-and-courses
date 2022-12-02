class DollarConverter :CurrencyConverter {
    override var currencyCode: String = "Dollar"
    override fun convertToRub(value: Double): Double {
        return value*60
    }
}