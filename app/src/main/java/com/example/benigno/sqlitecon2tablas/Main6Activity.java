package com.example.benigno.sqlitecon2tablas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Main6Activity extends AppCompatActivity {
    EditText id, descripcion, cliente, fecha;
    Spinner idabogado;
    Button modificar, eliminar, regresar;
    Abogado[] abogados;
    Expediente expediente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


    }
}
