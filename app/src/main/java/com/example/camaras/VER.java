package com.example.camaras;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VER extends AppCompatActivity {

    RecyclerView listarmostrar;
    ArrayList<Mostrar>listaArrayMostrar;

    TextView view_total1;

    Button button7; // Agrega el botón de regreso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver);

        listarmostrar = findViewById(R.id.listamostra);
        listarmostrar.setLayoutManager(new LinearLayoutManager(this));

        Ddguardar dbguardar = new Ddguardar(VER.this);

        listaArrayMostrar = new ArrayList<>();
        ListaMostrarAdapter adapter = new ListaMostrarAdapter(dbguardar.mostrarDatos());
        listarmostrar.setAdapter(adapter);


    }


    public void llamar(){

    }
    public void regresar(){

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cierra la actividad actual y vuelve a MainActivity
                finish();
            }
        });
    }

}