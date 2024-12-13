package com.example.pfinsight;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestaoRecyAdapter extends RecyclerView.Adapter<QuestaoRecyAdapter.ViewHolder> {
    private List<MyQuestao> questions;
    private Map<String, Integer> answers = new HashMap<>(); // Store answers

    public QuestaoRecyAdapter(List<MyQuestao> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_pergunta, parent, false); // Replace with your item layout file
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyQuestao question = questions.get(position);
        holder.questionTextView.setText(question.getQuestao());

        holder.radioGroup.removeAllViews();
        for (int i = 1; i <= 5; i++) {
            String alternative = question.getResposta(i);
            if (alternative != null) {
                RadioButton radioButton = new RadioButton(holder.itemView.getContext());
                radioButton.setText(alternative);
                radioButton.setId(View.generateViewId());
                radioButton.setTag(i);
                holder.radioGroup.addView(radioButton);
            }
        }

        holder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedRadioButton = holder.itemView.findViewById(checkedId);
            if (selectedRadioButton != null) {
                int opselec = (int) selectedRadioButton.getTag();
                answers.put(question.getDocumentId(), opselec);
            }
        });
    }

    @Override
    public int getItemCount() {

        return questions.size();
    }

    public void updateData(List<MyQuestao> newData) {
        this.questions = newData;
        notifyDataSetChanged();
    }

    public Map<String, Integer> getAnswers() {
        return answers;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView questionTextView;
        public RadioGroup radioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            radioGroup = itemView.findViewById(R.id.radioGroup);
        }
    }
}
