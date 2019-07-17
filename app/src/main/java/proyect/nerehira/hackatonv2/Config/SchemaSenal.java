package proyect.nerehira.hackatonv2.Config;

public interface SchemaSenal {
    String TABLENAME = "MSENALS";
    String _ID = "ID";
    String CODIGO = "CODIGO";
    String CATEGORIA = "CATEGORIA";
    String TIPO = "TIPO";
    String DETALLE = "DETALLE";
    String ID_USER = "ID_USER";
    String ID_UBICACION = "ID_UBICACION";
    String IMAGEN = "IMAGEN";
    String ESTADO = "ESTADO";

    String CREATE_TABLE =   "CREATE TABLE IF NOT EXISTS " + TABLENAME + " ( " +
                                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                DETALLE + " TEXT NOT NULL, " +
                                CODIGO  + " TEXT NOT NULL, " +
                                CATEGORIA + " TEXT NOT NULL, " +
                                TIPO + " TEXT NOT NULL, " +
                                ID_USER + " INTEGER NOT NULL, " +
                                ID_UBICACION + " INTEGER NOT NULL, " +
                                IMAGEN + " TEXT, " +
                                ESTADO + " TEXT NOT NULL " +
                               // "FOREIGN KEY(" + ID_INCIDENCIA + ") REFERENCES " + SchemaIncidencia.TABLENAME + "(" + SchemaIncidencia._ID + ") ON DELETE CASCADE ON UPDATE CASCADE, " +
                               // "FOREIGN KEY(" + ID_UBICACION + ") REFERENCES " + SchemaSenalUbicacion.TABLENAME + "(" + SchemaSenalUbicacion._ID + ") ON DELETE CASCADE ON UPDATE CASCADE " +
                            ")" ;
}
