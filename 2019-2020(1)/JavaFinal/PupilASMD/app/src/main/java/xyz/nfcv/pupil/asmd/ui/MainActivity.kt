package xyz.nfcv.pupil.asmd.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import org.intellij.lang.annotations.MagicConstant
import xyz.nfcv.pupil.asmd.R
import xyz.nfcv.pupil.asmd.`fun`.ASMD
import xyz.nfcv.pupil.asmd.app.APP
import xyz.nfcv.pupil.asmd.ui.theme.*
import xyz.nfcv.pupil.asmd.ui.widget.*
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}
val problems = ASMD.generateProblem(100, 3, ASMD.Operator.ADD)

@Preview
@Composable
fun Main() {
    val (drawerState, onDrawerStateChange) = +state { DrawerState.Closed }

    MaterialTheme(colors = lightThemeColors, typography = themeTypography) {
        ModalDrawerLayout(
                drawerState = drawerState, onStateChange = onDrawerStateChange,
                gesturesEnabled = drawerState == DrawerState.Opened,
                drawerContent = { AppDrawer(currentScreen = MainStatus.nowScreen, closeDrawer = { onDrawerStateChange(DrawerState.Closed) }) },
                bodyContent = { AppContent { onDrawerStateChange(DrawerState.Opened) } }
        )
    }
}

@Composable
private fun AppContent(openDrawer: () -> Unit) {
    Crossfade(MainStatus.nowScreen) { screen ->
        Surface(color = +themeColor { background }) {
            when (screen) {
                is Screen.Exam -> ExamScreen { openDrawer() }
                is Screen.Manage -> ManageScreen { openDrawer() }
                is Screen.Analysis -> AnalysisScreen { openDrawer() }
            }
        }
    }
}

@Composable
private fun AppDrawer(currentScreen: Screen, closeDrawer: () -> Unit) {
    Column(crossAxisSize = LayoutSize.Expand, mainAxisSize = LayoutSize.Expand) {
        HeightSpacer(24.dp)
        Padding(16.dp) {
            Row {
                VectorImage(id = R.drawable.ic_logo, tint = +themeColor { primary })
                WidthSpacer(8.dp)
                Text(text = "PupilASMD", style = (+themeTextStyle { h6 }))
            }
        }
        Divider(color = Color(0x14333333))
        DrawerButton(icon = R.drawable.ic_edit_white, label = "测试", isSelected = currentScreen == Screen.Exam) {
            navigateToScreen(Screen.Exam)
            closeDrawer()
        }
        DrawerButton(icon = R.drawable.ic_data_white, label = "分析", isSelected = currentScreen == Screen.Analysis) {
            navigateToScreen(Screen.Analysis)
            closeDrawer()
        }
        DrawerButton(icon = R.drawable.ic_data_white, label = "管理", isSelected = currentScreen == Screen.Manage) {
            navigateToScreen(Screen.Manage)
            closeDrawer()
        }
    }
}

@Composable
private fun DrawerButton(@DrawableRes icon: Int, label: String, isSelected: Boolean, action: () -> Unit) {
    val textIconColor = if (isSelected) {
        +themeColor { primary }
    } else {
        (+themeColor { onSurface }).copy(alpha = 0.6f)
    }

    val backgroundColor = if (isSelected) {
        (+themeColor { primary }).copy(alpha = 0.12f)
    } else {
        (+themeColor { surface }).copy(alpha = 0.12f)
    }

    Padding(left = 8.dp, top = 8.dp, right = 8.dp) {
        Surface(color = backgroundColor, shape = RoundedCornerShape(4.dp)) {
            Button(onClick = action, style = TextButtonStyle()) {
                Row(mainAxisSize = LayoutSize.Expand, crossAxisAlignment = CrossAxisAlignment.Center) {
                    VectorImage(id = icon, tint = textIconColor)
                    WidthSpacer(16.dp)
                    Text(text = label, style = (+themeTextStyle { body2 }).copy(color = textIconColor))
                }
            }
        }
    }
}

@Composable
private fun ItemDivider() {
    Padding(left = 14.dp, right = 14.dp) {
        Opacity(0.08f) { Divider() }
    }
}

@Composable
fun ExamScreen(openDrawer: () -> Unit) {
    val context = +ambient(ContextAmbient)
    FlexColumn {
        inflexible {
            TopAppBar(
                title = { Padding(padding = EdgeInsets(0.dp, 16.dp, 0.dp, 16.dp)) { Text(text = "测试") } },
                navigationIcon = { VectorImageButton(R.drawable.ic_edit_white) { openDrawer() } }
            )
        }
        flexible(flex = 1f) {
            ExamProblemsList(problems)
        }
    }
}


@Composable
fun AnalysisScreen(openDrawer: () -> Unit) {
    val context = +ambient(ContextAmbient)
    FlexColumn {
        inflexible {
            TopAppBar(title = { Padding(padding = EdgeInsets(0.dp, 16.dp, 0.dp, 16.dp)) { Text(text = "分析") } }, navigationIcon = { VectorImageButton(R.drawable.ic_data_white) { openDrawer() } })
        }
    }
}

@Composable
fun ManageScreen(openDrawer: () -> Unit) {
    val context = +ambient(ContextAmbient)
    var opt: ASMD.Operator by + state { ASMD.Operator.ADD }
    var len by + state { 1 }
    var showConfirmDialog by + state {false}
    val exams = APP.helper.getExams()

    if(showConfirmDialog) {
        AddExamConfirm(operator = opt, length = len) {
            showConfirmDialog = false
        }
    }

    FlexColumn {
        inflexible {
            TopAppBar(
                title = { Padding(padding = EdgeInsets(0.dp, 16.dp, 0.dp, 16.dp)) { Text(text = "管理") } },
                navigationIcon = { VectorImageButton(R.drawable.ic_data_white) { openDrawer() } }
            )
        }
        inflexible {
            Column {
                HeightSpacer(height = 8.dp)
                FlexRow {
                    inflexible {
                        WidthSpacer(width = 8.dp)
                        Text("$len 位 $opt $len 位 = ?", style = +themeTextStyle { h5 })
                    }
                    expanded(1f) {
                        WidthSpacer(width = 1.dp)
                    }
                    inflexible {
                        Button("提交", onClick = {
                            showConfirmDialog = true
                        })
                        WidthSpacer(width = 8.dp)
                    }
                }
                HeightSpacer(height = 8.dp)
                ASMDOptBar(listener = object : OnOperatorSelectedListener {
                    override fun onOperatorChanged(operator: ASMD.Operator) { opt = operator }
                })
                HeightSpacer(height = 8.dp)
                ASMDLenBar(listener = object : OnLenSelectedListener {
                    override fun onLenChanged(length: Int) { len = length }
                })
            }
        }
        expanded(1f) {
            ExamsList(exams) {}
        }
    }
}

@Composable
fun ASMDOptBar(listener: OnOperatorSelectedListener) {
    FlexRow {
        inflexible {
            WidthSpacer(width = 8.dp)
            Button(text = "加", onClick = {
                listener.onOperatorChanged(ASMD.Operator.ADD)
            })
        }
        expanded(1f) {
            WidthSpacer(width = 1.dp)
        }
        inflexible {
            Button(text = "减", onClick = {
                listener.onOperatorChanged(ASMD.Operator.SUB)
            })
        }
        expanded(1f) {
            WidthSpacer(width = 1.dp)
        }
        inflexible {
            Button(text = "乘", onClick = {
                listener.onOperatorChanged(ASMD.Operator.MULTI)
            })
        }
        expanded(1f) {
            WidthSpacer(width = 1.dp)
        }
        inflexible {
            Button(text = "除", onClick = {
                listener.onOperatorChanged(ASMD.Operator.DIVIDE)
            })
            WidthSpacer(width = 8.dp)
        }
    }
}

@Composable
fun ASMDLenBar(listener: OnLenSelectedListener) {
    FlexRow {
        FlexRow {
            inflexible {
                WidthSpacer(width = 8.dp)
                Button(text = "1", onClick = {
                    listener.onLenChanged(1)
                })
            }
            expanded(1f) {
                WidthSpacer(width = 1.dp)
            }
            inflexible {
                Button(text = "2", onClick = {
                    listener.onLenChanged(2)
                })
            }
            expanded(1f) {
                WidthSpacer(width = 1.dp)
            }
            inflexible {
                Button(text = "3", onClick = {
                    listener.onLenChanged(3)
                })
            }
            expanded(1f) {
                WidthSpacer(width = 1.dp)
            }
            inflexible {
                Button(text = "4", onClick = {
                    listener.onLenChanged(4)
                })
            }
            expanded(1f) {
                WidthSpacer(width = 1.dp)
            }
            inflexible {
                Button(text = "5", onClick = {
                    listener.onLenChanged(5)
                })
                WidthSpacer(width = 8.dp)
            }
        }
    }
}

@Composable
fun ASMDSizeBar(listener: OnLenSelectedListener) {
    FlexRow {
        FlexRow {
            inflexible {
                WidthSpacer(width = 8.dp)
                Button(text = "1", onClick = {
                    listener.onLenChanged(1)
                })
            }
            expanded(1f) {
                WidthSpacer(width = 1.dp)
            }
            inflexible {
                Button(text = "2", onClick = {
                    listener.onLenChanged(2)
                })
            }
            expanded(1f) {
                WidthSpacer(width = 1.dp)
            }
            inflexible {
                Button(text = "3", onClick = {
                    listener.onLenChanged(3)
                })
            }
            expanded(1f) {
                WidthSpacer(width = 1.dp)
            }
            inflexible {
                Button(text = "4", onClick = {
                    listener.onLenChanged(4)
                })
            }
            expanded(1f) {
                WidthSpacer(width = 1.dp)
            }
            inflexible {
                Button(text = "5", onClick = {
                    listener.onLenChanged(5)
                })
                WidthSpacer(width = 8.dp)
            }
        }
    }
}

interface OnOperatorSelectedListener {
    fun onOperatorChanged(operator: ASMD.Operator)
}

interface OnLenSelectedListener {
    fun onLenChanged(@MagicConstant(flags = [1, 2, 3, 4, 5]) length: Int)
}

interface OnSizeChangedListener {
    fun onSizeChanged(size: Int)
}

@Composable
fun AddExamConfirm(operator: ASMD.Operator, length: Int, onDismiss: () -> Unit) {
    val context = +ambient(ContextAmbient)
    AlertDialog(
            onCloseRequest = onDismiss,
            text = {
                Text("新建符号为$operator, ${length}位的题目", style = +themeTextStyle { h5 })
            },
            confirmButton = {
                onDismiss
            }
    )
}