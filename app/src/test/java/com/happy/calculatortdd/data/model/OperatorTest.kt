package com.happy.calculatortdd.data.model

import junit.framework.TestCase

class OperatorTest : TestCase() {

    private val left = 1.0
    private val right = 2.0
    private val max = Double.MAX_VALUE
    private val min = -max

    private val operator = Operator()

    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() {

    }

    /*
    1. (완료) 더하기
    2. (완료) 결과 값이 범위 초과면 error 표기
    3. (완료) 결과 값이 범위 미만이면 error 표기
    4. (완료) 정수도 입력할 수 있고, 결과 값은 Double
     */
    fun testPlus() {
        assertEquals(3.0, operator.plus(left, right).value)
        assertEquals("error", operator.plus(max, max).errorMessage)
        assertEquals("error", operator.plus(min, min).errorMessage)
        assertEquals(max, operator.plus(max, 0.0).value)
        assertEquals("error", operator.plus(max, 1.0).errorMessage)
        assertEquals("error", operator.plus(min, -100.0).errorMessage)
        assertEquals(3.0, operator.plus(2, 1).value)
    }

    /*
    1. (완료) 빼기
    2. (완료) 결과 값이 범위 초과면 error 표기
    3. (완료) 결과 값이 범위 미만이면 error 표기
    4. (완료) - (음수) = 더하기 계산
    5. (완료) 정수도 입력할 수 있고, 결과 값은 Double
     */
    fun testMinus() {
        assertEquals(-1.0, operator.minus(left, right).value)
        assertEquals("error", operator.minus(min, 1.0).errorMessage)
        assertEquals("error", operator.minus(max, -1.0).errorMessage)
        assertEquals(5.0, operator.minus(3, -2).value)
    }

    /*
    1. (완료) 곱하기
    2. (완료) 결과 값이 범위 초과면 error 표기
    3. (완료) 결과 값이 범위 미만이면 error 표기
    4. (완료) 정수도 입력할 수 있고, 결과 값은 Double
    5. (완료) 좌항, 우항 중에 하나라도 0이 있으면 결과는 0
    6. (완료) 음수 * 음수 = 양수
    7. (완료) -0.0 도 +0.0으로 변환 (편의상)
     */
    fun testMultiply() {
        assertEquals(6.0, operator.multiply(2.0, 3.0).value)
        assertEquals("error", operator.multiply(1.5, max).errorMessage)
        assertEquals("error", operator.multiply(1.1, min).errorMessage)
        assertEquals("error", operator.multiply(-1.01, min).errorMessage)
        assertEquals(100.0, operator.multiply(1, 100).value)
        assertEquals(0.0, operator.multiply(max, 0).value)
        assertEquals(0.0, operator.multiply(0, min).value)
        assertEquals(3.0, operator.multiply(-1, -3.0).value)
    }

    /*
    1. (완료) 나누기
    2. (완료) 결과 값이 범위 초과면 error 표기
    3. (완료) 결과 값이 범위 미만이면 error 표기
    4. (완료) 정수도 입력할 수 있고, 결과 값은 Double
    5. (완료) 0으로 나누면 error 표기
     */
    fun testDivision() {
        assertEquals(2.0, operator.division(6.0, 3.0).value)
        assertEquals("error", operator.division(max, 0.1).errorMessage)
        assertEquals("error", operator.division(min, 0.1).errorMessage)
        assertEquals("error", operator.division(max, 0.0).errorMessage)
        assertEquals(2.0, operator.division(6, 3).value)
        assertEquals("error", operator.division(10, 0).errorMessage)
    }

    /*
    1. (완료) 부호 변경
    2. (완료) 입력 값이 0이면 그대로 0 반환
    3. (완료) 정수로 입력할 수 있고, 결과 값은 Double
     */
    fun testToggleSign() {
        assertEquals(-1.0, operator.toggleSign(1.0))
        assertEquals(1.0, operator.toggleSign(-1.0))
        assertEquals(max, operator.toggleSign(min))
        assertEquals(min, operator.toggleSign(max))
        assertEquals(0.0, operator.toggleSign(0.0))
        assertEquals(-1.0, operator.toggleSign(1))
    }

    /*
    1. (완료) 퍼센트 계산 (100으로 나눔)
    2. (완료) 정수로 입력할 수 있고, 결과 값은 Double
    3. (완료) 입력 값이 0이면 그대로 0 반환
    4. (완료) 결과 값이 범위 초과면 error 표기
    5. (완료) 결과 값이 범위 미만이면 error 표기
     */
    fun testPercent() {
        assertEquals(0.01, operator.percent(1.0).value)
        assertEquals("error", operator.percent(max * 200).errorMessage)
        assertEquals(1.0, operator.percent(100).value)
        assertEquals(0.0, operator.percent(0).value)
        assertEquals(max/100.0, operator.percent(max).value)
    }
}