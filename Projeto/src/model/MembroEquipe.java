package model;

public class MembroEquipe extends Entidade {
    private int idUsuario; // FK
    private int idEquipe;  // FK

    public int getIdUsuario() { 
        return idUsuario; 
    }
    public void setIdUsuario(int idUsuario) { 
        this.idUsuario = idUsuario; 
    }

    public int getIdEquipe() { 
        return idEquipe; 
    }
    public void setIdEquipe(int idEquipe) { 
        this.idEquipe = idEquipe; 
    }

    @Override
    public String getResumo() {
        return "Membro (Usuário " + idUsuario + ") da Equipe " + idEquipe;
    }
}
