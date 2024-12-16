package com.example.pfinsight;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class resultadosForms extends AppCompatActivity {
    private RecyclerView recyclerView;
    private resultadoFormsRecView adapter;
    private List<MyQuestao> pergLista;

    MyDocument doc = MyRecyclerViewAdapter.doc;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_resultadoforms);
        System.out.println("-----------------------------------");
        System.out.println(doc.getDocumentId());
        System.out.println("-----------------------------------");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pergLista = new ArrayList<>();
        adapter = new resultadoFormsRecView(pergLista);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        fetchFormData();
    }

    private void fetchFormData() {
        db.collection("form")
                .whereEqualTo("idturma", doc.getDocumentId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MyQuestao perg = new MyQuestao(document.getId(), document.getString("questao"), document.getString("r1"), document.getString("r2"), document.getString("r3"), document.getString("r4"), document.getString("r5"), document.getString("respostas1"), document.getString("respostas2"), document.getString("respostas3"), document.getString("respostas4"), document.getString("respostas5"), document.getString("idturma"));
                                perg.setDocumentId(document.getId());

                                pergLista.add(perg);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            System.out.println("ERRO");
                        }
                    }
                });
    }

}
