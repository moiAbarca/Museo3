package com.example.moi.museo3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moi on 29-09-17.
 */

public class NegocioMantenedorMuseo extends SQLiteOpenHelper
{
    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATO = "museos.db";
    private static final String TABLA_MUSEO = "CREATE TABLE museos" +
            "(Id TEXT PRIMARY KEY, nombre TEXT, direccion TEXT, salas TEXT)";

    public NegocioMantenedorMuseo(Context context)
    {
        super(context, NOMBRE_BASEDATO, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLA_MUSEO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + TABLA_MUSEO);
        onCreate(db);
    }

    //insertar datos
    public void insertarDatos(Museos museos) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO museos (Id, nombre, direccion, salas) VALUES ("
                    + "'" + museos.getId() +
                    "', '" + museos.getNombre() +
                    "','" + museos.getDireccion()+
                    "','" + museos.getSalas()+
                    "');");
        }
        db.close();
    }//fin insertar

    //método actualizar museos
    public void actualizarMuseos(Museos museos)
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("UPDATE museos SET "
                    + "nombre ='" + museos.getNombre()
                    + "', direccion = '" + museos.getDireccion()
                    + "', salas = '" + museos.getSalas()
                    + "' WHERE Id = '" + museos.getId()+"' ;");
        }
        db.close();
    }

    //eliminar museos
    public void eliminarMuseos(String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("DELETE FROM  museos "
                    + " WHERE Id = '" + id + "'");
        }
        db.close();
    }

    //elimiar todos los museos
    public void eliminarTodosLosMuseos()
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("DELETE FROM  museos; ");
        }
        db.close();
    }

    //Retorna consulta clientes
    public List<Museos> retornaMuseos()
    {
        SQLiteDatabase db = getWritableDatabase();
        List<Museos> auxListaMuseos = new ArrayList<>();

        Cursor auxCusor = db.rawQuery("SELECT * FROM museos;", null);

        auxCusor.moveToFirst();

        do
        {
            Museos auxMuseos = new Museos();
            auxMuseos.setId(auxCusor.getString(0));
            auxMuseos.setNombre(auxCusor.getString(1));
            auxMuseos.setDireccion(auxCusor.getString(2));
            auxMuseos.setSalas(auxCusor.getString(3));

            auxListaMuseos.add(auxMuseos);

        }while (auxCusor.moveToNext());
        auxCusor.close();
        db.close();
        return auxListaMuseos;
    }

    //buscar museos por ID
    public Museos buscarMuseos(String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        Museos auxMuseos = new Museos();

        Cursor auxCusor = db.rawQuery("SELECT * FROM museos WHERE Id = '" + id +"';", null);
        auxCusor.moveToFirst();

        if(auxCusor != null)
        {
            auxMuseos.setId(auxCusor.getString(0));
            auxMuseos.setNombre(auxCusor.getString(1));
            auxMuseos.setDireccion(auxCusor.getString(2));
            auxMuseos.setSalas(auxCusor.getString(3));

        }
        else
        {
            auxMuseos.setId("");
            auxMuseos.setNombre("");
            auxMuseos.setDireccion("");
            auxMuseos.setSalas("");
        }
        auxCusor.close();
        db.close();
        return auxMuseos;
    }
}
