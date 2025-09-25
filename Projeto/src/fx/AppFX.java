package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class AppFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Gerenciamento");

        Button btnUsuarios = new Button("Usuários");
        btnUsuarios.setOnAction(e -> new TelaUsuarios().start(new Stage()));

        Button btnProjetos = new Button("Projetos");
        btnProjetos.setOnAction(e -> new TelaProjetos().start(new Stage()));

        Button btnEquipes = new Button("Equipes");
        btnEquipes.setOnAction(e -> new TelaEquipes().start(new Stage()));

        Button btnMembros = new Button("Membros de Equipe");
        btnMembros.setOnAction(e -> new TelaMembrosEquipe().start(new Stage()));

        Button btnTarefas = new Button("Tarefas");
        btnTarefas.setOnAction(e -> new TelaTarefas().start(new Stage()));

        VBox layout = new VBox(20, btnUsuarios, btnProjetos, btnEquipes, btnMembros, btnTarefas);
        Scene scene = new Scene(layout, 400, 300);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
