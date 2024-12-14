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
import java.util.List;
import java.util.Map;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

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
        db.collection("turmas")
                .whereArrayContains("membros", userId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<MyDocument> documents = new ArrayList<>();
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        MyDocument document = documentSnapshot.toObject(MyDocument.class);

                        document.setDocumentId(documentSnapshot.getId());
                        documents.add(document);

                    }
                    MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(documents);
                    recyclerView.setAdapter(adapter);

                    adapter.documents = documents; // Update adapter's data
                    adapter.notifyDataSetChanged(); // Refresh RecyclerView
                    System.out.println("teste do sucesso");
                })
                .addOnFailureListener(e -> {
                    System.out.println("erro de merda");
                });
    }

/*    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Criar ou entrar em uma turma?")
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
    }*/
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
                                    Toast.makeText(getApplicationContext(), "Tudo certo porra", Toast.LENGTH_SHORT).show();
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

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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


