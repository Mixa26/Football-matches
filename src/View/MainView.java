package View;

import Controller.FiltrirajController;
import Controller.PrikaziSveController;
import Controller.PrikaziTabeluController;
import Controller.StatistikaController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Module.Grupa;
import Module.Utakmica;
import Module.Tim;
import Module.Database;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
    private static MainView instance;

    //gore
    private HBox gornjiOver;
    private HBox gornji;
    private Label brojGolova;
    private ComboBox<String> manjeVeceJednako;
    private TextField golova;
    private Button filtriraj;
    private Button prikaziSve;
    private Button statistika;

    private TableView<Utakmica> tableGornji;
    private TableColumn<Utakmica, String> tcTim1;
    private TableColumn<Utakmica, String> tcTim2;
    private TableColumn<Utakmica, Integer> tcGoloviTim1;
    private TableColumn<Utakmica, Integer> tcGoloviTim2;
    private TableColumn<Utakmica, Grupa> tcGrupa;

    //sredina
    private HBox srednjiHBox;
    private Label grupa;
    private ComboBox<Grupa> grupaComboBox;
    private Button prikaziTabelu;

    //dole
    private TableView<Tim> tableDonji;
    private TableColumn<Tim, String> tcTim;
    private TableColumn<Tim, Integer> tcPobeda;
    private TableColumn<Tim, Integer> tcNeresenih;
    private TableColumn<Tim, Integer> tcPoraza;
    private TableColumn<Tim, String> tcGolovi;
    private TableColumn<Tim, Integer> tcPoeni;

    private MainView()
    {
        initElements();
        setElementProperties();
        initListeners();
    }

    static
    {
        instance = new MainView();
    }

    public static MainView getInstance()
    {
        return instance;
    }

    public Scene makeScene()
    {
        return new Scene(this, 800, 600);
    }

    private void initElements()
    {
        //gore
        gornjiOver = new HBox();
        gornji = new HBox();
        brojGolova = new Label("Broj golova");
        manjeVeceJednako = new ComboBox<>();
        manjeVeceJednako.getItems().add(">");
        manjeVeceJednako.getItems().add("<");
        manjeVeceJednako.getItems().add("=");
        manjeVeceJednako.getSelectionModel().selectFirst();
        golova = new TextField();
        golova.setPromptText("golova");
        filtriraj = new Button("Filtriraj");
        prikaziSve = new Button("Prikazi sve");
        statistika = new Button("Statistika");

        //add
        gornji.getChildren().addAll(brojGolova, manjeVeceJednako, golova, filtriraj, prikaziSve);
        gornjiOver.getChildren().addAll(gornji, statistika);

        //table gornji
        tableGornji = new TableView<>();

        tcTim1 = new TableColumn<>("Tim1");
        tcTim1.setCellValueFactory(new PropertyValueFactory<>("tim1"));
        tcTim2 = new TableColumn<>("Tim2");
        tcTim2.setCellValueFactory(new PropertyValueFactory<>("tim2"));
        tcGoloviTim1 = new TableColumn<>("Golovi tim1");
        tcGoloviTim1.setCellValueFactory(new PropertyValueFactory<>("goloviTim1"));
        tcGoloviTim2 = new TableColumn<>("Golovi tim2");
        tcGoloviTim2.setCellValueFactory(new PropertyValueFactory<>("goloviTim2"));
        tcGrupa = new TableColumn<>("Grupa");
        tcGrupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));

        tableGornji.getColumns().addAll(tcTim1, tcTim2, tcGoloviTim1, tcGoloviTim2, tcGrupa);

        //sredina
        srednjiHBox = new HBox();
        grupa = new Label("Grupa");
        grupaComboBox = new ComboBox<>();
        for (Grupa grupa : Grupa.values())
        {
            grupaComboBox.getItems().addAll(grupa);
        }
        grupaComboBox.getSelectionModel().selectFirst();
        prikaziTabelu = new Button("Prikazi tabelu");

        //add
        srednjiHBox.getChildren().addAll(grupa, grupaComboBox, prikaziTabelu);

        //table donji
        tableDonji = new TableView<>();
        tcTim = new TableColumn<>("Tim");
        tcTim.setCellValueFactory(new PropertyValueFactory<>("tim"));
        tcPobeda = new TableColumn<>("Pobeda");
        tcPobeda.setCellValueFactory(new PropertyValueFactory<>("pobeda"));
        tcNeresenih = new TableColumn<>("Neresenih");
        tcNeresenih.setCellValueFactory(new PropertyValueFactory<>("neresenih"));
        tcPoraza = new TableColumn<>("Poraza");
        tcPoraza.setCellValueFactory(new PropertyValueFactory<>("poraza"));
        tcGolovi = new TableColumn<>("Golovi");
        tcGolovi.setCellValueFactory(new PropertyValueFactory<>("datiPrimljeni"));
        tcPoeni = new TableColumn<>("Poeni");
        tcPoeni.setCellValueFactory(new PropertyValueFactory<>("poeni"));

        tableDonji.getColumns().addAll(tcTim, tcPobeda, tcNeresenih, tcPoraza, tcGolovi, tcPoeni);

        //add all to screen
        this.getChildren().addAll(gornjiOver, tableGornji, srednjiHBox, tableDonji);
    }

    private void setElementProperties()
    {
        gornjiOver.setSpacing(40);
        gornjiOver.setAlignment(Pos.CENTER);
        gornjiOver.setPadding(new Insets(10));

        gornji.setAlignment(Pos.CENTER);
        gornji.setSpacing(5);

        srednjiHBox.setAlignment(Pos.CENTER);
        srednjiHBox.setSpacing(5);
        srednjiHBox.setPadding(new Insets(10));
    }

    private void initListeners()
    {
        filtriraj.setOnAction(new FiltrirajController(this));
        prikaziSve.setOnAction(new PrikaziSveController());
        prikaziTabelu.setOnAction(new PrikaziTabeluController());
        statistika.setOnAction(new StatistikaController());
    }

    public TableView<Utakmica> getTableGornji() {
        return tableGornji;
    }

    public TextField getGolova() {
        return golova;
    }

    public ComboBox<String> getManjeVeceJednako() {
        return manjeVeceJednako;
    }

    public TableView<Tim> getTableDonji() {
        return tableDonji;
    }

    public ComboBox<Grupa> getGrupaComboBox() {
        return grupaComboBox;
    }
}
