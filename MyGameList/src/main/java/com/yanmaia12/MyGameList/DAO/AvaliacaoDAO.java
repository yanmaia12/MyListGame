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
                CREATE TABLE IF NOT EXISTS avaliacoes (
                id INT AUTO_INCREMENT PRIMARY KEY,
                usuario_id INT NOT NULL,
                jogo_id INT NOT NULL,
                nomeJogo VARCHAR(255) NOT NULL,
                avaliacao DOUBLE NOT NULL,
                FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                );
                """;

        try(Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Tabela avaliacoes criada!");
        }catch (SQLException e){
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void criarAvaliacao(Avaliacao avaliacaoUser){
        String sql = "INSERT INTO avaliacoes (usuario_id, jogo_id, nomeJogo, avaliacao) VALUES (?, ?, ?, ?)";

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

    public void atualizarNota(double avaliacaoNv, int idAv){
        String sql = "UPDATE avaliacoes SET avaliacao = ? WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDouble(1, avaliacaoNv);
            ps.setInt(2, idAv);

            int linhas = ps.executeUpdate();
            if (linhas > 0){
                System.out.println("Avaliação atualizada!");
            }
        }catch (SQLException e){
            System.out.println("Erro ao atualizar avaliação: " + e.getMessage());
        }
    }

    public void deleteAvaliacao(int idAV){
        String sql = "DELETE FROM avaliacoes WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, idAV);

            int linhas = ps.executeUpdate();
            if (linhas > 0){
                System.out.println("Avaliação apagada com sucesso!");
            }
        }catch (SQLException e){
            System.out.println("Erro ao apagar avalição: "+e.getMessage());
        }
    }
}
