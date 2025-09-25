package fx;

import dao.ProjetoDAO;
import dao.UsuarioDAO;
import model.Projeto;
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

public class TelaProjetos extends Application {

    private ProjetoDAO projetoDAO = new ProjetoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    private TableView<Projeto> tabela = new TableView<>();
    private ObservableList<Projeto> projetos = FXCollections.observableArrayList();
    private ObservableList<Usuario> gerentes = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gerenciar Projetos");

        // Campos
        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome do Projeto");

        TextField txtDescricao = new TextField();
        txtDescricao.setPromptText("Descrição");

        TextField txtDataInicio = new TextField();
        txtDataInicio.setPromptText("Data Início (YYYY-MM-DD)");

        TextField txtDataTermino = new TextField();
        txtDataTermino.setPromptText("Data Término Prevista (YYYY-MM-DD)");

        TextField txtStatus = new TextField();
        txtStatus.setPromptText("Status");

        // ComboBox de Gerentes
        ComboBox<Usuario> cbGerente = new ComboBox<>();
        cbGerente.setPromptText("Selecione o Gerente");
        gerentes.setAll(usuarioDAO.listar()); // carrega usuários
        cbGerente.setItems(gerentes);

        // Mostrar nome do gerente no ComboBox
        cbGerente.setCellFactory(param -> new ListCell<Usuario>() {
            @Override
            protected void updateItem(Usuario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });
        cbGerente.setButtonCell(new ListCell<Usuario>() {
            @Override
            protected void updateItem(Usuario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            Projeto p = new Projeto();
            p.setNome(txtNome.getText());
            p.setDescricao(txtDescricao.getText());
            p.setDataInicio(txtDataInicio.getText());
            p.setDataTerminoPrevista(txtDataTermino.getText());
            p.setStatus(txtStatus.getText());

            Usuario gerente = cbGerente.getValue();
            if (gerente != null) {
                p.setIdGerente(gerente.getId());
                projetoDAO.inserir(p);
                carregarProjetos();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione um gerente.");
                alert.showAndWait();
            }
        });
        
        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> {
            Projeto selecionado = tabela.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                projetoDAO.excluir(selecionado.getId());
                carregarProjetos();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione um projeto para excluir.");
                alert.showAndWait();
            }
        });

        // Layout de formulário
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);
        form.addRow(0, new Label("Nome:"), txtNome);
        form.addRow(1, new Label("Descrição:"), txtDescricao);
        form.addRow(2, new Label("Data Início:"), txtDataInicio);
        form.addRow(3, new Label("Data Término Prevista:"), txtDataTermino);
        form.addRow(4, new Label("Status:"), txtStatus);
        form.addRow(5, new Label("Gerente:"), cbGerente);
        form.addRow(6, btnSalvar, btnExcluir);

        // Tabela
        TableColumn<Projeto, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));

        TableColumn<Projeto, String> colDescricao = new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescricao()));

        TableColumn<Projeto, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));

        TableColumn<Projeto, String> colGerente = new TableColumn<>("Gerente (ID)");
        colGerente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getIdGerente())));

        tabela.getColumns().addAll(colNome, colDescricao, colStatus, colGerente);
        tabela.setItems(projetos);

        carregarProjetos();

        VBox layout = new VBox(10, form, tabela);
        Scene scene = new Scene(layout, 700, 500);

        stage.setScene(scene);
        stage.show();
    }

    private void carregarProjetos() {
        projetos.setAll(projetoDAO.listar());
    }
}
