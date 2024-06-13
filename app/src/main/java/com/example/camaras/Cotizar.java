package com.example.camaras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;

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


        // Configuración del listener para el botón de guardar
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!telefono.getText().toString().matches("[0-9]+")) {
                    // Si contiene caracteres que no son números, muestra un mensaje de error
                    Toast.makeText(Cotizar.this, "El teléfono debe contener solo números", Toast.LENGTH_SHORT).show();
                    return; // Detiene la ejecución del método onClick
                }
                Ddguardar ddguardar = new Ddguardar(Cotizar.this);
                long id = ddguardar.insertaDatos(cliente.getText().toString(), telefono.getText().toString(), fecha.getText().toString(), direccion.getText().toString(), descripcion.getText().toString(), total.getText().toString());

                if (id > 0) {
                    Toast.makeText(Cotizar.this, "REGISTRO GRUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(Cotizar.this, "ERROR AL GUARDAR REGUISTRO", Toast.LENGTH_LONG).show();
                }
                // Lógica para guardar los datos
            }
        });

        // Configuración del listener para el botón de regreso


        // Configuración del listener para el campo de fecha
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();
            }
        });
    }

    private void mostrarDatePicker() {
        final Calendar calendario = Calendar.getInstance();
        int año = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int día = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Cuando se selecciona una fecha, actualizamos el EditText con la fecha seleccionada
                String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                fecha.setText(fechaSeleccionada);
            }
        }, año, mes, día);

        // Mostrar el diálogo de selección de fecha
        datePickerDialog.show();
    }

    private void limpiar() {
        cliente.setText("");
        telefono.setText("");
        fecha.setText("");
        direccion.setText("");
        descripcion.setText("");
        total.setText("");
    }

}