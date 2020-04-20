package mx.itson.potroeats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.PointerIconCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VerComida extends AppCompatActivity {
    ImageView IWfoto;
    TextView TVnombre;
    TextView TVprecio;
    TextView TVdescripcion;
    Button btnordenar;
    ElegantNumberButton ENBcantidad;
    DatabaseReference reference;
    DatabaseReference reference2;
    Comida comida = new Comida();
    List<Comida> Ordenlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_comida);
        Bundle extras = getIntent().getExtras();
        int value = extras.getInt("indexComida");
        String value2 = Integer.toString(value);
        btnordenar = (Button) findViewById(R.id.btn_añadir);
        ENBcantidad = (ElegantNumberButton) findViewById(R.id.ENB_cantidad);
        ENBcantidad.setRange(1,10);
        reference = FirebaseDatabase.getInstance().getReference();
        reference2 = FirebaseDatabase.getInstance().getReference().child("Comiditas").child(value2);
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                comida = dataSnapshot.getValue(Comida.class);
                String precio = String.valueOf(comida.getPrecio());
                IWfoto = (ImageView) findViewById(R.id.IW_comida);
                Picasso.get().load(comida.getFotoURL()).into(IWfoto);
                TVnombre = (TextView) findViewById(R.id.TV_nombrecomida);
                TVnombre.setText(comida.getNombre());
                TVdescripcion = (TextView) findViewById(R.id.TV_descripcion);
                TVdescripcion.setText(comida.getDescripcion());
                TVprecio = (TextView) findViewById(R.id.TV_preciocomida);
                TVprecio.setText("$" + precio);
                btnordenar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ordenar();
                        Log.d("jajaja", "" + Ordenlist.get(1).getNombre());
                        irCarrito(v);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void ordenar() {
        int cantidad = Integer.valueOf(ENBcantidad.getNumber());
        for (int i = 0; i < cantidad; i++) {
            Ordenlist.add(comida);
        }

    }

    public  void irCarrito(View view){
        Intent intent = new Intent(this, CarritoActivity.class);
        startActivity(intent);
    }
}
