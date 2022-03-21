package com.happy.calculatortdd.data

/** 서버와 통신하여 계산 히스토리 조회, 추가, 삭제 담당하는 클래스*/
open class ListRepository {

    open fun getHistoryList(id: Long): List<String> {
        val list = listOf<String>("1", "2", "3")
        return list;
    }

    open fun addHistory(value: String) {
        // add
    }
}