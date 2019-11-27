package xyz.nfcv.pupil.asmd.ui.theme

import androidx.compose.frames.ModelList


sealed class Screen {
    object Exam : Screen()
    object Analysis : Screen()
    object Manage : Screen()
}

object MainStatus {
    var nowScreen: Screen = Screen.Exam
    val favorites = ModelList<String>()
    val selectedTopics = ModelList<String>()
}

fun navigateToScreen(screen: Screen) {
    MainStatus.nowScreen = screen
}