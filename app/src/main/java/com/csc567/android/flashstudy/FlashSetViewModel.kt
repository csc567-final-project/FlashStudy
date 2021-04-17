package com.csc567.android.flashstudy

import androidx.lifecycle.ViewModel

class FlashSetViewModel: ViewModel() {
    private val flashSetRepository = FlashSetRepository.get()
    val flashSetListLiveData = flashSetRepository.getFlashSet()

}