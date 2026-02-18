package com.yanmaia12.MyGameList.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class User {
    private int id;
    private String nome;
    private String username;
    private String email;
    private String senha;
    private LocalDate dataCadastro;

    public User(){}

    public User(String nome, String username, String email, String senha, LocalDate dataCadastro) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
    }

    public User(int id, String nome, String username, String email, String senha, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
    }

    public String dataUser(){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("MMMM 'de' yyyy", new Locale("pt", "BR"));

        return this.dataCadastro.format(formatador);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
