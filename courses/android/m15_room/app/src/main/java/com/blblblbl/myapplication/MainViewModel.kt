package com.blblblbl.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao):ViewModel() {
    val allWords = this.wordDao.getAll()
    fun onAddButton(word: String){
        if (word.filter{ it!='-' }=="") throw object : Throwable() {
            override val message = "word is empty"
        }

        if (!word.filter{ it!='-' }.all{ it.isLetter()})throw object : Throwable() {
            override val message = "word must contains only letters"
        }
        viewModelScope.launch {
            try {
                wordDao.insert(
                    Word(
                        //id = 1,
                        word = word,
                        repeatCount = 0
                    )
                )
            }
            catch (throwable:Throwable){
                //val newWord = wordDao.getWord(word)
                //newWord.repeatCount++
                //wordDao.update(newWord)
                wordDao.countIncUpdate(word)
            }
        }

    }
    fun onClearButton(){
        viewModelScope.launch {
            wordDao.clear()
        }
    }
}