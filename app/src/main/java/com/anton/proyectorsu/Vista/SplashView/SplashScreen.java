package com.anton.proyectorsu.Vista.SplashView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anton.proyectorsu.R;
import com.anton.proyectorsu.Vista.LoginView.VistaLogin;
import com.anton.proyectorsu.Vista.MenuPrincipalView.VistaPrincipal;
import com.anton.proyectorsu.Vista.RegistroView.VistaRegistro;

public class SplashScreen extends AppCompatActivity {

    //Declaracion de Variables
    LinearLayout contenedor_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Orientacion para el celular
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Casteo de Variables
        contenedor_splash = findViewById(R.id.contenedor_splash);

        //Codigo para mi animación
        Animation mianimacion = AnimationUtils.loadAnimation(this,R.anim.bounce);
        contenedor_splash.startAnimation(mianimacion);

        //Creando Hilo

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(SplashScreen.this, VistaLogin.class);
                startActivity(login);
                finish();

            }
        }, 4000);
    }
}