package com.example.pfinsight;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class recyclerviewmembros extends RecyclerView.Adapter<recyclerviewmembros.ViewHolder> {
    private List<String> mem;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public recyclerviewmembros(List<String> mem) {
        this.mem = mem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewmembros, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mem.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void updateData(List<String> newData) {
        this.mem = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mem.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvmembro);
        }
    }
}
