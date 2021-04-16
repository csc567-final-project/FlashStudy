
import android.app.Application

class FlashStudyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        CourseRepository.initialize(this)
        FlashSetRepository.initialize(this)
        FlashCardRepository.initialize(this)
    }
}