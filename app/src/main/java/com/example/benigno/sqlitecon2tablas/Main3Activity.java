package com.example.benigno.sqlitecon2tablas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main3Activity extends AppCompatActivity {
    ListView listita;
    Abogado ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        listita = findViewById(R.id.listaabogados);
        ab = new Abogado(this);
        listita.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Abogado[] s = ab.consulta();
                AlertDialog.Builder alerta = new AlertDialog.Builder(Main3Activity.this);
                alerta.setTitle("Detalle de "+s[i].nombre)
                        .setMessage("id: "+s[i].numero+"\nSueldo: "+s[i].sueldo+"\nTelefono: "
                        +s[i].telefono+"\n¿Deseas modificar/Eliminar registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent i44 = new Intent(Main3Activity.this, Main4Activity.class);
                                i44.putExtra("id",s[i].numero);
                                i44.putExtra("nombre",s[i].nombre);
                                i44.putExtra("telefono",s[i].telefono);
                                i44.putExtra("sueldo",s[i].sueldo);
                                startActivity(i44);
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }

    protected void onStart(){
        super.onStart();

        Abogado[] s = ab.consulta();
        String nombres[] = new String[s.length];
        for(int i=0; i<nombres.length; i++){
            nombres[i] = s[i].nombre;
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,nombres);
        listita.setAdapter(adaptador);
    }
}
