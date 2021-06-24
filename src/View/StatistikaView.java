package View;

import Controller.ZatvoriController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import Module.Utakmica;
import Module.Database;
import Module.Tim;

import View.MainView;

import java.util.ArrayList;
import java.util.List;

public class StatistikaView extends VBox {
    private static StatistikaView instance;

    static
    {
        instance = new StatistikaView();
    }

    public static StatistikaView getInstance()
    {
        return instance;
    }

    HBox jedan;
    HBox dva;
    HBox tri;
    HBox cetiri;

    Label utakmica;
    Label reprezentacijaGolova;
    Label reprezentacijaPobede;
    Button zatvori;

    TextField prvi;
    TextField drugi;
    ListView<String> treci;

    private StatistikaView()
    {
        initElements();
        setElementProperties();
        updateElements();
        initListeners();
    }

    private void initElements()
    {
        jedan = new HBox();
        dva = new HBox();
        tri = new HBox();
        cetiri = new HBox();

        utakmica = new Label("Utakmica sa najvise golova:");
        reprezentacijaGolova = new Label("Reprezentacija sa najvise datih golova:");
        reprezentacijaPobede = new Label("Reprezentacije sa tri pobede:");
        zatvori = new Button("Zatvori");

        prvi = new TextField();
        drugi = new TextField();
        treci = new ListView<>();

        //add
        jedan.getChildren().addAll(utakmica, prvi);
        dva.getChildren().addAll(reprezentacijaGolova, drugi);
        tri.getChildren().addAll(reprezentacijaPobede, treci);
        cetiri.getChildren().add(zatvori);

        this.getChildren().addAll(jedan, dva, tri, cetiri);
    }

    private void setElementProperties()
    {
        this.setPadding(new Insets(10, 20, 10, 20));
        this.setSpacing(5);
        prvi.setPrefWidth(250);
        drugi.setPrefWidth(250);
        treci.setPrefWidth(250);
        jedan.setAlignment(Pos.CENTER_LEFT);
        jedan.setSpacing(63);
        dva.setAlignment(Pos.CENTER_LEFT);
        dva.setSpacing(5);
        tri.setAlignment(Pos.CENTER_LEFT);
        tri.setSpacing(55);
        cetiri.setAlignment(Pos.CENTER_LEFT);
    }

    private void updateElements()
    {
        prvi.appendText(najviseGolova().toString());
        drugi.appendText(najviseDatihGolova().toString());
        triPobede();
    }

    private void initListeners()
    {
        zatvori.setOnAction(new ZatvoriController());
    }

    private Utakmica najviseGolova()
    {
        Utakmica utakmicaNajvise = new Utakmica();
        utakmicaNajvise.setGoloviTim1(0);
        utakmicaNajvise.setGoloviTim2(0);
        for (Utakmica utakmica : Database.getInstance().getMatches())
        {
            if ((utakmica.getGoloviTim1() + utakmica.getGoloviTim2()) > (utakmicaNajvise.getGoloviTim1() + utakmicaNajvise.getGoloviTim2()))
            {
                utakmicaNajvise = utakmica;
            }
        }
        return utakmicaNajvise;
    }

    private Tim najviseDatihGolova()
    {
        Tim timNajvise = new Tim();
        timNajvise.setDati(0);
        for (Tim tim : Database.getInstance().getTimovi())
        {
            if (tim.getDati() > timNajvise.getDati())
            {
                timNajvise = tim;
            }
        }
        return timNajvise;
    }

    private void triPobede()
    {
        List<Tim> timoviTri = new ArrayList<>();
        for (Tim tim : Database.getInstance().getTimovi())
        {
            if (tim.getPobeda() == 3)
            {
                timoviTri.add(tim);
            }
        }
        for (Tim tim : timoviTri)
        {
            treci.getItems().add(tim.getTim());
        }
    }

    public Scene makeScene()
    {
        return new Scene(this, 500, 200);
    }

}
