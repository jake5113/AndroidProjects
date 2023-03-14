package com.jake5113.ex84gson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.jake5113.ex84gson.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(v -> clickBtn());
        binding.btn2.setOnClickListener(v -> clickBtn2());
        binding.btn3.setOnClickListener(v -> clickBtn3());
    }

    private void clickBtn3() {
        //jsonArray --> Person object array
        String jsonStr = "[{'name':'sam', 'age':20},{'name':'jake', 'age':26}]";

        Gson gson = new Gson();
        Person[] people = gson.fromJson(jsonStr, Person[].class);
        binding.tv.setText("객체 수 : " + people.length);
    }

    private void clickBtn2() {
        // Person 객체 --> json 문자열
        Person person = new Person("Robin", 35);

        Gson gson = new Gson();
        String json = gson.toJson(person);
        binding.tv.setText(json);
    }

    private void clickBtn() {
        // GSON library 를 이용하여 편하게 json 문자열을 분석하여 객체로 생성
        // json 문자열
        String jsonStr = "{'name':'sam', 'age':20}";

        // GSON 을 이용하여 name, age를 멤버로 가지는 Person 클래스 객체로 한방에 분석하여 변환
        Gson gson = new Gson();
        Person person = gson.fromJson(jsonStr, Person.class);
        binding.tv.setText(person.name + ":" + person.age);

    }
}