package proyect.nerehira.hackatonv2.Entidad;

public class EUser {

    public int codigo;
    public String name;
    public String email;
    public String password;
    public String nickname;


    public EUser( String name,String email ,String password, String nickname) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public EUser(){

        this.name = "";
        this.email = "";
        this.password = "";
        this.nickname = "";
    }
//    public String getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(String codigo) {
//        this.codigo = codigo;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getNickname() {        return nickname;    }
//
//    public void setNickname(String nickname) {        this.nickname = nickname;    }
}
