package com.example.kyrsovaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static com.example.kyrsovaya.MainActivity.mDatabase;

public class Redact extends AppCompatActivity implements View.OnClickListener {
    static StorageReference mStorage = FirebaseStorage.getInstance().getReference();
    static Integer[] iconTable = new Integer[]{R.drawable.cat_29, R.drawable.cat_30, R.drawable.cat_31, R.drawable.cat_32, R.drawable.cat_33, R.drawable.cat_34, R.drawable.cat_35};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redact);
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        Spinner spinner = findViewById(R.id.spinner);
        SimpleImageArrayAdapter adapter = new SimpleImageArrayAdapter(this,
                iconTable);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public class SimpleImageArrayAdapter extends ArrayAdapter<Integer> {
        private Integer[] images;

        public SimpleImageArrayAdapter(Context context, Integer[] images) {
            super(context, android.R.layout.simple_spinner_item, images);
            this.images = images;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getImageForPosition(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getImageForPosition(position);
        }

        private View getImageForPosition(int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(images[position]);
            imageView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return imageView;
        }
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4:
                this.finish();
                break;
            case R.id.btnAdd:
                this.finish();

        }
    }

}