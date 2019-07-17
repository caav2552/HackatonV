package proyect.nerehira.hackatonv2.Config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import proyect.nerehira.hackatonv2.Datos.BaseDatos;

public class SingletonDatabase {
    private static  SingletonDatabase instancia;
    private SQLiteDatabase conexion;

    private SingletonDatabase(Context context){
        this.conexion = new BaseDatos(context, BaseDatos.DBNAME, null, BaseDatos.VERSION).getWritableDatabase();
    }

    public static SingletonDatabase getInstance(Context context){
        if (instancia == null){
           instancia = new SingletonDatabase(context);
        }

        return instancia;
    }

    public SQLiteDatabase getDB(){
        return conexion;
    }

}
