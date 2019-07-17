package proyect.nerehira.hackatonv2.Negocio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import proyect.nerehira.hackatonv2.Config.SingletonDatabase;
import proyect.nerehira.hackatonv2.Datos.DSenalUbicacion;
import proyect.nerehira.hackatonv2.Entidad.ESenalUbicacion;

public class NSenalUbicacion {

    /* public NSenal(){
         dSenal=new DSenal();
     }*/
    protected DSenalUbicacion dSenal;

    public NSenalUbicacion(Context context){
        SQLiteDatabase db = SingletonDatabase.getInstance(context).getDB();
        // SQLiteDatabase db = new BaseDatos(context, "SEMURB.db", null, 1).getWritableDatabase();

        dSenal=new DSenalUbicacion(db);

    }


    public long NinsertarSenal(String latitud,String longitud,String ubicacion){
        //validaciones

        return dSenal.create(new ESenalUbicacion(latitud,longitud,ubicacion));
    }

    public ArrayList<ESenalUbicacion> obtenerIncidencias(){
        return  dSenal.readAll();
    }

}
