package mx.itson.potroeats;

public class Usuario {
    private String Nombre;
    private String Correo;

    public Usuario() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Usuario(String Nombre, String Correo) {
        this.Nombre = Nombre;
        this.Correo = Correo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
