package mx.itson.potroeats;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import mx.itson.potroeats.Usuario;
import mx.itson.potroeats.Comida;
public class Orden {
    //FirebaseAuth auth;
    //DatabaseReference reference;

    String id;
    String compradorid;
    String compradorNombre;
    String fecha;
    float preciofinal;
    int cantidad;
    List<Comida> productos = new ArrayList<>();

    public Orden() {
    }

    public Orden(String id, String compradorid, String compradorNombre, String fecha, float preciofinal, int cantidad, List<Comida> productos) {
        this.id = id;
        this.compradorid = compradorid;
        this.compradorNombre = compradorNombre;
        this.fecha = fecha;
        this.preciofinal = preciofinal;
        this.cantidad = cantidad;
        this.productos = productos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompradorid() {
        return compradorid;
    }

    public void setCompradorid(String compradorid) {
        this.compradorid = compradorid;
    }

    public String getCompradorNombre() {
        return compradorNombre;
    }

    public void setCompradorNombre(String compradorNombre) {
        this.compradorNombre = compradorNombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getPreciofinal() {
        return preciofinal;
    }

    public void setPreciofinal(float preciofinal) {
        this.preciofinal = preciofinal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Comida> getProductos() {
        return productos;
    }

    public void setProductos(List<Comida> productos) {
        this.productos = productos;
    }
}
