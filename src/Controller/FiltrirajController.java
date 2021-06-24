package Controller;

import Main.Main;
import View.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import Module.Database;
import Module.Utakmica;

import java.util.List;

public class FiltrirajController implements EventHandler<ActionEvent> {
    private MainView mainView = MainView.getInstance();
            
    public FiltrirajController(MainView mainView)
    {
        this.mainView = mainView;
    }

    @Override
    public void handle(ActionEvent event) {
        List<Utakmica> utakmice = Database.getInstance().getUtakmice();
        mainView.getTableGornji().getItems().clear();
        mainView.getTableGornji().getItems().addAll(utakmice);
        String znak = mainView.getManjeVeceJednako().getSelectionModel().getSelectedItem();

        if (!mainView.getGolova().getText().isEmpty() && Character.isDigit(mainView.getGolova().getText().charAt(0)))
        {
            int golova = Integer.parseInt(mainView.getGolova().getText());
            if (znak.equals(new String(">"))) {
                mainView.getTableGornji().getItems().removeIf(utakmica -> !(utakmica.getGoloviTim1() + utakmica.getGoloviTim2() > golova));
            } else if (znak.equals(new String("<"))) {
                mainView.getTableGornji().getItems().removeIf(utakmica -> !(utakmica.getGoloviTim1() + utakmica.getGoloviTim2() < golova));
            } else if (znak.equals(new String("="))) {
                mainView.getTableGornji().getItems().removeIf(utakmica -> !(utakmica.getGoloviTim1() + utakmica.getGoloviTim2() == golova));
            }
        }

    }
}
