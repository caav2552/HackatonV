package proyect.nerehira.hackatonv2.Entidad;

public class ESenalUbicacion {
    public int id;
    public String latitud;
    public String longitud;
    public String ubicacion;

    public ESenalUbicacion(String latitud, String longitud, String ubicacion) {
        this.latitud = latitud;
        this.longitud =  longitud;
        this.ubicacion = ubicacion;

    }

    public ESenalUbicacion(){
        this.latitud = "";
        this.longitud=  "";
        this.ubicacion = "";


    }

}
