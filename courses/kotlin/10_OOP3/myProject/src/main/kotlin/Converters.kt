import kotlin.random.Random

object Converters {
    var hashMap:HashMap<String,CurrencyConverter> = hashMapOf("Dollar" to DollarConverter(), "Yuan" to YuanConverter())
    fun get(currencyCode:String):CurrencyConverter{
        if (hashMap.containsKey(currencyCode)) return hashMap[currencyCode]!!
        val obj = object :CurrencyConverter{
            override var currencyCode: String = currencyCode
            override fun convertToRub(value: Double): Double {
                return value*Random.nextDouble(1.0,100.0)
            }
        }
        return obj
    }
}