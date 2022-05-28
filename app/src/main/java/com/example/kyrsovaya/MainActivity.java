package com.example.kyrsovaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static com.example.kyrsovaya.Record.items;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int seconds;
    private boolean running;
    static ArrayList<String> arrayItems = new ArrayList<String>();
    static DatabaseReference mDatabase;
    static FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        runTimer();
        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        Button button2=(Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        mDatabase.child("titles").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int dataCount = (int) snapshot.getChildrenCount();
                if (snapshot.getChildrenCount() == 0){

                }
                else{
                    dNum = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void editDeal1(View view) {
        TextView dealCur = (TextView) findViewById(R.id.dealCur);
        TextView textDeal1 = (TextView) findViewById(R.id.textDeal1);
        dealCur.setText(textDeal1.getText().toString());
    }
    public void editDeal2(View view) {
        TextView dealCur = (TextView) findViewById(R.id.dealCur);
        TextView textDeal2 = (TextView) findViewById(R.id.textDeal2);
        dealCur.setText(textDeal2.getText().toString());
    }
    public void editDeal3(View view) {
        TextView dealCur = (TextView) findViewById(R.id.dealCur);
        TextView textDeal3 = (TextView) findViewById(R.id.textDeal3);
        dealCur.setText(textDeal3.getText().toString());
    }
    public void editDeal4(View view) {
        TextView dealCur = (TextView) findViewById(R.id.dealCur);
        TextView textDeal4 = (TextView) findViewById(R.id.textDeal4);
        dealCur.setText(textDeal4.getText().toString());
    }
    public void editDeal5(View view) {
        TextView dealCur = (TextView) findViewById(R.id.dealCur);
        TextView textDeal5 = (TextView) findViewById(R.id.textDeal5);
        dealCur.setText(textDeal5.getText().toString());
    }
    public void editDeal6(View view) {
        TextView dealCur = (TextView) findViewById(R.id.dealCur);
        TextView textDeal6 = (TextView) findViewById(R.id.textDeal6);
        dealCur.setText(textDeal6.getText().toString());
    }
    public void editDeal7(View view) {
        TextView dealCur = (TextView) findViewById(R.id.dealCur);
        TextView textDeal7 = (TextView) findViewById(R.id.textDeal7);
        dealCur.setText(textDeal7.getText().toString());
    }
    public void editDeal8(View view) {
        TextView dealCur = (TextView) findViewById(R.id.dealCur);
        TextView textDeal8 = (TextView) findViewById(R.id.textDeal8);
        dealCur.setText(textDeal8.getText().toString());
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
                Intent intent = new Intent(MainActivity.this, Redact.class);
                startActivity(intent);
                break;
            case R.id.button2:
                items.clear();
                mDatabase.child("titles").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(int i = 1; i <= dNum; i++){
                            String strI = Integer.toString(i);
                            items.add(new Item(snapshot.child(strI).getValue().toString()));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent a = new Intent(MainActivity.this, Record.class);
                startActivity(a);
                break;
        }
    }
    public String dateStart;
    public void onClickPusk(View view) {
        findViewById(R.id.pusk).setVisibility(GONE);
        findViewById(R.id.pause).setVisibility(View.VISIBLE);
        running = true;
        DateFormat dfStart = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm", Locale.getDefault());
        dateStart = dfStart.format(new Date());

    }

    public void onClickPause(View view) {
        findViewById(R.id.pause).setVisibility(GONE);
        findViewById(R.id.pusk).setVisibility(View.VISIBLE);
        running = false;
    }




    private final List<Item> itemMain = new ArrayList<>();
    static int dNum = 0;
    public String dStr;

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
        findViewById(R.id.pause).setVisibility(GONE);
        findViewById(R.id.pusk).setVisibility(View.VISIBLE);
        TextView itemText = (TextView) findViewById(R.id.dealCur);
        TextView itemTime = (TextView) findViewById(R.id.timeView);
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm", Locale.getDefault());
        String date = df.format(new Date());
        String itemFinal = itemText.getText().toString() + "\n" + dateStart + " - " + date + "\n" + itemTime.getText().toString();
        itemText.setText("");
        dNum += 1;
        dStr = Integer.toString(dNum);
        mDatabase.child("titles").child(dStr).setValue(itemFinal);
        items.clear();
    }

    private void runTimer(){
        final TextView textView = (TextView)findViewById(R.id.timeView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secon = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secon);
                textView.setText(time);
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Timer:")
                                .setContentText(time);

                Notification notification = builder.build();
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if(running){
                    seconds++;
                    notificationManager.notify(1, notification);
                }
                handler.postDelayed(this, 1000);
            }
        });
    }




}