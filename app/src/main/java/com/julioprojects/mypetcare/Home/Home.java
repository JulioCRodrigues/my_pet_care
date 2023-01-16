package com.julioprojects.mypetcare.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.julioprojects.mypetcare.R;
import com.julioprojects.mypetcare.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private RecyclerView recycler;
    private List<TitleTask> titleTaskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        recycler = binding.recyclerTask;

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



    }

    public void createTask(){

        TitleTask titleTask = new TitleTask("Lembretes");
        titleTaskList.add(titleTask);

        titleTask = new TitleTask("Vacinas");
        titleTaskList.add(titleTask);

        titleTask = new TitleTask("Banho e Tosa");
        titleTaskList.add(titleTask);

    }
}