package com.yanmaia12.MyGameList.DB;

import com.yanmaia12.MyGameList.Keys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection() {

        String url = Keys.DB_URL;
        String user = Keys.DB_USER;
        String password = Keys.DB_PASSWORD;

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro, não foi possível conectar no banco de dados! " + e.getMessage());
            return null;
        }
    }
}
