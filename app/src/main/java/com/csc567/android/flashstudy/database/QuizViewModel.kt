package com.csc567.android.flashstudy

import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {
    val flashCardRepository = FlashCardRepository.get()
}