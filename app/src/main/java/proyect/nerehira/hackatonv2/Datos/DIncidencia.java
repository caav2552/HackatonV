package proyect.nerehira.hackatonv2.Datos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import proyect.nerehira.hackatonv2.Config.SchemaIncidencia;
import proyect.nerehira.hackatonv2.Entidad.EIncidencia;

public class DIncidencia {
    private SQLiteDatabase db;

     public DIncidencia(SQLiteDatabase db) {
       this.db = db;
    }


    public long create(EIncidencia entidad) {
        ContentValues values = new ContentValues();
        values.put(SchemaIncidencia.OBSERVACION, entidad.observacion);
        values.put(SchemaIncidencia.FECHA, entidad.fecha);
        values.put(SchemaIncidencia.IMAGEN, entidad.imagen);
        values.put(SchemaIncidencia.ID_USUARIO, entidad.id_usuario);
        values.put(SchemaIncidencia.ID_SENAL, entidad.id_senal);
        try {
            long result = db.insert(SchemaIncidencia.TABLENAME, null, values);
            return  result;
        }
        catch (Exception e){
            Log.e("Incidencia", "create: e ", e);
            return  0;
        }

    }

    public EIncidencia read(int id) {
        String where = SchemaIncidencia._ID + "=? ";
        String[] args = {id + ""};
        Cursor cursor = db.query(SchemaIncidencia.TABLENAME, null, where, args, null, null, SchemaIncidencia._ID + " ASC");
        if (cursor == null) {
            return null;
        }

        if (cursor.moveToFirst()) {
            EIncidencia entidad = new EIncidencia();
            entidad.id_usuario = cursor.getInt(cursor.getColumnIndex(SchemaIncidencia.ID_USUARIO));
            entidad.id_senal = cursor.getInt(cursor.getColumnIndex(SchemaIncidencia.ID_SENAL));
            entidad.observacion = cursor.getString(cursor.getColumnIndex(SchemaIncidencia.OBSERVACION));
            entidad.fecha = cursor.getString(cursor.getColumnIndex(SchemaIncidencia.FECHA));
            entidad.imagen = cursor.getString(cursor.getColumnIndex(SchemaIncidencia.IMAGEN));
            cursor.close();
            return entidad;
        }

        return null;
    }

    public int update(int id, EIncidencia entidad) {
        ContentValues values = new ContentValues();
        values.put(SchemaIncidencia.OBSERVACION, entidad.observacion);
        values.put(SchemaIncidencia.FECHA, entidad.fecha);
        values.put(SchemaIncidencia.IMAGEN, entidad.imagen);
        values.put(SchemaIncidencia.ID_USUARIO, entidad.id_usuario);
        values.put(SchemaIncidencia.ID_SENAL, entidad.id_senal);

        String where = SchemaIncidencia._ID + "=?";
        String[] args = {id + ""};

        return db.update(SchemaIncidencia.TABLENAME, values, where, args);
    }

    public int delete(int id) {
        String where = SchemaIncidencia._ID + "=? ";
        String[] args = {id + "",};
        return db.delete(SchemaIncidencia.TABLENAME, where, args);
    }

    public ArrayList<EIncidencia> readAll() {
        ArrayList<EIncidencia> lista = new ArrayList<>();
        Cursor cursor = db.query(SchemaIncidencia.TABLENAME, null, null, null, null, null, null);
        int cantidad = cursor.getCount();
        boolean flag = cursor.moveToFirst();
        if (cantidad > 0 && flag) {
            do {
                EIncidencia entidad = new EIncidencia();
                entidad.id_usuario = cursor.getInt(cursor.getColumnIndex(SchemaIncidencia.ID_USUARIO));
                entidad.id_senal = cursor.getInt(cursor.getColumnIndex(SchemaIncidencia.ID_SENAL));
                entidad.observacion = cursor.getString(cursor.getColumnIndex(SchemaIncidencia.OBSERVACION));
                entidad.fecha = cursor.getString(cursor.getColumnIndex(SchemaIncidencia.FECHA));
                entidad.imagen = cursor.getString(cursor.getColumnIndex(SchemaIncidencia.IMAGEN));
                lista.add(entidad);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return lista;
    }
}