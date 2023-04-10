package com.jake5113.ex93kotlinopenapinaversearch

// Int -> String 상관 없음. String 이 안전!
data class NaverSearchApiResponse(
    var total: Int,
    var display: Int,
    var items: MutableList<ShoppingItem>
)

// 아이템 1개의 클래스
data class ShoppingItem(
    var title: String,
    var link: String,
    var image: String,

    // 읽어올 Int 값이 빈값으로 오면 에러날 수 있기에 타입을 그냥 String
    var lprice: String,
    var hprice: String,
    var mallName: String

    // 더 있지만 필요한 것만
)