package com.happy.calculatortdd.model

import android.util.Log

class Operator {

    enum class Type {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVISION,
        PERCENT,
        SIGN
    }

    private val max = Double.MAX_VALUE
    private val min = -max
    private val error = Result(null, "error")

    fun plus(left: Number, right: Number): Result {
        val doubleLeft = left.toDouble()
        val doubleRight = right.toDouble()
        val result = doubleLeft + doubleRight

        if (result.isInfinite() ||
            doubleLeft > 0 && doubleRight >= max ||
            doubleRight > 0 && doubleLeft >= max ||
            doubleLeft < 0 && doubleRight <= min ||
            doubleRight < 0 && doubleLeft <= min
        ) {
            return error
        }
        return Result(result)
    }

    fun minus(left: Number, right: Number): Result {
        return plus(left, -(right.toDouble()))
    }

    fun multiply(left: Number, right: Number): Result {
        val doubleLeft = left.toDouble()
        val doubleRight = right.toDouble()
        var result = doubleLeft * doubleRight
        Log.i("eunjin", "result $doubleLeft $doubleRight $result")

        if (result.isInfinite() ||
            doubleLeft > 1.0 && doubleRight >= max ||
            doubleLeft < -1.0 && doubleRight >= max ||
            doubleLeft < -1.0 && doubleRight <= min ||
            doubleRight > 1.0 && doubleLeft >= max ||
            doubleRight < -1.0 && doubleLeft >= max ||
            doubleRight < -1.0 && doubleLeft <= min
        ) {
            return error
        }

        if (result == -0.0) {
            result = 0.0
        }
        return Result(result)
    }

    fun division(left: Number, right: Number): Result {
        return multiply(left, 1/right.toDouble())
    }

    fun percent(value: Number): Result {
        return division(value, 100)
    }

    fun toggleSign(value: Number): Result {
        return Result(if (value == 0.0) 0.0 else -value.toDouble())
    }
}