package mx.itson.potroeats;

public class Comida {
    int ID;
    String Descripcion;
    String Nombre;
    Double Precio;
    String fotoURL;

    public Comida() {
    }

    public Comida(int ID, String descripcion, String nombre, Double Precio, String fotoURL) {
        this.ID = ID;
        Descripcion = descripcion;
        Nombre = nombre;
        this.Precio = Precio;
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
        return Precio;
    }

    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }
}
