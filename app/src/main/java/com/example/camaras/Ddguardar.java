package com.example.camaras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Ddguardar extends Helper {

    Context context;

    public Ddguardar(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaDatos(String CLIENTE, String TELEFONO, String FECHA, String DIRECCION, String DESCRIPCION, String TOTAL) {
        long id = 0;
        try {
            Helper helper = new Helper(context);
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("cliente", CLIENTE);
            values.put("telefono", TELEFONO);
            values.put("fecha", FECHA);
            values.put("direccion", DIRECCION);
            values.put("descripcion", DESCRIPCION);
            values.put("total", TOTAL);
            values.put("estado", 0); // Establece el estado como 0 por defecto

            id = db.insert(TABLE_COTIZACION, null, values);
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;

    }


    public ArrayList< Mostrar>mostrarDatos(){
        Helper Helper = new Helper(context);
        SQLiteDatabase db = Helper.getWritableDatabase();

        ArrayList<Mostrar>ListaMostrar = new ArrayList<>();
        Mostrar mostrar = null;
        Cursor cursorMostrar = null;

        cursorMostrar = db.rawQuery(" SELECT * FROM " + TABLE_COTIZACION,null);

        if( cursorMostrar.moveToFirst()){
            do{
                mostrar = new Mostrar();
                mostrar.setId(cursorMostrar.getInt( 0));
                mostrar.setCliente(cursorMostrar.getString( 1));
                mostrar.setTelefono(cursorMostrar.getString( 2));
                mostrar.setFecha(cursorMostrar.getString( 3));
                mostrar.setDireccion(cursorMostrar.getString( 4));
                mostrar.setDescripcion(cursorMostrar.getString( 5));
                mostrar.setTotal(cursorMostrar.getString( 6));
                mostrar.setEstado(cursorMostrar.getInt( 7));
                ListaMostrar.add(mostrar);
            }while (cursorMostrar.moveToNext());
        }
        cursorMostrar.close();

        return ListaMostrar;
    }


}
