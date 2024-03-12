package com.bitvolper.yogazzz.presentation.viewmodel

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.bitvolper.yogazzz.domain.model.SerenityData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class ExerciseUIState(val exercise: SerenityData.Data.Pose = SerenityData.Data.Pose(),
                           val timer: Int = 10,
                           val showExerciseDetails: Boolean = false,
                           val exerciseTimer: Int = 30)

@HiltViewModel
class YogaExerciseViewModel @Inject constructor(val player: Player): ViewModel() {

    companion object {
        private const val TAG = "YogaExerciseViewModel"
        const val TIME_LEFT_MILLI_SEC = 10_000L
        const val EXERCISE_TIME_LEFT_MILLI_SEC = 30_000L
    }

    private lateinit var timer: CountDownTimer
    private var timeInMilliSec: Long = TIME_LEFT_MILLI_SEC
    private var exerciseTimeInMilliSec: Long = EXERCISE_TIME_LEFT_MILLI_SEC

    private var _currentExercise = MutableStateFlow(ExerciseUIState())
    val currentExercise: StateFlow<ExerciseUIState> get() = _currentExercise

    var currentExerciseIndex by mutableIntStateOf(0)
        private set

    var totalExerciseSize by mutableIntStateOf(0)
        private set

    val serenityDataList = mutableStateListOf<SerenityData.Data.Pose?>()

    fun startExercise(serenityData: List<SerenityData.Data.Pose?>, nextScreen: () -> Unit = {  }, completeScreen: () -> Unit = { }) {
        _currentExercise.update {
            it.copy(exercise = serenityData[currentExerciseIndex] ?: return)
        }
        totalExerciseSize = serenityData.size
        serenityDataList.addAll(serenityDataList)

        timer = object : CountDownTimer(TIME_LEFT_MILLI_SEC, 1_000L) {
            override fun onTick(millisUntilFinished: Long) {
                timeInMilliSec = millisUntilFinished

                _currentExercise.update {
                    it.copy(
                        timer = currentExercise.value.timer.dec()
                    )
                }
            }

            override fun onFinish() {
                _currentExercise.update {
                    it.copy(
                        timer = 0,
                        showExerciseDetails = true
                    )
                }

                stopTimer()
                exerciseTimer(serenityData, nextScreen, completeScreen)
                startTimer()
            }
        }

        startTimer()
    }

    fun startTimer() {
        timer.start()
    }

    fun stopTimer() {
        timer.cancel()
    }

    fun exerciseTimer(serenityData: List<SerenityData.Data.Pose?>, nextScreen: () -> Unit = {  }, completeScreen: () -> Unit = { }) {

        timer = object : CountDownTimer(exerciseTimeInMilliSec, 1_000L) {

            override fun onTick(millisUntilFinished: Long) {
                timeInMilliSec = millisUntilFinished

                _currentExercise.update {
                    it.copy(
                        exerciseTimer = currentExercise.value.exerciseTimer.dec()
                    )
                }
            }

            override fun onFinish() {

                _currentExercise.update {
                    it.copy(
                        exerciseTimer = 0
                    )
                }

                if (currentExerciseIndex >= serenityData.size - 1) {
                    playerStop()
                    Timber.tag(TAG).d("Complete -> Size: ${serenityData.size} Index: $currentExerciseIndex")
                    completeScreen()
                } else {
                    playerStop()
                    currentExerciseIndex += 1
                    Timber.tag(TAG).d("Next screen -> Size: ${serenityData.size} Index: $currentExerciseIndex")
                    _currentExercise.update {
                        it.copy(
                            exerciseTimer = 30,
                            exercise = serenityData[currentExerciseIndex] ?: SerenityData.Data.Pose()
                            )
                    }
                    nextScreen()
                    exerciseTimer(serenityData, nextScreen, completeScreen)
                }
            }
        }
    }

    fun pauseExerciseTimer(completeScreen: () -> Unit = {  }, nextScreen: () -> Unit = {  }) {
        stopTimer()
        exerciseTimeInMilliSec = timeInMilliSec

        timer = object : CountDownTimer(exerciseTimeInMilliSec, 1_000L) {
            override fun onTick(millisUntilFinished: Long) {
                timeInMilliSec = millisUntilFinished

                _currentExercise.update {
                    it.copy(
                        exerciseTimer = currentExercise.value.exerciseTimer.dec()
                    )
                }
            }

            override fun onFinish() {
                _currentExercise.update {
                    it.copy(
                        exerciseTimer = 0
                    )
                }

                if (currentExerciseIndex >= serenityDataList.size - 1) {
                    Timber.tag(TAG).d("Complete -> Size: ${serenityDataList.size} Index: $currentExerciseIndex")
                    completeScreen()
                } else {
                    currentExerciseIndex += 1
                    Timber.tag(TAG).d("Next screen -> Size: ${serenityDataList.size} Index: $currentExerciseIndex")
                    _currentExercise.update {
                        it.copy(
                            exerciseTimer = 30,
                            exercise = serenityDataList[currentExerciseIndex] ?: SerenityData.Data.Pose()
                        )
                    }
                    nextScreen()
                    exerciseTimer(serenityDataList, nextScreen, completeScreen)
                }
            }
        }
    }

    fun resumeExerciseTimer() {
        startTimer()
    }


    fun skipExercise(completeScreen: () -> Unit = {  }, nextScreen: () -> Unit = {  }) {
        if (serenityDataList.getOrNull(currentExerciseIndex.inc()) != null) {
            stopTimer()

            timer = object : CountDownTimer(exerciseTimeInMilliSec, 1_000L) {
                override fun onTick(millisUntilFinished: Long) {
                    timeInMilliSec = millisUntilFinished

                    _currentExercise.update {
                        it.copy(
                            exerciseTimer = currentExercise.value.exerciseTimer.dec()
                        )
                    }
                }

                override fun onFinish() {
                    _currentExercise.update {
                        it.copy(
                            exerciseTimer = 0
                        )
                    }

                    if (currentExerciseIndex >= serenityDataList.size - 1) {
                        Timber.tag(TAG).d("Complete -> Size: ${serenityDataList.size} Index: $currentExerciseIndex")
                        completeScreen()
                    } else {
                        currentExerciseIndex += 1
                        Timber.tag(TAG).d("Next screen -> Size: ${serenityDataList.size} Index: $currentExerciseIndex")
                        _currentExercise.update {
                            it.copy(
                                exerciseTimer = 30,
                                exercise = serenityDataList[currentExerciseIndex] ?: SerenityData.Data.Pose()
                            )
                        }
                        nextScreen()
                        exerciseTimer(serenityDataList, nextScreen, completeScreen)
                    }
                }
            }
        }
    }

    fun previousExercise(completeScreen: () -> Unit = {  }, nextScreen: () -> Unit = {  }) {
        if (serenityDataList.getOrNull(currentExerciseIndex.dec()) != null) {
            stopTimer()

            timer = object : CountDownTimer(exerciseTimeInMilliSec, 1_000L) {
                override fun onTick(millisUntilFinished: Long) {
                    timeInMilliSec = millisUntilFinished

                    _currentExercise.update {
                        it.copy(
                            exerciseTimer = currentExercise.value.exerciseTimer.dec()
                        )
                    }
                }

                override fun onFinish() {
                    _currentExercise.update {
                        it.copy(
                            exerciseTimer = 0
                        )
                    }

                    if (currentExerciseIndex >= serenityDataList.size - 1) {
                        Timber.tag(TAG).d("Complete -> Size: ${serenityDataList.size} Index: $currentExerciseIndex")
                        completeScreen()
                    } else {
                        currentExerciseIndex += 1
                        Timber.tag(TAG).d("Next screen -> Size: ${serenityDataList.size} Index: $currentExerciseIndex")
                        _currentExercise.update {
                            it.copy(
                                exerciseTimer = 30,
                                exercise = serenityDataList[currentExerciseIndex] ?: SerenityData.Data.Pose()
                            )
                        }
                        nextScreen()
                        exerciseTimer(serenityDataList, nextScreen, completeScreen)
                    }
                }
            }
        }
    }


    fun playerStart(filePath: String) {
        player.setMediaItem(MediaItem.fromUri(filePath))
        player.play()
    }

    private fun playerStop() {
        player.pause()
    }

    override fun onCleared() {
        super.onCleared()
        stopTimer()
        player.release()
    }

    init {
        player.prepare()
    }
}