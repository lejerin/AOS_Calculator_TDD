package com.happy.calculatortdd

import androidx.lifecycle.MutableLiveData
import com.happy.calculatortdd.data.model.Operator
import com.happy.calculatortdd.data.model.Result

class Calculator {
    val resultLiveData = MutableLiveData("0")

    var left = mutableListOf<Char>()
    val leftDouble: Double
        get() {
            return if (left.isEmpty()) {
                0.0
            } else {
                var str = left.joinToString("")
                if (str.last() == '.') {
                    str += '0'
                }
                str.toDouble()
            }
        }
    var right = mutableListOf<Char>()
    val rightDouble: Double
        get() {
            return if (right.isEmpty()) {
                0.0
            } else {
                var str = right.joinToString("")
                if (str.last() == '.') {
                    str += '0'
                }
                str.toDouble()
            }
        }

    var operatorType: Operator.Type? = null

    private val operator = Operator()

    fun addNum(num: Int) {
        if (!checkMaxLength()) {
            return
        }
        if (operatorType != null) {
            right.add(Character.forDigit(num, 10))
        } else {
            left.add(Character.forDigit(num, 10))
        }
        updateResult()
    }

    private fun updateResult() {
        val current = if (operatorType != null) {
            right.joinToString("")
        } else {
            left.joinToString("")
        }
        resultLiveData.postValue(
            when {
                current == "" -> "0"
                !current.contains('.') -> {
                    current.toInt().toString()
                }
                else -> current
            }
        )
    }

    private fun checkMaxLength(): Boolean {
        return if (operatorType != null) {
            right.size < 10
        } else {
            left.size < 10
        }
    }

    fun addDot() {
        if (operatorType != null) {
            if (!right.contains('.')) {
                if (right.isEmpty()) {
                    right.add('0')
                }
                right.add('.')
            }
        } else {
            if (!left.contains('.')) {
                if (left.isEmpty()) {
                    left.add('0')
                }
                left.add('.')
            }
        }
        updateResult()
    }

    fun setOperator(type: Operator.Type) {
        operatorType = type
        when (type) {
            Operator.Type.PERCENT,
            Operator.Type.SIGN -> calculate()
            else -> {

            }
        }
        updateResult()
    }

    fun calculate(): Result {
        return when (operatorType) {
            Operator.Type.PLUS -> operator.plus(leftDouble, rightDouble)
            Operator.Type.MINUS -> operator.minus(leftDouble, rightDouble)
            Operator.Type.MULTIPLY -> operator.multiply(leftDouble, rightDouble)
            Operator.Type.DIVISION -> operator.division(leftDouble, rightDouble)
            Operator.Type.PERCENT -> operator.percent(leftDouble)
            Operator.Type.SIGN -> operator.toggleSign(leftDouble)
            else -> {
                Result(null, null)
            }
        }.also {
            it.value?.let { successValue ->
                left.clear()
                right.clear()
                operatorType = null
                left.addAll(
                    if (successValue - successValue.toInt() == 0.0) {
                        successValue.toInt().toString().toList()
                    } else {
                        successValue.toString().toList()
                    }
                )
                updateResult()
            }
            // 에러 난 경우는?
        }
    }

    fun clear() {
        left.clear()
        right.clear()
        operatorType = null
        updateResult()
    }
}