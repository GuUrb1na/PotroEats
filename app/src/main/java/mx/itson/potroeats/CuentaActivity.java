package mx.itson.potroeats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.EventListener;
import java.util.Random;
import java.util.UUID;

public class CuentaActivity extends AppCompatActivity {
    TextView txtUser;
    TextView txtCorreo;
    Button btnlogout;
    //Inicializacion de la base de datos
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
        //instancion de Views
        txtUser = (TextView) findViewById(R.id.TV_user);
        txtCorreo = (TextView) findViewById(R.id.TV_correo);
        btnlogout = (Button) findViewById(R.id.btn_logout);
        //instancion de la base de datos
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        //Recuperacion de la id de usuario
        reference = FirebaseDatabase.getInstance().getReference().child("Usuario").child(user.getUid());
        //Metodo para acceder a los datos del usuario
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Nombre = dataSnapshot.child("nombre").getValue().toString();
                String Correo = dataSnapshot.child("correo").getValue().toString();
                txtUser.setText(Nombre);
                txtCorreo.setText(Correo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // boton para cerrar sesion
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                irinicio(v);

            }
        });
        


    }

    public void irinicio(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}