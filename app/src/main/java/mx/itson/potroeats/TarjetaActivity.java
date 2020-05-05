package mx.itson.potroeats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.braintreepayments.cardform.view.CardForm;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.Locale;

public class TarjetaActivity extends AppCompatActivity {
    Button btnconfirmar;
    CardForm cardForm;
    DatabaseReference reference;
    DatabaseReference reff;
    FirebaseUser user;
    FirebaseAuth auth;
    Orden orden = new Orden();
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault());
    String currentDateandTime = sdf.format(new Date());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjeta);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        reff = FirebaseDatabase.getInstance().getReference().child("Ordenes");
        btnconfirmar = (Button) findViewById(R.id.btn_confirmar);
        cardForm = (CardForm) findViewById(R.id.card_form);
        final CardForm cardForm = (CardForm) findViewById(R.id.card_form);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .postalCodeRequired(false)
                .mobileNumberRequired(false)
                .mobileNumberExplanation(null)
                .actionLabel("Purchase")
                .setup(this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        btnconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardForm.isValid()) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(TarjetaActivity.this);
                    alertBuilder.setTitle("Confirma antes de comprar");
                    alertBuilder.setMessage("Numero: " + cardForm.getCardNumber() + "\n" +
                            "Fecha de expiracion: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                            "CVV: " + cardForm.getCvv());
                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            reference = FirebaseDatabase.getInstance().getReference().child("Usuario").child(user.getUid());
                            //Metodo para acceder a los datos del usuario
                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    ArrayList<Comida> comidas = getIntent().getParcelableArrayListExtra("Orden");
                                    float Total = getIntent().getFloatExtra("TOTAL", 0);
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

                            dialogInterface.dismiss();
                            Toast.makeText(TarjetaActivity.this, "Gracias por su compra", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TarjetaActivity.this, Welcomeactivity.class);
                            startActivity(intent);

                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(TarjetaActivity.this, "Porfavor ingrese sus datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
