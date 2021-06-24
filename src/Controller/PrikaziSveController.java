package Controller;

import View.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import Module.Utakmica;
import Module.Database;

import java.util.List;

public class PrikaziSveController implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        List<Utakmica> utakmice = Database.getInstance().getUtakmice();
        MainView.getInstance().getTableGornji().getItems().clear();
        MainView.getInstance().getTableGornji().getItems().addAll(utakmice);
    }
}
