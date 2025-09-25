package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class GenericDAO {
    protected Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:sqlite:gerenciamento.db");
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar no banco: " + e.getMessage());
        }
    }
}
