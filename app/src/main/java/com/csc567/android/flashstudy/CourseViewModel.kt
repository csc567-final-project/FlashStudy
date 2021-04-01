package com.csc567.android.flashstudy

import androidx.lifecycle.ViewModel

class CourseViewModel: ViewModel() {
    private val courseRepository = CourseRepository.get()
    val courseListLiveData = courseRepository.getCourses()
}