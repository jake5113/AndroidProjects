package com.jake5113.ex67locationgeocoding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText et;
    EditText etLat, etLng;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        etLat = findViewById(R.id.et_lat);
        etLng = findViewById(R.id.et_lng);

        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());
        findViewById(R.id.btn2).setOnClickListener(v -> clickBtn2());
        findViewById(R.id.btn_map).setOnClickListener(v -> clickBtnMap());
    }

    private void clickBtnMap() {
        // 지도 앱을 실행시키는 인텐트
        Intent intent = new Intent(Intent.ACTION_VIEW);

        // 지도의 좌표 uri
        Uri uri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "&z=10");
        intent.setData(uri);

        startActivity(intent);
    }

    private void clickBtn() {
        // 주소 -> 좌표 [ 지오코딩 ] : 지오코딩 작업은 Google 지도 서버를 이용함. 그래서 인터넷 사용 퍼미션

        String addr = et.getText().toString(); // 검색 주소

        // 지오코딍을 해주는 객체 생성
        Geocoder geocoder = new Geocoder(this, Locale.KOREA);

        // 주소에 해당하는 좌표가 여러개일 수 있음. 그래서 좌표결과를 List 로 반환함
        try {
            List<Address> addresses = geocoder.getFromLocationName(addr, 3);

            StringBuffer buffer = new StringBuffer();
            for (Address address : addresses) {
                buffer.append(address.getLatitude() + " , " + address.getLongitude() + "\n");
            }

            // 지도에 보여줄 좌표값
            latitude = addresses.get(0).getLatitude();
            longitude = addresses.get(0).getLongitude();

            new AlertDialog.Builder(this).setMessage(buffer.toString()).create().show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void clickBtn2() {
        // 좌표 -> 주소 [ 역지오코딩 ]
        double latitude = Double.parseDouble(etLat.getText().toString());
        double longitude = Double.parseDouble(etLng.getText().toString());

        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 3);

            StringBuffer buffer = new StringBuffer();
            for (Address address : addresses) {
                buffer.append(address.getCountryName() + "\n"); // 나라 이름
                buffer.append(address.getCountryCode() + "\n"); // 나라 코드
                buffer.append(address.getPostalCode() + "\n");  // 우편 번호
                buffer.append(address.getAddressLine(0) + "\n");  // 주소1 : 도로명 건물번호
                buffer.append(address.getAddressLine(1) + "\n");  // 주소2 : 상세주소 1 -- 없으면 null
                buffer.append(address.getAddressLine(2) + "\n");  // 주소3 : 상세주소 2 -- 없으면 null
                buffer.append("=======================\n");
            }

            new AlertDialog.Builder(this).setMessage(buffer.toString()).create().show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}