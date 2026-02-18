package com.yanmaia12.MyGameList.service;

import com.yanmaia12.MyGameList.DAO.AvaliacaoDAO;
import com.yanmaia12.MyGameList.DB.Conexao;
import com.yanmaia12.MyGameList.model.Avaliacao;
import com.yanmaia12.MyGameList.model.Jogo;
import com.yanmaia12.MyGameList.model.User;

import java.sql.Connection;
import java.util.Scanner;

public class UI {
    public void avaliarJogo(Scanner sc, User user){
        Igdb igdb = new Igdb();


        System.out.print("Qual jogo você deseja avaliar? ");
        String jogo = sc.nextLine();

        try {
            Jogo[] jogos = igdb.buscarJogo(jogo);
            System.out.print("Escreva o número do jogo que quer avaliar: ");
            for (int i = 0; i < jogos.length; i++) {
                System.out.println((i + 1) + " - " + jogos[i]);
            }
            System.out.print("Insira aqui: ");
            int num = sc.nextInt();
            sc.nextLine();

            System.out.print("Qual a sua avaliação sobre o jogo (1-10): ");
            double avaliacao = sc.nextInt();
            sc.nextLine();

            Avaliacao avaliacaoUser = new Avaliacao(user.getId(), jogos[num-1].id(), jogos[num-1].name(), avaliacao);

            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(Conexao.getConnection());

            avaliacaoDAO.criarAvaliacao(avaliacaoUser);


        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void menuLogin(Scanner sc){
        System.out.println("-----BEM-VINDO AO MyListGame-----");
        while (true){
            System.out.println("1 - Criar conta\s2 - Fazer login\s0 - Sair");
            int escolha = sc.nextInt();
            sc.nextLine();

            if (escolha == 1){
                System.out.println("Digite");
            }
        }


    }
}
