package com.csc567.android.flashstudy

import androidx.lifecycle.ViewModel

class StudyViewModel: ViewModel() {
    val flashCardRepository = FlashCardRepository.get()
}