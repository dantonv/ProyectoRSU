package com.anton.proyectorsu.Vista.MenuPrincipalView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anton.proyectorsu.Vista.ImageView.VistaImage;
import com.anton.proyectorsu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VistaPrincipal extends AppCompatActivity {
    Button btn_productos;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        //Instanciamos las dependencias del Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Casteamos variables
        btn_productos = findViewById(R.id.btn_productos);

        //Creo mi Bienvenido al usuario ingresado
        mDatabase.child("Usuarios").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(VistaPrincipal.this, "Bienvenido: "+dataSnapshot.child("nombre").getValue(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Creo mi accion al boton

        btn_productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(VistaPrincipal.this, VistaImage.class);
                startActivity(intent);
                finish();
            }
        });

    }
}