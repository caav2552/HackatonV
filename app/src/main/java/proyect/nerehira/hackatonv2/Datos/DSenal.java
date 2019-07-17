package proyect.nerehira.hackatonv2.Datos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import proyect.nerehira.hackatonv2.Config.SchemaSenal;
import proyect.nerehira.hackatonv2.Config.SchemaSenalUbicacion;
import proyect.nerehira.hackatonv2.Entidad.ESenal;
import proyect.nerehira.hackatonv2.Entidad.ESenalUbicacion;

public class DSenal {
    private SQLiteDatabase db;

    public DSenal(SQLiteDatabase db) {
        this.db = db;
    }

    public long create(ESenal entidad) {
        ContentValues values = new ContentValues();
        values.put(SchemaSenal.DETALLE, entidad.detalle);
        values.put(SchemaSenal.CODIGO, entidad.codigo);
        values.put(SchemaSenal.IMAGEN, entidad.imagen);
        values.put(SchemaSenal.CATEGORIA, entidad.categoria);
        values.put(SchemaSenal.TIPO, entidad.tipo);
        values.put(SchemaSenal.ESTADO, entidad.estado);
        values.put(SchemaSenal.ID_USER, entidad.id_user);
        values.put(SchemaSenal.ID_UBICACION, entidad.id_ubicacion);


        try {
            long result = db.insert(SchemaSenal.TABLENAME, null, values);
            return result;
        } catch (Exception e) {
            Log.e("Senal", "create: e ", e);
            return 0;
        }

    }

    public ESenal read(int id) {
        String where = SchemaSenal._ID + "=? ";
        String[] args = {id + ""};
        Cursor cursor = db.query(SchemaSenal.TABLENAME, null, where, args, null, null, SchemaSenal._ID + " ASC");
        if (cursor == null) {
            return null;
        }

        if (cursor.moveToFirst()) {
            ESenal entidad = new ESenal();
            entidad.detalle = cursor.getString(cursor.getColumnIndex(SchemaSenal.DETALLE));
            entidad.codigo = cursor.getString(cursor.getColumnIndex(SchemaSenal.CODIGO));
            entidad.imagen = cursor.getString(cursor.getColumnIndex(SchemaSenal.IMAGEN));
            entidad.categoria = cursor.getString(cursor.getColumnIndex(SchemaSenal.CATEGORIA));
            entidad.estado = cursor.getString(cursor.getColumnIndex(SchemaSenal.ESTADO));
            entidad.id_user = cursor.getInt(cursor.getColumnIndex(SchemaSenal.ID_USER));
            entidad.id_ubicacion = cursor.getInt(cursor.getColumnIndex(SchemaSenal.ID_UBICACION));
            cursor.close();
            return entidad;
        }

        return null;
    }

    public int update(int id, ESenal entidad) {
        ContentValues values = new ContentValues();
        values.put(SchemaSenal.DETALLE, entidad.detalle);
        values.put(SchemaSenal.CODIGO, entidad.codigo);
        values.put(SchemaSenal.IMAGEN, entidad.imagen);
        values.put(SchemaSenal.CATEGORIA, entidad.categoria);
        values.put(SchemaSenal.ESTADO, entidad.estado);
        values.put(SchemaSenal.ID_USER, entidad.id_user);

        values.put(SchemaSenal.ID_UBICACION, entidad.id_ubicacion);

        String where = SchemaSenal._ID + "=?";
        String[] args = {id + ""};

        return db.update(SchemaSenal.TABLENAME, values, where, args);
    }

    public int delete(int id) {
        String where = SchemaSenal._ID + "=? ";
        String[] args = {id + "",};
        return db.delete(SchemaSenal.TABLENAME, where, args);
    }

    public ArrayList<ESenal> readAll() {
        ArrayList<ESenal> lista = new ArrayList<>();
        Cursor cursor = db.query(SchemaSenal.TABLENAME, null, null, null, null, null, SchemaSenal._ID + " ASC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                ESenal entidad = new ESenal();
                entidad.detalle = cursor.getString(cursor.getColumnIndex(SchemaSenal.DETALLE));
                entidad.codigo = cursor.getString(cursor.getColumnIndex(SchemaSenal.CODIGO));
                entidad.imagen = cursor.getString(cursor.getColumnIndex(SchemaSenal.IMAGEN));
                entidad.categoria = cursor.getString(cursor.getColumnIndex(SchemaSenal.CATEGORIA));
                entidad.estado = cursor.getString(cursor.getColumnIndex(SchemaSenal.ESTADO));
                entidad.id_user = cursor.getInt(cursor.getColumnIndex(SchemaSenal.ID_USER));
                entidad.id_ubicacion = cursor.getInt(cursor.getColumnIndex(SchemaSenal.ID_UBICACION));
                lista.add(entidad);

            } while (cursor.moveToNext());
            cursor.close();

        }
        return lista;
    }

    public ArrayList<ESenal> readAllWithLocation() {
        ArrayList<ESenal> lista = new ArrayList<>();
        String sql = "SELECT a.*, " + SchemaSenalUbicacion.UBICACION + ", " + SchemaSenalUbicacion.LATITUD + ", " + SchemaSenalUbicacion.LONGITUD +
                     " FROM " + SchemaSenal.TABLENAME + " a " +
                     " INNER JOIN " + SchemaSenalUbicacion.TABLENAME + " b ON " +
                     " a." + SchemaSenal.ID_UBICACION + "=" + "b." + SchemaSenalUbicacion._ID;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                ESenal entidad = new ESenal();
                entidad.detalle = cursor.getString(cursor.getColumnIndex(SchemaSenal.DETALLE));
                entidad.codigo = cursor.getString(cursor.getColumnIndex(SchemaSenal.CODIGO));
                entidad.imagen = cursor.getString(cursor.getColumnIndex(SchemaSenal.IMAGEN));
                entidad.categoria = cursor.getString(cursor.getColumnIndex(SchemaSenal.CATEGORIA));
                entidad.estado = cursor.getString(cursor.getColumnIndex(SchemaSenal.ESTADO));
                entidad.id_user = cursor.getInt(cursor.getColumnIndex(SchemaSenal.ID_USER));
                entidad.id_ubicacion = cursor.getInt(cursor.getColumnIndex(SchemaSenal.ID_UBICACION));

                ESenalUbicacion ubicacion = new ESenalUbicacion();
                ubicacion.ubicacion = cursor.getString(cursor.getColumnIndex(SchemaSenalUbicacion.UBICACION));
                ubicacion.latitud = cursor.getString(cursor.getColumnIndex(SchemaSenalUbicacion.LATITUD));
                ubicacion.longitud = cursor.getString(cursor.getColumnIndex(SchemaSenalUbicacion.LONGITUD));
                entidad.ubicacion = ubicacion;

                lista.add(entidad);

            } while (cursor.moveToNext());
            cursor.close();

        }
        return lista;
    }
}
