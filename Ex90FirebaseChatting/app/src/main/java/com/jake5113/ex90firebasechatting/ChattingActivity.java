package com.jake5113.ex90firebasechatting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.jake5113.ex90firebasechatting.databinding.ActivityChattingBinding;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ChattingActivity extends AppCompatActivity {

    ActivityChattingBinding binding;
    FirebaseFirestore firestore;
    CollectionReference chatRef;

    String chatName = "chat1";

    ArrayList<MessageItem> messageItems = new ArrayList<>();
    MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChattingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // XML에서도 가능
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setStackFromEnd(true);
//        binding.recycler.setLayoutManager(manager);

        // 제목줄에 채팅방 이름의 표시됨.
        getSupportActionBar().setTitle(chatName);
        getSupportActionBar().setSubtitle("상대방 이름");

        adapter = new MessageAdapter(this, messageItems);
        binding.recycler.setAdapter(adapter);

        // Firebase Firestore 관리객체 및 (채팅방 이름) 참조객체 소환
        firestore = FirebaseFirestore.getInstance();
        chatRef = firestore.collection(chatName);

        // '채팅방이름' 컬렉션에 저장되어 있는 데이터들 읽어오기
        // chatRef의 데이터가 변경될때마다 반응하는 리스너 추가
        chatRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                // 변경된 Document 만 찾아달라고 요청.
                List<DocumentChange> documentChanges = value.getDocumentChanges();
                for (DocumentChange documentChange : documentChanges) {
                    // 변경된 문서내역의 데이터를 촬영한 SnapShot 얻어오기
                    DocumentSnapshot snapshot = documentChange.getDocument();

                    // Document 에 있는 필드값 가져오기
                    Map<String, Object> msg = snapshot.getData();
                    String name = msg.get("name").toString();
                    String message = msg.get("message").toString();
                    String profileUrl = msg.get("profileUrl").toString();
                    String time = msg.get("time").toString();

                    // 읽어온 메세지를 리스트에 추가
                    messageItems.add(new MessageItem(name, message, profileUrl, time));

                    // 아답터에게 데이터가 추가되었다고 공지해야 화면이 갱신됨.
                    adapter.notifyItemInserted(messageItems.size() - 1);
                    // 리사이클러뷰의 스크롤위치가 가장 아래로 이동
                    binding.recycler.scrollToPosition(messageItems.size() - 1);
                }
            }
        });

        binding.btn.setOnClickListener(v -> clickSend());
    }

    private void clickSend() {
        String nickName = G.nickName;
        String message = binding.et.getText().toString();
        String profileUrl = G.profileUrl;
        // 메세지 작성 시간을 문자열 [시:분]
        Calendar calendar = Calendar.getInstance();
        String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);

        // Field에 넣을 값들을 MessageItem 객체로 만들어서 한방에 입력
        MessageItem item = new MessageItem(nickName, message, profileUrl, time);

        // '채팅방이름' 컬렉션에 채팅메세지들을 저장
        // 단, 시간순으로 정렬되도록 document 의 이름은 현재시간(1970년부터 카운트 된 ms)으로 지정
        chatRef.document("MSG_" + System.currentTimeMillis()).set(item);

        // 다음 메세지를 입력이 수월하도록 EditText에 있는 글씨 없애기
        binding.et.setText("");

        // 소프트키보드 숨기기
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}