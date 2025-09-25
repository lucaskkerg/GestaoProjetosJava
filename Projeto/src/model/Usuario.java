package model;

public class Usuario extends Pessoa {
    private String cpf;
    private String cargo;
    private String login;
    private String senha;

    // Getters e Setters
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String getResumo() {
        return String.format(
            "ID: %d | Nome: %s | CPF: %s | Email: %s | Cargo: %s | Login: %s",
            getId(),
            getNome(),
            getCpf(),
            getEmail(),
            getCargo(),
            getLogin()
        );
    }
}