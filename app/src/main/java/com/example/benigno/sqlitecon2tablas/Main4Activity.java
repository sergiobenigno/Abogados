package com.example.benigno.sqlitecon2tablas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main4Activity extends AppCompatActivity {
    Abogado res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        /*res = new Abogado(this);
        res = (Abogado) getIntent().getSerializableExtra("Abogado");
        setTitle(res.nombre+"  "+res.telefono);*/

        int id= getIntent().getExtras().getInt("id");
        String nombre = getIntent().getExtras().getString("nombre");
        //String telefono = getIntent().getExtras().getString("telefono");
        //float sueldo = getIntent().getExtras().getFloat("sueldo");
        setTitle(nombre);
    }
}
