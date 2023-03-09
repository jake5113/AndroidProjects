package com.jake5113.ex79databinding;

import android.database.Observable;
import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class User {
    // 일반 자료형은 값이 변경되어도 화면갱신이 이루어지지 않음.
    // data binding 기능에 의해 변수값이 바뀌면 화면이 자동 갱신되는 특별한 자료형
    // ObservableXXX 클래스 객체
    public ObservableField<String> name = new ObservableField<>();
    public ObservableInt age = new ObservableInt();
    public ObservableBoolean fav = new ObservableBoolean();

    public User(String name, int age, boolean fav) {
        this.name.set(name);
        this.age.set(age);
        this.fav.set(fav);
    }

    // 버튼 클릭시에 실행될 콜백메소드 - 바꾸려는 변수가 이 클래스에 있으니까.
    // 리스너의 콜백메소드에 있는 파라미터가 반드시 똑같이 있어야만 됨.
    public void changeName(View view) {
        this.name.set("ROBIN");
    }

    public void increaseAge(View view) {
        this.age.set(this.age.get() + 1);
    }
}
