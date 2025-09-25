package model;

public class Projeto extends Entidade {
    private String nome;                 
    private String descricao;
    private String dataInicio;
    private String dataTerminoPrevista;
    private String status;
    private int idGerente;               // FK para Usuario

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDataTerminoPrevista() { return dataTerminoPrevista; }
    public void setDataTerminoPrevista(String dataTerminoPrevista) { this.dataTerminoPrevista = dataTerminoPrevista; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getIdGerente() { return idGerente; }
    public void setIdGerente(int idGerente) { this.idGerente = idGerente; }

    @Override
    public String getResumo() {
        return String.format(
            "ID: %d | Nome: %s | Gerente(ID): %d | Descrição: %s | Início: %s | Término Previsto: %s | Status: %s",
            getId(), nome, idGerente, descricao, dataInicio, dataTerminoPrevista, status
        );
    }
}
