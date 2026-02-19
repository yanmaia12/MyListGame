package com.yanmaia12.MyGameList;

import com.yanmaia12.MyGameList.DAO.AvaliacaoDAO;
import com.yanmaia12.MyGameList.DAO.UserDAO;
import com.yanmaia12.MyGameList.DB.Conexao;
import com.yanmaia12.MyGameList.model.Jogo;
import com.yanmaia12.MyGameList.service.Igdb;
import com.yanmaia12.MyGameList.service.TwitchAuth;
import com.yanmaia12.MyGameList.service.UI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MyGameListApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MyGameListApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Connection conexao = Conexao.getConnection();

		if (conexao != null) {
			UserDAO userDAO = new UserDAO(conexao);
			AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(conexao);
			userDAO.createUserTable();
			avaliacaoDAO.criarTabelaAvaliacao();

			Scanner sc = new Scanner(System.in);
			UI interfaceUsuario = new UI();

			interfaceUsuario.menuLogin(sc);

		} else {
			System.out.println("Não foi possível iniciar o MyGameList pois o banco de dados está offline.");
		}
	}
}
