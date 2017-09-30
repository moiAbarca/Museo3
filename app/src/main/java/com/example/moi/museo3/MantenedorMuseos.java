package com.example.moi.museo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MantenedorMuseos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenedor_museos);
    }

    public void Guardar(View view)
    {
        try
        {
            EditText auxId = (EditText) findViewById(R.id.txtId2Museo);
            EditText auxNombre = (EditText) findViewById(R.id.txtNombre3);
            EditText auxDireccion = (EditText) findViewById(R.id.txtDireccionMuseo);
            EditText auxSalas = (EditText) findViewById(R.id.txtSalasMuseo);

            Museos auxmuseos = new Museos();
            auxmuseos.setId(auxId.getText().toString());
            auxmuseos.setNombre(auxNombre.getText().toString());
            auxmuseos.setDireccion(auxDireccion.getText().toString());
            auxmuseos.setSalas(auxSalas.getText().toString());

            NegocioMantenedorMuseo auxNegocio = new NegocioMantenedorMuseo(this);
            auxNegocio.insertarDatos(auxmuseos);
            this.Mensaje("Museo Guardado");
            auxId.setText("");
            auxNombre.setText("");
            auxDireccion.setText("");
            auxSalas.setText("");

        }catch (Exception ex)
        {
            this.Mensaje("Error al guardar el museo" + ex.getMessage());
        }

    }

    public void eliminar(View view)
    {
        try
        {
            EditText auxId = (EditText) findViewById(R.id.txtId2Museo);

            String id = auxId.getText().toString();

            NegocioMantenedorMuseo auxNegocio = new NegocioMantenedorMuseo(this);
            auxNegocio.eliminarMuseos(id);
            this.Mensaje("Museo Eliminado");
            auxId.setText("");


        }catch (Exception ex)
        {
            this.Mensaje("Error al eliminar el museo" + ex.getMessage());
        }
    }

    public void listar(View view)
    {
        Intent intent = new Intent(this, ListarMuseos.class);
        startActivity(intent);


    }

    public void Mensaje(String texto)
    {

        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    public void actualizar(View view)
    {
        try
        {
            EditText auxId = (EditText) findViewById(R.id.txtId2Museo);

            String id = auxId.getText().toString();
            Museos auxmuseos = new Museos();

            NegocioMantenedorMuseo auxNegocio = new NegocioMantenedorMuseo(this);
            auxmuseos = auxNegocio.buscarMuseos(id);
            auxNegocio.actualizarMuseos(auxmuseos);
            this.Mensaje("Museo Actualizado");
            auxId.setText("");


        }catch (Exception ex)
        {
            this.Mensaje("Error al actualizar el museo" + ex.getMessage());
        }
    }
}
