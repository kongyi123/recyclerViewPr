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
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewHolder라고 하는 걸 생성할 시점에 자동으로 호출됨.
        // 각각의 아이템을위한 뷰홀더임. (뷰 홀더를 만들기만하고 set은 안함, set하는 건 따로 있음)

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent, false);

        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // set만 하는 함수
        // ViewHolder가 계속 새로 만들어지진 않는다. 보이는 부분의 개수만큼만 만들어지고
        // 나머지는 재사용되는데, 재사용할 때 호출되는 함수임.

        Person item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder { // 단지 뷰 하나에 관한 것
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView, final OnPersonItemClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() { // 클릭 이벤트 추가
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
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
