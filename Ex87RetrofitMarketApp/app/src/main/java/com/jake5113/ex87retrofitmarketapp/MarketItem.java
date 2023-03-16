package com.jake5113.ex87retrofitmarketapp;

public class MarketItem {

    // DB의 market 테이블의 column 값들을 저장할 멤버변수(필드)
    String no;
    String name;
    String title;
    String msg;
    String price;
    String image;
    String date;

    public MarketItem() {

    }

    public MarketItem(String no, String name, String title, String msg, String price, String image, String date) {
        this.no = no;
        this.name = name;
        this.title = title;
        this.msg = msg;
        this.price = price;
        this.image = image;
        this.date = date;
    }
}
