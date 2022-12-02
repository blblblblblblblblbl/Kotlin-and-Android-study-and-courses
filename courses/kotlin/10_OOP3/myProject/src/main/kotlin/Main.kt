fun main(args: Array<String>) {
    val a = Converters.get("Dollar")
    val b = Converters.get("Yuan")
    val c = Converters.get("Something")
    val n = 10.0
    println("$n рублей равно ${n/a.convertToRub(1.0)} ${a.currencyCode}")
    println("$n рублей равно ${n/b.convertToRub(1.0)} ${b.currencyCode}")
    println("$n рублей равно ${n/c.convertToRub(1.0)} ${c.currencyCode}")
}