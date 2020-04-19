package mx.itson.potroeats;

public class Comida {
    int ID;
    String Descripcion;
    String Nombre;
    Double precio;
    String fotoURL;

    public Comida() {
    }

    public Comida(int ID, String descripcion, String nombre, Double precio, String fotoURL) {
        this.ID = ID;
        Descripcion = descripcion;
        Nombre = nombre;
        this.precio = precio;
        this.fotoURL = fotoURL;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }
}
