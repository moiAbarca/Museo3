package com.example.moi.museo3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Iterator;
import java.util.List;

public class ListarExposiciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_exposiciones);
    }

    public void ListarExposiciones2(View view)
    {
        NegocioExposiciones auxNegocio = new NegocioExposiciones(this);
        List<claseExposicion> auxLista = auxNegocio.retornaExpo();
        String[] listaString =  new String[auxLista.size()];
        Iterator iter = auxLista.iterator();

        int pos = 0;

        while(iter.hasNext())
        {
            claseExposicion auxExpo = new claseExposicion();
            auxExpo = (claseExposicion) iter.next();
            listaString[pos] = auxExpo.getId() + " " + auxExpo.getNombre()+ " " + auxExpo.getMuseo();
            pos++;
        }

        //Rescatamos el ListView de la activity
        ListView auxlistView = (ListView) findViewById(R.id.lstListarExposiciones);
        auxlistView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));
    }
}
