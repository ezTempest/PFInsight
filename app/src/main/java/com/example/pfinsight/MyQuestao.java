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
    private String resposta1;
    private String resposta2;
    private String resposta3;
    private String resposta4;
    private String resposta5;
    private String idturma;


    public MyQuestao() {
    }

    public MyQuestao(String idquestao, String questao, String r1, String r2, String r3, String r4, String r5, String resposta1, String resposta2, String resposta3, String resposta4, String resposta5,String idturma) {
        this.idquestao = idquestao;
        this.questao = questao;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
        this.resposta1 = resposta1;
        this.resposta2 = resposta2;
        this.resposta3 = resposta3;
        this.resposta4 = resposta4;
        this.resposta5 = resposta5;
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
    public void setr1(String resposta) {
        this.r1 = resposta;
    }
    public void setr2(String resposta) {
        this.r2 = resposta;
    }
    public void setr3(String resposta) {
        this.r3 = resposta;
    }
    public void setr4(String resposta) {
        this.r4 = resposta;
    }
    public void setr5(String resposta) {
        this.r5 = resposta;
    }
    //------------------------------------------------\\
    public String getRespostas1() {
        return resposta1;
    }
    public void setRespostas1(String resposta1) {
        this.resposta1 = resposta1;
    }
    //------------------------------------------------\\
    public String getRespostas2() {
        return resposta2;
    }
    public void setRespostas2(String resposta2) {
        this.resposta2 = resposta2;
    }
    //------------------------------------------------\\
    public String getRespostas3() {
        return resposta3;
    }
    public void setRespostas3(String resposta3) {
        this.resposta3 = resposta3;
    }
    //------------------------------------------------\\
    public String getRespostas4() {
        return resposta4;
    }
    public void setRespostas4(String resposta4) {
        this.resposta4 = resposta4;
    }
    //------------------------------------------------\\
    public String getRespostas5() {
        return resposta5;
    }
    public void setRespostas5(String resposta5) {
        this.resposta5 = resposta5;
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
                ", resposta1='" + resposta1 + '\'' +
                ", resposta2='" + resposta2 + '\'' +
                ", resposta3='" + resposta3 + '\'' +
                ", resposta4='" + resposta4 + '\'' +
                ", resposta5='" + resposta5 + '\'' +
                ", idturma='" + idturma + '\'' +
                '}';
    }
}
