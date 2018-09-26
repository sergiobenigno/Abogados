package com.example.benigno.sqlitecon2tablas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   EditText nombre, telefono, sueldo;
   Button guardar;
   Abogado a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.editText);
        telefono = findViewById(R.id.editText2);
        sueldo = findViewById(R.id.editText3);

        guardar = findViewById(R.id.guardarabogado);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = new Abogado(MainActivity.this);
                boolean res = a.insertar(new Abogado(0,nombre.getText().toString(),
                        telefono.getText().toString(), Float.parseFloat(sueldo.getText().toString())));

                if(res) {
                    Toast.makeText(MainActivity.this,"Se pudo insertar",
                            Toast.LENGTH_LONG).show();
                    nombre.setText("");
                    telefono.setText("");
                    sueldo.setText("");
                } else {
                    Toast.makeText(MainActivity.this,"ERROR AL INSERTAR",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}
