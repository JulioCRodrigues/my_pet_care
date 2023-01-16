package com.julioprojects.mypetcare.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.julioprojects.mypetcare.R;

import java.util.List;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.MyViewHolder> {

    private List<TitleTask> listTask;

    public AdapterTask(List<TitleTask> list){
        this.listTask = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemTask = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_task, parent, false);

        return new MyViewHolder(itemTask);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TitleTask titleTask = listTask.get(position);

        holder.title.setText(titleTask.getTitle());

    }

    @Override
    public int getItemCount() {
        return listTask.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title_task);
        }
    }
}
