package com.happy.calculatortdd

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest : TestCase() {

    @get:Rule
    var mainActivityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() { }

    /*
    1. 아무것도 입력되지 않은 초기상태에서 소숫점을 누르면 0. 으로 결과값 표시
    */
    @Test
    fun testClickAddDot() {
        clickViewId(R.id.btn_dot)
        checkResult("0.")
    }

    /*
    1. 소숫점 . 이 중복 입력되지 않아야 함
     */
    @Test
    fun testNotAllowedDuplicateAddDot() {
        testClickAddDot()
        testClickAddDot() // 변경 없어야
    }

    /*
    1. 101.000901 입력하기
     */
    @Test
    fun testDouble() {
        clickNum("101.000901")
        checkResult("101.000901")
    }

    /*
    1. 숫자를 입력했을 때, 현재 값이 결과창에 보여져야 함
     */
    @Test
    fun testClickNum() {
        clickNum("12.9")
        checkResult("12.9")
    }

    /*
    1. + 연산자 정수 테스트
     */
    @Test
    fun testOperatorPlus() {
        clickNum("12")
        clickViewId(R.id.btn_plus)
        clickNum("2")
        clickViewId(R.id.btn_result)
        checkResult("${12 + 2}")
    }

    /*
    1. + 연산자 실수 테스트
     */
    @Test
    fun testOperatorPlusDouble() {
        clickNum("12.2")
        clickViewId(R.id.btn_plus)
        clickNum("2")
        clickViewId(R.id.btn_result)
        checkResult("${12.2 + 2}")
    }

    /*
   1. - 연산자 실수 테스트
    */
    @Test
    fun testOperatorMinus() {
        clickNum("1004.009")
        clickViewId(R.id.btn_minus)
        clickNum("104.009")
        clickViewId(R.id.btn_result)
        checkResult("${1004 - 104}")
    }

    private fun clickViewId(viewId: Int) {
        onView(withId(viewId))
            .check(matches(isDisplayed()))
            .perform(click())
    }

    private fun clickNum(input: String) {
        for (c in input) {
            when (c) {
                '0' -> clickViewId(R.id.btn_num_0)
                '1' -> clickViewId(R.id.btn_num_1)
                '2' -> clickViewId(R.id.btn_num_2)
                '3' -> clickViewId(R.id.btn_num_3)
                '4' -> clickViewId(R.id.btn_num_4)
                '5' -> clickViewId(R.id.btn_num_5)
                '6' -> clickViewId(R.id.btn_num_6)
                '7' -> clickViewId(R.id.btn_num_7)
                '8' -> clickViewId(R.id.btn_num_8)
                '9' -> clickViewId(R.id.btn_num_9)
                '.' -> clickViewId(R.id.btn_dot)
            }
        }
    }

    private fun checkResult(expectedValue: String) {
        onView(withId(R.id.tv_result))
            .check(matches(isDisplayed()))
            .check(matches(withText(expectedValue)))
    }

}