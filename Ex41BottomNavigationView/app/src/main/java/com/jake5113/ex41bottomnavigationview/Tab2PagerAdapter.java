package com.jake5113.ex41bottomnavigationview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Tab2PagerAdapter extends FragmentStateAdapter {

    Fragment[] fragments = new Fragment[2];

    public Tab2PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragments[0] = new AAFragment();
        fragments[1] = new BBFragment();

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
