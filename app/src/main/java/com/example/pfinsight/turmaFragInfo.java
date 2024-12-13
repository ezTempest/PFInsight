package com.example.pfinsight;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

public class turmaFragInfo extends Fragment {
    TextView nomeT, descT;
    TextView hSeg, hTer, hQua, hQui, hSex, hSab;
    Button btCopCod, btEditForm;
    Button BtEditTurma;
    private Intent intent;
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragturma, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nomeT = view.findViewById(R.id.tvmembro);
        descT = view.findViewById(R.id.descT);
        hSeg = view.findViewById(R.id.hSeg);
        hTer = view.findViewById(R.id.hTer);
        hQua = view.findViewById(R.id.hQua);
        hQui = view.findViewById(R.id.hQui);
        hSex = view.findViewById(R.id.hSex);
        hSab = view.findViewById(R.id.hSab);
        btCopCod = view.findViewById(R.id.btCopCod);
        btEditForm = view.findViewById(R.id.btEditForm);
        BtEditTurma = view.findViewById(R.id.BtEditTurma);
        MyDocument doc = MyRecyclerViewAdapter.doc;
        System.out.println(FirebaseAuth.getInstance().getCurrentUser().getUid());
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println("teste teste "+id);
        System.out.println("teste teste "+doc.getMembros().get(0));
        if (id.equals(doc.getMembros().get(0))) {
            BtEditTurma.setVisibility(View.VISIBLE);
            btCopCod.setVisibility(View.VISIBLE);
            btEditForm.setVisibility(View.VISIBLE);
            System.out.println("ta dentro de onde deveria estar");
            intent = new Intent(getContext(), eformulario.class);
        } else {
            BtEditTurma.setVisibility(View.GONE);
            btEditForm.setText("Responder formulário");
            btCopCod.setVisibility(View.GONE);
            intent = new Intent(getContext(), rformulario.class);
        }
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        if (doc == null) {
            System.out.println("ta nulo essa bosta");
        }


        nomeT.setText(doc.getNome());
        descT.setText(doc.getDesc());
        if (doc.getHorarioSeg().equals("00:00")) {
            hSeg.setText("Segunda: Sem Aula");
        } else {
            hSeg.setText("Segunda: " + doc.getHorarioSeg());
        }
        if (doc.getHorarioTer().equals("00:00")) {
            hTer.setText("Terça: Sem Aula");
        } else {
            hTer.setText("Terça: " + doc.getHorarioTer());
        }
        if (doc.getHorarioQua().equals("00:00")) {
            hQua.setText("Quarta: Sem Aula");
        } else {
            hQua.setText("Quarta: " + doc.getHorarioQua());
        }
        if (doc.getHorarioQui().equals("00:00")) {
            hQui.setText("Quinta: Sem Aula");
        } else {
            hQui.setText("Quinta: " + doc.getHorarioQui());
        }
        if (doc.getHorarioSex().equals("00:00")) {
            hSex.setText("Sexta: Sem Aula");
        } else {
            hSex.setText("Sexta: " + doc.getHorarioSex());
        }
        if (doc.getHorarioSab().equals("00:00")) {
            hSab.setText("Sábado: Sem Aula");
        } else {
            hSab.setText("Sábado: " + doc.getHorarioSab());
        }

        BtEditTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), resultadosForms.class);
                startActivity(intent);
            }
        });

        btCopCod.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("DENTRO DO COPCOD"+doc.getDocumentId());
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                String codigoturma = doc.getDocumentId().toString();
                ClipData clip = ClipData.newPlainText("label", codigoturma);
                if (clipboard != null) {
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getContext(), "Código de entrada copiado!", Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("TA VAZIO CARALHOOOOOOOOOOOOO");
                }

            }
        });
        btEditForm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(intent);
            }
        });





    }

    private Object getSystemService(String clipboardService) {
        return null;
    }

}

