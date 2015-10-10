package com.example.sharevoice.sharevoice;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
 Button record ;
 Button stopRecordButton;
 Button playButton ;
 Button pauseButton ;
    MediaRecorder myRecorder;
    MediaPlayer myPlayer;
    String outputfile=null;
    String filePath=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       outputfile = Environment.DIRECTORY_MUSIC+"\\"+"mohebtest.3gpp";
        myRecorder = new MediaRecorder();
        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
         filePath = this.getFilesDir().getPath().toString() + "/mohebtest.3gpp";
        myRecorder.setOutputFile(filePath);
        record = (Button) findViewById(R.id.recordButton);
        stopRecordButton = (Button) findViewById(R.id.stopButton);
        playButton = (Button) findViewById(R.id.playButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
record.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        start(v);
    }
});

        stopRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               stop(v);
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(v);
            }
        });

    }
    public void start(View view){
        try {
            myRecorder.prepare();
            myRecorder.start();
            Toast.makeText(MainActivity.this, "Recording Started", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void stop(View view){
        myRecorder.stop();
        myRecorder.release();
        myRecorder=null;
        Toast.makeText(MainActivity.this, "Recording Stopped", Toast.LENGTH_SHORT).show();
    }

    public void play(View view){
        myPlayer = new MediaPlayer();
        try {
            Toast.makeText(MainActivity.this, "Playing The Audio File...", Toast.LENGTH_SHORT).show();
            myPlayer.setDataSource(filePath);
            myPlayer.prepare();
            myPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
