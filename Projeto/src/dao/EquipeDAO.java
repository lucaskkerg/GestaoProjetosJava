package dao;

import model.Equipe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO extends GenericDAO {

	public void inserir(Equipe e) {
	    String sql = "INSERT INTO equipes (nome, descricao, id_projeto) VALUES (?, ?, ?)";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, e.getNome());
	        stmt.setString(2, e.getDescricao());
	        stmt.setInt(3, e.getIdProjeto());
	        stmt.executeUpdate();

	    } catch (SQLException ex) {
	        System.out.println("Erro ao inserir equipe: " + ex.getMessage());
	    }
	}

	public List<Equipe> listar() {
	    List<Equipe> equipes = new ArrayList<>();
	    String sql = "SELECT * FROM equipes";

	    try (Connection conn = getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Equipe e = new Equipe();
	            e.setId(rs.getInt("id_equipe"));
	            e.setNome(rs.getString("nome"));
	            e.setDescricao(rs.getString("descricao"));
	            e.setIdProjeto(rs.getInt("id_projeto"));
	            equipes.add(e);
	        }

	    } catch (SQLException ex) {
	        System.out.println("Erro ao listar equipes: " + ex.getMessage());
	    }
	    return equipes;
	}

	public void atualizar(Equipe e) {
	    String sql = "UPDATE equipes SET nome=?, descricao=?, id_projeto=? WHERE id_equipe=?";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, e.getNome());
	        stmt.setString(2, e.getDescricao());
	        stmt.setInt(3, e.getIdProjeto());
	        stmt.setInt(4, e.getId());
	        stmt.executeUpdate();

	    } catch (SQLException ex) {
	        System.out.println("Erro ao atualizar equipe: " + ex.getMessage());
	    }
	}

    public void excluir(int id) {
        String sql = "DELETE FROM equipes WHERE id_equipe=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro ao excluir equipe: " + ex.getMessage());
        }
    }
}
