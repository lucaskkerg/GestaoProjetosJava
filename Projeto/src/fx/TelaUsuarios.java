package fx;

import dao.UsuarioDAO;
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

public class TelaUsuarios extends Application {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private TableView<Usuario> tabela = new TableView<>();
    private ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gerenciar Usuários");

        // Campos de entrada
        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome completo");

        TextField txtCpf = new TextField();
        txtCpf.setPromptText("CPF");

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Email");

        TextField txtCargo = new TextField();
        txtCargo.setPromptText("Cargo");

        TextField txtLogin = new TextField();
        txtLogin.setPromptText("Login");

        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Senha");

        // Botão salvar
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            Usuario u = new Usuario();
            u.setNome(txtNome.getText());
            u.setCpf(txtCpf.getText());
            u.setEmail(txtEmail.getText());
            u.setCargo(txtCargo.getText());
            u.setLogin(txtLogin.getText());
            u.setSenha(txtSenha.getText());

            usuarioDAO.inserir(u);
            carregarUsuarios();
        });

        // Botão excluir
        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> {
            Usuario selecionado = tabela.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                usuarioDAO.excluir(selecionado.getId());
                carregarUsuarios();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione um usuário para excluir.");
                alert.showAndWait();
            }
        });

        // Layout do formulário
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);
        form.addRow(0, new Label("Nome:"), txtNome);
        form.addRow(1, new Label("CPF:"), txtCpf);
        form.addRow(2, new Label("Email:"), txtEmail);
        form.addRow(3, new Label("Cargo:"), txtCargo);
        form.addRow(4, new Label("Login:"), txtLogin);
        form.addRow(5, new Label("Senha:"), txtSenha);
        form.addRow(6, btnSalvar, btnExcluir);

        // Tabela
        TableColumn<Usuario, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));

        TableColumn<Usuario, String> colCpf = new TableColumn<>("CPF");
        colCpf.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCpf()));

        TableColumn<Usuario, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<Usuario, String> colCargo = new TableColumn<>("Cargo");
        colCargo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCargo()));

        tabela.getColumns().addAll(colNome, colCpf, colEmail, colCargo);
        tabela.setItems(usuarios);

        carregarUsuarios();

        VBox layout = new VBox(10, form, tabela);
        Scene scene = new Scene(layout, 700, 500);

        stage.setScene(scene);
        stage.show();
    }

    private void carregarUsuarios() {
        usuarios.setAll(usuarioDAO.listar());
    }
}
