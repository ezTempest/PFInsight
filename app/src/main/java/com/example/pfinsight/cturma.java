package com.example.pfinsight;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class cturma extends AppCompatActivity {
    EditText nomeTurma, descTurma;
    EditText tempoSeg, tempoTer, tempoQua, tempoQui, tempoSex, tempoSab;
    Button cancelar;
    Button criarTurma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cturma);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            nomeTurma = findViewById(R.id.nomeTurma);
            descTurma = findViewById(R.id.descTurma);
            tempoSeg = findViewById(R.id.tempoSeg);
            tempoTer = findViewById(R.id.tempoTer);
            tempoQua = findViewById(R.id.tempoQua);
            tempoQui = findViewById(R.id.tempoQui);
            tempoSex = findViewById(R.id.tempoSex);
            tempoSab = findViewById(R.id.tempoSab);
            cancelar = findViewById(R.id.cancelar);
            criarTurma = findViewById(R.id.btCriar);
            String documentId = UUID.randomUUID().toString();









            //bt do cancelamento do twitter
            cancelar.setOnClickListener(v -> {
                Intent Intent = new
                Intent(this, inicial.class);
                startActivity(Intent);
                    });





            // c turma
            criarTurma.setOnClickListener(v -> {

                String nome = nomeTurma.getText().toString();
                String desc = descTurma.getText().toString();
                String seg = tempoSeg.getText().toString();
                String ter = tempoTer.getText().toString();
                String qua = tempoQua.getText().toString();
                String qui = tempoQui.getText().toString();
                String sex = tempoSex.getText().toString();
                String sab = tempoSab.getText().toString();


                if(nome.isEmpty() || desc.isEmpty()){
                    Toast.makeText(this, "Preencha os campos obrigatórios, nome e descrição", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(seg.isEmpty() && ter.isEmpty() && qua.isEmpty() && qui.isEmpty() && sex.isEmpty() && sab.isEmpty()){
                    Toast.makeText(this, "Preencha o horário de pelo menos uma aula", Toast.LENGTH_SHORT).show();
                    return;
                }
                //formatar c nao ta vazio
                if(!seg.isEmpty()){
                    seg = StringModifier.insertColonAtSecondPosition(seg);
                }
                if(!ter.isEmpty()){
                    ter = StringModifier.insertColonAtSecondPosition(ter);
                }
                if(!qua.isEmpty()){
                    qua = StringModifier.insertColonAtSecondPosition(qua);
                }
                if(!qui.isEmpty()) {
                    qui = StringModifier.insertColonAtSecondPosition(qui);
                }
                if(!sex.isEmpty()){
                    sex = StringModifier.insertColonAtSecondPosition(sex);
                }
                if(!sab.isEmpty()) {
                    sab = StringModifier.insertColonAtSecondPosition(sab);
                }
                //formatar as vazias
                if(seg.isEmpty()){
                    seg = "00:00";
                }
                if(ter.isEmpty()){
                    ter = "00:00";
                }
                if(qua.isEmpty()){
                    qua = "00:00";
                }
                if(qui.isEmpty()){
                    qui = "00:00";
                }
                if(sex.isEmpty()){
                    sex = "00:00";
                }
                if(sab.isEmpty()){
                    sab = "00:00";
                }
                ArrayList<String> membros1 = new ArrayList<>();
                Map<String, Object> turmaData = new HashMap<>();
                turmaData.put("membros", membros1);

                String professor = new String();
                professor = FirebaseAuth.getInstance().getCurrentUser().getUid();
                turmaData.put("professor", professor);

                Map<String, Object> dataAula = new HashMap<>();
                dataAula.put("seg", seg);
                dataAula.put("ter", ter);
                dataAula.put("qua", qua);
                dataAula.put("qui", qui);
                dataAula.put("sex", sex);
                dataAula.put("sab", sab);
                turmaData.put("dataAula", dataAula);

                turmaData.put("nome", nome);
                turmaData.put("desc", desc);

                db.collection("turmas")
                        .document(documentId)
                        .set(turmaData)
                        .addOnSuccessListener(aVoid -> {
                            Intent intent = new Intent(this, inicial.class);
                            Toast.makeText(this, "Turma criada com sucesso", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Erro ao criar turma", Toast.LENGTH_SHORT).show();
                        });
                    });



    };
    public static class StringModifier {

        public static String insertColonAtSecondPosition(String originalString) {
            if (originalString == null || originalString.length() < 2) {
                return originalString;
            }

            StringBuilder modifiedString = new StringBuilder(originalString);
            modifiedString.insert(2, ':');
            return modifiedString.toString();
        }
    }

}