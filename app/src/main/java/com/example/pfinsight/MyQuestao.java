package com.example.pfinsight;

import androidx.annotation.Keep;

public class MyQuestao {
    private String idquestao;
    private String questao;
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private String r5;
    private String respostas1;
    private String respostas2;
    private String respostas3;
    private String respostas4;
    private String respostas5;
    private String idturma;


    public MyQuestao() {
    }

    public MyQuestao(String idquestao, String questao, String r1, String r2, String r3, String r4, String r5, String respostas1, String respostas2, String respostas3, String respostas4, String respostas5,String idturma) {
        this.idquestao = idquestao;
        this.questao = questao;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
        this.respostas1 = respostas1;
        this.respostas2 = respostas2;
        this.respostas3 = respostas3;
        this.respostas4 = respostas4;
        this.respostas5 = respostas5;
        this.idturma = idturma;
    }


    public String getDocumentId() {
        return idquestao;
    }

    public void setDocumentId(String documentId) {
        this.idquestao = documentId;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }
    //------------------------------------------------\\
    public String getIdturma() {
        return idturma;
    }
    public void setIdturma(String idturma) {
        this.idturma = idturma;
    }

    //------------------------------------------------\\
    public void setr1(String respostas) {
        this.r1 = respostas;
    }
    public void setr2(String respostas) {
        this.r2 = respostas;
    }
    public void setr3(String respostas) {
        this.r3 = respostas;
    }
    public void setr4(String respostas) {
        this.r4 = respostas;
    }
    public void setr5(String respostas) {
        this.r5 = respostas;
    }
    //------------------------------------------------\\
    public String getRespostas1() {
        return respostas1;
    }
    public void setRespostas1(String respostas1) {
        this.respostas1 = respostas1;
    }
    //------------------------------------------------\\
    public String getRespostas2() {
        return respostas2;
    }
    public void setRespostas2(String respostas2) {
        this.respostas2 = respostas2;
    }
    //------------------------------------------------\\
    public String getRespostas3() {
        return respostas3;
    }
    public void setRespostas3(String respostas3) {
        this.respostas3 = respostas3;
    }
    //------------------------------------------------\\
    public String getRespostas4() {
        return respostas4;
    }
    public void setRespostas4(String respostas4) {
        this.respostas4 = respostas4;
    }
    //------------------------------------------------\\
    public String getRespostas5() {
        return respostas5;
    }
    public void setRespostas5(String respostas5) {
        this.respostas5 = respostas5;
    }
    //------------------------------------------------\\
    public String getResposta(int index) {
        switch (index) {
            case 1: return r1;
            case 2: return r2;
            case 3: return r3;
            case 4: return r4;
            case 5: return r5;
            default: return null;
        }
    }

    @Override
    public String toString() {
        return "MyQuestao{" +
                "idquestao='" + idquestao + '\'' +
                ", questao='" + questao + '\'' +
                ", r1='" + r1 + '\'' +
                ", r2='" + r2 + '\'' +
                ", r3='" + r3 + '\'' +
                ", r4='" + r4 + '\'' +
                ", r5='" + r5 + '\'' +
                ", respostas1='" + respostas1 + '\'' +
                ", respostas2='" + respostas2 + '\'' +
                ", respostas3='" + respostas3 + '\'' +
                ", respostas4='" + respostas4 + '\'' +
                ", respostas5='" + respostas5 + '\'' +
                ", idturma='" + idturma + '\'' +
                '}';
    }
}
