package com.example.pfinsight;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerviewmembros extends RecyclerView.Adapter<recyclerviewmembros.ViewHolder> {
    private List<String> mem;


    public recyclerviewmembros(List<String> mem) {
        this.mem = mem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ... other code ...
        // Create and return a ViewHolder for the second RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewmembros, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mem.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle CardView visibility in the second RecyclerView

                // Optional: Update data and notify adapter if needed
                // data.get(position).setVisible(!data.get(position).isVisible()); // Assuming your data model has a visibility property
                // notifyItemChanged(position);
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

    // ... other code ...

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // ... other code ...
        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvmembro); // Replace with your TextView ID
        }
    }
}
