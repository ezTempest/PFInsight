package com.example.pfinsight;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class RecyclersFragment extends Fragment {
    private TextView nomeprof;
    private RecyclerView membros;
    private recyclerviewmembros adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragturmarec, container, false);
        MyDocument doc = MyRecyclerViewAdapter.doc;
        nomeprof = view.findViewById(R.id.tvmembro);
        membros = view.findViewById(R.id.recview);

        membros.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new recyclerviewmembros(new ArrayList<>());
        membros.setAdapter(adapter);
        
        FirebaseFirestore.getInstance().collection("turmas")
                .document(doc.getDocumentId())
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        List<String> membros = (List<String>) documentSnapshot.get("membros");
                        if (membros != null && !membros.isEmpty()) {
                            if(membros.size() == 1){
                                nomeprof.setText(membros.get(0));
                                Toast.makeText(getContext(), "Essa turma não possui alunos", Toast.LENGTH_SHORT).show();
                            }else {
                                System.out.println(membros.size());
                                System.out.println(membros.get(1));
                                nomeprof.setText(membros.get(0));
                                adapter.updateData(membros.subList(1, membros.size()));
                            }
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    System.out.println("erro grande pra caralho");
                });

        return view;
    }}
