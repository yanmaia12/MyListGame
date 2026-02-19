package com.yanmaia12.MyGameList.DAO;

import com.yanmaia12.MyGameList.model.User;

import java.sql.*;
import java.time.LocalDate;


public class UserDAO {
    public Connection connection;

    public UserDAO(Connection connection){
        this.connection = connection;
    }

    public void createUserTable(){
        String sql = """
                CREATE TABLE IF NOT EXISTS usuarios (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nome VARCHAR(255) NOT NULL,
                username VARCHAR(50) NOT NULL UNIQUE,
                email VARCHAR(255) NOT NULL UNIQUE,
                senha VARCHAR(255) NOT NULL,
                data_registro DATE NOT NULL
                );
                """;
        try(Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Tabela usuarios criada!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela 'usuarios': " + e.getMessage());
        }
    }

    public boolean emailExists(String email){
        String sql = "SELECT email FROM usuarios WHERE email = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, email);

            try(ResultSet rs = preparedStatement.executeQuery()){
                if (rs.next()){
                    return true;
                }else{
                    return false;
                }
            }

        }catch (SQLException e){
            System.out.println("Erro ao verificar email: " + e.getMessage());
        }
        return true;
    }

    public boolean usernameExists(String usernameNovo){
        String sql = "SELECT username FROM usuarios WHERE username = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, usernameNovo);

            try(ResultSet rs = preparedStatement.executeQuery()){
                if (rs.next()){
                    return true;
                }else{
                    return false;
                }
            }

        }catch (SQLException e){
            System.out.println("Erro ao verificar email: " + e.getMessage());
        }
        return true;
    }

    public void registerUser(User user){
        String sql = "INSERT INTO usuarios (nome, username, email, senha, data_registro) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getSenha());
            preparedStatement.setString(5, String.valueOf(Date.valueOf(user.getDataCadastro())));

            preparedStatement.executeUpdate();
            System.out.println("Usuário %s criado com sucesso!".formatted(user.getUsername()));
        }catch (SQLException e){
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public User fazerLogin(String username, String senha){
        String sql = "SELECT * FROM usuarios WHERE username = ? AND senha = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, senha);

            try(ResultSet rs = preparedStatement.executeQuery()){
                if (rs.next()){
                    User userLogado = new User();

                    userLogado.setId(rs.getInt("id"));
                    userLogado.setNome(rs.getString("nome"));
                    userLogado.setUsername(rs.getString("username"));
                    userLogado.setEmail(rs.getString("email"));
                    userLogado.setSenha(rs.getString("senha"));
                    userLogado.setDataCadastro(rs.getDate("data_registro").toLocalDate());

                    return userLogado;
                }
            }catch (SQLException e){
                System.out.println("Erro ao fazer login: " + e.getMessage());
            }
        }catch (SQLException e){
            System.out.println("Erro ao fazer login: " + e.getMessage());
        }
        return null;
    }
}