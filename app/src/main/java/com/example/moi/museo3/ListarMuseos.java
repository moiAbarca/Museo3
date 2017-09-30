package com.example.moi.museo3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Iterator;
import java.util.List;

public class ListarMuseos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_museos);
    }

    public void Listar2(View view)
    {
        NegocioMantenedorMuseo auxNegocio = new NegocioMantenedorMuseo(this);
        List<Museos> auxLista = auxNegocio.retornaMuseos();
        String[] listaString =  new String[auxLista.size()];
        Iterator iter = auxLista.iterator();

        int pos = 0;

        while(iter.hasNext())
        {
            Museos auxMuseos = new Museos();
            auxMuseos = (Museos) iter.next();
            listaString[pos] = auxMuseos.getId() + " " + auxMuseos.getNombre()+ " " + auxMuseos.getDireccion()+ " " +auxMuseos.getSalas();
            pos++;
        }

        //Rescatamos el ListView de la activity
        ListView auxlistView = (ListView) findViewById(R.id.lstListarMuseos2);
        auxlistView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));
    }
}
