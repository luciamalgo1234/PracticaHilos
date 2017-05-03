package com.example.alonso.busquedalibros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText IngresoLibros;
    private TextView autor,titulo;
    private Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IngresoLibros = (EditText)findViewById(R.id.EditText1);
        autor = (TextView)findViewById(R.id.Autor);
        titulo = (TextView)findViewById(R.id.Titutlo);
        buscar = (Button)findViewById(R.id.Boton1);


    }
    public void searchBooks(){
        String consulta = IngresoLibros.getText().toString();
        new FetchBook(titulo,autor).execute((Runnable) IngresoLibros);

    }
}

