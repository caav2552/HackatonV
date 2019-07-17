package proyect.nerehira.hackatonv2.Negocio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import proyect.nerehira.hackatonv2.Config.SingletonDatabase;
import proyect.nerehira.hackatonv2.Datos.BaseDatos;
import proyect.nerehira.hackatonv2.Datos.DSenal;
import proyect.nerehira.hackatonv2.Datos.DSenalUbicacion;
import proyect.nerehira.hackatonv2.Datos.DUser;
import proyect.nerehira.hackatonv2.Entidad.EUser;

public class NUser {

    protected DUser dUser;
    public NUser(Context context) {
        SQLiteDatabase db = SingletonDatabase.getInstance(context).getDB();
       dUser = new DUser(db);


    }




    public long insertarUser( String name, String mail, String password, String nickname){



            return dUser.create(new EUser(name,mail,password,nickname));
    }


    public int consultarUser(String nickname){

        return dUser.consultarUser(nickname);
    }
    public int consultarPasword(String nickname,String password){

        return dUser.consultarPassword(nickname,password);
    }

}
