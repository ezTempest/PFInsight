package com.example.pfinsight;


import java.util.ArrayList;
import java.util.HashMap;

public class MyDocument {
    private String documentId;
    private String nome;
    private String desc;
    private HashMap<String,String> dataAula;
    private ArrayList<String> membros;

    public MyDocument() {} //tem que estar aqui


    public MyDocument(String documentId, String nome, String desc, HashMap<String,String> dataAula, ArrayList<String> membros) {
        this.documentId = documentId;
        this.nome = nome;
        this.desc = desc;
        this.dataAula = dataAula;
        this.membros = membros;
    }
    //---------------------------------//---------------------------------//
    public String getDocumentId(){
        return documentId;
    }
    public void setDocumentId(String documentId){
        this.documentId = documentId;
    }
    //---------------------------------//---------------------------------//
    public String getNome(){
        System.out.println(nome);
        return nome;
    }
    public void SetName(){
        this.nome = nome;
    }
    //---------------------------------//---------------------------------//
    public String getDesc(){
        System.out.println(desc);
        return desc;
    }
    public void setDesc(){
        this.desc = desc;
    }
    //---------------------------------//---------------------------------//
    public HashMap<String,String> getDataAula(){
        return dataAula;
    }
    public void setDataAula(){
        this.dataAula = dataAula;
    }
    //---------------------------------//---------------------------------//
    public ArrayList<String> getMembros(){
        return membros;
    }
    public void setMembros() {
        this.membros = membros;
    }
    //---------------------------------//---------------------------------//
    public String getHorarioSeg(){
        return dataAula.get("seg");
    }
    public String getHorarioTer(){
        return dataAula.get("ter");
    }
    public String getHorarioQua(){
        return dataAula.get("qua");
    }
    public String getHorarioQui(){
        return dataAula.get("qui");
    }
    public String getHorarioSex(){
        return dataAula.get("sex");
    }
    public String getHorarioSab(){
        return dataAula.get("sab");
    }
    //---------------------------------//---------------------------------//
    public String getMembrosNumero(){
        String n = String.valueOf(membros.size());
        return n;
    }
}