package com.example.pfinsight;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class rformulario extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private QuestaoRecyAdapter adapter;
    private FirebaseFirestore db;
    private Button submitButton;
    MyDocument doc = MyRecyclerViewAdapter.doc;
    String res1, res2, res3, res4, res5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_responderform);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestaoRecyAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        submitButton = findViewById(R.id.enviar);
        submitButton.setOnClickListener(v -> submitAnswers());

        db = FirebaseFirestore.getInstance();
        fetchQuestions();
    }

    private void fetchQuestions() {
        db.collection("form").whereEqualTo("idturma", doc.getDocumentId()).get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<MyQuestao> questions = new ArrayList<>();
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                MyQuestao question = new MyQuestao(document.getId(), document.getString("questao"), document.getString("r1"), document.getString("r2"), document.getString("r3"), document.getString("r4"), document.getString("r5"), document.getString("respostas1"), document.getString("respostas2"), document.getString("respostas3"), document.getString("respostas4"), document.getString("respostas5"), document.getString("idturma"));
                res1 = question.getRespostas1();
                res2 = question.getRespostas2();
                res3 = question.getRespostas3();
                res4 = question.getRespostas4();
                res5 = question.getRespostas5();
                question.setDocumentId(document.getId());
                questions.add(question);

            }
            adapter.updateData(questions);

        }).addOnFailureListener(e -> {
            System.out.println("DEU TUDO ERRADO EM PEGAR AS COISA DO BANCO /RFORMULARIO");
        });
    }

    private void submitAnswers() {
        Map<String, Integer> MapaResposta = adapter.getAnswers();
        System.out.println("fodase tudo ta dando certo");
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
        System.out.println(res5);
        for (Map.Entry<String, Integer> ODAIR_LER : MapaResposta.entrySet()) {
            String docId = ODAIR_LER.getKey();
            int index = ODAIR_LER.getValue();

            String campoDBsubs = "respostas" + index;
            int xunxo = 0;
            //gambiarra aumentar contador resposta
            if(index == 1){
                xunxo = Integer.parseInt(res1);
            }if(index == 2){
                xunxo = Integer.parseInt(res2);
            }if(index == 3){
                xunxo = Integer.parseInt(res3);
            }if(index == 4){
                xunxo = Integer.parseInt(res4);
            }if(index == 5){
                xunxo = Integer.parseInt(res5);
            }
            Map<String, Object> updates = new HashMap<>();
            xunxo +=1;
            String xunxo1 = String.valueOf(xunxo);
            updates.put(campoDBsubs, xunxo1);

            db.collection("form").document(docId)
                    .update(updates)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Respostas inseridas: " + MapaResposta.size(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, turma.class);
                        startActivity(intent);
                    })
                    .addOnFailureListener(e -> {
                        System.out.println("DEU ERRO NA ENTRADA DAS RESPOTA /RFORMULARIO");
                    });
        }



    }
}
