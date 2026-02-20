package com.yanmaia12.MyGameList.service;

import com.yanmaia12.MyGameList.DAO.AvaliacaoDAO;
import com.yanmaia12.MyGameList.DB.Conexao;
import com.yanmaia12.MyGameList.model.Jogo;
import com.yanmaia12.MyGameList.model.User;
import org.apache.catalina.mapper.Mapper;

import java.util.*;
import java.util.stream.Collectors;

public class Calculos {
    AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(Conexao.getConnection());

    public void calculosAv(User userlogado){
        List<Jogo> listaJogos = avaliacaoDAO.listarAvaliacoesuserLista(userlogado);

        if (listaJogos.isEmpty()){
            System.out.println("Você ainda não tem nenhuma avaliação!");
        }else {
            DoubleSummaryStatistics dst = listaJogos.stream().collect(Collectors.summarizingDouble(Jogo::rating));

            System.out.println("Você avaliou um total de %d jogos, com a avaliação média de %.2f⭐!".formatted(dst.getCount(), dst.getAverage()));

            Optional<Jogo> melhorNota = listaJogos.stream().max(Comparator.comparingDouble(Jogo::rating));

            Optional<Jogo> piorNota = listaJogos.stream().min(Comparator.comparingDouble(Jogo::rating));

            melhorNota.ifPresent(jogo -> System.out.println("O seu jogo melhor avaliado foi: '%s' com %.2f⭐!".formatted(jogo.name(), jogo.rating())));

            piorNota.ifPresent(jogo -> System.out.println("O seu jogo pior avaliado foi: '%s' com %.2f⭐!".formatted(jogo.name(), jogo.rating())));
            System.out.println();
        }
    }
}
