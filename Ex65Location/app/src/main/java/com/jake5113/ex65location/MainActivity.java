package com.jake5113.ex65location;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;

    TextView tv, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        // 위치정보 관리자 객체 소환
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 디바이스에서 위치정보를 제공하는 장치 여러개
        // 이런것들을 위치정보 제공자(Location Provider) 라고 부름

        // 디바이스에서 제공하는 프로바이더의 종류들을 먼저 확인해보기
        List<String> providers = locationManager.getAllProviders();
        StringBuffer buffer = new StringBuffer();
        for (String provider : providers) {
            buffer.append(provider + ", ");
        }
        tv.setText(buffer.toString());

        // 내 위치 얻어오기
        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());
        findViewById(R.id.btn2).setOnClickListener(v -> clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(v -> clickBtn3());

        // 위치 정보 제공에 대한 동적 퍼미션
        int checkPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if (checkPermission == PackageManager.PERMISSION_DENIED) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    // 퍼미션 요청 및 결과를 받아주는 대행사 객체 생성
    ActivityResultLauncher<String> permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result) Toast.makeText(MainActivity.this, "위치 허용", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainActivity.this, "위치정보 제공 거부", Toast.LENGTH_SHORT).show();
        }
    });

    private void clickBtn() {
        Location location = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (locationManager.isProviderEnabled(LocationManager.FUSED_PROVIDER)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                location = locationManager.getLastKnownLocation(LocationManager.FUSED_PROVIDER);
            }
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if (location == null) {
            tv2.setText("내 위치 못 찾겠어!");
        } else {
            //위도, 경도 얻어오기
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            tv2.setText(latitude + ", " + longitude);
        }
    }

    private void clickBtn2() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (locationManager.isProviderEnabled("fused")) {
            locationManager.requestLocationUpdates("fused", 5000, 2, locationListener);
        } else if (locationManager.isProviderEnabled("gps")) {
            locationManager.requestLocationUpdates("gps", 5000, 2, locationListener);
        }
    }

    private void clickBtn3() {
        locationManager.removeUpdates(locationListener);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            tv3.setText(latitude + ", " + longitude);
        }
    };


}