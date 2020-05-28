package com.example.campusform

import android.content.Context

object ItemFactory {
    fun createItem(type:QuestionType,context:Context):Item{
        return when(type){
            QuestionType.SINGLE_QUESTION -> SingleItem(context)
            QuestionType.MULTI_QUESTION -> MultiItem(context)
            QuestionType.TEXT_QUESTION -> TextItem(context)
            QuestionType.TEN_QUESTION -> TenItem(context)
            QuestionType.HUNDRED_QUESTION -> HundredItem(context)
            QuestionType.SORT_QUESTION -> SortItem(context)
        }
    }
}