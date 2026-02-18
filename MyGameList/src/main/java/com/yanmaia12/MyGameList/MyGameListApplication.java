package com.yanmaia12.MyGameList;

import com.yanmaia12.MyGameList.model.Jogo;
import com.yanmaia12.MyGameList.service.Igdb;
import com.yanmaia12.MyGameList.service.TwitchAuth;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MyGameListApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MyGameListApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Igdb igdb = new Igdb();

		Jogo[] jogos = igdb.buscarJogo("zelda");
		for (int i = 0; i < jogos.length; i++) {
			System.out.println((i + 1) + " - " + jogos[i]);
		}
	}
}
