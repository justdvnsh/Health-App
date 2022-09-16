package divyansh.tech.healthappudemy

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var currentStep = mutableStateOf(1)
    private var bottomBarVisible = mutableStateOf(false)
    private var shouldStepbeVisible = mutableStateOf(false)

    fun getCurrentStep(): Int = currentStep.value

    fun setCurrentStep(step: Int) {
        currentStep.value = step
    }

    fun getBottomBarVisible(): Boolean = bottomBarVisible.value

    fun setBottomBarVisible(visible: Boolean) {
        bottomBarVisible.value = visible
    }

    fun shouldStepBeVisible(): Boolean = shouldStepbeVisible.value

    fun setShouldStepBeVisible(visible: Boolean) {
        shouldStepbeVisible.value = visible
    }
}