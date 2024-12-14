package com.example.pfinsight;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Context context = null;
    public List<MyDocument> documents;
    public static MyDocument doc;
    public <E> MyRecyclerViewAdapter(List<MyDocument> es) {
        this.context = context;
    }

    public MyRecyclerViewAdapter(Context context, List<String> data) {
        this.context = context;
        this.documents = data != null ? documents : new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerviewiturma, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyDocument document = documents.get(position);
        holder.nome.setText(document.getNome());
        holder.desc.setText(document.getDesc());
        holder.membros.setText(document.getMembrosNumero());
        System.out.println(document.getDataAula().get("seg"));
    }


    public int getItemCount() {
        try {
            System.out.println(documents.size());
            return documents.size();
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }


    public interface OnItemClickListener {
        void onItemClick(MyDocument document);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }





    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome, desc, membros;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.tvmembro);
            desc = itemView.findViewById(R.id.dT);
            membros = itemView.findViewById(R.id.oM);


            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            System.out.println("posição" + position);
            if (position != RecyclerView.NO_POSITION) {
                MyDocument document = documents.get(position);
                doc = document;

                System.out.println("teste vagabundo teste dnv "+document.getNome());
                Intent intent = new Intent(view.getContext(), turma.class);
                SharedViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) view.getContext()).get(SharedViewModel.class);
                viewModel.setSelectedObject(document);
                view.getContext().startActivity(intent);
            }
        }
    }
}