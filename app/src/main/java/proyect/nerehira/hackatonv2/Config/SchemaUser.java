package proyect.nerehira.hackatonv2.Config;

public interface SchemaUser {
    String TABLENAME = "USER";
    String _ID = "CODIGO";
    String NAME = "NAME";
    String EMAIL = "EMAIL";
    String PASSWORD = "PASSWORD";
    String NICKNAME= "NICKNAME";

    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLENAME + " ( " +
                            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            NAME + " TEXT NOT NULL, " +
                            EMAIL + " TEXT NOT NULL, " +
                            PASSWORD + " TEXT NOT NULL, " +
                            NICKNAME + " TEXT NOT NULL " +
                            ")";
}
