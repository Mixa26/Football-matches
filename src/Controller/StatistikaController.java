package Controller;

import Main.Main;
import View.MainView;
import View.StatistikaView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StatistikaController implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (StatistikaView.getInstance().getScene() != null)
        {
            Main.stage.setScene(StatistikaView.getInstance().getScene());
        }
        else
        {
            Main.stage.setScene(StatistikaView.getInstance().makeScene());
        }
    }
}
