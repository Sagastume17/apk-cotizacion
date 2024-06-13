package com.example.camaras;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VER extends AppCompatActivity {

    RecyclerView listarmostrar;
    ArrayList<Mostrar> listaArrayMostrar;
    ListaMostrarAdapter adapter;

    EditText etBuscarCliente; // Campo de texto para la búsqueda


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver);

        listarmostrar = findViewById(R.id.listamostra);
        listarmostrar.setLayoutManager(new LinearLayoutManager(this));
        Ddguardar dbguardar = new Ddguardar(VER.this);
        listaArrayMostrar = dbguardar.mostrarDatos(); // Asegúrate de que este método retorne la lista completa.
        adapter = new ListaMostrarAdapter(listaArrayMostrar, new Helper(this));
        listarmostrar.setAdapter(adapter);

        etBuscarCliente = findViewById(R.id.editTextCliente);


        EditText etBuscarCliente = findViewById(R.id.editTextCliente);
        etBuscarCliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String textoCliente = s.toString();
                adapter.filtrarPorCliente(textoCliente);
            }
        });

    }
}