package com.example.clase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pantalla_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
    }
    public void foto(View view){
        Intent intent = new Intent(getApplicationContext(),Pantalla_Videos.class) ;
        startActivity(intent);
    }
    public void video(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class) ;
        startActivity(intent);
    }
}
