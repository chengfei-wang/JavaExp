package xyz.nfcv.pupil.asmd.ui.widget

import android.widget.Toast
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import xyz.nfcv.pupil.asmd.R
import xyz.nfcv.pupil.asmd.`fun`.ASMD
import xyz.nfcv.pupil.asmd.`fun`.ProblemSQLHelper

@Composable
fun ExamProblemsList(problems: ArrayList<ASMD.Problem>) {
    val context = +ambient(ContextAmbient)
    VerticalScroller {
        Column {
            repeat(problems.size) {
                Container(height = 60.dp, expanded = true) {
                    FlexRow(mainAxisSize = LayoutSize.Expand) {
                        inflexible {
                            WidthSpacer(8.dp)
                            Text("$it.", style = +themeTextStyle { h4 })
                        }
                        expanded(0.5f) {
                            WidthSpacer(1.dp)
                        }
                        inflexible {
                            Text("${problems[it].arg0}", style = +themeTextStyle { h4 })
                            Text(" ${problems[it].operator} ", style = +themeTextStyle { h4 })
                            Text("${problems[it].arg1}", style = +themeTextStyle { h4 })
                        }
                        expanded(0.5f) {
                            WidthSpacer(1.dp)
                        }
                        inflexible {
                            Text("=", style = +themeTextStyle { h4 })
                        }
                        expanded(0.5f) {
                            WidthSpacer(1.dp)
                        }
                        inflexible {
                            Text("${problems[it].answer}", style = +themeTextStyle { h4 })
                            WidthSpacer(8.dp)
                        }
                        expanded(0.5f) {
                            WidthSpacer(1.dp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExamsList(exams: ArrayList<ProblemSQLHelper.Exam>, onClick: () -> Unit) {
    val context = +ambient(ContextAmbient)
    VerticalScroller {
        Column {
            repeat(exams.size) {
                Container(height = 56.dp, expanded = true) {
                    FlexRow(mainAxisSize = LayoutSize.Expand) {
                        inflexible {
                            WidthSpacer(8.dp)
                            Text("$it.", style = +themeTextStyle { h4 })
                            WidthSpacer(8.dp)
                            Text(exams[it].title, style = +themeTextStyle { h4 })
                        }
                        expanded(0.5f) {
                            WidthSpacer(1.dp)
                        }
                        inflexible {
                            Text("${exams[it].createTime}", style = (+themeTextStyle { h4 }).copy(color = Color.Gray) )
                            WidthSpacer(8.dp)
                            VectorImageButton(R.drawable.ic_edit) {
                                Toast.makeText(context, "TODO", Toast.LENGTH_LONG).show()
                            }
                            WidthSpacer(8.dp)
                        }
                    }
                }
            }
        }
    }
}