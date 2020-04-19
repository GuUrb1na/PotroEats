package mx.itson.potroeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void irLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void irRegistro(View view) {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }
}
