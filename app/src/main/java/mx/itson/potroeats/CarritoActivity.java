package mx.itson.potroeats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import mx.itson.potroeats.recyclercarrito.Carritoadapter;
import mx.itson.potroeats.recyclercarrito.Carritoitem;

public class CarritoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button comprar;
    Button borrar;
    TextView total;
    FirebaseUser user;
    FirebaseAuth auth;
    DatabaseReference reff;
    DatabaseReference reference;
    Orden orden = new Orden();
    float Total = 0;
    ArrayList<Carritoitem> carritoList = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault());
    String currentDateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        reff = FirebaseDatabase.getInstance().getReference().child("Ordenes");
        comprar = (Button) findViewById(R.id.btn_Comprar);
        borrar = (Button) findViewById(R.id.btn_borrarorden);
        comprar.setClickable(false);
        borrar.setClickable(false);
        total = (TextView) findViewById(R.id.TV_total);
        actualizartotal();
        ArrayList<Comida> comidas = getIntent().getParcelableArrayListExtra("Orden");
        if (comidas != null) {
            comprar.setClickable(true);
            comprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comprars();
                }
            });
            borrar.setClickable(true);
            borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    borrarorden();
                }
            });
            for (int i = 0; i < comidas.size(); i++) {
                carritoList.add(new Carritoitem(comidas.get(i).fotoURL, comidas.get(i).getNombre(), String.valueOf(comidas.get(i).getPrecio())));
            }
        }
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Carritoadapter(carritoList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void onBackPressed() {
        ArrayList<Comida> comidas = getIntent().getParcelableArrayListExtra("Orden");
        Intent intent = new Intent(this, Welcomeactivity.class);
        intent.putParcelableArrayListExtra("Orden", comidas);
        startActivity(intent);
    }


    public void actualizartotal() {
        ArrayList<Comida> comidas = getIntent().getParcelableArrayListExtra("Orden");
        if (comidas != null) {
            for (int i = 0; i < comidas.size(); i++) {
                Total += comidas.get(i).getPrecio();
            }
        }
        total.setText("Total: " + Total);
    }

    public void comprars() {
        ArrayList<Comida> comidas = getIntent().getParcelableArrayListExtra("Orden");
        Intent intent = new Intent(this, TarjetaActivity.class);
        intent.putParcelableArrayListExtra("Orden", comidas);
        intent.putExtra("TOTAL", Total);
        startActivity(intent);

        /*reference = FirebaseDatabase.getInstance().getReference().child("Usuario").child(user.getUid());
        //Metodo para acceder a los datos del usuario
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Comida> comidas = getIntent().getParcelableArrayListExtra("Orden");
                reference = FirebaseDatabase.getInstance().getReference().child("Usuario").child(user.getUid());
                orden.setCompradorID(user.getUid());
                orden.setComprador(dataSnapshot.child("nombre").getValue().toString());
                orden.setComidas(comidas);
                orden.setFecha(currentDateandTime);
                orden.setTotal(Total);
                reff.push().setValue(orden);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

         */
    }

    public void borrarorden() {
        ArrayList<Comida> comidas = getIntent().getParcelableArrayListExtra("Orden");
        carritoList.clear();
        comidas.clear();
        Intent intent = new Intent(this, Welcomeactivity.class);
        startActivity(intent);


    }
}
