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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText Correo;
    private EditText Contraseña;
    private EditText Nombre;
    private Button btn_Registrarse;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Iniciamos la Realtime database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciamos los views
        Correo = (EditText) findViewById(R.id.ET_Correo);
        Contraseña = (EditText) findViewById(R.id.ET_Contraseña);
        Nombre = (EditText) findViewById(R.id.ET_Nombre);

        btn_Registrarse = (Button) findViewById(R.id.btn_Registrarse);

        progressDialog = new ProgressDialog(this);
    }

    public void RegistrarUsuario(View view) {

        //Obtenemos el email y la contraseña desde las cajas de texto
        String nombre = Nombre.getText().toString().trim();
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
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(this, "Falta ingresar el nombre", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                            Toast.makeText(Registro.this, "Se ha registrado exitosamente", Toast.LENGTH_LONG).show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Registro.this, "Ese usuario ya existe", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Registro.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }


                        }
                        progressDialog.dismiss();
                    }
                });


    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = Nombre.getText().toString().trim();;
        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());

        // Go to MainActivity
        startActivity(new Intent(Registro.this, MainActivity.class));
        finish();
    }
    //Escribe los datos en la Realtime DB
    private void writeNewUser(String userId, String nombre, String email) {
        Usuario usuario = new Usuario(nombre, email);

        mDatabase.child("Usuario").child(userId).setValue(usuario);
    }


    public void irPrincipal(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
