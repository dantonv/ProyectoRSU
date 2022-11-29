package com.anton.proyectorsu.Vista.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.anton.proyectorsu.Modelo.ImageModel;
import com.anton.proyectorsu.Presentador.ImagePresenter.RecyclerImageAdapter;
import com.anton.proyectorsu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VistaImage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ImageModel> imageModelArrayList;
    private RecyclerImageAdapter recyclerImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(VistaImage.this, 2));
        recyclerView.setHasFixedSize(true);

        imageModelArrayList = new ArrayList<>();

        clearAll();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Images");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearAll();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        ImageModel imageModel = new ImageModel();
                        imageModel.setImageurl(dataSnapshot1.getValue().toString());

                        imageModelArrayList.add(imageModel);

                }

                recyclerImageAdapter = new RecyclerImageAdapter(getApplicationContext(), imageModelArrayList);
                recyclerView.setAdapter(recyclerImageAdapter);
                recyclerImageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(VistaImage.this, "Error! ", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void clearAll() {

        if (imageModelArrayList != null){
            imageModelArrayList.clear();

            if (recyclerImageAdapter != null){
                recyclerImageAdapter.notifyDataSetChanged();
            }
        }

        imageModelArrayList = new ArrayList<>();
    }
}