package com.jake5113.ex80activitylifecylcemethod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i("TAG", "second onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG", "second onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG", "second onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG", "second onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG", "second onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "second onDestroy");
    }
}