package com.example.pfinsight;
import static java.security.AccessController.getContext;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;
import java.util.Map;
public class FormularioRecyAdapter extends RecyclerView.Adapter<FormularioRecyAdapter.ViewHolder>{

    private List<Map<String, Object>> formDataList;
    private FirebaseFirestore db;
    private Context context;

    public FormularioRecyAdapter(List<Map<String, Object>> formDataList, FirebaseFirestore db, Context context) {
        this.formDataList = formDataList;
        this.db = db;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_formulario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, Object> formData = formDataList.get(position);
        if (formData == null) {
            System.out.println("o formdata ta nulo");
            return;
        }else{
            System.out.println("o formdata nao ta nulo");
        }
        holder.questionTextView.setText(formData.get("questao").toString());

        holder.deleteButton.setOnClickListener(v -> {
            String documentId = formData.get("idquestao").toString();
            if (documentId == null) {
                System.out.println("deletamento deletamento falhou");
                return;
            }
            db.collection("form").document(documentId)
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        System.out.println("Aquele lá foi pro saco");
                        formDataList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .addOnFailureListener(e -> {
                        System.out.println("deletamento deletação falhou");
                    });
        });
    }

    @Override
    public int getItemCount() {
        return formDataList.size();
    }

    public void updateData(List<Map<String, Object>> newData) {
        this.formDataList = newData;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        @Nullable
        View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView questionTextView;
        public ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
