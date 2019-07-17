package proyect.nerehira.hackatonv2.Datos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import proyect.nerehira.hackatonv2.Config.SchemaSenal;
import proyect.nerehira.hackatonv2.Config.SchemaUser;

import org.apache.log4j.LogManager;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.xml.validation.Schema;

import proyect.nerehira.hackatonv2.Entidad.EUser;
import proyect.nerehira.hackatonv2.MainActivity;

public class DUser {
    private SQLiteDatabase db;

    public DUser(SQLiteDatabase db) {
        this.db = db;
    }


    public long create(EUser entidad) {
        ContentValues values = new ContentValues();
        values.put(SchemaUser.NAME, entidad.name);
        values.put(SchemaUser.EMAIL, entidad.email);
        values.put(SchemaUser.PASSWORD, entidad.password);
        values.put(SchemaUser.NICKNAME, entidad.nickname);



        try {
            long result = db.insert(SchemaUser.TABLENAME, null, values);
            return result;
        } catch (Exception e) {
            Log.e("User", "create: e ", e);
            return 0;
        }

    }
    public int consultarUser(String nickname) {

        String where = SchemaUser.NICKNAME + "=? ";
        String[] args = {nickname + ""};
        Cursor cursor = db.query(SchemaUser.TABLENAME, null, where, args, null, null, SchemaUser.NICKNAME+ " ASC");
         if (cursor.getCount()>0) {

             return 1;
         }
         return 0;
    }
    public int consultarPassword(String nickname,String password) {

//        String where = SchemaUser.PASSWORD + "=? and" ;
//        String[] args = {password + "",};
//        Cursor cursor = db.query(SchemaUser.TABLENAME, null, where, args, null, null, SchemaUser.PASSWORD+ " ASC");

       Cursor cursor= db.rawQuery("Select * from USER where nickname=? and password=?",new String[]{nickname,password});
        if (cursor.getCount()>0) {
            return 1;
        }
        return 0;
    }

//    public int insertarUser(BaseDatos con,String codigo,String name,String mail,String password){
//            int id = -1;
//            try {
//                SQLiteDatabase db=con.getWritableDatabase();
//                ContentValues valores = new ContentValues();
//
//                valores.put("NAME", name);
//                valores.put("EMAIL",mail);
//                valores.put("PASSWORD", password);
//                //id = (int) db.insert("USER", codigo, valores);
//                id = (int) db.insert("USER", null, valores);
//                log4.info(DUser.class.getName()+"-> InsertarUser"+"Usuario insertado correctamente");
//                db.close();
//            } catch (SQLException ex) {
//                id=-1;
//                log4.info(DUser.class.getName()+"-> InsertarUser"+"Usuario no insertado");
//                System.out.println("Error");
//            }
//            return id;
//
//    }
//    public ArrayList consultarUser(BaseDatos con,String codigo,String password){
//        ArrayList res=new ArrayList();
//
//        try {
//            SQLiteDatabase myDatabase = con.getReadableDatabase();
//            String[] parametros=new String[]{codigo};
//            String[] resultado=new String[]{"CODIGO","NAME","PASSWORD"};
//
//            Cursor cursor= myDatabase.query("USER", resultado,"CODIGO=? and password?" ,parametros,null,null,null);
//            //Cursor cursor=myDatabase.rawQuery("SELECT * FROM USER",null);
//            if(cursor!=null && cursor.getCount()>0) {
//                cursor.moveToFirst();
//                do {
//                    String a=cursor.getString(0);
//                    String b=cursor.getString(1);
//                    String c=cursor.getString(2);
//                    res.add(a);
//                    res.add(b);
//                    res.add(c);
//
//                } while(cursor.moveToNext());
//                myDatabase.close();
//                log4.info(DUser.class.getName()+"-> InsertarUser"+"Usuario Consultado correctamente");
//                return res;
//            }else{
//                return res;
//            }
//        } catch (SQLException ex) {
//            log4.info(DUser.class.getName()+"-> InsertarUser"+"Usuario no consultado");
//            System.out.println("Error"+ ex.getMessage());
//        }
//        return res;
//    }
}
