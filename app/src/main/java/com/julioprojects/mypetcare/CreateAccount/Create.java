package com.julioprojects.mypetcare.CreateAccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.julioprojects.mypetcare.CreateAccount.Entities.User;
import com.julioprojects.mypetcare.Login.Login;
import com.julioprojects.mypetcare.R;
import com.julioprojects.mypetcare.databinding.ActivityCreateLoginBinding;

public class Create extends AppCompatActivity {

    private ActivityCreateLoginBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Instanciando objeto usuario
        user = new User();

        // Recuperando instancia do firebase

        // Init auth
        mAuth = FirebaseAuth.getInstance();

        // Init database
        database = FirebaseDatabase.getInstance();


        // Init Spinner Login
        Spinner spinner = (Spinner) binding.spinnerPet;

        // Adapter Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_pets, R.layout.spinner_color);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set Adapter
        spinner.setAdapter(adapter);

        Button btnCreate = binding.elevatedButton;
        TextInputEditText edtEmail = binding.createEmail;
        TextInputEditText edtPassword = binding.createPassword;
        TextInputEditText edtName = binding.createName;
        Spinner typePet = binding.spinnerPet;

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Populando objeto user
                user.setName(edtName.getText().toString());
                user.setEmail(edtEmail.getText().toString());
                user.setTypePet(typePet.getSelectedItem().toString());


                insertUserDatabase(user);
                createAccount(edtEmail.getText().toString(), edtPassword.getText().toString());
            }
        });




    }


    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            Toast.makeText(Create.this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Create.this, "Falha ao registrar",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void insertUserDatabase(User user){
        myRef = database.getReference("users");

        String key = myRef.child("users").push().getKey();

        user.setKeyUser(key);

        myRef.child(key).setValue(user);

    }
}