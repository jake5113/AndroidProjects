package com.mrhi2023.ex34actionbarlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ViewPager2 pager;
    MyAdapter adapter;

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pager= findViewById(R.id.pager);
        adapter= new MyAdapter(this);
        pager.setAdapter(adapter);

        tabLayout= findViewById(R.id.tab_layout);

        String[] titles= new String[]{"TAB1", "TAB2", "TAB3", "TAB4", "TAB5", "TAB6","TAB7", "TAB8", "TAB9"};
        TabLayoutMediator mediator= new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
            }
        });
        mediator.attach();
    }
}