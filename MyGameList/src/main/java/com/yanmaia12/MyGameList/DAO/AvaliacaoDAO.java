package com.yanmaia12.MyGameList.DAO;

import com.yanmaia12.MyGameList.model.Avaliacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AvaliacaoDAO {
    public Connection connection;

    public AvaliacaoDAO(Connection connection){
        this.connection = connection;
    }

    public void criarTabelaAvaliacao(){
        String sql = """
                CREATE TABLE IF NOT EXISTS avaliacao (
                id INTEGER PRIMARY KEY AUTO_INCREMENT,
                usuario_id INTEGER NOT NULL,
                jogo_id INTEGER NOT NULL,
                nomeJogo TEXT NOT NULL,
                avaliacao DOUBLE NOT NULL,
                FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                );
                """;

        try(Statement statement = connection.createStatement()){
            statement.execute(sql);
        }catch (SQLException e){
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void criarAvaliacao(Avaliacao avaliacaoUser){
        String sql = "INSERT INTO avaliacao (usuario_id, jogo_id, nomeJogo, avaliacao) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, avaliacaoUser.getUserId());
            ps.setInt(2, avaliacaoUser.getJogoId());
            ps.setString(3, avaliacaoUser.getNomeJogo());
            ps.setDouble(4, avaliacaoUser.getNota());

            ps.executeUpdate();
            System.out.println("Avaliou %s com %.2f".formatted(avaliacaoUser.getNomeJogo(), avaliacaoUser.getNota()));
        }catch (SQLException e){
            System.out.println("Erro ao avaliar:" + e.getMessage());
        }
    }
}
