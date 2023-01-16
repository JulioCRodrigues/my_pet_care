package com.julioprojects.mypetcare.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.julioprojects.mypetcare.CreateAccount.Create;
import com.julioprojects.mypetcare.Home.Home;
import com.julioprojects.mypetcare.databinding.ActivityLoginBinding;


public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Instanciando Firebase (recuperando instancia - aautenticação)
        mAuth = FirebaseAuth.getInstance();

        TextView txtCreate = binding.tvCreateCount;

        txtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Create.class);
                startActivity(intent);
            }
        });

        Button btnLogin = binding.login;
        TextInputEditText edtEmail = binding.edtTextEmail;
        TextInputEditText edtPassword = binding.edtTextPassword;


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(edtEmail.getText().toString(), edtPassword.getText().toString());
            }
        });
    }

    private void login(String email, String password ){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            Toast.makeText(Login.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Falha ao autenticar!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}