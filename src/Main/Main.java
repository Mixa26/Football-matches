package Main;

import View.MainView;
import javafx.application.Application;
import javafx.stage.Stage;
import Module.Database;

public class Main extends Application {
    private final MainView mainView = MainView.getInstance();
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Utakmice");
        stage.setScene(mainView.makeScene());
        mainView.getTableGornji().getItems().addAll(Database.getInstance().getMatches());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
