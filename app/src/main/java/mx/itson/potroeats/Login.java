package mx.itson.potroeats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Login extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText Correo;
    private EditText Contraseña;
    private Button btn_Registrarse;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciamos los views
        Correo = (EditText) findViewById(R.id.ET_Correo);
        Contraseña = (EditText) findViewById(R.id.ET_Contraseña);

        btn_Registrarse = (Button) findViewById(R.id.btn_Registrarse);

        progressDialog = new ProgressDialog(this);
    }

    public void irPrincipal(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void irWelcome(View view) {
        Intent intent = new Intent(this, Welcomeactivity.class);
        startActivity(intent);
    }

    public void LogearUsuario(final View view) {

        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = Correo.getText().toString().trim();
        String password = Contraseña.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Espere un momento...");
        progressDialog.show();

        //Logue a user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            irWelcome(view);
                        } else {
                            Toast.makeText(Login.this, "Este Usuario no existe o la contraseña es incorrecta", Toast.LENGTH_LONG).show();
                        }


                        progressDialog.dismiss();
                    }
                });


    }
}
