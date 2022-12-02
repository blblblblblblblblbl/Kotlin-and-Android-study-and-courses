package com.blblblbl.myapplication

sealed class State() {
    object Loading:State()
    object Success:State()
    object Fail:State()
}