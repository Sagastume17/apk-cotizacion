package com.example.camaras;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

    // Método para insertar datos con estado predeterminado de 0
    public long insertaDatos(String cliente, String telefono, String fecha, String direccion, String descripcion, float total) {
        long id = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COL_CLIENTE, cliente);
            values.put(COL_TELEFONO, telefono);
            values.put(COL_FECHA, fecha);
            values.put(COL_DIRECCION, direccion);
            values.put(COL_DESCRIPCION, descripcion);
            values.put(COL_TOTAL, total);
            values.put(COL_ESTADO, 0); // Estado predeterminado: 0

            id = db.insert(TABLE_COTIZACION, null, values);
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    // Método para actualizar el estado de un registro a 1
    public void actualizarEstado(int id, int nuevoEstado) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COL_ESTADO, nuevoEstado);

            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};

            db.update(TABLE_COTIZACION, values, whereClause, whereArgs);
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}



