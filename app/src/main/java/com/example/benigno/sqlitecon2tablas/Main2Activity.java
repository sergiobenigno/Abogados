package com.example.benigno.sqlitecon2tablas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText clave;
    Button buscar;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lista);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this, Main5Activity.class);
                startActivity(i);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.capturaabogado:
                Intent i = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.detalleabogado:
                Intent i2 = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(i2);
                break;
        }
        return true;
    }

    protected void onStart(){
        super.onStart();
        Expediente expe = new Expediente(this);
        Expediente expedientes[] = expe.consulta();
        if(expedientes.length==0){
            Toast.makeText(this,"NO HAY EXPEDIENTES",Toast.LENGTH_LONG).show();
            return;
        }
        String NoExp[] = new String[expedientes.length];
        for(int i=0; i<NoExp.length; i++){
            NoExp[i] = expedientes[i].id+"\n"+expedientes[i].cliente;
        }
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,NoExp);
        lista.setAdapter(adap);

    }

}
