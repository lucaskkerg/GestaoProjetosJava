package model;

// Classe genérica para herança
public abstract class Entidade {
    protected int id;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // cada entidade vai dar um resumo próprio
    public abstract String getResumo();
}
