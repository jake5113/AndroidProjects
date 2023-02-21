package com.mrhi2023.ex34actionbarlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab1Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab1, container, false);
        return view;
    }

    Button btn;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btn= view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 탭의 위치를 변경 - TabLayout에게 요청 - MainActivity에게 요청
                MainActivity ma= (MainActivity)getActivity();
                ma.tabLayout.selectTab( ma.tabLayout.getTabAt(2) );
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
