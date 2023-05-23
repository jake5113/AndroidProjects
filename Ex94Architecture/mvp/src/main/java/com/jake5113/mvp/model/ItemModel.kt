package com.jake5113.mvp.model

import android.content.Context

// 데이터를 제어하는 기능 2개를 가진 클래스
class ItemModel constructor(val context: Context) { // 데이터를 제어하기 위해 Context 능력이 필요한 경우가 있다면 주 생성자로 전달받기

    // 1) 데이터를 전달받아 SharedPreferences 에 데이터를 저장하는 기능
    fun saveData(name: String, email: String) {
        val pref = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        pref.edit().apply() {
            putString("name", name)
            putString("email", email)
            commit()
        }
    }

    // 2) SharedPreferences 에서 데이터를 읽어와서 내보내는(리턴하는) 기능
    fun loadData(): Item {
        val pref = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        val name: String = pref.getString("name", "") as String
        val email: String = pref.getString("email", "") as String

        return Item(name, email)
    }
}