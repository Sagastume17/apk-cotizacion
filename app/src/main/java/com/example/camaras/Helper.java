package com.example.camaras;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cotizacion2.db";
    public static final String TABLE_COTIZACION = "cotizacion2";
    public static final String COL_CLIENTE = "cliente";
    public static final String COL_TELEFONO = "telefono";
    public static final String COL_FECHA = "fecha";
    public static final String COL_DIRECCION = "direccion";
    public static final String COL_DESCRIPCION = "descripcion";
    public static final String COL_TOTAL = "total";
    public static final String COL_ESTADO = "estado";

    public Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + TABLE_COTIZACION + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_CLIENTE + " TEXT NOT NULL," +
                COL_TELEFONO + " TEXT NOT NULL," +
                COL_FECHA + " TEXT NOT NULL," +
                COL_DIRECCION + " TEXT NOT NULL," +
                COL_DESCRIPCION + " TEXT NOT NULL," +
                COL_TOTAL + " FLOAT NOT NULL," +
                COL_ESTADO + " INTEGER NOT NULL DEFAULT 0)"; // Estado predeterminado: 0
        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COTIZACION);
        onCreate(sqLiteDatabase);
    }


    // Método para actualizar el estado de un registro a 1
    @SuppressLint("Range")
    public void actualizarEstado(int id, int nuevoEstado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ESTADO, nuevoEstado);
        String whereClause = "ID = ?";
        String[] whereArgs = {String.valueOf(id)};

        try {
            int rowsAffected = db.update(TABLE_COTIZACION, values, whereClause, whereArgs);
            if (rowsAffected > 0) {
                Log.d("Helper", "Actualización exitosa para el ID: " + id);
            } else {
                Log.d("Helper", "No se encontró el registro con ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void eliminarCotizacion(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COTIZACION, "ID = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    public ArrayList<Mostrar> obtenerTodosLosMostrar() {
        ArrayList<Mostrar> lista = new ArrayList<>();
        // Abrir base de datos para lectura
        SQLiteDatabase db = this.getReadableDatabase();
        // Consulta para seleccionar todos los registros
        Cursor cursor = db.rawQuery("SELECT * FROM tablaMostrar WHERE estado = 1", null);

        if (cursor.moveToFirst()) {
            do {
                Mostrar mostrar = new Mostrar();
                mostrar.setId(cursor.getInt(cursor.getColumnIndex("id")));
                mostrar.setCliente(cursor.getString(cursor.getColumnIndex("cliente")));
                mostrar.setTelefono(cursor.getString(cursor.getColumnIndex("telefono")));
                mostrar.setFecha(cursor.getString(cursor.getColumnIndex("fecha")));
                mostrar.setDireccion(cursor.getString(cursor.getColumnIndex("direccion")));
                mostrar.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                mostrar.setTotal(cursor.getString(cursor.getColumnIndex("total")));
                mostrar.setEstado(cursor.getInt(cursor.getColumnIndex("estado")));
                lista.add(mostrar);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }


}
