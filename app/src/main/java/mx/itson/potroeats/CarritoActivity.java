package mx.itson.potroeats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import mx.itson.potroeats.recyclercarrito.Carritoadapter;
import mx.itson.potroeats.recyclercarrito.Carritoitem;

public class CarritoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        ArrayList<Carritoitem> carritoleList = new ArrayList<>();
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 1", "Line 2"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 3", "Line 4"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 5", "Line 6"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 7", "Line 8"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 9", "Line 10"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 11", "Line 12"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 13", "Line 14"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 15", "Line 16"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 17", "Line 18"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 19", "Line 20"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 21", "Line 22"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 23", "Line 24"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 25", "Line 26"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 27", "Line 28"));
        carritoleList.add(new Carritoitem("https://static.myfigurecollection.net/pics/figure/big/555874.jpg", "Line 29", "Line 30"));
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Carritoadapter(carritoleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
