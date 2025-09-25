# Sistema de Gerenciamento — Projeto Acadêmico em Java
Este projeto acadêmico é um sistema de projetos e pessoas. Ele foi implementado em Java e utiliza SQLite como banco de dados e JavaFX como interface gráfica. 

Ele foi desenvolvido como trabalho acadêmico, aplicando conceitos de programação orientada a objetos, como:  
- Herança  
- Polimorfismo  
- Encapsulamento (Getters e Setters)  
- Coleções (Arrays / ArrayList / ObservableList)  
- Tratamento de Exceções com try/catch  

O sistema permite o gerenciamento de:  
- Usuários (com CPF, cargo, login e senha)  
- Projetos (com gerente responsável)  
- Equipes (ligadas a projetos, com descrição)  
- Membros de Equipe (usuários vinculados a equipes)  
- Tarefas (associadas a um projeto e a um usuário responsável)

# Interface
<img width="390" height="322" alt="image" src="https://github.com/user-attachments/assets/da04fb4c-3137-4ca5-a789-244f73d44008" /> <br>

<img width="390" height="322" alt="image" src="https://github.com/user-attachments/assets/1ff0d468-161a-47bd-97de-849904ef4224" /> <br>

<img width="390" height="322" alt="image" src="https://github.com/user-attachments/assets/aa7c1deb-3798-4bcf-b360-8fa59c48ca07" /> <br>

<img width="390" height="322" alt="image" src="https://github.com/user-attachments/assets/ef0e48ad-ff3e-4135-bfdc-86c1550b55f2" /> <br>

<img width="390" height="322" alt="image" src="https://github.com/user-attachments/assets/b4a7a4f1-ac27-4fa8-a7b0-d022afdc3027" /> <br>




## Tecnologias Utilizadas
- Java SE 21 (recomendado)  
- Eclipse IDE  
- JavaFX (interface gráfica)  
- SQLite (banco de dados local, `gerenciamento.db`)  
- JDBC (conexão com o banco)  


## Estrutura do Projeto
- **dao**  
  - DAOs responsáveis pela comunicação com o banco de dados  
  - Arquivos: `UsuarioDAO`, `ProjetoDAO`, `EquipeDAO`, `MembroEquipeDAO`, `TarefaDAO`, `ConnectionFactory`  

- **model**  
  - Classes de modelo que representam as entidades do sistema  
  - Arquivos: `Usuario`, `Projeto`, `Equipe`, `MembroEquipe`, `Tarefa`  

- **fx**  
  - Telas e interface gráfica feitas com JavaFX  
  - Arquivos: `TelaUsuarios`, `TelaProjetos`, `TelaEquipes`, `TelaMembrosEquipe`, `TelaTarefas`, `AppFX`  

- **util**  
  - Utilitários para configuração do banco  
  - Arquivo: `DatabaseSetup`  

## Como Executar
1. Utilizar o JDK 21.  
2. Importar o projeto no Eclipse:  
   - `File > Import > Existing Projects into Workspace`  
3. Adicionar o JavaFX ao projeto:  
   - Em `Run Configurations > VM Arguments` incluir:  
     ```
     --module-path "C:\caminho\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml
     ```
4. Configurar o SQLite:  
   - Verifique se o driver `sqlite-jdbc` está adicionado ao projeto.  
   - O banco `gerenciamento.db` será criado automaticamente.  
5. Executar o `DatabaseSetup.java` para criar as tabelas no banco.  
6. Rodar a aplicação principal:  
   - `AppFX.java` → abre o menu com acesso a todas as telas CRUD.

## Funcionalidades

### Usuários
- Inserir, listar, excluir.  
- Campos: Nome, CPF, Email, Cargo, Login, Senha.  

### Projetos
- Inserir, listar, excluir.  
- Campos: Nome, Descrição, Data Início, Data Termino Prevista, Status, Gerente (Usuário).  

### Equipes
- Inserir, listar, excluir.  
- Campos: Nome, Descrição, Projeto vinculado.  

### Membros de Equipe
- Inserir, listar, excluir.  
- Relaciona Usuários ↔ Equipes.  

### Tarefas
- Inserir, listar, excluir.  
- Campos: Nome, Descrição, Data Início, Data Fim, Status, Projeto, Usuário Responsável.  


## Conceitos Acadêmicos Demonstrados
- Herança e Polimorfismo: compartilhamento e especialização de atributos entre entidades.  
- Encapsulamento: uso consistente de getters e setters.  
- Coleções (ArrayList/ObservableList): exibição e manipulação de dados nas tabelas.  
- Tratamento de Exceções: blocos try/catch em operações de banco.  
- Padrão DAO: separação entre lógica de persistência e lógica de negócio.  
- Banco Relacional (SQLite): uso de chaves primárias e estrangeiras, mantendo relacionamentos entre tabelas.  


## Observações 
- Funciona em qualquer sistema operacional (Windows/Linux/Mac) desde que configurado corretamente o JavaFX.  
- O banco SQLite é criado localmente no arquivo `gerenciamento.db`.

## Integrantes

- Lucas Sergio de Oliveira | @lucaskkerg
- Reinhold Gustav Berner | @k1ngberner
- Hiago Costa Serafim | @HiagoSerafim
- Andre Luiz de Souza | @andresouzs-1212
- Felipe Braga da Silva Sampaio


