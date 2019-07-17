package proyect.nerehira.hackatonv2.Config;

public interface SchemaSenalUbicacion {
    String TABLENAME = "MSENALS_UBICACIONES";
    String _ID = "ID";
    String LATITUD = "LATITUD";
    String LONGITUD = "LONGITUD";
    String UBICACION = "UBICACION";
    String CREATE_TABLE =   "CREATE TABLE IF NOT EXISTS " + TABLENAME + " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            LATITUD + " FLOAT NOT NULL, " +
            LONGITUD  + " FLOAT NOT NULL, " +
            UBICACION + " TEXT NOT NULL " +
            ")" ;
}
