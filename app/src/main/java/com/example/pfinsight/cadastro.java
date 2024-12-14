package com.example.pfinsight;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class cadastro extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputLayout nameLayout;
    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout confirmPasswordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();

        nameLayout = findViewById(R.id.nameLayout);
        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout);
        Button signUpButton = findViewById(R.id.btncadastro);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameLayout.getEditText().getText().toString();
                String email = emailLayout.getEditText().getText().toString();
                String password = passwordLayout.getEditText().getText().toString();
                String confirmPassword = confirmPasswordLayout.getEditText().getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) ||
                        TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(cadastro.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(cadastro.this, "Senhas não coincidem", Toast.LENGTH_SHORT).show();
                    return;
                }

                // metodo bala pra criar usuario
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(cadastro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(cadastro.this, "Cadastro feito com sucesso", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(cadastro.this, inicial.class);
                                    startActivity(intent);

                                } else {

                                    Toast.makeText(cadastro.this, "Falha no cadastro, tente novamente", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                System.out.println("eu sei que ta chegando aqui krl");
                user.put("nome", name);
                user.put("email", email);
                user.put("id", mAuth.getCurrentUser().getUid().toString());
                db.collection("users").document(mAuth.getCurrentUser().getUid().toString()).set(user).addOnSuccessListener(documentReference -> {
                    System.out.println("deu certo o input no bd ");
                });

            }
        });
    }
}
