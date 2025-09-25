package projeto;

import dao.*;
import model.*;

import java.util.List;
import java.util.Scanner;

public class MenuConsole {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ProjetoDAO projetoDAO = new ProjetoDAO();
        TarefaDAO tarefaDAO = new TarefaDAO();
        EquipeDAO equipeDAO = new EquipeDAO();
        MembroEquipeDAO membroEquipeDAO = new MembroEquipeDAO();

        int opcao;
        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1 - Usuários");
            System.out.println("2 - Projetos");
            System.out.println("3 - Tarefas");
            System.out.println("4 - Equipes");
            System.out.println("5 - Membros de Equipe");
            System.out.println("0 - Sair");
            opcao = readInt("Escolha: ");

            switch (opcao) {
                case 1: menuUsuarios(usuarioDAO); break;
                case 2: menuProjetos(projetoDAO); break;
                case 3: menuTarefas(tarefaDAO); break;
                case 4: menuEquipes(equipeDAO); break;
                case 5: menuMembros(membroEquipeDAO); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    //					USUARIOS
    private static void menuUsuarios(UsuarioDAO usuarioDAO) {
        int opcao;
        do {
            System.out.println("\n=== MENU USUÁRIOS ===");
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            opcao = readInt("Escolha: ");

            switch (opcao) {
                case 1: {
                	Usuario u = new Usuario();
                	System.out.print("Nome completo: "); u.setNome(sc.nextLine());
                	System.out.print("CPF: "); u.setCpf(sc.nextLine());
                	System.out.print("Email: "); u.setEmail(sc.nextLine());
                	System.out.print("Cargo: "); u.setCargo(sc.nextLine());
                	System.out.print("Login: "); u.setLogin(sc.nextLine());
                	System.out.print("Senha: "); u.setSenha(sc.nextLine());
                	usuarioDAO.inserir(u);
                	System.out.println("Usuário inserido!");

                }
                case 2: {
                    List<Usuario> usuarios = usuarioDAO.listar();
                    if (usuarios.isEmpty()) System.out.println("Nenhum usuário cadastrado.");
                    for (Usuario u : usuarios) System.out.println(u.getResumo());
                    break;
                }
                case 3: {
                    int id = readInt("ID do usuário: ");
                    Usuario u = new Usuario();
                    u.setId(id);
                    System.out.print("Nome completo: "); u.setNome(sc.nextLine());
                    System.out.print("CPF: "); u.setCpf(sc.nextLine());
                    System.out.print("Email: "); u.setEmail(sc.nextLine());
                    System.out.print("Cargo: "); u.setCargo(sc.nextLine());
                    System.out.print("Login: "); u.setLogin(sc.nextLine());
                    System.out.print("Senha: "); u.setSenha(sc.nextLine());
                    usuarioDAO.atualizar(u);
                    System.out.println("Usuário atualizado!");

                }
                case 4: {
                    int id = readInt("ID do usuário para excluir: ");
                    usuarioDAO.excluir(id);
                    break;
                }
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    //					PROJETOS
    private static void menuProjetos(ProjetoDAO projetoDAO) {
        int opcao;
        do {
            System.out.println("\n=== MENU PROJETOS ===");
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            opcao = readInt("Escolha: ");

            switch (opcao) {
                case 1: {
                	Projeto p = new Projeto();
                	System.out.print("Nome: "); p.setNome(sc.nextLine());
                	System.out.print("Descrição: "); p.setDescricao(sc.nextLine());
                	System.out.print("Data início: "); p.setDataInicio(sc.nextLine());
                	System.out.print("Data término prevista: "); p.setDataTerminoPrevista(sc.nextLine());
                	System.out.print("Status: "); p.setStatus(sc.nextLine());
                	System.out.print("ID do gerente responsável: "); p.setIdGerente(readInt(""));
                	projetoDAO.inserir(p);

                    break;
                }
                case 2: {
                    List<Projeto> projetos = projetoDAO.listar();
                    if (projetos.isEmpty()) System.out.println("Nenhum projeto cadastrado.");
                    for (Projeto p : projetos) System.out.println(p.getResumo());
                    break;
                }
                case 3: {
                    int id = readInt("ID do projeto: ");
                    Projeto p = new Projeto();
                    p.setId(id);
                    System.out.print("Nome: "); p.setNome(sc.nextLine());
                    System.out.print("Descrição: "); p.setDescricao(sc.nextLine());
                    System.out.print("Data início: "); p.setDataInicio(sc.nextLine());
                    System.out.print("Data fim: "); p.setDataTerminoPrevista(sc.nextLine());
                    System.out.print("Status: "); p.setStatus(sc.nextLine());
                    System.out.print("ID do gerente responsável: "); p.setIdGerente(readInt(""));
                    projetoDAO.atualizar(p);
                    break;
                }
                case 4: {
                    int id = readInt("ID do projeto para excluir: ");
                    projetoDAO.excluir(id);
                    break;
                }
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    //					TAREFAS
    private static void menuTarefas(TarefaDAO tarefaDAO) {
        int opcao;
        do {
            System.out.println("\n=== MENU TAREFAS ===");
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            opcao = readInt("Escolha: ");

            switch (opcao) {
                case 1: {
                    Tarefa t = new Tarefa();
                    System.out.print("Nome: "); t.setNome(sc.nextLine());
                    System.out.print("Descrição: "); t.setDescricao(sc.nextLine());
                    System.out.print("Data início: "); t.setDataInicio(sc.nextLine());
                    System.out.print("Data fim: "); t.setDataFim(sc.nextLine());
                    System.out.print("Status: "); t.setStatus(sc.nextLine());
                    t.setIdProjeto(readInt("ID Projeto: "));
                    t.setIdUsuario(readInt("ID Usuário: "));
                    tarefaDAO.inserir(t);
                    break;
                }
                case 2: {
                    List<Tarefa> tarefas = tarefaDAO.listar();
                    if (tarefas.isEmpty()) System.out.println("Nenhuma tarefa cadastrada.");
                    for (Tarefa t : tarefas) System.out.println(t.getResumo());
                    break;
                }
                case 3: {
                    int id = readInt("ID da tarefa: ");
                    Tarefa t = new Tarefa();
                    t.setId(id);
                    System.out.print("Nome: "); t.setNome(sc.nextLine());
                    System.out.print("Descrição: "); t.setDescricao(sc.nextLine());
                    System.out.print("Data início: "); t.setDataInicio(sc.nextLine());
                    System.out.print("Data fim: "); t.setDataFim(sc.nextLine());
                    System.out.print("Status: "); t.setStatus(sc.nextLine());
                    t.setIdProjeto(readInt("ID Projeto: "));
                    t.setIdUsuario(readInt("ID Usuário: "));
                    tarefaDAO.atualizar(t);
                    break;
                }
                case 4: {
                    int id = readInt("ID da tarefa para excluir: ");
                    tarefaDAO.excluir(id);
                    break;
                }
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    //					EQUIPES
    private static void menuEquipes(EquipeDAO equipeDAO) {
        int opcao;
        do {
            System.out.println("\n=== MENU EQUIPES ===");
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            opcao = readInt("Escolha: ");

            switch (opcao) {
                case 1: {
                	Equipe e = new Equipe();
                	System.out.print("Nome da equipe: "); e.setNome(sc.nextLine());
                	System.out.print("Descrição: "); e.setDescricao(sc.nextLine());
                	System.out.print("ID do projeto: "); e.setIdProjeto(readInt(""));
                	equipeDAO.inserir(e);
                	System.out.println("Equipe inserida!");
                    break;
                }
                case 2: {
                    List<Equipe> equipes = equipeDAO.listar();
                    if (equipes.isEmpty()) System.out.println("Nenhuma equipe cadastrada.");
                    for (Equipe e : equipes) System.out.println(e.getResumo());
                    break;
                }
                case 3: {
                    int id = readInt("ID da equipe: ");
                    Equipe e = new Equipe();
                    e.setId(id);
                    System.out.print("Nome: "); e.setNome(sc.nextLine());
                    e.setIdProjeto(readInt("ID Projeto: "));
                    equipeDAO.atualizar(e);
                    break;
                }
                case 4: {
                    int id = readInt("ID da equipe para excluir: ");
                    equipeDAO.excluir(id);
                    break;
                }
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    //					MEMBROS
    private static void menuMembros(MembroEquipeDAO membroDAO) {
        int opcao;
        do {
            System.out.println("\n=== MENU MEMBROS DE EQUIPE ===");
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            opcao = readInt("Escolha: ");

            switch (opcao) {
                case 1: {
                    MembroEquipe m = new MembroEquipe();
                    m.setIdUsuario(readInt("ID Usuário: "));
                    m.setIdEquipe(readInt("ID Equipe: "));
                    membroDAO.inserir(m);
                    break;
                }
                case 2: {
                    List<MembroEquipe> membros = membroDAO.listar();
                    if (membros.isEmpty()) System.out.println("Nenhum membro cadastrado.");
                    for (MembroEquipe m : membros) System.out.println(m.getResumo());
                    break;
                }
                case 3: {
                    int id = readInt("ID do membro: ");
                    MembroEquipe m = new MembroEquipe();
                    m.setId(id);
                    m.setIdUsuario(readInt("Novo ID Usuário: "));
                    m.setIdEquipe(readInt("Novo ID Equipe: "));
                    membroDAO.atualizar(m);
                    break;
                }
                case 4: {
                    int id = readInt("ID do membro para excluir: ");
                    membroDAO.excluir(id);
                    break;
                }
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }
}
