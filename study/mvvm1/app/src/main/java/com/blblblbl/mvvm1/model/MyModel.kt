package com.blblblbl.mvvm1.model

import kotlin.random.Random

class MyModel {
    val phrases:Array<String> = arrayOf<String>("aaaa","bbbb","cccc")
    fun  getRandomphrase():String
    {
        val num = Random.nextInt(0,3)
        return phrases[num]
    }
}