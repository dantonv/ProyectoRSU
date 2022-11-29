package com.anton.proyectorsu.Vista.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.anton.proyectorsu.R;
import com.bumptech.glide.Glide;

public class NewImageActivity extends AppCompatActivity {

    private ImageView fullImageView;
    private Button cotizar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        fullImageView = findViewById(R.id.fullImageView);

        Glide.with(this).load(getIntent().getStringExtra("Image@#")).into(fullImageView);
    }
}