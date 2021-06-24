package Controller;

import View.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import Module.Tim;
import Module.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrikaziTabeluController implements EventHandler<ActionEvent> {
    private List<Tim> tim = new ArrayList<>();

    @Override
    public void handle(ActionEvent event) {
        tim = Database.getInstance().getTimovi();
        Collections.sort(tim);
        MainView.getInstance().getTableDonji().getItems().clear();
        MainView.getInstance().getTableDonji().getItems().addAll(tim);

        MainView.getInstance().getTableDonji().getItems().removeIf(tim1 -> (tim1.getGrupa() != MainView.getInstance().getGrupaComboBox().getSelectionModel().getSelectedItem()));
    }
}
