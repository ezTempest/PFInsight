package com.example.pfinsight;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.ClipData;
import android.content.ClipboardManager;
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
import androidx.lifecycle.viewmodel.CreationExtras;

import com.google.android.gms.common.util.JsonUtils;

public class turmaFragInfo extends Fragment {
    TextView nomeT, descT;
    TextView hSeg, hTer, hQua, hQui, hSex, hSab;
    Button btCopCod, btEditForm, BtEditTurma;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragturma, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        MyDocument doc = MyRecyclerViewAdapter.doc;
        if (doc == null) {
            System.out.println("ta nulo essa bosta");
        }
        nomeT = view.findViewById(R.id.nomeT);
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

        nomeT.setText(doc.getNome());
        descT.setText(doc.getDesc());
        if(doc.getHorarioSeg().equals("00:00")){
            hSeg.setText("Segunda: Sem Aula");
        }else {
            hSeg.setText("Segunda: " + doc.getHorarioSeg());
        }
        if(doc.getHorarioTer().equals("00:00")){
            hTer.setText("Terça: Sem Aula");
        }else {
            hTer.setText("Terça: "+doc.getHorarioTer());
        }
        if(doc.getHorarioQua().equals("00:00")){
            hQua.setText("Quarta: Sem Aula");
        }else {
            hQua.setText("Quarta: "+doc.getHorarioQua());
        }
        if(doc.getHorarioQui().equals("00:00")){
            hQui.setText("Quinta: Sem Aula");
        }else {
            hQui.setText("Quinta: "+doc.getHorarioQui());
        }
        if(doc.getHorarioSex().equals("00:00")){
            hSex.setText("Sexta: Sem Aula");
        }else {
            hSex.setText("Sexta: "+doc.getHorarioSex());
        }
        if(doc.getHorarioSab().equals("00:00")){
            hSab.setText("Sábado: Sem Aula");
        }else {
            hSab.setText("Sábado: "+doc.getHorarioSab());
        }


        btCopCod.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.newPlainText("label", doc.getDocumentId());
//                clipboard.setPrimaryClip(clip);
//                Toast.makeText(getContext(), "Código de entrada copiado!", Toast.LENGTH_SHORT).show();

            }
        });




        // 1. Get references to views in the layout (if needed)
        // Example: TextView textView = view.findViewById(R.id.text_view);

        // 2. Set up any listeners or logic for interactions within the fragment
        // Example: button.setOnClickListener(new View.OnClickListener() { ... });

        // 3. Perform any data loading or initialization
        // Example: loadDataFromDatabase();
    }

    private Object getSystemService(String clipboardService) {
        return null;
    }
//    public CreationExtras getDefaultViewModelCreationExtras() {
//        return super.getDefaultViewModelCreationExtras();
//    }
}

