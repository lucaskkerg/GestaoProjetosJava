package fx;

import dao.EquipeDAO;
import dao.MembroEquipeDAO;
import dao.UsuarioDAO;
import model.Equipe;
import model.MembroEquipe;
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

public class TelaMembrosEquipe extends Application {

    private EquipeDAO equipeDAO = new EquipeDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private MembroEquipeDAO membroEquipeDAO = new MembroEquipeDAO();

    private ObservableList<Equipe> equipes = FXCollections.observableArrayList();
    private ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    private ObservableList<MembroEquipe> membros = FXCollections.observableArrayList();

    private TableView<MembroEquipe> tabela = new TableView<>();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gerenciar Membros de Equipe");

        // ComboBox Equipes
        ComboBox<Equipe> cbEquipe = new ComboBox<>();
        equipes.setAll(equipeDAO.listar());
        cbEquipe.setItems(equipes);
        cbEquipe.setPromptText("Selecione a Equipe");

        cbEquipe.setCellFactory(param -> new ListCell<Equipe>() {
            @Override
            protected void updateItem(Equipe item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });
        cbEquipe.setButtonCell(new ListCell<Equipe>() {
            @Override
            protected void updateItem(Equipe item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });

        // ComboBox Usuários
        ComboBox<Usuario> cbUsuario = new ComboBox<>();
        usuarios.setAll(usuarioDAO.listar());
        cbUsuario.setItems(usuarios);
        cbUsuario.setPromptText("Selecione o Usuário");

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

        // Botão para adicionar membro
        Button btnAdicionar = new Button("Adicionar Membro");
        btnAdicionar.setOnAction(e -> {
            Equipe equipe = cbEquipe.getValue();
            Usuario usuario = cbUsuario.getValue();

            if (equipe == null || usuario == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione equipe e usuário.");
                alert.showAndWait();
                return;
            }

            MembroEquipe membro = new MembroEquipe();
            membro.setIdEquipe(equipe.getId());
            membro.setIdUsuario(usuario.getId());

            membroEquipeDAO.inserir(membro);
            carregarMembros(equipe.getId());
        });
        
        Button btnExcluir = new Button("Excluir Membro");
        btnExcluir.setOnAction(e -> {
            MembroEquipe selecionado = tabela.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                membroEquipeDAO.excluir(selecionado.getId());
                carregarMembros(selecionado.getIdEquipe());
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione um membro para excluir.");
                alert.showAndWait();
            }
        });

        // Tabela
        TableColumn<MembroEquipe, String> colEquipe = new TableColumn<>("Equipe (ID)");
        colEquipe.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getIdEquipe())));

        TableColumn<MembroEquipe, String> colUsuario = new TableColumn<>("Usuário (ID)");
        colUsuario.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getIdUsuario())));

        tabela.getColumns().addAll(colEquipe, colUsuario);
        tabela.setItems(membros);

        // Quando mudar equipe, carregar membros
        cbEquipe.setOnAction(e -> {
            Equipe equipe = cbEquipe.getValue();
            if (equipe != null) {
                carregarMembros(equipe.getId());
            }
        });

        // Layout
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);
        form.addRow(0, new Label("Equipe:"), cbEquipe);
        form.addRow(1, new Label("Usuário:"), cbUsuario);
        form.addRow(2, btnAdicionar, btnExcluir);

        VBox layout = new VBox(10, form, tabela);
        Scene scene = new Scene(layout, 700, 400);

        stage.setScene(scene);
        stage.show();
    }

    private void carregarMembros(int idEquipe) {
        membros.setAll(membroEquipeDAO.listarPorEquipe(idEquipe));
    }
}
