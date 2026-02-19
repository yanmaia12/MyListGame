package com.yanmaia12.MyGameList.service;

import com.yanmaia12.MyGameList.DAO.AvaliacaoDAO;
import com.yanmaia12.MyGameList.DAO.UserDAO;
import com.yanmaia12.MyGameList.DB.Conexao;
import com.yanmaia12.MyGameList.model.Avaliacao;
import com.yanmaia12.MyGameList.model.Jogo;
import com.yanmaia12.MyGameList.model.User;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

public class UI {
    public void avaliarJogo(Scanner sc, User user){
        Igdb igdb = new Igdb();


        System.out.print("Qual jogo você deseja avaliar? ");
        String jogo = sc.nextLine();

        try {
            Jogo[] jogos = igdb.buscarJogo(jogo);
            System.out.println("Escreva o número do jogo que quer avaliar: ");
            for (int i = 0; i < jogos.length; i++) {
                System.out.println((i + 1) + " - " + jogos[i]);
            }
            System.out.println();
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

    public void menuUsuarioLogado(Scanner sc, User usuarioLogado) {
        boolean logado = true;

        while (logado) {
            System.out.println();
            System.out.print("1 - Avaliar um Jogo\n2 - Ver perfil\n3 - Sair\nInsira aqui: ");
            int escolha = sc.nextInt();
            System.out.println();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    avaliarJogo(sc, usuarioLogado);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Nome: " + usuarioLogado.getNome());
                    System.out.println("Email: " + usuarioLogado.getEmail());
                    System.out.println("Membro desde: " + usuarioLogado.dataUser());
                    break;
                case 3:
                    logado = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public void menuLogin(Scanner sc){
        User userD = new User();
        UserDAO userDAO = new UserDAO(Conexao.getConnection());
        System.out.println("-----BEM-VINDO AO MyGameList-----");
        while (true){
            System.out.print("1 - Criar conta\n2 - Fazer login\n0 - Sair\nInsira aqui: ");
            int escolha = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (escolha){
                case 1:
                    System.out.print("Digite seu nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Digite seu email: ");
                    String email = sc.nextLine();
                    System.out.print("Digite seu username: ");
                    String username = sc.nextLine();
                    System.out.print("Digite sua senha: ");
                    String senha = sc.nextLine();
                    User user = new User(nome, username, email, senha, LocalDate.now());
                    userDAO.registerUser(user);
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Digite seu username: ");
                    String usernamelogin = sc.nextLine();
                    System.out.print("Digite sua senha: ");
                    String senhalogin = sc.nextLine();

                    User userLogado = userDAO.fazerLogin(usernamelogin, senhalogin);

                    if (userLogado != null){
                        System.out.println("Login realizado com sucesso!");
                        System.out.println();
                        menuUsuarioLogado(sc, userLogado);
                    }
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Opção Inválida!");
            }
        }


    }
}
