package dao;

import model.Tarefa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO extends GenericDAO {

    public void inserir(Tarefa t) {
        String sql = "INSERT INTO tarefas (nome, descricao, data_inicio, data_fim, status, id_projeto, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getNome());
            stmt.setString(2, t.getDescricao());
            stmt.setString(3, t.getDataInicio());
            stmt.setString(4, t.getDataFim());
            stmt.setString(5, t.getStatus());
            stmt.setInt(6, t.getIdProjeto());
            stmt.setInt(7, t.getIdUsuario());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir tarefa: " + e.getMessage());
        }
    }

    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tarefa t = new Tarefa();
                t.setId(rs.getInt("id_tarefa"));
                t.setNome(rs.getString("nome"));
                t.setDescricao(rs.getString("descricao"));
                t.setDataInicio(rs.getString("data_inicio"));
                t.setDataFim(rs.getString("data_fim"));
                t.setStatus(rs.getString("status"));
                t.setIdProjeto(rs.getInt("id_projeto"));
                t.setIdUsuario(rs.getInt("id_usuario"));
                tarefas.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }
        return tarefas;
    }

    public void atualizar(Tarefa t) {
        String sql = "UPDATE tarefas SET nome=?, descricao=?, data_inicio=?, data_fim=?, status=?, id_projeto=?, id_usuario=? WHERE id_tarefa=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getNome());
            stmt.setString(2, t.getDescricao());
            stmt.setString(3, t.getDataInicio());
            stmt.setString(4, t.getDataFim());
            stmt.setString(5, t.getStatus());
            stmt.setInt(6, t.getIdProjeto());
            stmt.setInt(7, t.getIdUsuario());
            stmt.setInt(8, t.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM tarefas WHERE id_tarefa=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao excluir tarefa: " + e.getMessage());
        }
    }
}
