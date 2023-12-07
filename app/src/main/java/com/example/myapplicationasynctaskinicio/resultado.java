package com.example.myapplicationasynctaskinicio;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class resultado extends AppCompatActivity {

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        result = findViewById(R.id.etResult);
        String nameUser = getIntent().getStringExtra("usuario");
        result.setText("Bienvenido "+nameUser);
    }
}