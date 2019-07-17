package proyect.nerehira.hackatonv2.Entidad;

public class EIncidencia {

    public String observacion;
    public String fecha;
    public String imagen;
    public int id_senal;

    public int id_usuario;

    public EIncidencia(String observacion, String fecha, String imagen, int id_usuario,int id_senal) {
        this.observacion = observacion;
        this.fecha = fecha;
        this.imagen = imagen;
        this.id_senal = id_senal;
        this.id_usuario = id_usuario;
    }

    public EIncidencia(){
        this.observacion = "";
        this.fecha = "";
        this.imagen = "";
        this.id_senal = 0;
        this.id_usuario = 0;
    }
}
