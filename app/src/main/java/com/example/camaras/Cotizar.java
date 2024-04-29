package com.example.camaras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Cotizar extends AppCompatActivity {
    EditText cliente,telefono,fecha,direccion,descripcion,total;
    Button btnguardar;
    Button button7; // Agrega el botón de regreso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cotizar);

        cliente = findViewById(R.id.cliente);
        telefono = findViewById(R.id.telefono);
        fecha = findViewById(R.id.fecha);
        direccion = findViewById(R.id.direccion);
        descripcion = findViewById(R.id.descripcion);
        total = findViewById(R.id.total);
        btnguardar = findViewById(R.id.btnguardar);
        button7 = findViewById(R.id.button7); // Busca el botón de regreso en tu diseño XML

        // Configuración del listener para el botón de guardar
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ddguardar  ddguardar = new Ddguardar(Cotizar.this);
                long id = ddguardar.insertaDatos(cliente.getText().toString(),telefono.getText().toString(),fecha.getText().toString(),direccion.getText().toString(),descripcion.getText().toString(),total.getText().toString());

                if(id > 0){

                    Toast.makeText(Cotizar.this,"REGISTRO GRUARDADO",Toast.LENGTH_LONG).show();
                    limpiar();

                }else {

                    Toast.makeText(Cotizar.this,"ERROR AL GUARDAR REGUISTRO",Toast.LENGTH_LONG).show();
                }
                // Lógica para guardar los datos
            }
        });

        // Configuración del listener para el botón de regreso
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cierra la actividad actual y vuelve a MainActivity
                finish();
            }
        });
    }
private void limpiar(){
    cliente.setText("");
    telefono.setText("");
    fecha.setText("");
    direccion.setText("");
    descripcion.setText("");
    total.setText("");
   // btnguardar.setText("");
    }
}