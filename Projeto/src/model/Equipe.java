package model;

import java.util.ArrayList;
import java.util.List;

public class Equipe extends Entidade {
    private String nome;
    private String descricao;
    private int idProjeto;
    private List<Usuario> membros = new ArrayList<>(); // relação N:N

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getIdProjeto() { return idProjeto; }
    public void setIdProjeto(int idProjeto) { this.idProjeto = idProjeto; }

    public List<Usuario> getMembros() { return membros; }
    public void setMembros(List<Usuario> membros) { this.membros = membros; }

    @Override
    public String getResumo() {
        return String.format(
            "ID: %d | Nome: %s | Descrição: %s | Projeto(ID): %d | Membros: %d",
            getId(), nome, descricao, idProjeto, membros.size()
        );
    }
}
