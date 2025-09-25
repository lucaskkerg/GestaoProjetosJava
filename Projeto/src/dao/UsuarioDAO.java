package dao;

import model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends GenericDAO {

	public void inserir(Usuario u) {
	    String sql = "INSERT INTO usuarios (nome, cpf, email, cargo, login, senha) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, u.getNome());
	        stmt.setString(2, u.getCpf());
	        stmt.setString(3, u.getEmail());
	        stmt.setString(4, u.getCargo());
	        stmt.setString(5, u.getLogin());
	        stmt.setString(6, u.getSenha());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Erro ao inserir usuário: " + e.getMessage());
	    }
	}

	public List<Usuario> listar() {
	    List<Usuario> usuarios = new ArrayList<>();
	    String sql = "SELECT * FROM usuarios";

	    try (Connection conn = getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Usuario u = new Usuario();
	            u.setId(rs.getInt("id_usuario"));
	            u.setNome(rs.getString("nome"));
	            u.setCpf(rs.getString("cpf"));
	            u.setEmail(rs.getString("email"));
	            u.setCargo(rs.getString("cargo"));
	            u.setLogin(rs.getString("login"));
	            u.setSenha(rs.getString("senha"));
	            usuarios.add(u);
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao listar usuários: " + e.getMessage());
	    }
	    return usuarios;
	}


	public void atualizar(Usuario u) {
	    String sql = "UPDATE usuarios SET nome=?, cpf=?, email=?, cargo=?, login=?, senha=? WHERE id_usuario=?";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, u.getNome());
	        stmt.setString(2, u.getCpf());
	        stmt.setString(3, u.getEmail());
	        stmt.setString(4, u.getCargo());
	        stmt.setString(5, u.getLogin());
	        stmt.setString(6, u.getSenha());
	        stmt.setInt(7, u.getId());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar usuário: " + e.getMessage());
	    }
	}

public void excluir(int id) {
    String sql = "DELETE FROM usuarios WHERE id_usuario=?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        stmt.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Erro ao excluir usuário: " + e.getMessage());
    }
}
}