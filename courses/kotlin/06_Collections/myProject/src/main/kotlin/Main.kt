import kotlin.random.Random

fun main(args: Array<String>) {
    part2()
}
fun part1() {
    var N = 0
    while (N <= 0) {
        println("N должно быть >0")
        N = readln().toInt()
    }
    val list = List(N) { Random.nextInt(-5, 6) }
    println(list.toString())
    var mutableList = list.toMutableList().map { it -> if (it % 2 == 0) it * 10 else it }
    println(mutableList.toString())
    println(list.sumOf { it })
    mutableList = mutableList.filter { it > 0 }
    println(mutableList)
}
fun part2(){
    /*input
    login1 password1
    login2 password2
    login3 password3
    login4 password4
    login5 password5
    */
    var N = 0
    while (N <= 0) {
        println("ведите количество пользователей >0")
        N = readln().toInt()
    }
    val hashMap = HashMap<String,String>()
    var input = ""
    var login = ""
    var password = ""
    println("Подготовка данных начата:")
    for (i in 0 until N){
        println("Заполните логин для ${i+1} пользователя:")
        login = readln()
        println("Заполните пароль для ${i+1} пользователя:")
        password = readln()
        hashMap[login] = password
    }
    println("Подготовка данных закончена!")
    println(hashMap)
    println("Авторизация пользователей:")
    while (true){
        println("Введите логин:")
        login = readln()
        println("Введите пароль:")
        password = readln()
        if (hashMap.keys.contains(login)&&(hashMap[login] == password)){
            println("Добро пожаловать $login")
        }
        else println("Указан неверный логин или пароль")
    }
}