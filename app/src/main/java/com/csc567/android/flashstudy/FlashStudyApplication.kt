package com.csc567.android.flashstudy

import android.app.Application

class FlashStudyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        CourseRepository.initialize(this)
        FlashSetRepository.initialize(this)
    }
}