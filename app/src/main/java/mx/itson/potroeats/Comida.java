package mx.itson.potroeats;

import android.os.Parcel;
import android.os.Parcelable;

public class Comida implements Parcelable {
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

    protected Comida(Parcel in) {
        ID = in.readInt();
        Descripcion = in.readString();
        Nombre = in.readString();
        if (in.readByte() == 0) {
            Precio = null;
        } else {
            Precio = in.readDouble();
        }
        fotoURL = in.readString();
    }

    public static final Creator<Comida> CREATOR = new Creator<Comida>() {
        @Override
        public Comida createFromParcel(Parcel in) {
            return new Comida(in);
        }

        @Override
        public Comida[] newArray(int size) {
            return new Comida[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Descripcion);
        dest.writeString(Nombre);
        if (Precio == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Precio);
        }
        dest.writeString(fotoURL);
    }
}
