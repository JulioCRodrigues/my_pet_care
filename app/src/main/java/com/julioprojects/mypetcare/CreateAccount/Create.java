package com.julioprojects.mypetcare.CreateAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.julioprojects.mypetcare.Login.Login;
import com.julioprojects.mypetcare.R;
import com.julioprojects.mypetcare.databinding.ActivityCreateLoginBinding;

public class Create extends AppCompatActivity {

    private ActivityCreateLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Init Spinner Login
        Spinner spinner = (Spinner) binding.spinnerPet;

        // Adapter Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_pets, R.layout.spinner_color);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set Adapter
        spinner.setAdapter(adapter);

        Button btnCreate = binding.elevatedButton;

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });




    }
}