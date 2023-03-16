package com.jake5113.ex87retrofitmarketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.jake5113.ex87retrofitmarketapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                //onResume() 라이프사이클은 직접 호출하지 않는 것이 좋다.
                binding.refreshLayout.setRefreshing(false);
            }
        });

        binding.fabEdit.setOnClickListener(view -> {
            startActivity(new Intent(this, EditActivity.class));
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<MarketItem>> call = retrofitService.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<MarketItem>>() {
            @Override
            public void onResponse(Call<ArrayList<MarketItem>> call, Response<ArrayList<MarketItem>> response) {
                ArrayList<MarketItem> items = response.body();

                binding.recycler.setAdapter(new MarketAdapter(MainActivity.this, items));
            }

            @Override
            public void onFailure(Call<ArrayList<MarketItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}