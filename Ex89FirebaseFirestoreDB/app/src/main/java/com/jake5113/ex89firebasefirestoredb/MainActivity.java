package com.jake5113.ex89firebasefirestoredb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jake5113.ex89firebasefirestoredb.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSave.setOnClickListener(view -> clickSave());
        binding.btnLoad.setOnClickListener(view -> clickLoad());
    }

    private void clickSave() {
        String name = binding.etName.getText().toString();
        int age = Integer.parseInt(binding.etAge.getText().toString());
        String address = binding.etAddress.getText().toString();

        // Firestore DB 에 저장 [ Map Collection 단위로 저장 ]
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        // "person" 이라는 이름의 컬렉션을 참조하는 참조 객체 소환
        CollectionReference personRef = firestore.collection("person"); // 없으면 만들고, 있으면 참조

        // Field 값들을 Map 으로 준비 
        Map<String, Object> person = new HashMap<>();
        person.put("name", name);
        person.put("age", age);
        person.put("address", address);

        // personRef 참조 컬렉션에 값들 넣기
        // .document()안에 값을 주지 않으면 랜덤한 문자열이 식별자로 지정됨
//        personRef.document().set(person).addOnSuccessListener(unused -> {
//            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
//        });

        // .document().set(person) 의 축약기능 --> .add()
        personRef.add(person).addOnSuccessListener(documentReference -> {
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        });

    }

    private void clickLoad() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference personRef = firestore.collection("person");
        personRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                // Collection 안에 여러개의 Document 가 있기에
                StringBuffer buffer = new StringBuffer();
                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    Map<String, Object> person = snapshot.getData();
                    String name = person.get("name").toString();
                    int age = Integer.parseInt(person.get("age").toString());
                    String address = person.get("address").toString();

                    buffer.append(name + " : " + age + " - " + address + "\n");
                }
                binding.tv.setText(buffer.toString());
            }
        });

        // ** 별외. 특정 필드값에 해당하는 데이터를 검색하고 싶다면.
        //personRef.get(); // 이건 모든 데이터
//        personRef.whereEqualTo("name", "sam").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//            }
//        });
    }
}