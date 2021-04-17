package com.csc567.android.flashstudy

import androidx.lifecycle.ViewModel

class FlashSetViewModel: ViewModel() {
    val flashSetRepository = FlashSetRepository.get()

}