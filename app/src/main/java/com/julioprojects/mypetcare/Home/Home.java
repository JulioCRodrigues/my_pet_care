package com.julioprojects.mypetcare.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.julioprojects.mypetcare.Login.Login;
import com.julioprojects.mypetcare.R;
import com.julioprojects.mypetcare.databinding.ActivityHomeBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private RecyclerView recycler;
    private List<TitleTask> titleTaskList = new ArrayList<>();
    private Dialog dialog;
    private BottomNavigationView navigationView;


    // Vars
    private TextView txtUploadPicture;
    private TextView txtTakePicture;
    private View viewPicture;
    // Const
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        recycler = binding.recyclerTask;
        viewPicture = binding.viewPicture;

        // Bottom Navigation - navegações com intent
        navigationView = (BottomNavigationView) binding.bottomNavigation;
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.help:
                        Intent intent = new Intent(getApplicationContext(), Maps.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        // Criando tarefas
        this.createTask();

        // Adapter
        AdapterTask adapterTask = new AdapterTask(titleTaskList);

        // Layout Menager Recycler Tasks
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);

        // Set Adapter
        recycler.setAdapter(adapterTask);
        
        // Abrindo dialog para inserir fotos 
        
        View btnInsertPicture = binding.viewPicture;
        
        btnInsertPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlert();
            }
        });



    }

    public void createTask(){

        TitleTask titleTask = new TitleTask("Lembretes");
        titleTaskList.add(titleTask);

        titleTask = new TitleTask("Vacinas");
        titleTaskList.add(titleTask);

        titleTask = new TitleTask("Banho e Tosa");
        titleTaskList.add(titleTask);

    }

    private void openAlert(){
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.alert_picture);

        txtUploadPicture = (TextView) dialog.findViewById(R.id.txtUploadPicture);
        txtTakePicture = (TextView) dialog.findViewById(R.id.txtTakePicture);


        txtUploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startUploadPhoto();
            }
        });

        txtTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        dialog.show();

    }

    private void takePhoto() {
        // Intent da camera
        Intent intentCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intentCam);
    }

    private void startUploadPhoto() {

        Toast.makeText(this, "Buscar!", Toast.LENGTH_SHORT).show();

    }

}