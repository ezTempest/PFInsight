package com.example.pfinsight;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.text.InputType;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class inicial extends AppCompatActivity implements
        MyRecyclerViewAdapter.OnItemClickListener {

        private RecyclerView recyclerView;
        private MyRecyclerViewAdapter adapter;
        private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial);
        System.out.println("teste de merda1");

        Toolbar toolbar = findViewById(R.id.barra);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.btCriarForm);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(new ArrayList<>());


        db = FirebaseFirestore.getInstance();

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println(userId);
        Task<QuerySnapshot> membrosQuery = db.collection("turmas")
                .whereArrayContains("membros", userId)
                .get();

        Task<QuerySnapshot> professorQuery = db.collection("turmas")
                .whereEqualTo("professor", userId)
                .get();

        Tasks.whenAllSuccess(membrosQuery, professorQuery)
                .addOnSuccessListener(tasks -> {
                    Set<DocumentSnapshot> resultSet = new HashSet<>();

                    QuerySnapshot membrosResult = (QuerySnapshot) tasks.get(0);
                    resultSet.addAll(membrosResult.getDocuments());

                    QuerySnapshot professorResult = (QuerySnapshot) tasks.get(1);
                    resultSet.addAll(professorResult.getDocuments());

                    List<MyDocument> results = new ArrayList<>();
                    for (DocumentSnapshot doc: resultSet) {
                        MyDocument document = doc.toObject(MyDocument.class);
                        document.setDocumentId(doc.getId());
                        results.add(document);
                    }

                    MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(results);
                    recyclerView.setAdapter(adapter);

                    adapter.documents = results;
                    adapter.notifyDataSetChanged();
                    System.out.println("teste do sucesso");
                });
    }


        private void showAlertDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.MyAlertDialogStyle));
            builder.setTitle("Criar ou entrar em uma turma?")
                    .setMessage("Você deseja criar uma turma nova ou entrar em uma já existente?")
                    .setPositiveButton("Entrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showAlertDialogWithEditText();
                        }
                    })
                    .setNegativeButton("Criar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(inicial.this, cturma.class);
                            startActivity(intent);
                        }
                    })
                    .show();
        }
    private void showAlertDialogWithEditText() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new
                ContextThemeWrapper(this, R.style.MyAlertDialogStyle));
        builder.setTitle("Insira o código da turma:");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String entradaCod = input.getText().toString();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("turmas").document(entradaCod);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user != null) {
                                    String uid = user.getUid();
                                    DocumentReference docRef1 = db.collection("turmas").document(entradaCod);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("membros", FieldValue.arrayUnion(uid));
                                    docRef1.update(updates)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    System.out.println("add com sucesso");                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    System.out.println("foi pro saco essa bosta peluda");                                                }
                                            });
                                    Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                                }
                                System.out.println("turma existe porra");

                            } else {
                                Toast.makeText(getApplicationContext(), "Turma não encontrada.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            System.out.println(task.getException());                        }
                    }
                });
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }
    @Override
    public void onItemClick(MyDocument document) {
        Intent intent = new Intent(this, turma.class);
        intent.putExtra("documentId", document.getDocumentId());
        startActivity(intent);
    }
}


