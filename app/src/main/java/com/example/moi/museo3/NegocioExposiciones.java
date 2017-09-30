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

public class NegocioExposiciones extends SQLiteOpenHelper
{
    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATO = "museos.db";
    private static final String TABLA_EXPOSICION = "CREATE TABLE exposicion" +
            "(Id TEXT PRIMARY KEY, nombre TEXT, museo TEXT)";

    public NegocioExposiciones(Context context)
    {
        super(context, NOMBRE_BASEDATO, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLA_EXPOSICION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + TABLA_EXPOSICION);
        onCreate(db);
    }

    //insertar datos
    public void insertarDatos(claseExposicion expo) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO exposicion (Id, nombre, museo) VALUES ("
                    + "'" + expo.getId() +
                    "', '" + expo.getNombre() +
                    "','" + expo.getMuseo()+
                    "');");
        }
        db.close();
    }//fin insertar

    //método actualizar expo
    public void actualizarExpo(claseExposicion expo)
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("UPDATE exposicion SET "
                    + "nombre ='" + expo.getNombre()
                    + "', direccion = '" + expo.getMuseo()
                    + "' WHERE Id = '" + expo.getId()+"' ;");
        }
        db.close();
    }

    //eliminar expo
    public void eliminarExpo(String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("DELETE FROM  exposicion "
                    + " WHERE Id = '" + id + "'");
        }
        db.close();
    }

    //elimiar todos las expo
    public void eliminarTodosLasExpo()
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("DELETE FROM  exposicion; ");
        }
        db.close();
    }

    //Retorna consulta expo
    public List<claseExposicion> retornaExpo()
    {
        SQLiteDatabase db = getWritableDatabase();
        List<claseExposicion> auxListaMuseos = new ArrayList<>();

        Cursor auxCusor = db.rawQuery("SELECT * FROM exposicion;", null);

        auxCusor.moveToFirst();

        do
        {
            claseExposicion auxMuseos = new claseExposicion();
            auxMuseos.setId(auxCusor.getString(0));
            auxMuseos.setNombre(auxCusor.getString(1));
            auxMuseos.setMuseo(auxCusor.getString(2));

            auxListaMuseos.add(auxMuseos);

        }while (auxCusor.moveToNext());
        auxCusor.close();
        db.close();
        return auxListaMuseos;
    }

    //buscar museos por ID
    public claseExposicion buscarExpo(String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        claseExposicion auxMuseos = new claseExposicion();

        Cursor auxCusor = db.rawQuery("SELECT * FROM exposicion WHERE Id = '" + id +"';", null);
        auxCusor.moveToFirst();

        if(auxCusor != null)
        {
            auxMuseos.setId(auxCusor.getString(0));
            auxMuseos.setNombre(auxCusor.getString(1));
            auxMuseos.setMuseo(auxCusor.getString(2));
        }
        else
        {
            auxMuseos.setId("");
            auxMuseos.setNombre("");
            auxMuseos.setMuseo("");
        }
        auxCusor.close();
        db.close();
        return auxMuseos;
    }
}
