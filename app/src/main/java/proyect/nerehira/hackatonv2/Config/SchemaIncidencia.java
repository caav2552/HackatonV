package proyect.nerehira.hackatonv2.Config;

public interface SchemaIncidencia {
    String TABLENAME = "INSIDENCIAS";
    String _ID = "ID";
    String OBSERVACION = "OBSERVACION";
    String FECHA = "FECHA";
    String IMAGEN = "IMAGEN";
    String ID_SENAL = "ID_SENAL";
    String ID_USUARIO = "ID_USUARIO";


    String CREATE_TABLE =   "CREATE TABLE IF NOT EXISTS " + TABLENAME + " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            OBSERVACION + " TEXT NOT NULL, " +
            FECHA  + " TEXT NOT NULL, " +
            IMAGEN  + " TEXT NOT NULL, " +
            ID_SENAL  + " INTEGER NOT NULL, " +
            ID_USUARIO + " INTEGER NOT NULL " +
           // "FOREIGN KEY(" + ID_USUARIO + ") REFERENCES " + SchemaUser.TABLENAME + "(" + SchemaUser._ID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
            ")" ;
}
