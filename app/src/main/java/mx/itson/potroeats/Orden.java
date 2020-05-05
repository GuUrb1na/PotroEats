package mx.itson.potroeats;

import java.util.ArrayList;
import java.util.Date;

public class Orden {
    ArrayList<Comida> comidas = new ArrayList<>();
    float total;
    String fecha;
    String Comprador;
    String CompradorID;

    public Orden() {
    }

    public Orden(ArrayList<Comida> comidas, float total, String fecha, String comprador, String compradorID) {
        this.comidas = comidas;
        this.total = total;
        this.fecha = fecha;
        Comprador = comprador;
        CompradorID = compradorID;
    }

    public ArrayList<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(ArrayList<Comida> comidas) {
        this.comidas = comidas;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComprador() {
        return Comprador;
    }

    public void setComprador(String comprador) {
        Comprador = comprador;
    }

    public String getCompradorID() {
        return CompradorID;
    }

    public void setCompradorID(String compradorID) {
        CompradorID = compradorID;
    }
}
