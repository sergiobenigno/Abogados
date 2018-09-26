package com.example.benigno.sqlitecon2tablas;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {
    Abogado abogado;
    EditText cnombre, ctelefono, csueldo;
    int id;
    Button modificar, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        abogado = new Abogado(this);

        id= getIntent().getExtras().getInt("id");
        String nombre = getIntent().getExtras().getString("nombre");
        String telefono = getIntent().getExtras().getString("telefono");
        float sueldo = getIntent().getExtras().getFloat("sueldo");

        cnombre = findViewById(R.id.abogadonombre);
        ctelefono = findViewById(R.id.abogadotelefono);
        csueldo = findViewById(R.id.abogadosueldo);
        modificar = findViewById(R.id.abogadomodificar);
        eliminar = findViewById(R.id.abogadoeliminar);

        cnombre.setText(nombre);
        ctelefono.setText(telefono);
        csueldo.setText(""+sueldo);

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respuesta = abogado.actualizar(new Abogado(id,cnombre.getText().toString(),ctelefono.getText().toString(),
                        Float.parseFloat(csueldo.getText().toString())));
                if(respuesta){
                    AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                    a.setTitle("Exito").setMessage("se actualizó").show();
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                    a.setTitle("ERROR").setMessage("no se pudo actualizar").show();
                }
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respuesta = abogado.eliminar(new Abogado(id,cnombre.getText().toString(),ctelefono.getText().toString(),
                        Float.parseFloat(csueldo.getText().toString())));
                if(respuesta){
                    AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                    a.setTitle("Exito").setMessage("se eliminó").show();
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(Main4Activity.this);
                    a.setTitle("ERROR").setMessage("no se pudo eliminar").show();
                }

            }
        });
    }
}
