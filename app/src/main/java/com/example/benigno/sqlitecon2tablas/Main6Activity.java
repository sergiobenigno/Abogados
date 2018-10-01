package com.example.benigno.sqlitecon2tablas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

        id = findViewById(R.id.idexpediente2);
        descripcion = findViewById(R.id.descripcion2);
        cliente = findViewById(R.id.cliente2);
        fecha = findViewById(R.id.fecha2);
        idabogado = findViewById(R.id.abogado2);
        modificar = findViewById(R.id.modificarexpediente);
        eliminar = findViewById(R.id.eliminarexpediente);
        regresar = findViewById(R.id.patras);


        String idx = getIntent().getExtras().getString("id");
        String dx= getIntent().getExtras().getString("descripcion");
        String cx = getIntent().getExtras().getString("cliente");
        String fx = getIntent().getExtras().getString("fecha");
        int idax = getIntent().getExtras().getInt("idabogado");

        id.setText(idx);
        descripcion.setText(dx);
        cliente.setText(cx);
        fecha.setText(fx);
        idabogado.setPrompt(""+idabogado);


        abogados = new Abogado(this).consulta();
        if(abogados.length==0){
            Toast.makeText(this,"NO HAY ABOGADOS, CAPTURE PRIMERO",Toast.LENGTH_LONG).show();
            modificar.setEnabled(false);eliminar.setEnabled(false);
            idabogado.setEnabled(false);
            return;
        }
        final String[] nombres = new String[abogados.length];
        for(int i=0; i<nombres.length; i++){
            nombres[i] = abogados[i].nombre;
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        idabogado.setAdapter(adaptador);
        expediente = new Expediente(this);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expediente.eliminar(new Expediente(id.getText().toString(),
                        descripcion.getText().toString(),"","",0))){
                    Toast.makeText(Main6Activity.this,"SE BORRO EXPEDIENTE",
                            Toast.LENGTH_LONG).show();
                    idabogado.setEnabled(false);
                    descripcion.setEnabled(false);
                    cliente.setEnabled(false);
                    fecha.setEnabled(false);
                    idabogado.setEnabled(false);
                    modificar.setEnabled(false);
                    eliminar.setEnabled(false);
                } else {
                    Toast.makeText(Main6Activity.this,"UPS!!!, NO SE BORRO EXPEDIENTE",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expediente.actualizar(new Expediente(id.getText().toString(),
                        descripcion.getText().toString(),cliente.getText().toString(),
                        fecha.getText().toString(),abogados[idabogado.getSelectedItemPosition()].numero))){
                    Toast.makeText(Main6Activity.this,"SE BORRO EXPEDIENTE",
                            Toast.LENGTH_LONG).show();
                    idabogado.setEnabled(false);
                    descripcion.setEnabled(false);
                    cliente.setEnabled(false);
                    fecha.setEnabled(false);
                    idabogado.setEnabled(false);
                    modificar.setEnabled(false);
                    eliminar.setEnabled(false);
                } else {
                    Toast.makeText(Main6Activity.this,"UPS!!!, NO SE BORRO EXPEDIENTE",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
