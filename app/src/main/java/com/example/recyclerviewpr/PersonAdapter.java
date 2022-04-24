package com.example.recyclerviewpr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person> items = new ArrayList<Person>();
    OnPersonItemClickListener listener; // 클릭이벤트 리스너

    public void addItem(Person item) {
        items.add(item);
    }

    public void setItems(ArrayList<Person> items) {
        this.items = items;
    }

    public Person getItem(int position) {
        return  items.get(position);
    }

    public void setItem(int position, Person item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnPersonItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override // 필요한 순간에 보여줄 각 아이템 뷰를 만들어야 하는데, 이게 그 때 호출된다.
    // 각 아이템의 뷰를 만들어서 리턴하면 각각의 뷰 홀더가 그걸 들고 있는 꼴이다.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewHolder라고 하는 걸 생성할 시점에 자동으로 호출됨.
        // 각각의 아이템을위한 뷰홀더임. (뷰 홀더를 만들기만하고 set은 안함, set하는 건 따로 있음)
        // ViewHolder : 뷰를 갖고 있는 놈
        // 여기서 원소뷰의 모양을 결정한다.

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent, false);

        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // set만 하는 함수
        // ViewHolder가 계속 새로 만들어지진 않는다. 보이는 부분의 개수만큼만 만들어지고
        // 나머지는 재사용되는데,  새로 만들든 재사용하든 여튼 그것이 필요할때 바인딩(연결)시켜줄 때 호출되는 놈이다.
        // position 번째의 홀더에 position 번쨰의 데이터를 받아서 값을 넣어주는 작업을 한다.
        Person item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    // 이건 리사이클러 뷰의 필수 요소가 아니고
    // 커스텀 하게 만든 class임.
    static class ViewHolder extends RecyclerView.ViewHolder { // 단지 뷰 하나에 관한 것
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView, final OnPersonItemClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            // 아이템을 클릭했을 때 발생하는 이벤트 넣기
            itemView.setOnClickListener(new View.OnClickListener() { // 클릭 이벤트 추가
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        // 바깥(MainActivity)에서 정의한 내용을 수행하도록 만들었음.
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Person item) {
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }
    }
}
