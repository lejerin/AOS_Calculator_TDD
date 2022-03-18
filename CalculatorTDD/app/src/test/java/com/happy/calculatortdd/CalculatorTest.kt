package com.happy.calculatortdd

import com.happy.calculatortdd.model.Operator
import junit.framework.TestCase

class CalculatorTest : TestCase() {

    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() {}

    /*
    1. (완료) 좌항과 우항을 갖고있음 (초기값 0)
    2. (완료) 연산자 입력 전까지는 좌항에 더해지거나 처리된다.
    */
    fun testAddNum() {
        val calculator = Calculator()
        assertEquals(0.0, calculator.leftDouble)
        assertEquals(0.0, calculator.rightDouble)
        calculator.addNum(1)
        calculator.addNum(3)
        calculator.addNum(0)
        assertEquals(130.0, calculator.leftDouble)
    }

    /*
       (완료) 1. 연산자 입력 후에는 우항에 더해지거나 처리된다.
     */
    fun testSetOperator() {
        val calculator = Calculator()
        calculator.addNum(1)
        calculator.setOperator(Operator.Type.PLUS)
        calculator.addNum(2)
        assertEquals(1.0, calculator.left.joinToString("").toDouble())
        assertEquals(2.0, calculator.right.joinToString("").toDouble())
        calculator.addNum(3)
        assertEquals(23.0, calculator.right.joinToString("").toDouble())
    }

    /*
       (완료) 1. = 입력하면 좌항, 우항에 연산자가 계산되고, 계산값은 좌항에 저장되며 우항은 0으로 초기화 된다.
     */
    fun testCalculate() {
        val calculator = Calculator()
        calculator.addNum(1)
        calculator.setOperator(Operator.Type.PLUS)
        calculator.addNum(2)
        assertEquals(3.0, calculator.calculate().value)
        assertEquals(3.0, calculator.leftDouble)
        assertEquals(0.0, calculator.rightDouble)
    }

    /*
        (완료) 1. . 입력되면 입력 값이 소숫점에 더해진다
     */
    fun testAddDot() {
        val calculator = Calculator()
        calculator.addNum(1)
        calculator.addDot()
        calculator.addNum(5)
        assertEquals(1.5, calculator.left.joinToString("").toDouble())
    }

    /*
        (완료) 1. 연산자 입력후 . 입력되고 숫자를 입력하면 우항 소숫점에 더해진다.
    */
    fun testDotAfterOperator() {
        val calculator = Calculator()
        calculator.addNum(1)
        calculator.addDot()
        calculator.addNum(5)
        assertEquals(1.5, calculator.leftDouble)
        calculator.setOperator(Operator.Type.PLUS)
        calculator.addDot()
        calculator.addNum(5)
        assertEquals(0.5, calculator.rightDouble)
        assertEquals(2.0, calculator.calculate().value)
    }

    /*
        (완료) 1. clear 가 되면 좌항, 우항 모두 0이 된다.
        (완료) 2. 연산자, dot 모두 초기화
     */
    fun testClear() {
        val calculator = Calculator()
        calculator.addNum(1)
        // 초기화
        calculator.clear()
        // 확인
        assertEquals(0.0, calculator.leftDouble)
        assertEquals(0.0, calculator.rightDouble)
        calculator.addDot()
        calculator.addNum(9)
        assertEquals(0.9, calculator.leftDouble)
    }

    /*
        (완료) 1. 좌항, 우항 최대 length 는 12자 (소숫점 포함)
     */
    fun testMaxLength() {
        val calculator = Calculator()
        for (i in 0 until 12) {
            calculator.addNum(1)
        }
        val maxLength = calculator.left.size
        calculator.addNum(1)
        assertEquals(maxLength, calculator.left.size)
    }
}