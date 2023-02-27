package com.jake5113.ex53xmlpullparsermovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 영화정보를 가진 MovieItem을 여러개 관리하는 리스트 객체
    ArrayList<MovieItem> movieItems = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 원래는 서버의 xml 문서를 읽어와야 하지만..리사이클러뷰의 동작 테스트를 위해.. 가상의 값을 추가해보기
        movieItems.add(new MovieItem("1", "스머프", "2020-02-02", "123456"));
        movieItems.add(new MovieItem("2", "아바타", "2020-02-03", "456789"));

        recyclerView = findViewById(R.id.recycler);
        adapter = new MyAdapter(this, movieItems);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());


    }

    private void clickBtn() {
        // 영화진흥위원회 open API 정보(일일 박스오피스)를 가져와서 리사이클러뷰에 보여주기
        // xml 파일포멧의 데이터를 읽어와서 분석.

        // 네트워크 작업은 권한이 반드시 요구됨. [ AndroidManifest.xml 에서 권한부여 ]
        // 네트워크작업은 오래걸리는 작업으로 인식됨. 그래서 반드시 별도의 Thread 가 작업해야만 함.
        new Thread() {
            @Override
            public void run() {

                // 네트워크 작업 비동기로 시작.

                // xml 문서의 REST url 주소
                // sample 주소
                String address = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=f5eef3421c602c6cb7ea224104795888&targetDt=20120101";

                // 위 서버 url 위치까지 무지개 로드를 열어주는 해임달 객체 생성
                try {
                    URL url = new URL(address);

                    // 무지개로드 열기
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is); // 바이트스트림 --> 문자 스트림

                    // XML 문서를 조금 더 쉽게 분석(Parse) 해주는 객체 생성
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(isr);

                    // XML Parser 에게 분석작업을 요청
                    int eventType = xpp.getEventType(); // 시작위치가 START_DOCUMENT

                    while (eventType != XmlPullParser.END_DOCUMENT) {

                        // TODO : 마무리 하기
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                break;
                            case XmlPullParser.START_TAG:
                                break;
                            case XmlPullParser.END_TAG:
                                break;
                            case XmlPullParser.TEXT:
                                break;
                        }

                        eventType = xpp.next();
                    }

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();
    }
}