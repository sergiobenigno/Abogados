package com.example.benigno.sqlitecon2tablas;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.Serializable;

public class Abogado{
    int numero;
    String nombre, telefono;
    float sueldo;
    BaseDatosSQLITE base;

    public Abogado(int nc, String n, String t, float s){
        numero = nc;
        nombre = n;
        telefono = t;
        sueldo = s;
    }
    public Abogado(Activity activity){
        base = new BaseDatosSQLITE(activity,"buffete",null,1);
    }

    public boolean insertar(Abogado abogado){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("NOMBRE",abogado.nombre);
            data.put("TELEFONO",abogado.telefono);
            data.put("SUELDO",abogado.sueldo);
            long res = tabla.insert("ABOGADO","IDABOGADO",data);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            Log.e("ERROR: ",e.getMessage());
            return false;
        }
        return true;
    }

    public Abogado consultar(String telefono){
        Abogado ab=null;
        try{
            SQLiteDatabase tabla = base.getReadableDatabase();
            String SQL = "SELECT * FROM ABOGADO WHERE TELEFONO=?";
            String claves[] = {telefono};

            Cursor c = tabla.rawQuery(SQL,claves);
            if(c.moveToFirst()){
                ab = new Abogado(c.getInt(0),c.getString(1),c.getString(2),c.getFloat(3));
            }
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return ab;
    }

    public Abogado[] consulta(){
        Abogado[] resultado=null;
        try{
            SQLiteDatabase tabla = base.getReadableDatabase();
            String SQL = "SELECT * FROM ABOGADO";


            Cursor c = tabla.rawQuery(SQL,null);
            if(c.moveToFirst()){
                resultado = new Abogado[c.getCount()];
                int i=0;
                do {
                    resultado[i++] = new Abogado(c.getInt(0), c.getString(1), c.getString(2), c.getFloat(3));
                }while(c.moveToNext());
            }
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return resultado;
    }

    public boolean eliminar(Abogado abogado){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            String[] data = {""+abogado.numero};
            long res = tabla.delete("ABOGADO","IDABOGADO=?",data);
            tabla.close();
            if(res==0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    public boolean actualizar(Abogado abogado){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("NOMBRE",abogado.nombre);
            data.put("TELEFONO",abogado.telefono);
            data.put("SUELDO",abogado.sueldo);
            String[] clave = {""+abogado.numero};
            long res = tabla.update("ABOGADO",data,"IDABOGADO=?",clave);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }
}
