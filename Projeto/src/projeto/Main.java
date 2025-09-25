package projeto;

import dao.*;
import model.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ProjetoDAO projetoDAO = new ProjetoDAO();
            TarefaDAO tarefaDAO = new TarefaDAO();

            // =====================
            // CREATE
            // =====================
            Usuario u1 = new Usuario();
            u1.setNome("Maria Costa");
            u1.setEmail("Maria@email.com");
            u1.setSenha("1234");
            u1.setCargo("Gerente");
            usuarioDAO.inserir(u1);

            Usuario u2 = new Usuario();
            u2.setNome("Edilson Serafim");
            u2.setEmail("Edilson@email.com");
            u2.setSenha("abcd");
            u2.setCargo("Colaborador");
            usuarioDAO.inserir(u2);

            Projeto p1 = new Projeto();
            p1.setNome("Sistema Acadêmico");
            p1.setDescricao("Gerenciar alunos e disciplinas");
            p1.setDataInicio("22-08-2025");
            p1.setDataTerminoPrevista("25-09-2025");
            p1.setStatus("Em andamento");
            projetoDAO.inserir(p1);

            Tarefa t1 = new Tarefa();
            t1.setNome("Modelagem de Banco");
            t1.setDescricao("Criar tabelas e relacionamentos");
            t1.setDataInicio("25-08-2025");
            t1.setDataFim("25-09-2025");
            t1.setStatus("Pendente");
            t1.setIdProjeto(1); // FK projeto
            t1.setIdUsuario(1); // FK usuário
            tarefaDAO.inserir(t1);

            // =====================
            // READ
            // =====================
            System.out.println("\n=== Usuários ===");
            List<Usuario> usuarios = usuarioDAO.listar();
            for (Usuario u : usuarios) {
                System.out.println(u.getResumo()); // polimorfismo
            }

            System.out.println("\n=== Projetos ===");
            List<Projeto> projetos = projetoDAO.listar();
            for (Projeto p : projetos) {
                System.out.println(p.getResumo());
            }

            System.out.println("\n=== Tarefas ===");
            List<Tarefa> tarefas = tarefaDAO.listar();
            for (Tarefa t : tarefas) {
                System.out.println(t.getResumo());
            }

            // =====================
            // UPDATE
            // =====================
            if (!usuarios.isEmpty()) {
                Usuario usuarioUpdate = usuarios.get(0);
                usuarioUpdate.setNome("Maria Costa Atualizada");
                usuarioDAO.atualizar(usuarioUpdate);
                System.out.println("\nUsuário atualizado com sucesso!");
            }

            if (!projetos.isEmpty()) {
                Projeto projetoUpdate = projetos.get(0);
                projetoUpdate.setStatus("Concluído");
                projetoDAO.atualizar(projetoUpdate);
                System.out.println("Projeto atualizado com sucesso!");
            }

            if (!tarefas.isEmpty()) {
                Tarefa tarefaUpdate = tarefas.get(0);
                tarefaUpdate.setStatus("Em andamento");
                tarefaDAO.atualizar(tarefaUpdate);
                System.out.println("Tarefa atualizada com sucesso!");
            }

            // =====================
            // DELETE
            // =====================
            if (usuarios.size() > 1) {
                Usuario usuarioDelete = usuarios.get(1);
                usuarioDAO.excluir(usuarioDelete.getId()); // herdado de Entidade
                System.out.println("Usuário excluído com sucesso!");
            }

            if (!projetos.isEmpty()) {
                Projeto projetoDelete = projetos.get(0);
                projetoDAO.excluir(projetoDelete.getId()); // herdado de Entidade
                System.out.println("Projeto excluído com sucesso!");
            }

            if (!tarefas.isEmpty()) {
                Tarefa tarefaDelete = tarefas.get(0);
                tarefaDAO.excluir(tarefaDelete.getId()); // herdado de Entidade
                System.out.println("Tarefa excluída com sucesso!");
            }

            // =====================
            // READ novamente
            // =====================
            System.out.println("\n=== Usuários após exclusão ===");
            for (Usuario u : usuarioDAO.listar()) {
                System.out.println(u.getResumo());
            }

        } catch (Exception e) {
            System.out.println("Erro na execução: " + e.getMessage());
        }
    }
}
