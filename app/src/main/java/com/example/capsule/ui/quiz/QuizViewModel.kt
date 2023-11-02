package com.example.capsule.ui.quiz

import androidx.lifecycle.ViewModel
import com.example.capsule.data_model.Mcq

class QuizViewModel: ViewModel() {
    var qNo = 1
    var marks = 0
    var optionSelected = 0
    var isAnswered = false
    var isQuizFinished = false

    init {
        Mcq.populateList()
    }
}