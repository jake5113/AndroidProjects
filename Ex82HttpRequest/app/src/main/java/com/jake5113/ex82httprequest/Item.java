package com.jake5113.ex82httprequest;

// 서버 DB의 board2 테이블의 한 줄(row : 레코드)의 값들을 가지고 있는 데이터용 클래스
public class Item {
    int no;
    String name;
    String msg;
    String date;
    public Item() {

    }
    public Item(int no, String name, String msg, String date) {
        this.no = no;
        this.name = name;
        this.msg = msg;
        this.date = date;
    }
}
