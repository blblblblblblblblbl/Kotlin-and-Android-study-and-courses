package com.example.myapp2

class Person(name:String,pass:String, image:String)
{
    var name = name;
    var pass = pass;
    var image = image;
    override fun toString(): String {
        return "${name} ${pass} ${image}"
    }
}