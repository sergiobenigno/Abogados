package com.example.benigno.sqlitecon2tablas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatosSQLITE extends SQLiteOpenHelper {
    public BaseDatosSQLITE(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ABOGADO(IDABOGADO INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE VARCHAR(500), TELEFONO VARCHAR(500), SUELDO FLOAT)");
        db.execSQL("CREATE TABLE EXPEDIENTE(IDEXPEDIENTE VARCHAR(200) PRIMARY KEY NOT NULL, DESCRIPCION VARCHAR(500), CLIENTE VARCHAR(500), FECHA DATE, IDABOGADO INTEGER, FOREIGN KEY(IDABOGADO) REFERENCES ABOGADO(IDABOGADO))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
