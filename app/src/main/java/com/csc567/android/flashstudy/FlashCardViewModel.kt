package com.csc567.android.flashstudy

import androidx.lifecycle.ViewModel

class FlashCardViewModel: ViewModel() {
    val flashCardRepository = FlashCardRepository.get()
}