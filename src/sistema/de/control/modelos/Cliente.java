package sistema.de.control.modelos;

public class Cliente {

    private int id;
    private String Nombre;
    private String Apellidos;
    private String Domicilio;
    private String Email;

    public Cliente(int id, String Nombre, String Apellidos, String Domicilio, String Email) {
        this.id = id;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Domicilio = Domicilio;
        this.Email = Email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String Domicilio) {
        this.Domicilio = Domicilio;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}
