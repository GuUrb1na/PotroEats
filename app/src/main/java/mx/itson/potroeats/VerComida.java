package mx.itson.potroeats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class VerComida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_comida);
        Bundle extras = getIntent().getExtras();
        int value = extras.getInt("indexComida");

    }
}
