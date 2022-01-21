package com.example.helloworld

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class MainViewModel: ViewModel() {
    enum class Project(val activityName: String) {
        DICE_ROLL("DiceRoll"),
        COROUTINES_PLAYGROUND("CoroutinesPlayground"),
        DUMMY("dummy")
    }
    private var _projects = MutableLiveData<List<Project>>(
        listOf(
            Project.DICE_ROLL,
            Project.COROUTINES_PLAYGROUND,
            Project.DUMMY,
            Project.DUMMY,
            Project.DUMMY,
            Project.DUMMY,
            Project.DUMMY,
            Project.DUMMY,
            Project.DUMMY
        )
    )
    val projects: LiveData<List<Project>> = _projects
}