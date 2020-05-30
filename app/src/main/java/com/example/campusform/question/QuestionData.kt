package com.example.campusform.question

import com.example.campusform.Item

object QuestionData {
    val data: ArrayList<Question> = arrayListOf()
    val itemSelectedList = arrayListOf<Int>()

    fun getQuestion(position: Int): Question = data[position]


    fun addQuestion() {
        val question = Question()
        data.add(question)
    }

    fun removeQuestion(position: Int) {
        data.removeAt(position)
    }

    fun removeQuestion(question: Question) {
        data.remove(question)
    }

    fun updateQuestion(questionPosition: Int, question: Question) {
        data[questionPosition] = question
    }

    fun clear() {
        data.clear()
    }

    fun setTitle(questionPosition: Int, title: String) {
        data[questionPosition].title = title
    }

    fun addChoice(questionPosition: Int, choice: String? = null) {
        data[questionPosition].choices.add(choice)
    }

    fun removeChoice(questionPosition: Int, choicePosition: Int) {
        data[questionPosition].choices.removeAt(choicePosition)
    }

    fun removeChoice(questionPosition: Int, choice: String) {
        data[questionPosition].choices.remove(choice)
    }

    fun changeChoice(questionPosition: Int, choicePosition: Int, choice: String) {
        data[questionPosition].choices[choicePosition] = choice
    }
}

data class Question(
    var title: String? = null,
    var choices: ArrayList<String?> = arrayListOf()
)