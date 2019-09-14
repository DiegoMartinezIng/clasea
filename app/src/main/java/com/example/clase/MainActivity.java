package com.example.clase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ImageView imagen;
    ArrayList<Bitmap> galeria = new ArrayList<>();
    public int foto_actual = 0;
    static final int request_image_capture = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = findViewById(R.id.imagen);
    }
    public void takePicture(View vista){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity((getPackageManager()))!=null){
            startActivityForResult(takePictureIntent,request_image_capture);
        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode == request_image_capture && resultCode == RESULT_OK){
            Bundle extas = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extas.get("data");
            imagen.setImageBitmap(imageBitmap);
            galeria.add(imageBitmap);
            foto_actual= galeria.size()-1;
        }
    }
    public void foto_anterior(View view){
        if(foto_actual<=0){
            Toast.makeText(getApplicationContext(),"No hay foto anterior",Toast.LENGTH_LONG).show();
        }
        else {
            foto_actual--;
            imagen.setImageBitmap(galeria.get(foto_actual));
        }
    }
    public void foto_siguiente(View view){
        if(foto_actual>=galeria.size()-1){
            Toast.makeText(getApplicationContext(),"No hay foto siguiente",Toast.LENGTH_LONG).show();
        }
        else {
            foto_actual++;
            imagen.setImageBitmap((galeria.get(foto_actual)));
        }
    }
}
