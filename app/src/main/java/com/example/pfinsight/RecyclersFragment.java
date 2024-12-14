package com.example.pfinsight;

import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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
                        String professorId = documentSnapshot.getString("professor");
                        if (professorId != null) {
                            FirebaseFirestore.getInstance()
                                    .collection("users")
                                    .whereEqualTo("id", professorId)
                                    .get()
                                    .addOnSuccessListener(prof -> {
                                        if (!prof.isEmpty()) {
                                            DocumentSnapshot profDoc = prof.getDocuments().get(0);
                                            nomeprof.setText(profDoc.getString("nome"));
                                        }
                                    });
                        }

                        List<String> membros = (List<String>) documentSnapshot.get("membros");
                        if (membros != null && !membros.isEmpty()) {
                            ArrayList<String> usuarios = new ArrayList<>();

                            int batchSize = 10;
                            List<Task<QuerySnapshot>> tasks = new ArrayList<>();

                            for (int i = 0; i < membros.size(); i += batchSize) {
                                List<String> batch = membros.subList(i, Math.min(i + batchSize, membros.size()));
                                Task<QuerySnapshot> task = FirebaseFirestore.getInstance()
                                        .collection("users")
                                        .whereIn("id", batch)
                                        .get();
                                tasks.add(task);
                            }

                            Tasks.whenAllComplete(tasks)
                                    .addOnSuccessListener(taskResults -> {
                                        for (Task<?> task : taskResults) {
                                            if (task.isSuccessful()) {
                                                QuerySnapshot querySnapshot = (QuerySnapshot) task.getResult();
                                                for (DocumentSnapshot user : querySnapshot.getDocuments()) {
                                                    usuarios.add(user.getString("nome"));
                                                }
                                            }
                                        }
                                        if (!usuarios.isEmpty()) {
                                            adapter.updateData(usuarios);
                                        } else {
                                            Toast.makeText(getContext(), "Essa turma não possui alunos", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(getContext(), "Essa turma não possui membros.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("Firestore", "Document does not exist.");
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error fetching turmas document: ", e);
                });


        return view;
    }}
