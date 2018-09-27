package com.example.benigno.sqlitecon2tablas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    EditText id, descripcion, cliente, fecha;
    Spinner idabogado;
    Button guardar, regresar;
    Abogado[] abogados;
    Expediente expediente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        id = findViewById(R.id.idexpediente);
        descripcion = findViewById(R.id.descripcion);
        cliente = findViewById(R.id.cliente);
        fecha = findViewById(R.id.fecha);
        idabogado = findViewById(R.id.abogado);
        expediente = new Expediente(this);

        guardar = findViewById(R.id.guardarexpediente);
        regresar = findViewById(R.id.regresarexpediente);

        abogados = new Abogado(this).consulta();
        if(abogados.length==0){
            Toast.makeText(this,"NO HAY ABOGADOS, CAPTURE PRIMERO",Toast.LENGTH_LONG).show();
            guardar.setEnabled(false);
            idabogado.setEnabled(false);
            return;
        }
        String[] nombres = new String[abogados.length];
        for(int i=0; i<nombres.length; i++){
            nombres[i] = abogados[i].nombre;
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        idabogado.setAdapter(adaptador);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = idabogado.getSelectedItemPosition();
                Expediente ex = new Expediente(id.getText().toString(),descripcion.getText().toString(),
                        cliente.getText().toString(), fecha.getText().toString(), abogados[pos].numero);
                boolean respuesta = expediente.insertar(ex);
                if(respuesta) {
                    Toast.makeText(Main5Activity.this,"Se pudo insertar",
                            Toast.LENGTH_LONG).show();
                    id.setText("");descripcion.setText("");cliente.setText("");
                    fecha.setText("");
                } else {
                    Toast.makeText(Main5Activity.this,"ERROR AL INSERTAR",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
