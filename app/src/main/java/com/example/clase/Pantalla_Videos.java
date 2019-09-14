package com.example.clase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.VideoView;

import java.util.ArrayList;

public class Pantalla_Videos extends AppCompatActivity {
    public int numeroVideoActual = 0;
    public ArrayList<Uri> galeria = new ArrayList<Uri>();
    public VideoView videoActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__videos);
        videoActual = findViewById(R.id.Video);
    }
    static final int REQUEST_VIDEO_CAPTURE = 1;

    public void tomarVideo(View view) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();
            galeria.add(videoUri);
            videoActual.setMediaController(new MediaController(this));
            videoActual.setVideoURI(videoUri);
            videoActual.requestFocus();
            videoActual.start();
            numeroVideoActual=galeria.size()-1;
        }
    }
    public void videoSiguiente(View view){
        if(numeroVideoActual>=galeria.size()-1){
            Toast.makeText(getApplicationContext(),"No hay video siguiente",Toast.LENGTH_LONG).show();
        }
        else {
            numeroVideoActual++;
            videoActual.setMediaController(new MediaController(this));
            videoActual.setVideoURI(galeria.get(numeroVideoActual));
            videoActual.requestFocus();
            videoActual.start();
        }
    }
    public void videoAnterior(View view){
        if(numeroVideoActual<=0){
            Toast.makeText(getApplicationContext(),"No hay video anterior",Toast.LENGTH_LONG).show();
        }
        else {
            numeroVideoActual--;
            videoActual.setMediaController(new MediaController(this));
            videoActual.setVideoURI(galeria.get(numeroVideoActual));
            videoActual.requestFocus();
            videoActual.start();
        }
    }
}
