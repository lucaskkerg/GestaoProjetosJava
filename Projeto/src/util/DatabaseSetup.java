package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSetup {
    public static void main(String[] args) {
        // Banco criado na raiz do projeto
        String url = "jdbc:sqlite:gerenciamento.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Usuários
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS usuarios (
                    id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    cpf TEXT NOT NULL UNIQUE,
                    email TEXT NOT NULL,
                    cargo TEXT NOT NULL,
                    login TEXT NOT NULL UNIQUE,
                    senha TEXT NOT NULL
                )
            """);

            // Projetos
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS projetos (
                    id_projeto INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    descricao TEXT,
                    data_inicio TEXT,
                    data_termino_prevista TEXT,
                    status TEXT,
                    id_gerente INTEGER NOT NULL,
                    FOREIGN KEY (id_gerente) REFERENCES usuarios(id_usuario)
                )
            """);

            // Tarefas
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS tarefas (
                    id_tarefa INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    descricao TEXT,
                    data_inicio TEXT,
                    data_fim TEXT,
                    status TEXT,
                    id_projeto INTEGER,
                    id_usuario INTEGER,
                    FOREIGN KEY (id_projeto) REFERENCES projetos(id_projeto),
                    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
                )
            """);

            // Equipes
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS equipes (
                    id_equipe INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    descricao TEXT,
                    id_projeto INTEGER,
                    FOREIGN KEY (id_projeto) REFERENCES projetos(id_projeto)
                )
            """);

            // Membros de Equipe
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS membros_equipe (
                    id_membro INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_usuario INTEGER,
                    id_equipe INTEGER,
                    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
                    FOREIGN KEY (id_equipe) REFERENCES equipes(id_equipe)
                )
            """);

            System.out.println("Banco de dados inicializado com sucesso! Arquivo: gerenciamento.db");

        } catch (Exception e) {
            System.out.println("Erro ao inicializar banco: " + e.getMessage());
        }
    }
}
