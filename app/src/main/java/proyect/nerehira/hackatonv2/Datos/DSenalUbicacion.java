package proyect.nerehira.hackatonv2.Datos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;


import proyect.nerehira.hackatonv2.Config.SchemaSenal;
import proyect.nerehira.hackatonv2.Config.SchemaSenalUbicacion;
import proyect.nerehira.hackatonv2.Entidad.ESenalUbicacion;

public class DSenalUbicacion {
    private SQLiteDatabase db;

    public DSenalUbicacion(SQLiteDatabase db) {
        this.db = db;
    }


    public long create(ESenalUbicacion entidad) {
        ContentValues values = new ContentValues();
        values.put(SchemaSenalUbicacion.LATITUD,entidad.latitud);
        values.put(SchemaSenalUbicacion.LONGITUD,entidad.longitud);
        values.put(SchemaSenalUbicacion.UBICACION,entidad.ubicacion);
        try {
            long result = db.insert(SchemaSenalUbicacion.TABLENAME, null, values);
            return  result;
        }
        catch (Exception e){
            Log.e("SenalUbicacion", "create: e ", e);
            return  0;
        }

    }

    public ESenalUbicacion read(int id) {
        String where = SchemaSenalUbicacion._ID + "=? ";
        String[] args = {id + ""};
        Cursor cursor = db.query(SchemaSenalUbicacion.TABLENAME, null, where, args, null, null, SchemaSenalUbicacion._ID + " ASC");
        if (cursor == null) {
            return null;
        }

        if (cursor.moveToFirst()) {
            ESenalUbicacion entidad = new ESenalUbicacion();
            entidad.latitud=cursor.getString(cursor.getColumnIndex(SchemaSenalUbicacion.LATITUD));
            entidad.longitud=cursor.getString(cursor.getColumnIndex(SchemaSenalUbicacion.LONGITUD));
            entidad.ubicacion=cursor.getString(cursor.getColumnIndex(SchemaSenalUbicacion.UBICACION));
            cursor.close();
            return entidad;
        }

        return null;
    }

    public int update(int id, ESenalUbicacion entidad) {
        ContentValues values = new ContentValues();
        values.put(SchemaSenalUbicacion.LATITUD, entidad.latitud);
        values.put(SchemaSenalUbicacion.LONGITUD, entidad.longitud);
        values.put(SchemaSenalUbicacion.UBICACION, entidad.ubicacion);

        String where = SchemaSenalUbicacion._ID + "=?";
        String[] args = {id + ""};

        return db.update(SchemaSenalUbicacion.TABLENAME, values, where, args);
    }

    public int delete(int id) {
        String where = SchemaSenalUbicacion._ID + "=? ";
        String[] args = {id + "",};
        return db.delete(SchemaSenalUbicacion.TABLENAME, where, args);
    }

    public ArrayList<ESenalUbicacion> readAll() {
        ArrayList<ESenalUbicacion> lista = new ArrayList<>();
        Cursor cursor = db.query(SchemaSenalUbicacion.TABLENAME, null, null, null, null, null, SchemaSenalUbicacion._ID + " ASC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                ESenalUbicacion entidad = new ESenalUbicacion();
                entidad.latitud = cursor.getString(cursor.getColumnIndex(SchemaSenalUbicacion.LATITUD));
                entidad.longitud = cursor.getString(cursor.getColumnIndex(SchemaSenalUbicacion.LONGITUD));
                entidad.ubicacion= cursor.getString(cursor.getColumnIndex(SchemaSenalUbicacion.UBICACION));
                //entidad.imagen = cursor.getString(cursor.getColumnIndex(SchemaIncidencia.IMAGEN));
                lista.add(entidad);
            } while (cursor.moveToNext());
            cursor.close();

        }
        return lista;
    }
}
