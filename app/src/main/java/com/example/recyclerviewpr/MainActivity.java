package com.example.recyclerviewpr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // (1) 리사이클러뷰의 뷰 찾고
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // (2) 리사이클러뷰의 옵션 설정 (모양 및 형태 set)
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager); // 리사이클러 뷰가 보여지는 모양을 설정

        // (3) 리사이클러뷰의 adapter 설정 (데이터 set)
        adapter = new PersonAdapter();
        adapter.addItem(new Person("김민수", "010-4005-1234"));
        adapter.addItem(new Person("김띨띨", "010-2039-9934"));
        adapter.addItem(new Person("홍삼삼", "010-2229-1524"));
        recyclerView.setAdapter(adapter);

        // 데이터 원소에 클릭아이템 리스너 설정
        adapter.setOnItemClickListener(new OnPersonItemClickListener() { // 클릭 이벤트 추가
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                Person item = adapter.getItem(position);
                showToast("아이템 선택됨 : " + item.getName());
            }
        });
    }

    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}