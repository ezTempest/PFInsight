package com.example.pfinsight;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class cformulario extends AppCompatActivity {
    private static final String TAG = "";
    MyDocument doc = MyRecyclerViewAdapter.doc;


    private EditText pergunta;
    private EditText[] opcoes = new EditText[5];
    private Button criarQ, btnlimpar;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_criarform);

        db = FirebaseFirestore.getInstance();

        pergunta = findViewById(R.id.questao);
        opcoes[0] = findViewById(R.id.r1);
        opcoes[1] = findViewById(R.id.r2);
        opcoes[2] = findViewById(R.id.r3);
        opcoes[3] = findViewById(R.id.r4);
        opcoes[4] = findViewById(R.id.r5);

        criarQ = findViewById(R.id.btcriar);
        btnlimpar = findViewById(R.id.btlimpar);

        criarQ.setOnClickListener(v -> criarForm());
        btnlimpar.setOnClickListener(v -> limpalixo());
    }

    private void criarForm() {
        String question = pergunta.getText().toString().trim();

        if (TextUtils.isEmpty(question)) {
            Toast.makeText(this, "Insira uma pergunta", Toast.LENGTH_SHORT).show();
            return;
        }
        String idturma = doc.getDocumentId();
        Map<String, String> dadosForm = new HashMap<>();
        dadosForm.put("questao", question);
        dadosForm.put("idturma", idturma);

        int alternativeCount = 0;
        for (int i = 0; i < opcoes.length; i++) {
            String alternative = opcoes[i].getText().toString().trim();
            if (!TextUtils.isEmpty(alternative)) {
                dadosForm.put("r" + (i + 1), alternative);
                dadosForm.put("respostas"+(i+1), 0 + "");
                alternativeCount++;
            }
        }


        if (alternativeCount < 2) {
            Toast.makeText(this, "Insira pelo menos duas alternativas", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("form").add(dadosForm).addOnSuccessListener(documentReference -> {
            Toast.makeText(this, "Pergunta inserida com sucesso", Toast.LENGTH_SHORT).show();
            limpalixo();
            db.collection("form").document(documentReference.getId()).update("idquestao", documentReference.getId().toString());
            Intent intent = new Intent(cformulario.this, turma.class);
            startActivity(intent);
        }).addOnFailureListener(e -> {
            
            Toast.makeText(this, "Erro na criação, tente novamente mais tarde", Toast.LENGTH_SHORT).show();
        });
    }
        
    

    private void limpalixo() {
        pergunta.setText("");
        for (EditText editText : opcoes) {
            editText.setText("");
        }
    }
}
