package com.example.myapplicationasynctaskinicio;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button iniciar;
    private EditText user,pass;
    private ProgressBar pBar;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciar = findViewById(R.id.btnIniciar);
        user = findViewById(R.id.etUser);
        pass = findViewById(R.id.etPass);
        pBar = findViewById(R.id.progressBar);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute(user.getText().toString());
            }
        });
    }
    class Task extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute(){
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    counter++;
                    pBar.setProgress(counter);
                    if (counter==100){
                        timer.cancel();
                        pBar.setVisibility(View.INVISIBLE);
                        counter = 0;
                    }
                }
            };
            timer.schedule(timerTask,50,50);
            pBar.setVisibility(View.VISIBLE);
            iniciar.setEnabled(false);
        }
        @Override
        protected String doInBackground(String... strings){
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return strings[0];
        }
        @Override
        protected void onPostExecute(String s){
            pBar.setVisibility(View.VISIBLE);
            iniciar.setEnabled(true);
            Intent i = new Intent(MainActivity.this, resultado.class);
            i.putExtra("usuario",user.getText().toString());
            startActivity(i);
        }
    }
}