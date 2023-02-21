package com.mrhi2023.ex34actionbarlayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyAdapter extends FragmentStateAdapter {

    Fragment[] fragments= new Fragment[9];

    public MyAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragments[0]= new Tab1Fragment();
        fragments[1]= new Tab2Fragment();
        fragments[2]= new Tab3Fragment();
        fragments[3]= new Tab1Fragment();
        fragments[4]= new Tab2Fragment();
        fragments[5]= new Tab3Fragment();
        fragments[6]= new Tab1Fragment();
        fragments[7]= new Tab2Fragment();
        fragments[8]= new Tab3Fragment();

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
