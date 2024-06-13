package com.example.camaras;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VER2 extends AppCompatActivity {

    RecyclerView listarmostrar2;
    ArrayList<Mostrar> listaArrayMostrar2;
    ListaMostrarAdapter2 adapter2;

    EditText etBuscarCliente; // Campo de texto para la búsqueda

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver2);

        // Inicializar RecyclerView y el adaptador
        listarmostrar2 = findViewById(R.id.listamostra2);
        listarmostrar2.setLayoutManager(new LinearLayoutManager(this));
        Ddguardar dbguardar = new Ddguardar(VER2.this);
        listaArrayMostrar2 = dbguardar.mostrarDatos();
        adapter2 = new ListaMostrarAdapter2(listaArrayMostrar2, new Helper(this));
        listarmostrar2.setAdapter(adapter2);

        // Inicializar EditText y agregar TextWatcher
        etBuscarCliente = findViewById(R.id.editTextCliente);
        etBuscarCliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No hacer nada
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No hacer nada
            }

            @Override
            public void afterTextChanged(Editable s) {
                String textoCliente = s.toString();
                adapter2.filtrarPorCliente(textoCliente);
            }
        });
    }
}
