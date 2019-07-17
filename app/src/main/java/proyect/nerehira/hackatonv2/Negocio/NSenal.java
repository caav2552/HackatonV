package proyect.nerehira.hackatonv2.Negocio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import proyect.nerehira.hackatonv2.Config.SingletonDatabase;
import proyect.nerehira.hackatonv2.Datos.DSenal;
import proyect.nerehira.hackatonv2.Datos.DSenalUbicacion;
import proyect.nerehira.hackatonv2.Entidad.ESenal;
import proyect.nerehira.hackatonv2.Entidad.ESenalUbicacion;

public class NSenal {
//    protected DSenal dSenal;

    /* public NSenal(){
         dSenal=new DSenal();
     }*/
    private DSenal dSenal;
    private DSenalUbicacion dUbicacion;

    public NSenal(Context context) {
        SQLiteDatabase db = SingletonDatabase.getInstance(context).getDB();
        // SQLiteDatabase db = new BaseDatos(context, "SEMURB.db", null, 1).getWritableDatabase();

        this.dSenal = new DSenal(db);
        this.dUbicacion = new DSenalUbicacion(db);

    }


    public long insertarSenal(String detalle, String codigo, String imagen, String categoria, String tipo,
                                String estado, int id_usuario, ESenalUbicacion ubicacion) {
        //validaciones
        long successful_ubicacion = dUbicacion.create(ubicacion);
        if (successful_ubicacion > 0) {
            return dSenal.create(new ESenal(detalle, codigo, imagen, categoria, tipo, estado, id_usuario, (int)successful_ubicacion, ubicacion));
        }

        return -1;
    }

    public ArrayList<ESenal> obtenerSenales() {
        return dSenal.readAll();
    }

    public ArrayList<ESenal> getAllSenales(){
        return  dSenal.readAllWithLocation();
    }


}
