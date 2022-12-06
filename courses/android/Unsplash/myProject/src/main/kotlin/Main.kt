fun main(args: Array<String>) {
    println("вам нужно ввести количество номеров")
    val N : Int = readln().toInt()
    var numbersList:MutableList<String> = mutableListOf<String>()
    println("вам нужно ввести $N номеров")
    for (i in 1..N){
        println("введите $i й номер: ")
        numbersList.add(readln())
    }
    val filteredlist = numbersList.filter { it.startsWith("+7") }
    println("только номера телефонов, начинающиеся с приставки +7")
    println(filteredlist)
    println("количество уникальных введённых номеров")
    println(numbersList.toSet().size)
    println("сумма длин всех номеров телефонов")
    println(numbersList.sumBy { it.length })
    val numberFiltrSet = filteredlist.toSet()
    val numberMap = mutableMapOf<String,String>()
    for (i in numberFiltrSet.indices){
        println("номер контакта ${numberFiltrSet.elementAt(i)} Введитеимя человека:")
        numberMap.put(numberFiltrSet.elementAt(i), readln())
    }
    numberMap.forEach { number, name ->
        println("Человек: $name. Номер телефона: $number")
    }
}