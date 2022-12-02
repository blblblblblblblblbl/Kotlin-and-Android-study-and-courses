class NatureReserve {
    var animals = mutableListOf<Animal>()
    init {
        for (i in 1..5){
            animals.add(Bird(5,3,10,"bird$i"))
        }
        for (i in 1..3){
            animals.add(Fish(5,8,5,"fish$i"))
        }
        for (i in 1..2){
            animals.add(Dog(50,50,20,"dog$i"))
        }
        for (i in 1..3){
            animals.add(Animal(50,50,20,"animal$i"))
        }
    }
}