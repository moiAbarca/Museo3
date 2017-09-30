package com.example.moi.museo3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Iterator;
import java.util.List;

public class ListarUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
    }

    public void ListarUsuarios(View view)
    {
        NegocioMantenedorUsuario auxNegocio = new NegocioMantenedorUsuario(this);
        List<Usuarios> auxLista = auxNegocio.retornaUsuario();
        String[] listaString =  new String[auxLista.size()];
        Iterator iter = auxLista.iterator();

        int pos = 0;

        while(iter.hasNext())
        {
            Usuarios auxMuseos = new Usuarios();
            auxMuseos = (Usuarios) iter.next();
            listaString[pos] = auxMuseos.getRut() + " " + auxMuseos.getNombre()+ " " + auxMuseos.getDireccion();
            pos++;
        }

        //Rescatamos el ListView de la activity
        ListView auxlistView = (ListView) findViewById(R.id.lstListarUsuarios);
        auxlistView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));
    }
}
