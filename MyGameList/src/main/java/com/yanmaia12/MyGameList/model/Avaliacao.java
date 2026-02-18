package com.yanmaia12.MyGameList.model;

public class Avaliacao{
    private int id;
    private int userId;
    private int jogoId;
    private String nomeJogo;
    private Double nota;

    public Avaliacao(int userId, int jogoId, String nomeJogo, Double nota) {
        this.userId = userId;
        this.jogoId = jogoId;
        this.nomeJogo = nomeJogo;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getJogoId() {
        return jogoId;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public Double getNota() {
        return nota;
    }
}
