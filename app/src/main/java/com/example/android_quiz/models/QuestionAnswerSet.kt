package com.example.android_quiz.models;


class QuestionAnswerSet(
    val question:String,
    val answerList:List<String>,
    val correctSet: MutableSet<Int>,
)

enum class AnswerCheckStatus(){
    CORRECT,
    WRONG,
    NOTSELECTED
}





