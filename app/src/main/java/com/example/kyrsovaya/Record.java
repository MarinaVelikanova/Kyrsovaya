package com.example.kyrsovaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import static com.example.kyrsovaya.MainActivity.mDatabase;

public class Record extends AppCompatActivity implements View.OnClickListener{
    static  final List<Item> items = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new ItemAdapter(this.items);
    final static String savedItems = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener((View.OnClickListener) this);

        RecyclerView recycler = findViewById(R.id.recordsList);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);


    }


    public void deleteItem (View view){

        String deleteText = "аааааа";
        mDatabase.child("titles").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int dataCount = (int) snapshot.getChildrenCount();
                for (int i=1; i<=dataCount;i++){
                    String strI = Integer.toString(i);
                    if (deleteText == snapshot.child(strI).getValue().toString()){
                        DatabaseReference refDelete = snapshot.child(strI).getRef();
                        refDelete.removeValue();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        items.clear();
    }

    public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button3:
                        this.finish();
                        break;
                }
            }

    public static final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Item> items;

        public ItemAdapter(List<Item> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.items, parent, false)
            ) {};
        }


        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
            TextView name = holder.itemView.findViewById(R.id.name);
            name.setText(String.format("%s", this.items.get(index).getName()));

        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }

    }



}



