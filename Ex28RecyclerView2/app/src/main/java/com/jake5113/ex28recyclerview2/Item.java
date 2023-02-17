package com.jake5113.ex28recyclerview2;

public class Item {
    String name;         //"루피"
    String role;         //"밀집모자 해적단 선장"
    int profileImgId;    //R.drawable.crew_luffy
    int imgId;           //R.drawable.bg_one01

    public Item(String name, String role, int profileImgId, int imgId) {
        this.name = name;
        this.role = role;
        this.profileImgId = profileImgId;
        this.imgId = imgId;
    }
}
