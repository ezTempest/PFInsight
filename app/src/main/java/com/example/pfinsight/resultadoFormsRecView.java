package com.example.pfinsight;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class resultadoFormsRecView extends RecyclerView.Adapter<resultadoFormsRecView.ViewHolder>{


    private List<MyQuestao> pergLista;

    public resultadoFormsRecView(List<MyQuestao> pergLista) {
        this.pergLista = pergLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_respostas_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyQuestao perg = pergLista.get(position);
        holder.bigText.setText(perg.getQuestao());
        if(perg.getResposta(1) == null){
            holder.alterna1.setText("");
        }else{
            holder.alterna1.setText(perg.getResposta(1) + " teve "+perg.getRespostas1()+" votos");
        }
        if(perg.getResposta(2) == null){
            holder.alterna2.setText("");
        }else{
            holder.alterna2.setText(perg.getResposta(2) + " teve "+perg.getRespostas2()+" votos");
        }
        if(perg.getResposta(3) == null){
            holder.alterna3.setText("");
        }else{
            holder.alterna3.setText(perg.getResposta(3) + " teve "+perg.getRespostas3()+" votos");
        }
        if(perg.getResposta(4) == null){
            holder.alterna4.setText("");
        }else{
            holder.alterna4.setText(perg.getResposta(4) + " teve "+perg.getRespostas4()+" votos");
        }
        if(perg.getResposta(5) == null){
            holder.alterna5.setText("");
        }else{
            holder.alterna5.setText(perg.getResposta(5) + " teve "+perg.getRespostas5()+" votos");
        }
    }

    @Override
    public int getItemCount() {
        return pergLista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bigText;
        public TextView alterna1;
        public TextView alterna2;
        public TextView alterna3;
        public TextView alterna4;
        public TextView alterna5;
    public ViewHolder(View itemView) {
        super(itemView);
        bigText = itemView.findViewById(R.id.bigText);
        alterna1 = itemView.findViewById(R.id.smallText1);
        alterna2 = itemView.findViewById(R.id.smallText2);
        alterna3 = itemView.findViewById(R.id.smallText3);
        alterna4 = itemView.findViewById(R.id.smallText4);
        alterna5 = itemView.findViewById(R.id.smallText5);
    }
}
}
