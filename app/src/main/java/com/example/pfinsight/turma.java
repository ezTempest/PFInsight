package com.example.pfinsight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


public class turma extends AppCompatActivity {
    Fragment fragment1 = new Fragment(); // Replace with your fragment classes
    Fragment fragment2 = new Fragment();
    Button bti, btm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turma);
        bti = findViewById(R.id.infoT);
        btm = findViewById(R.id.infoM);
        fragment1 = new turmaFragInfo();
        fragment2 = new RecyclersFragment();

        Toolbar toolbar = findViewById(R.id.barra);
         setSupportActionBar(toolbar);

        SharedViewModel viewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        // Access the selected object from the ViewModel
        MyDocument document = MyRecyclerViewAdapter.doc;
        if (document != null) {
            System.out.println("turmajava"+document.getNome()+" teste teste");
        }else{
            System.out.println("ta nulo turma.java");
        }


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.
                        fragment_container, new
                        turmaFragInfo())
                .commit();

//        private void trocarFrag(Fragment fragment) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, fragment) // Replace with your container ID
//                    .addToBackStack(null) // Optional: Add to back stack for navigation
//                    .commit();
//        }


        bti.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                trocarFrag(fragment1);
            }

            private void trocarFrag(Fragment fragment1) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment1)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trocarFrag(fragment2);
            }

            private void trocarFrag(Fragment fragment2) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();
            }
        });
}
}
