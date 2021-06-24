package Controller;

import Main.Main;
import View.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ZatvoriController implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        if (MainView.getInstance().getScene() != null)
        {
            Main.stage.setScene(MainView.getInstance().getScene());
        }
        else
        {
            Main.stage.setScene(MainView.getInstance().makeScene());
        }
    }
}
