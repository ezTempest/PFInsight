package com.example.pfinsight;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.appcompat.app.AppCompatActivity;

public class entrada extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputLayout usernameLayout;
    private TextInputLayout passwordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        usernameLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);

        findViewById(R.id.loginButton).setOnClickListener(view -> {
            String email = usernameLayout.getEditText().getText().toString();
            String password = passwordLayout.getEditText().getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(entrada.this, "Senha e email né irmao", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(entrada.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                System.out.println(idUsuario);
                                Toast.makeText(entrada.this, "Login feito com sucesso",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(entrada.this, inicial.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(entrada.this, "Falha no login, tente novamente",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }

}
