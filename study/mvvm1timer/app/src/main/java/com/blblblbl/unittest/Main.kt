package com.blblblbl.unittest

object Main{

    @JvmStatic
    fun main(args:Array<String>){
        val triangle = Triangle(3,4,5)
        println(triangle.isRightTriangle())
    }
}

 class Triangle(private val sideA:Int,private val sideB:Int,private val sideC:Int){

     init {
         if(sideA<=0||sideB<=0||sideC<=0) throw IllegalArgumentException("side cant be equal or less then zero")
         if (sideA+sideB<=sideC||sideB+sideC<=sideA||sideC+sideA<=sideB) throw IllegalArgumentException("this triangle can't exist")
     }

     fun isRightTriangle():Boolean{
         return (sideA.square()+sideB.square()==sideC.square())
                 ||(sideB.square()+sideC.square()==sideA.square())
                 ||(sideC.square()+sideA.square()==sideB.square())
     }

     private fun Int.square():Int{
         return this*this
     }
 }