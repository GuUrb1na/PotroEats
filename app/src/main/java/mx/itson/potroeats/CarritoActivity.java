package mx.itson.potroeats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import mx.itson.potroeats.recyclercarrito.Carritoadapter;
import mx.itson.potroeats.recyclercarrito.Carritoitem;

public class CarritoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static ArrayList<Carritoitem> carritoList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        ArrayList<Comida> comidas = getIntent().getParcelableArrayListExtra("Orden");
        for(int i = 0; i < comidas.size(); i++){
            carritoList.add(new Carritoitem(comidas.get(i).fotoURL, comidas.get(i).getNombre(), String.valueOf(comidas.get(i).getPrecio())));
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
        Intent intent = new Intent(this, Welcomeactivity.class);
        startActivity(intent);
    }
}
