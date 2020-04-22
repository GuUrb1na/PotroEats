package mx.itson.potroeats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
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

import org.w3c.dom.Text;

import java.util.EventListener;

public class Welcomeactivity extends AppCompatActivity {
    private String[] imageUrls = new String[]{
            //Imagenes que se usan en el slider
            "https://i.ibb.co/M9c5tSX/itsonbienvenida1.png",
            "http://iswug.net/tempsite/wp-content/uploads/2017/12/mapaItsonGuaymas.jpg",
            "http://iswug.net/tempsite/wp-content/uploads/2017/12/ITSON1.jpg",
            "https://i.ibb.co/N2mkzhv/e-Ty-Qy-VSt-THq-Zzkm-800x450-no-Pad.jpg",
            "https://i.ibb.co/dKYTWnD/AXJn-Nykbc-Pjkj-Yj-800x450-no-Pad.jpg"
    };
    //Inicializacion de la base de datos
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;

    TextView txtUser;
    Button btncUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeactivity);
        //Instacion del slider
        ViewPager viewPager = findViewById(R.id.Viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);
        //instancion de Views
        txtUser = (TextView) findViewById(R.id.TV_user);
        btncUser = (Button) findViewById(R.id.btn_cuenta);
        //instancion de la base de datos
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        //
        ActualizarUI();



    }
    public void irMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void irCuenta(View view) {
        Intent intent = new Intent(this, CuentaActivity.class);
        startActivity(intent);
    }

    public void irCarrito(View view) {
        Intent intent = new Intent(this, CarritoActivity.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
          //vacio para que nada pase
    }

    //Cambia los string de acuerdo a el usuario que acab de ingresar
    public void ActualizarUI(){
        //Recuperacion de la id de usuario
        reference = FirebaseDatabase.getInstance().getReference().child("Usuario").child(user.getUid());
        //Metodo para acceder a los datos del usuario
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nombre = dataSnapshot.child("nombre").getValue().toString();
                txtUser.setText(nombre);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
