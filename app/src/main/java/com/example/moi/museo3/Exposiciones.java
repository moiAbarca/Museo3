package com.example.moi.museo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Exposiciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposiciones);
    }

    public void eliminarExposicion(View view)
    {
        try
        {
            EditText auxId = (EditText) findViewById(R.id.txtIdExpo);

            String id = auxId.getText().toString();

            NegocioExposiciones auxNegocio = new NegocioExposiciones(this);
            auxNegocio.eliminarExpo(id);
            this.Mensaje("Exposición Eliminada");
            auxId.setText("");


        }catch (Exception ex) {
            this.Mensaje("Error al eliminar la exposición" + ex.getMessage());
        }
    }

    public void ListarExposicion(View view)
    {
        Intent intent = new Intent(this, ListarExposiciones.class);
        startActivity(intent);
    }

    public void actualizarExposicion(View view)
    {
        try
        {
            EditText auxId = (EditText) findViewById(R.id.txtIdExpo);

            String id = auxId.getText().toString();
            claseExposicion auxExpo = new claseExposicion();

            NegocioExposiciones auxNegocio = new NegocioExposiciones(this);
            auxExpo = auxNegocio.buscarExpo(id);
            auxNegocio.actualizarExpo(auxExpo);
            this.Mensaje("Exposición Actualizada");
            auxId.setText("");


        }catch (Exception ex) {
            this.Mensaje("Error al actualizar el museo" + ex.getMessage());
        }
    }

    public void guardarExposicion(View view) {
        try
        {
            EditText auxId = (EditText) findViewById(R.id.txtIdExpo);
            EditText auxNombre = (EditText) findViewById(R.id.txtNombreExposicion);
            EditText auxMuseo = (EditText) findViewById(R.id.txtMuseoExposicion);


            claseExposicion auxeExpo = new claseExposicion();
            auxeExpo.setId(auxId.getText().toString());
            auxeExpo.setNombre(auxNombre.getText().toString());
            auxeExpo.setMuseo(auxMuseo.getText().toString());

            NegocioExposiciones auxNegocio = new NegocioExposiciones(this);
            auxNegocio.insertarDatos(auxeExpo);
            this.Mensaje("Museo Guardado");
            auxId.setText("");
            auxNombre.setText("");
            auxMuseo.setText("");

        }catch (Exception ex)
        {
            this.Mensaje("Error al guardar el museo" + ex.getMessage());
        }
    }

    public void Mensaje(String texto)
    {

        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}
