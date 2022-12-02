fun main(args: Array<String>) {
    println(decryption("F2p)v\"y233{0->c}ttelciFc"))
}

fun decryption(input: String): String {
    val half1 = input.subSequence(0, input.length / 2).toString()
    val half2 = input.subSequence(input.length / 2, input.length).toString()
    return decryption1half(half1) + decryption2half(half2)
}
fun decryption1half(input: String):String{
    val str =shift(input,{char: Char->char+1})
        .replace('5','s')
        .replace('4','u')
    return shift(str,{char: Char -> char - 3 }).replace('0','o')
}

fun decryption2half(input: String): String {
    /*var str = input.reversed()
     str = shift(str,{char: Char -> char-4})
     str = str.replace('_',' ')*/
    return shift(input.reversed(), { char: Char -> char - 4 }).replace('_', ' ')
}

fun shift(input: String, func: (char: Char) -> Char): String {
    return input.map(func).joinToString("")
}