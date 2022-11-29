package com.anton.proyectorsu.Presentador.RegistroPresenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.anton.proyectorsu.Modelo.Usuario;
import com.anton.proyectorsu.Vista.MenuPrincipalView.VistaPrincipal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class PresentadorRegistro {

    private Context mContext;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String TAG = "PresentadorRegistro";

    public PresentadorRegistro(Context mContext, FirebaseAuth mAuth, DatabaseReference mDatabase) {

        this.mContext = mContext;
        this.mAuth = mAuth;
        this.mDatabase = mDatabase;
    }

    public void RegistroUsuario(final String correo, String password,final String nombre,
                                String apellido, String nueva_password){

        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Registrando Usuario");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //Aqui creo mi objeto de tipo usuario-instancia
        Usuario usuario = new Usuario(nombre, apellido, correo, password, nueva_password);
        //Creo mi metodo de servicios Firebase (Autentication)

        mAuth.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener((Activity) mContext,(OnCompleteListener<AuthResult>) task ->{
                    if(task.isSuccessful()){
                        Log.d(TAG,"createUserWithEmail: Registrado exitosamente" );
                        progressDialog.dismiss();


                        //Creo mi objeto de tipo usuario
                        Map<String,Object> crearUsuario = new HashMap<>();
                        crearUsuario.put("nombre", usuario.getNombre());
                        crearUsuario.put("apellido",usuario.getApellido());
                        crearUsuario.put("correo", usuario.getCorreo());
                        crearUsuario.put("contraseña", usuario.getContraseña());
                        crearUsuario.put("nueva contraseña", usuario.getConstraseña_secundaria());
                        mDatabase.child("Usuarios").child(task.getResult().getUser().getUid()).updateChildren(crearUsuario);

                        //LLamo a mi intent
                        Intent intent = new Intent(mContext, VistaPrincipal.class);
                        mContext.startActivity(intent);
                        ((Activity) mContext).finish();

                    }else{
                        progressDialog.dismiss();
                        Log.d(TAG, "createUserWithEmail: No se puedo registrar al usuario", task.getException());
                        Toast.makeText(mContext,"Autentificacion Fallida", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
