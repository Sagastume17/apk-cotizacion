package com.example.camaras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnp;
    Button btnp1;

    Button btnp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnp = (Button) findViewById(R.id.btnp);
        btnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Cotizar.class);
                startActivity(intent);
            }
        });

        btnp1 = (Button) findViewById(R.id.btnp1);
        btnp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VER.class);
                startActivity(intent);
            }
        });

        btnp2 = (Button) findViewById(R.id.btnp2);
        btnp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VER2.class);
                startActivity(intent);
            }
        });
    }


}
