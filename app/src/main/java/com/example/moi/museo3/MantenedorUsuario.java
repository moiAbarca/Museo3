package com.example.moi.museo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MantenedorUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenedor_usuario);
    }

    public void eliminarUsuario(View view)
    {
        try
        {
            EditText auxRut = (EditText) findViewById(R.id.txtRutUsuario);

            String id = auxRut.getText().toString();

            NegocioMantenedorUsuario auxNegocio = new NegocioMantenedorUsuario(this);
            auxNegocio.eliminarUsuario(id);
            this.Mensaje("Museo Eliminado");
            auxRut.setText("");


        }catch (Exception ex)
        {
            this.Mensaje("Error al eliminar el usuario" + ex.getMessage());
        }
    }

    public void ListarUsuario(View view)
    {
        Intent intent = new Intent(this, ListarUsuarios.class);
        startActivity(intent);
    }

    public void GuardarUsuario(View view)
    {
        try
        {
            EditText auxRut = (EditText) findViewById(R.id.txtRutUsuario);
            EditText auxNombre = (EditText) findViewById(R.id.txtNombreUsuario);
            EditText auxDireccion = (EditText) findViewById(R.id.txtDireccionUsuario);

            Usuarios auxmuseos = new Usuarios();
            auxmuseos.setRut(auxRut.getText().toString());
            auxmuseos.setNombre(auxNombre.getText().toString());
            auxmuseos.setDireccion(auxDireccion.getText().toString());

            NegocioMantenedorUsuario auxNegocio = new NegocioMantenedorUsuario(this);
            auxNegocio.insertarDatos(auxmuseos);
            this.Mensaje("Usuario Guardado");
            auxRut.setText("");
            auxNombre.setText("");
            auxDireccion.setText("");


        }catch (Exception ex)
        {
            this.Mensaje("Error al guardar el usuario" + ex.getMessage());
        }
    }

    public void actualizarUsuario(View view)
    {
        try
        {
            EditText auxRut = (EditText) findViewById(R.id.txtRutUsuario);

            String id = auxRut.getText().toString();
            Usuarios auxmuseos = new Usuarios();

            NegocioMantenedorUsuario auxNegocio = new NegocioMantenedorUsuario(this);
            auxmuseos = auxNegocio.buscarUsuario(id);
            auxNegocio.actualizarUsuarios(auxmuseos);
            this.Mensaje("Usuario Actualizado");
            auxRut.setText("");


        }catch (Exception ex)
        {
            this.Mensaje("Error al actualizar el museo" + ex.getMessage());
        }
    }

    public void Mensaje(String texto)
    {

        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}
