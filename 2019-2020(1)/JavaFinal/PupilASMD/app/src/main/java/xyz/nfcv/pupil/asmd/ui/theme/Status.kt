package xyz.nfcv.pupil.asmd.ui.theme

sealed class Screen {
    object Exam : Screen()
    object Analysis : Screen()
    object Manage : Screen()
}

object MainStatus {
    var nowScreen: Screen = Screen.Exam
}

fun navigateToScreen(screen: Screen) {
    MainStatus.nowScreen = screen
}