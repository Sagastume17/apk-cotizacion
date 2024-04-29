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

public class VER2 extends AppCompatActivity {

    RecyclerView listarmostrar2;
    ArrayList<Mostrar>listaArrayMostrar2;

    TextView view_total1;

    Button button7; // Agrega el botón de regreso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver2);

        listarmostrar2 = findViewById(R.id.listamostra);
        listarmostrar2.setLayoutManager(new LinearLayoutManager(this));

        Ddguardar dbguardar = new Ddguardar(VER2.this);

        listaArrayMostrar2 = new ArrayList<>();
        ListaMostrarAdapter2 adapter = new ListaMostrarAdapter2(dbguardar.mostrarDatos());
        listarmostrar2.setAdapter(adapter);


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