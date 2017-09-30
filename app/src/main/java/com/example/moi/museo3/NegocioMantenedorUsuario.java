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

public class NegocioMantenedorUsuario extends SQLiteOpenHelper
{
    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATO = "museos.db";
    private static final String TABLA_USUARIO = "CREATE TABLE usuario" +
            "(rut TEXT PRIMARY KEY, nombre TEXT, direccion TEXT)";

    public NegocioMantenedorUsuario(Context context)
    {
        super(context, NOMBRE_BASEDATO, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLA_USUARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + TABLA_USUARIO);
        onCreate(db);
    }

    //insertar datos
    public void insertarDatos(Usuarios usuario) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO usuario (rut, nombre, direccion) VALUES ("
                    + "'" + usuario.getRut() +
                    "', '" + usuario.getNombre() +
                    "','" + usuario.getDireccion()+
                    "');");
        }
        db.close();
    }//fin insertar

    //método actualizar usuarios
    public void actualizarUsuarios(Usuarios usuario)
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("UPDATE usuario SET "
                    + "nombre ='" + usuario.getNombre()
                    + "', direccion = '" + usuario.getDireccion()
                    + "' WHERE rut = '" + usuario.getRut()+"' ;");
        }
        db.close();
    }

    //eliminar Usuario
    public void eliminarUsuario(String rut)
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("DELETE FROM  usuario "
                    + " WHERE rut = '" + rut + "'");
        }
        db.close();
    }



    //Retorna consulta clientes
    public List<Usuarios> retornaUsuario()
    {
        SQLiteDatabase db = getWritableDatabase();
        List<Usuarios> auxListausuario = new ArrayList<>();

        Cursor auxCusor = db.rawQuery("SELECT * FROM usuario;", null);

        auxCusor.moveToFirst();

        do
        {
            Usuarios auxMuseos = new Usuarios();
            auxMuseos.setRut(auxCusor.getString(0));
            auxMuseos.setNombre(auxCusor.getString(1));
            auxMuseos.setDireccion(auxCusor.getString(2));

            auxListausuario.add(auxMuseos);

        }while (auxCusor.moveToNext());
        auxCusor.close();
        db.close();
        return auxListausuario;
    }

    //buscar usuario por ID
    public Usuarios buscarUsuario(String rut)
    {
        SQLiteDatabase db = getWritableDatabase();
        Usuarios auxMuseos = new Usuarios();

        Cursor auxCusor = db.rawQuery("SELECT * FROM usuario WHERE rut = '" + rut +"';", null);
        auxCusor.moveToFirst();

        if(auxCusor != null)
        {
            auxMuseos.setRut(auxCusor.getString(0));
            auxMuseos.setNombre(auxCusor.getString(1));
            auxMuseos.setDireccion(auxCusor.getString(2));

        }
        else
        {
            auxMuseos.setRut("");
            auxMuseos.setNombre("");
            auxMuseos.setDireccion("");
        }
        auxCusor.close();
        db.close();
        return auxMuseos;
    }
}
