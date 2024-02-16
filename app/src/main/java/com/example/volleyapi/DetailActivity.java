package com.example.volleyapi;

import static com.example.volleyapi.MainActivity.EXTRA_CREATOR;
import static com.example.volleyapi.MainActivity.EXTRA_LIKES;
import static com.example.volleyapi.MainActivity.EXTRA_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        String imgurls=intent.getStringExtra(EXTRA_URL);
        String cretername = intent.getStringExtra(EXTRA_CREATOR);
        String likes =intent.getStringExtra(EXTRA_LIKES);

        ImageView imageView=findViewById(R.id.detailimage_view);
        TextView textView=findViewById(R.id.detailcreatertext_view);
        TextView textView1=findViewById(R.id.detailliketext_view);

        Picasso.get().load(imgurls).fit().centerInside().into(imageView);
        textView.setText(cretername);
        textView1.setText("likes"+likes);
    }
}