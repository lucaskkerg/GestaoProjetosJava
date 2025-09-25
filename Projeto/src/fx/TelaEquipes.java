package fx;

import dao.EquipeDAO;
import model.Equipe;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaEquipes extends Application {

    private EquipeDAO equipeDAO = new EquipeDAO();
    private TableView<Equipe> tabela = new TableView<>();
    private ObservableList<Equipe> equipes = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gerenciar Equipes");

        // Campos
        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome da Equipe");

        TextField txtDescricao = new TextField();
        txtDescricao.setPromptText("Descrição");

        TextField txtIdProjeto = new TextField();
        txtIdProjeto.setPromptText("ID do Projeto");

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            Equipe eq = new Equipe();
            eq.setNome(txtNome.getText());
            eq.setDescricao(txtDescricao.getText());
            try {
                eq.setIdProjeto(Integer.parseInt(txtIdProjeto.getText()));
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "ID do Projeto deve ser um número.");
                alert.showAndWait();
                return;
            }

            equipeDAO.inserir(eq);
            carregarEquipes();
        });
        
        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> {
            Equipe selecionada = tabela.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                equipeDAO.excluir(selecionada.getId());
                carregarEquipes();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione uma equipe para excluir.");
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
        form.addRow(2, new Label("ID Projeto:"), txtIdProjeto);
        form.addRow(3, btnSalvar, btnExcluir);

        // Tabela
        TableColumn<Equipe, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));

        TableColumn<Equipe, String> colDescricao = new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescricao()));

        TableColumn<Equipe, String> colProjeto = new TableColumn<>("Projeto (ID)");
        colProjeto.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getIdProjeto())));

        tabela.getColumns().addAll(colNome, colDescricao, colProjeto);
        tabela.setItems(equipes);

        carregarEquipes();

        VBox layout = new VBox(10, form, tabela);
        Scene scene = new Scene(layout, 700, 400);

        stage.setScene(scene);
        stage.show();
    }

    private void carregarEquipes() {
        equipes.setAll(equipeDAO.listar());
    }
}
