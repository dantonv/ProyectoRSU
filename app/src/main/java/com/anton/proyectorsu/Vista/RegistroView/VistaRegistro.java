package com.anton.proyectorsu.Vista.RegistroView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anton.proyectorsu.Presentador.RegistroPresenter.PresentadorRegistro;
import com.anton.proyectorsu.R;
import com.anton.proyectorsu.Vista.LoginView.VistaLogin;
import com.anton.proyectorsu.Vista.MenuPrincipalView.VistaPrincipal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VistaRegistro extends AppCompatActivity implements View.OnClickListener {
    //Declaro mis variables
    private EditText txt_nombre, txt_apellido, txt_correo, txt_contra;
    private EditText txt_contra1;
    private Button btn_registrar;
    private TextView txt_volver;
    //Declaramos mi presentador
    private PresentadorRegistro presentadorRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_registro);
        //Lllamo a mis dependencias de Firebase y las instancio
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        //Llamo a mi Presentador REGISTRO
        presentadorRegistro = new PresentadorRegistro(this,mAuth, mDatabase);

        txt_nombre = findViewById(R.id.txt_nombre);
        txt_apellido = findViewById(R.id.txt_apellido);
        txt_correo = findViewById(R.id.txt_correo);
        txt_contra = findViewById(R.id.txt_contra);
        txt_contra1 = findViewById(R.id.txt_contra1);
        btn_registrar = findViewById(R.id.btn_registrar);
        txt_volver = findViewById(R.id.txt_volver);
        btn_registrar.setOnClickListener(this);
        //Creo mis eventos de mis botones
        txt_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(VistaRegistro.this, VistaLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            //Codigo para eliminar los espacios en cada caja de texto
            case R.id.btn_registrar:
                String nombre = txt_nombre.getText().toString().trim();
                String apellido = txt_apellido.getText().toString().trim();
                String correo = txt_correo.getText().toString().trim();
                String password = txt_contra.getText().toString().trim();
                String nueva_password = txt_contra1.getText().toString().trim();

                //Validamos los espacios en blanco para que sean ejecutados
                if(TextUtils.isEmpty(nombre)){
                    txt_nombre.setError("Ingrese nombre");
                    txt_nombre.requestFocus();
                }else if(TextUtils.isEmpty(apellido)){
                    txt_apellido.setError("Ingrese apellido");
                }else if(TextUtils.isEmpty(correo)){
                    txt_correo.setError("Ingrese correo");
                }else if(TextUtils.isEmpty(password)){
                    txt_contra.setError("Ingrese clave");
                }else if(TextUtils.isEmpty(nueva_password)) {
                    txt_contra1.setError("Ingrese nueva clave");
                }else{
                    if (password.equals(nueva_password)){
                        presentadorRegistro.RegistroUsuario(correo,password, nombre, apellido,
                                nueva_password);
                    }else{
                        Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}