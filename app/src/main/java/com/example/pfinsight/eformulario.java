package com.example.pfinsight;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class eformulario extends AppCompatActivity  {

    private static final String TAG = "FormListFragment";
    MyDocument doc = MyRecyclerViewAdapter.doc;

    private RecyclerView recyclerView;
    private FormularioRecyAdapter adapter;
    private FirebaseFirestore db;
    private FloatingActionButton btCriarForm;

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_editarform);
        System.out.println("intent do eformulario 3");

        btCriarForm = findViewById(R.id.btCriarForm);

        btCriarForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(eformulario.this, cformulario.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        adapter = new FormularioRecyAdapter(new ArrayList<>(), db, this); // Pass db instance to adapter
        recyclerView.setAdapter(adapter);


        puxarItens();

    }

    private void puxarItens() {
        db.collection("form")
                .whereEqualTo("idturma", doc.getDocumentId().toString())
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Map<String, Object>> formDataList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        formDataList.add(document.getData());
                    }
                    adapter.updateData(formDataList);
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error getting documents.", e);
                });
    }



}
