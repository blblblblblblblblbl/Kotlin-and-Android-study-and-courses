package com.blblblbl.skillboxtest

class Task2 {
    companion object{
        fun answer(input :Int):Int{
            var a:Int =0
            var b:Int =0
            var c:Int =0
            var d:Int =0
            var num:String = "1000"
            var ab = 0
            var cd = 0
            var temp = 0
            //var num:Int = 1000
            while (true)
            {
                a = num[0].digitToInt()
                b = num[1].digitToInt()
                c = num[2].digitToInt()
                d = num[3].digitToInt()
                ab = a+b
                cd = c+d
                if(ab<cd){
                    temp = ab*100+cd
                }
                else{
                    temp = cd*100+ab
                }
                if(temp==input){
                    return num.toInt()
                }
                num = (num.toInt()+1).toString()
            }
        }
    }
}