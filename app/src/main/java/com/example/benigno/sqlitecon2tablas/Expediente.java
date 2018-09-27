package com.example.benigno.sqlitecon2tablas;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class Expediente {
    BaseDatosSQLITE base;
    String id, descripcion, cliente, fecha;
    int idabogado;

    public Expediente(Activity activity){
        base = new BaseDatosSQLITE(activity,"buffete", null, 1);
    }

    public Expediente(String id, String des, String cli, String fec, int abogado){
        this.id = id;
        descripcion = des;
        cliente = cli;
        fecha = fec;
        idabogado = abogado;
    }

    public boolean insertar(Expediente expediente){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("IDEXPEDIENTE",expediente.id);
            data.put("DESCRIPCION",expediente.descripcion);
            data.put("CLIENTE",expediente.cliente);
            data.put("FECHA",expediente.fecha);
            data.put("IDABOGADO",expediente.idabogado);
            long res = tabla.insert("EXPEDIENTE",null,data);
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

    public Expediente[] consulta(){
        Expediente[] resultado=null;
        try{
            SQLiteDatabase tabla = base.getReadableDatabase();
            String SQL = "SELECT * FROM EXPEDIENTE";


            Cursor c = tabla.rawQuery(SQL,null);
            if(c.moveToFirst()){
                resultado = new Expediente[c.getCount()];
                int i=0;
                do {
                    resultado[i++] = new Expediente(c.getString(0), c.getString(1), c.getString(2),
                            c.getString(3),c.getInt(4));
                }while(c.moveToNext());
            }
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return resultado;
    }

    public boolean eliminar(Expediente expediente){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            String[] data = {expediente.id};
            long res = tabla.delete("EXPEDIENTE","IDEXPEDIENTE=?",data);
            tabla.close();
            if(res==0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    public boolean actualizar(Expediente expediente){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("DESCRIPCION",expediente.descripcion);
            data.put("CLIENTE",expediente.cliente);
            data.put("FECHA",expediente.fecha);
            data.put("IDABOGADO",expediente.idabogado);
            String[] clave = {expediente.id};
            long res = tabla.update("EXPEDIENTE",data,"IDEXPEDIENTE=?",clave);
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
