package com.yogeshaswar.todoproroom;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    //constructor
    private Context context;
    private List<Task> taskList;
    private EditText edtTask;

    public TaskAdapter(Context context, List<Task> taskList, EditText edtTask) {
        this.context = context;
        this.taskList = taskList;
        this.edtTask = edtTask;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_card, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        //set task text
        holder.tvTask.setText(taskList.get(position).getTask());
        //set isPriority text
        //boolean to string conversion

        String priority = "";
        if(taskList.get(position).isPriority == true){
            priority = "High Priority";
            holder.tvPriority.setTextColor(Color.WHITE);
            holder.cardPriority.setBackgroundColor(Color.RED);

        } else {
            priority = "Low Priority";
            holder.tvPriority.setTextColor(Color.WHITE);
            holder.cardPriority.setBackgroundColor(Color.GREEN);


        }
        holder.tvPriority.setText(priority);

        //delete
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                // task
                Task task = taskList.get(holder.getAdapterPosition());
                MainActivity.delete(task);
            }
        });
        //edit
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
                edtTask.setText(taskList.get(holder.getAdapterPosition()).getTask());
                MainActivity.delete(taskList.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTask;
        TextView tvPriority;
        ImageView ivDelete;
        ImageView ivEdit;
        CardView cardPriority;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTask = itemView.findViewById(R.id.txt_show_task);
            tvPriority = itemView.findViewById(R.id.txt_show_priority);
            ivDelete = itemView.findViewById(R.id.iv_delete);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            cardPriority = itemView.findViewById(R.id.cv_priority);
        }
    }
}
