package com.julioprojects.mypetcare.Home;

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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
                Toast.makeText(Home.this, "Click!", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void startUploadPhoto() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 123);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 123){
                Uri image = data.getData();
                Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(viewPicture);

            }
        }
    }
}