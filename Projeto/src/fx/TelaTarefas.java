package fx;

import dao.ProjetoDAO;
import dao.TarefaDAO;
import dao.UsuarioDAO;
import model.Projeto;
import model.Tarefa;
import model.Usuario;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaTarefas extends Application {

    private TarefaDAO tarefaDAO = new TarefaDAO();
    private ProjetoDAO projetoDAO = new ProjetoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    private TableView<Tarefa> tabela = new TableView<>();
    private ObservableList<Tarefa> tarefas = FXCollections.observableArrayList();
    private ObservableList<Projeto> projetos = FXCollections.observableArrayList();
    private ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gerenciar Tarefas");

        // Campos
        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome da Tarefa");

        TextField txtDescricao = new TextField();
        txtDescricao.setPromptText("Descrição");

        TextField txtDataInicio = new TextField();
        txtDataInicio.setPromptText("Data Início (YYYY-MM-DD)");

        TextField txtDataFim = new TextField();
        txtDataFim.setPromptText("Data Fim (YYYY-MM-DD)");

        TextField txtStatus = new TextField();
        txtStatus.setPromptText("Status");

        // ComboBox Projetos
        ComboBox<Projeto> cbProjeto = new ComboBox<>();
        projetos.setAll(projetoDAO.listar());
        cbProjeto.setItems(projetos);
        cbProjeto.setPromptText("Selecione o Projeto");
        cbProjeto.setCellFactory(param -> new ListCell<Projeto>() {
            @Override
            protected void updateItem(Projeto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });
        cbProjeto.setButtonCell(new ListCell<Projeto>() {
            @Override
            protected void updateItem(Projeto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });

        // ComboBox Usuários
        ComboBox<Usuario> cbUsuario = new ComboBox<>();
        usuarios.setAll(usuarioDAO.listar());
        cbUsuario.setItems(usuarios);
        cbUsuario.setPromptText("Selecione o Responsável");
        cbUsuario.setCellFactory(param -> new ListCell<Usuario>() {
            @Override
            protected void updateItem(Usuario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });
        cbUsuario.setButtonCell(new ListCell<Usuario>() {
            @Override
            protected void updateItem(Usuario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });

        // Botão salvar
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            Tarefa t = new Tarefa();
            t.setNome(txtNome.getText());
            t.setDescricao(txtDescricao.getText());
            t.setDataInicio(txtDataInicio.getText());
            t.setDataFim(txtDataFim.getText());
            t.setStatus(txtStatus.getText());

            Projeto projeto = cbProjeto.getValue();
            Usuario usuario = cbUsuario.getValue();

            if (projeto != null && usuario != null) {
                t.setIdProjeto(projeto.getId());
                t.setIdUsuario(usuario.getId());
                tarefaDAO.inserir(t);
                carregarTarefas();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione um projeto e um responsável.");
                alert.showAndWait();
            }
        });
        
        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> {
            Tarefa selecionada = tabela.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                tarefaDAO.excluir(selecionada.getId());
                carregarTarefas();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione uma tarefa para excluir.");
                alert.showAndWait();
            }
        });

        // Formulário
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);
        form.addRow(0, new Label("Nome:"), txtNome);
        form.addRow(1, new Label("Descrição:"), txtDescricao);
        form.addRow(2, new Label("Data Início:"), txtDataInicio);
        form.addRow(3, new Label("Data Fim:"), txtDataFim);
        form.addRow(4, new Label("Status:"), txtStatus);
        form.addRow(5, new Label("Projeto:"), cbProjeto);
        form.addRow(6, new Label("Responsável:"), cbUsuario);
        form.addRow(7, btnSalvar, btnExcluir);

        // Tabela
        TableColumn<Tarefa, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));

        TableColumn<Tarefa, String> colDescricao = new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescricao()));

        TableColumn<Tarefa, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));

        TableColumn<Tarefa, String> colProjeto = new TableColumn<>("Projeto (ID)");
        colProjeto.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getIdProjeto())));

        TableColumn<Tarefa, String> colUsuario = new TableColumn<>("Responsável (ID)");
        colUsuario.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getIdUsuario())));

        tabela.getColumns().addAll(colNome, colDescricao, colStatus, colProjeto, colUsuario);
        tabela.setItems(tarefas);

        carregarTarefas();

        VBox layout = new VBox(10, form, tabela);
        Scene scene = new Scene(layout, 800, 500);

        stage.setScene(scene);
        stage.show();
    }

    private void carregarTarefas() {
        tarefas.setAll(tarefaDAO.listar());
    }
}
