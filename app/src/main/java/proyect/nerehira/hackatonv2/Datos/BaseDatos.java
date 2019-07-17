package proyect.nerehira.hackatonv2.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.apache.log4j.LogManager;

import java.sql.ParameterMetaData;
import java.util.ArrayList;

import proyect.nerehira.hackatonv2.Config.SchemaIncidencia;
import proyect.nerehira.hackatonv2.Config.SchemaSenal;
import proyect.nerehira.hackatonv2.Config.SchemaSenalUbicacion;
import proyect.nerehira.hackatonv2.Config.SchemaUser;

public class BaseDatos extends SQLiteOpenHelper {

    //protected SQLiteDatabase myDataBase;
    //private org.apache.log4j.Logger log4;

    public static  String DBNAME="proyectoFin.db";
    public static int VERSION=1;



    public BaseDatos(Context context,String NOMBRE_BD,SQLiteDatabase.CursorFactory factory,int VERSION_BD){
        //super(context, NOMBRE_BD, context.getExternalFilesDir(null).getAbsolutePath(), null, VERSION_BD);
        super(context, NOMBRE_BD, factory, VERSION_BD);
        //log4= LogManager.getLogger(DUser.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{

            db.execSQL(SchemaUser.CREATE_TABLE);
            db.execSQL(SchemaIncidencia.CREATE_TABLE);
            db.execSQL(SchemaSenalUbicacion.CREATE_TABLE);
            db.execSQL(SchemaSenal.CREATE_TABLE);

            //String TABLA_USER="CREATE TABLE USER(CODIGO TEXT PRIMARY KEY, NAME TEXT, EMAIL TEXT, PASSWORD TEXT)";
            /*String TABLA_USER="CREATE TABLE USER(CODIGO INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, NAME TEXT, EMAIL TEXT,PASSWORD TEXT)";
            sqLiteDatabase.execSQL(TABLA_USER);
            String TABLA_MSENALS="CREATE TABLE MSENALS(ID INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, DETALLE TEXT, CODIGO TEXT,IMAGEN TEXT,CATEGORIA TEXT,TIPO TEXT,ESTADO TEXT ,ID_INSIDENCIA TEXT,ID_UBICACION TEXT)";
            sqLiteDatabase.execSQL(TABLA_MSENALS);
            String TABLA_MSENAL_UBICACIONES="CREATE TABLE MSENALS_UBICACIONES(ID INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, LATITUD TEXT, LONGITUD TEXT,UBICACION TEXT)";
            sqLiteDatabase.execSQL(TABLA_MSENAL_UBICACIONES);
            String TABLA_INSIDENCIAS="CREATE TABLE INSIDENCIAS(ID INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, OBSERVACION TEXT, FECHA TEXT,IMAGEN TEXT,ID_USUARIO TEXT)";
            sqLiteDatabase.execSQL(TABLA_INSIDENCIAS);*/


        }catch (Exception e){
           //log4.info(BaseDatos.class.getName()+"->onCreate"+"Tabla USER no creada");
            Log.e("Database", "onCreate: error ",e);
            System.out.println("Error");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version_anterior, int version_actual) {
        if (version_anterior ==1 && version_actual == 2){
           // sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS alumnos();");
        }
        /*sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TABLA_USER");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TABLA_MSENALS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TABLA_MSENAL_UBICACIONES");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TABLA_INSIDENCIAS");
        onCreate(sqLiteDatabase);
        */
        /*try{
            String TABLA_USER="CREATE TABLE USER (CODIGO TEXTO PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT)";
            sqLiteDatabase.execSQL(TABLA_USER);
        }catch (Exception e){
           // log4.info(BaseDatos.class.getName()+"->onUpgrade");
            System.out.println("Error");
        }*/
    }

   /* public int insertarUser(String name,String mail,String password){
        int id = -1;

        try {
            myDataBase = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("NAME", name);
            valores.put("EMAIL",mail);
            valores.put("PASSWORD", password);

            id = (int) myDataBase.insert("USER", null, valores);

        } catch (SQLException ex) {
            //log4.info(DUser.class.getName()+"-> InsertarUser"+"Usuario no insertado");
            System.out.println("Error");
        }
        return id;
    }
    public ArrayList consultarUser(String codigo){
        ArrayList res=new ArrayList();

        try {
            myDataBase = this.getReadableDatabase();
            String[] parametros={codigo};
            String[] resultado={"CODIGO","PASSWORD"};

            Cursor cursor= myDataBase.query("USER", resultado,"CODIGO=?",parametros,null,null,null);

            if(cursor!=null) {
                cursor.moveToFirst();
                do {
                    res.add(cursor.getInt(0));
                    res.add(cursor.getString(1));
                } while(cursor.moveToNext());
                return res;
            }else{
                return res;
            }

        } catch (SQLException ex) {
            //log4.info(DUser.class.getName()+"-> InsertarUser"+"Usuario no insertado");
            System.out.println("Error");
        }
        return res;
    }*/
}
