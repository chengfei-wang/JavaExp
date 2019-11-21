package xyz.nfcv.pupil.asmd.`fun`

import org.intellij.lang.annotations.MagicConstant

object ASMD {
    fun randInt(@MagicConstant(flags = [1, 2, 3, 4, 5]) len: Int): Int {
        return when (len) {
            1 -> (1..9).random()
            2 -> (10..99).random()
            3 -> (100..999).random()
            4 -> (1000..9999).random()
            5 -> (10000..99999).random()
            else -> 0
        }
    }

    enum class Operator {
        ADD, SUB, MULTI, DIVIDE
    }

    class Problem(val operator: Operator, val arg0: Int, val arg1: Int) {
        val answer: Int = when(operator) {
            Operator.ADD -> arg0 + arg1
            Operator.SUB -> arg0 - arg1
            Operator.MULTI -> arg0 * arg1
            Operator.DIVIDE -> arg0 / arg1
        }

        override fun toString(): String {
            return "$arg0 "+ when(operator) {
                Operator.ADD -> "+"
                Operator.SUB -> "-"
                Operator.MULTI -> "*"
                Operator.DIVIDE -> "/"
            }+" $arg1 = $answer"
        }
    }

    fun generateProblem(size: Int, @MagicConstant(flags = [1, 2, 3, 4, 5]) len: Int, operator: Operator): ArrayList<Problem> {
        val list = ArrayList<Problem>()
        if (size <= 0) {
            return list
        }
        for(i in 0 until size) {
            list.add(Problem(operator, randInt(len), randInt(len)))
        }
        return list
    }
}