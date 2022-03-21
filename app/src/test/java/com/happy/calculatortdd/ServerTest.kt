package com.happy.calculatortdd

import com.happy.calculatortdd.data.ListRepository
import junit.framework.TestCase
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ServerTest: TestCase() {

    /*
    Todo: 해야할 목록
    - (완료) 서버에서 목록 가져오기
    - (완료) 계산 완료되면 서버에 추가하기
    - 초기화 요청 되면, 서버에 목록 모두 지우기
     */

    @Mock
    private lateinit var listRepository: ListRepository

    public override fun setUp() {
        MockitoAnnotations.initMocks(this)
        super.setUp()
    }

    public override fun tearDown() {}


    // 서버에서 목록 가져오기
    @Test
    fun testGetHistoryList() {
        `when`(listRepository.getHistoryList(1234)).thenReturn(
            listOf(
                "1004",
                "31",
                "444"
            )
        )

        assertEquals(3, listRepository.getHistoryList(1234).size)
    }

    // 숫자일 경우, 유효한 값으로 서버 히스토리에 저장
    @Test
    fun testAddValidHistory() {
        `when`(listRepository.getHistoryList(1234)).thenReturn(
            listOf( // 첫 번째 호출 반환
                "1004",
                "31",
                "444"
            ),
            listOf( // 두 번째 호출 반환
                "1004",
                "31",
                "444",
                "1234"
            )
        )
        val beforeList = listRepository.getHistoryList(1234)
        listRepository.addHistory("1234")
        val afterList = listRepository.getHistoryList(1234)
        assertEquals(3, beforeList.size)
        assertEquals(4, afterList.size)
        assertEquals("1234", afterList.last())
        // listRepository.addHistory("1234") 이 한 번만 불렸어야 함을 검증한다.
        verify(listRepository, times(1)).addHistory("1234")
    }

    // 초기화 요청 되면, 서버에 목록 모두 지우기
    @Test
    fun testClearHistory() {
        `when`(listRepository.getHistoryList(1234)).thenReturn(
            listOf( // 첫 번째 호출 반환
                "1004",
                "31",
                "444"
            ),
            listOf()
        )
        val beforeList = listRepository.getHistoryList(1234)
        listRepository.clearHistory()
        val afterList = listRepository.getHistoryList(1234)
        assertEquals(3, beforeList.size)
        assertEquals(0, afterList.size)
        // listRepository.clearHistory() 이 한 번만 불렸어야 함을 검증한다.
        verify(listRepository, times(1)).clearHistory()
    }
}