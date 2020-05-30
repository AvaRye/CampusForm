package com.example.campusform.question

object QuestionData {
    val data :ArrayList<ArrayList<Data>> = arrayListOf()
}
data class Data(
    var title :String = "",
    var contents:ArrayList<String> = arrayListOf()
)