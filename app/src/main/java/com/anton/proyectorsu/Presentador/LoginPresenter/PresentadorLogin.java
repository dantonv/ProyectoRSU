package com.anton.proyectorsu.Presentador.LoginPresenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.anton.proyectorsu.Vista.MenuPrincipalView.VistaPrincipal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
//PRESENTADOR = LOGICA
public class PresentadorLogin {

    //creamos el contexto que viaja a la vista
    //string para saber donde estoy
    //servicios de cloud
    private Context mContext;
    private String TAG = "PresentardorLogin";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    //creamos mi constructor

    public PresentadorLogin(Context mContext, FirebaseAuth mAuth, DatabaseReference mDatabase){

        this.mContext = mContext;
        this.mAuth = mAuth;
        this.mDatabase = mDatabase;

    }
    //Creo mi metodo para mi logica de los eventos

    public void LoginUsuario(String email, String password){

        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Ingresando....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //Creo mi metodo de servicios Firebase (Autentication)
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) mContext,(OnCompleteListener<AuthResult>) task ->{

                    if(task.isSuccessful()){
                        Log.d(TAG,"signInWithEmailAndPassword: Exitoso" );
                        progressDialog.dismiss();
                        //llamo a mi bd de firebase y registro mi usuaurio
                        mDatabase.child("Usuarios").child(task.getResult().getUser().getUid()).child("Titulo").setValue("Lista de Usuarios");
                        //LLamo a mi intent
                        Intent intent = new Intent(mContext, VistaPrincipal.class);
                        mContext.startActivity(intent);
                        ((Activity) mContext).finish();
                    }else{
                        progressDialog.dismiss();
                        Log.d(TAG, "signInWithEmailAndPassword: Fallido", task.getException());
                        Toast.makeText(mContext,"Autentificacion Fallida", Toast.LENGTH_SHORT).show();
                    }

                });

    }

}



















