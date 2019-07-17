package proyect.nerehira.hackatonv2.Datos;

public class DSingletonSession {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String codigo;

    private static DSingletonSession instance;

    private DSingletonSession(){
        this.id = 1;
        this.nombre = "Ivette Sangalo";
        this.email = "ivette.sangalo@gmail.com";
        this.telefono = "33358789";
        this.codigo = "01524";
    }

    public static DSingletonSession getInstance(){
        if (instance == null){
            instance = new DSingletonSession();
        }

        return instance;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
