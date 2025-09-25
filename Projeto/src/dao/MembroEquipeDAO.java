package dao;

import model.MembroEquipe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembroEquipeDAO extends GenericDAO {

    // Inserir
    public void inserir(MembroEquipe m) {
        String sql = "INSERT INTO membros_equipe (id_usuario, id_equipe) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, m.getIdUsuario());
            stmt.setInt(2, m.getIdEquipe());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir membro: " + e.getMessage());
        }
    }

    // Atualizar
    public void atualizar(MembroEquipe m) {
        String sql = "UPDATE membros_equipe SET id_usuario=?, id_equipe=? WHERE id_membro=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, m.getIdUsuario());
            stmt.setInt(2, m.getIdEquipe());
            stmt.setInt(3, m.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar membro: " + e.getMessage());
        }
    }

    // Excluir
    public void excluir(int id) {
        String sql = "DELETE FROM membros_equipe WHERE id_membro=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao excluir membro: " + e.getMessage());
        }
    }

    // Listar todos os membros
    public List<MembroEquipe> listar() {
        List<MembroEquipe> membros = new ArrayList<>();
        String sql = "SELECT * FROM membros_equipe";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                MembroEquipe m = new MembroEquipe();
                m.setId(rs.getInt("id_membro"));
                m.setIdUsuario(rs.getInt("id_usuario"));
                m.setIdEquipe(rs.getInt("id_equipe"));
                membros.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar membros: " + e.getMessage());
        }

        return membros;
    }

    // Listar membros de uma equipe específica
    public List<MembroEquipe> listarPorEquipe(int idEquipe) {
        List<MembroEquipe> membros = new ArrayList<>();
        String sql = "SELECT * FROM membros_equipe WHERE id_equipe=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEquipe);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MembroEquipe m = new MembroEquipe();
                m.setId(rs.getInt("id_membro"));
                m.setIdUsuario(rs.getInt("id_usuario"));
                m.setIdEquipe(rs.getInt("id_equipe"));
                membros.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar membros: " + e.getMessage());
        }

        return membros;
    }
}