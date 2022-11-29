package com.anton.proyectorsu.Vista.LoginView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anton.proyectorsu.Presentador.LoginPresenter.PresentadorLogin;
import com.anton.proyectorsu.R;
import com.anton.proyectorsu.Vista.RegistroView.VistaRegistro;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VistaLogin extends AppCompatActivity implements View.OnClickListener {

    private EditText txt_correo, txt_clave;
    private TextView txt_registro;
    private Button btn_ingresar;
    //Llamamos a mi Presentador desde mi Vista
    private PresentadorLogin presentadorLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_login);

        //Instancio mis servicios de firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        presentadorLogin = new PresentadorLogin(this, mAuth, mDatabase);

        //Casteo mis variables
        txt_correo = findViewById(R.id.txt_correo);
        txt_clave = findViewById(R.id.txt_clave);
        btn_ingresar = findViewById(R.id.btn_ingresar);
        txt_registro = findViewById(R.id.txt_registro);

        btn_ingresar.setOnClickListener(this);
        txt_registro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_ingresar://Hacemos la logica del Login

                String email = txt_correo.getText().toString().trim();
                String password = txt_clave.getText().toString().trim();
                presentadorLogin.LoginUsuario(email, password);

                if(TextUtils.isEmpty(email)){
                    txt_correo.setError("Ingrese correo");
                    txt_correo.requestFocus();
                }else if(TextUtils.isEmpty(password)){
                    txt_clave.setError("Ingrese su contraseña");
                    txt_clave.requestFocus();
                }
                break;

            case R.id.txt_registro:
                Intent intent = new Intent(VistaLogin.this, VistaRegistro.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}







































