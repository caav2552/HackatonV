package proyect.nerehira.hackatonv2.Negocio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import proyect.nerehira.hackatonv2.Config.SingletonDatabase;
import proyect.nerehira.hackatonv2.Datos.BaseDatos;
import proyect.nerehira.hackatonv2.Datos.DIncidencia;
import proyect.nerehira.hackatonv2.Entidad.EIncidencia;

public class NIncidencia {
    protected DIncidencia dIncidencia;

    public NIncidencia(Context context){
        SQLiteDatabase db = SingletonDatabase.getInstance(context).getDB();
        // SQLiteDatabase db = new BaseDatos(context, "SEMURB.db", null, 1).getWritableDatabase();

        dIncidencia=new DIncidencia(db);

    }

    public long NInsertarIncidencia(String observacion,String Fecha,String imagen,int id_usuario,int id_senal){
        //validaciones

       return dIncidencia.create(new EIncidencia(observacion,Fecha,imagen,id_usuario , id_senal));
    }

    public ArrayList<EIncidencia> obtenerIncidencias(){
        return  dIncidencia.readAll();
    }

}
