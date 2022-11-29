package com.anton.proyectorsu.Vista.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anton.proyectorsu.R;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class NewImageActivity extends AppCompatActivity {

    private ImageView fullImageView;
    private TextView txt_new_activity, txt_descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        fullImageView = findViewById(R.id.fullImageView);

        Glide.with(this).load(getIntent().getStringExtra("Image@#")).into(fullImageView);

        //Casteo mis variables
        txt_new_activity = findViewById(R.id.txt_new_activity);
        txt_new_activity.setText(getIntent().getStringExtra("nombre@#"));
        txt_descripcion = findViewById(R.id.txt_descripcion);
        txt_descripcion.setText(getIntent().getStringExtra("descripcion@#"));

    }
}