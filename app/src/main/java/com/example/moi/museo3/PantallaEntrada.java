package com.example.moi.museo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PantallaEntrada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_entrada);
    }

    public void MantenedorMuseos(View view)
    {
        Intent intent = new Intent(this, MantenedorMuseos.class);
        //intent.putExtra()
        startActivity(intent);
    }

    public void Reportes(View view)
    {
        Intent intent = new Intent(this, Reportes.class);
        //intent.putExtra()
        startActivity(intent);

    }

    public void MantenedorUsuario(View view)
    {
        Intent intent = new Intent(this, MantenedorUsuario.class);
        //intent.putExtra()
        startActivity(intent);

    }

    public void Exposiciones(View view)
    {
        Intent intent = new Intent(this, Exposiciones.class);
        //intent.putExtra()
        startActivity(intent);

    }

    public void Mensaje(String texto)
    {

        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}
