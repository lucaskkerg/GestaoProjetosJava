package dao;

import model.Projeto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO extends GenericDAO {

	public void inserir(Projeto p) {
	    String sql = "INSERT INTO projetos (nome, descricao, data_inicio, data_termino_prevista, status, id_gerente) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, p.getNome());
	        stmt.setString(2, p.getDescricao());
	        stmt.setString(3, p.getDataInicio());
	        stmt.setString(4, p.getDataTerminoPrevista());
	        stmt.setString(5, p.getStatus());
	        stmt.setInt(6, p.getIdGerente());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Erro ao inserir projeto: " + e.getMessage());
	    }
	}

	public List<Projeto> listar() {
	    List<Projeto> projetos = new ArrayList<>();
	    String sql = "SELECT * FROM projetos";

	    try (Connection conn = getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Projeto p = new Projeto();
	            p.setId(rs.getInt("id_projeto"));
	            p.setNome(rs.getString("nome"));
	            p.setDescricao(rs.getString("descricao"));
	            p.setDataInicio(rs.getString("data_inicio"));
	            p.setDataTerminoPrevista(rs.getString("data_termino_prevista"));
	            p.setStatus(rs.getString("status"));
	            p.setIdGerente(rs.getInt("id_gerente"));
	            projetos.add(p);
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao listar projetos: " + e.getMessage());
	    }
	    return projetos;
	}

	public void atualizar(Projeto p) {
	    String sql = "UPDATE projetos SET nome=?, descricao=?, data_inicio=?, data_termino_prevista=?, status=?, id_gerente=? WHERE id_projeto=?";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, p.getNome());
	        stmt.setString(2, p.getDescricao());
	        stmt.setString(3, p.getDataInicio());
	        stmt.setString(4, p.getDataTerminoPrevista());
	        stmt.setString(5, p.getStatus());
	        stmt.setInt(6, p.getIdGerente());
	        stmt.setInt(7, p.getId());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar projeto: " + e.getMessage());
	    }
	}


	public void excluir(int id) {
	    String sql = "DELETE FROM projetos WHERE id_projeto=?";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, id);
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Erro ao excluir projeto: " + e.getMessage());
	    }
	}
}