package mx.itson.potroeats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mx.itson.potroeats.recyclermenu.ExampleAdapter;
import mx.itson.potroeats.recyclermenu.ExampleItem;

public class MenuActivity extends AppCompatActivity {
    //Instanciamos el recycler view que es el menu que proyectaremos
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //Instanciamos la base de datos
    DatabaseReference myRef;
    //Creamos una lista para los productos del menu
    List<Comida> ComidaList = new ArrayList<>();
    //Creamos la lista que le pasaremos al adapter
    static ArrayList<ExampleItem> examplelist = new ArrayList<>();
    //Variables que se usan para que el menu se cree de forma dinamica
    int i;
    private static boolean RUN_ONCE = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //Obtiene la tabla Comiditas de la base de datos q
        myRef = FirebaseDatabase.getInstance().getReference().child("Comiditas");
        //Instrucciones que se realizaran cuando se entre a la actividad
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Consiguiendos todos las entidades de la tabla Comiditas
                GenericTypeIndicator<List<Comida>> t = new GenericTypeIndicator<List<Comida>>() {};
                ComidaList = snapshot.getValue(t);
                //Ejecuta el codigo que añade elementos a la lista del adaptador
                runOnce();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MenuActivity.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
            }
        });

        }
    private void runOnce() {
        //Este metodo asegura que la creacion del menu solo se haga una vez cuando se inicie la aplicacion
        if (RUN_ONCE) {
            RUN_ONCE = false;
            //Añade todos los entes de la tabla Comiditas a la lista que usara el adapter
            for ( i = 1; i < ComidaList.size(); i++){
                examplelist.add(new ExampleItem(ComidaList.get(i).fotoURL, ComidaList.get(i).Nombre));
            }
        }

        //inicializacion del menu
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(examplelist);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MenuActivity.this, VerComida.class);
                intent.putExtra("indexComida",position+1);
                startActivity(intent);


            }
        });
    }
}
