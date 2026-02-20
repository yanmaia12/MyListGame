package com.yanmaia12.MyGameList.service;

import com.yanmaia12.MyGameList.DAO.AvaliacaoDAO;
import com.yanmaia12.MyGameList.DAO.UserDAO;
import com.yanmaia12.MyGameList.DB.Conexao;
import com.yanmaia12.MyGameList.model.Avaliacao;
import com.yanmaia12.MyGameList.model.Jogo;
import com.yanmaia12.MyGameList.model.User;

import java.io.IOException;
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
            if (jogos == null || jogos.length == 0){
                System.out.println("Nenhum jogo encontrado, voltando para o menu!");
                return;
            }
            System.out.println("Escreva o número do jogo que quer avaliar: ");
            for (int i = 0; i < jogos.length; i++) {
                System.out.println((i + 1) + " - " + jogos[i]);
            }
            System.out.println();
            System.out.print("Insira aqui: ");
            int num = sc.nextInt();
            sc.nextLine();

            if (num > jogos.length || num < 1){
                System.out.println("Número fora dos limites!");
                return;
            }

            double avaliacao = 0.0;
            while (true) {
                System.out.print("Qual a sua avaliação sobre o jogo (1-10): ");
                String avaliacaoSTR = sc.nextLine();
                avaliacaoSTR = avaliacaoSTR.replace(",", ".");

                try {
                    avaliacao = Double.parseDouble(avaliacaoSTR);
                    if (avaliacao >= 0 && avaliacao <= 10) {
                        break;
                    } else {
                        System.out.println("A nota precisa ser entre 0 e 10! (ex: 8.8)");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Por favor, digite apenas números! (ex: 8.5)");
                }
            }

            Avaliacao avaliacaoUser = new Avaliacao(user.getId(), jogos[num-1].id(), jogos[num-1].name(), avaliacao);

            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(Conexao.getConnection());

            avaliacaoDAO.criarAvaliacao(avaliacaoUser);


        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void menuUsuarioLogado(Scanner sc, User usuarioLogado) {
        boolean logado = true;
        AvaliacaoDAO  avaliacaoDAO = new AvaliacaoDAO(Conexao.getConnection());

        while (logado) {
            System.out.println();
            System.out.print("1 - Avaliar um Jogo\n2 - Ver perfil\n3 - Listar jogos avaliados\n4 - Atualizar nota (precisa do id do jogo)\n5 - Apagar avaliação (precisa do id do jogo)\n0 - Sair\nInsira aqui: ");
            int escolha = sc.nextInt();
            System.out.println();
            sc.nextLine();
            Igdb igdb = new Igdb();

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
                    System.out.println();
                    avaliacaoDAO.listarAvaliacoesuser(usuarioLogado);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Digite o id do jogo: ");
                    int idJogo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Digite a nova nota entre 1-10 (ex: 8.8): ");
                    double novaNota = sc.nextDouble();
                    sc.nextLine();
                    avaliacaoDAO.atualizarNota(idJogo, novaNota, usuarioLogado);
                    break;
                case 5:
                    System.out.print("Digite o id do jogo: ");
                    int idJogoDelete = sc.nextInt();
                    sc.nextLine();
                    try {
                        Jogo jogoApagar = igdb.buscarJogoID(idJogoDelete);
                        if (jogoApagar != null) {
                            System.out.print("Tem certeza que deseja apagar a avaliação para %s? (s/n): ".formatted(jogoApagar));
                            String respo = sc.nextLine().toLowerCase().trim();
                            if (respo.equals("s") || respo.equals("sim")) {
                                avaliacaoDAO.deleteAvaliacao(usuarioLogado, idJogoDelete);
                            } else {
                                System.out.println("Exclusão cancelada!");
                            }
                        } else {
                            System.out.println("Jogo não encontrado na api com esse id!");
                        }
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Erro ao buscar jogo na api: " + e.getMessage());
                    }
                    break;
                case 0:
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
                    String email = "";
                    while (true){
                        System.out.print("Digite seu email: ");
                        email = sc.nextLine();
                        if (!email.contains("@") || !email.contains(".com")){
                            System.out.println("O email precisa ter @ e .com!");
                        }else if (userDAO.emailExists(email)){
                            System.out.println("Esse email ja possui um cadastro no app!");
                        }else{
                            break;
                        }
                    }
                    String username = "";
                    while (true){
                        System.out.print("Digite seu username: ");
                        username = sc.nextLine();
                        if (username.contains(" ")){
                            System.out.println("O nome de usuário nao pode conter espaços!");
                        }else if (userDAO.usernameExists(username)){
                            System.out.println("Esse username já existe, tente outro!");
                        }else{
                            break;
                        }
                    }
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
