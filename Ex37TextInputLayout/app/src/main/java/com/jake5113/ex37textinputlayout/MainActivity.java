package com.jake5113.ex37textinputlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout til;

    AutoCompleteTextView acTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        til = findViewById(R.id.til);
        String s = til.getEditText().getText().toString();

        acTv = findViewById(R.id.ac_tv);
        String[] cities = getResources().getStringArray(R.array.cities);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cities);
        acTv.setAdapter(adapter);

    }
}