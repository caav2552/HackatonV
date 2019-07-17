package proyect.nerehira.hackatonv2.Entidad;

public class ESenal {
    public int id;
    public int id_user;
    public int id_ubicacion;
    public String detalle;
    public String codigo;
    public String imagen;
    public String categoria;
    public String tipo;
    public String estado;
    public ESenalUbicacion ubicacion;


    public ESenal(String detalle, String codigo, String imagen,
                  String categoria, String tipo,String estado,
                  int id_user,int id_ubicacion, ESenalUbicacion ubicacion) {
        this.detalle = detalle;
        this.codigo =  codigo;
        this.imagen = imagen;
        this.categoria = categoria;
        this.tipo = tipo;
        this.estado=estado;
        this.id_user=id_user;
        this.id_ubicacion= id_ubicacion;
        this.ubicacion = ubicacion;

    }

    public ESenal(){
        this.detalle = "";
        this.codigo =  "";
        this.imagen = "";
        this.categoria = "";
        this.tipo = "";
        this.estado="";
        this.id_user=0;
        this.id_ubicacion= 0;

    }

}
