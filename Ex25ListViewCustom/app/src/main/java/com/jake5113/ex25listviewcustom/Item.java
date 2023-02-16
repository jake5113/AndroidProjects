package com.jake5113.ex25listviewcustom;

public class Item {
    String name; // "전현무"
    String nation; // "대한민국"
    int imgId; // R.drawable.korea

    // 객체를 생성(new)할때 자동으로 실행되는 특별한 메소드 - constructor  생성자
    public Item(String name, String nation, int imgId){
        this.name = name;
        this.nation = nation;
        this.imgId = imgId;
    }
}
