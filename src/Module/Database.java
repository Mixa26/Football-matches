package Module;

import View.MainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    private static final Database instance;
    private final MainView mainView = MainView.getInstance();
    List<Utakmica> utakmice = new ArrayList<>();
    List<Tim> timovi = new ArrayList<>();

    private Database()
    {
        loadMatches();
        loadTeams();
    }

    static
    {
        instance = new Database();
    }

    public static Database getInstance()
    {
        return instance;
    }

    public List<Utakmica> getMatches()
    {
        return utakmice;
    }

    public void loadTeams()
    {
        for (Utakmica utakmica : utakmice)
        {
            if (!containsTim(utakmica.getTim1()))
            {
                Tim tim1 = new Tim();
                tim1 = sracunajTim(utakmica.getTim1());
                timovi.add(tim1);
            }
            if (!containsTim(utakmica.getTim2()))
            {
                Tim tim2 = new Tim();
                tim2 = sracunajTim(utakmica.getTim2());
                timovi.add(tim2);
            }
        }
    }

    private boolean containsTim(String tim)
    {
        for (Tim tim1 : timovi)
        {
            if (tim1.getTim().equals(tim))
            {
                return true;
            }
        }
        return false;
    }

    private Tim sracunajTim(String tim)
    {
        int pobeda = 0;
        int neresenih = 0;
        int poraz = 0;
        int datih = 0;
        int primljenih = 0;
        int poeni = 0;
        Grupa grupa = Grupa.A;
        for (Utakmica utakmica : utakmice)
        {
            if (utakmica.getTim1().equals(tim))
            {
                if (utakmica.getGoloviTim1() > utakmica.getGoloviTim2())
                {
                    pobeda++;
                }
                else if (utakmica.getGoloviTim1() < utakmica.getGoloviTim2())
                {
                    poraz++;
                }
                else if (utakmica.getGoloviTim1() == utakmica.getGoloviTim2())
                {
                    neresenih++;
                }
                datih += utakmica.getGoloviTim1();
                primljenih += utakmica.getGoloviTim2();
                grupa = utakmica.getGrupa();
            }
            else if (utakmica.getTim2().equals(tim))
            {
                if (utakmica.getGoloviTim1() < utakmica.getGoloviTim2())
                {
                    pobeda++;
                }
                else if (utakmica.getGoloviTim1() > utakmica.getGoloviTim2())
                {
                    poraz++;
                }
                else if (utakmica.getGoloviTim1() == utakmica.getGoloviTim2())
                {
                    neresenih++;
                }
                datih += utakmica.getGoloviTim2();
                primljenih += utakmica.getGoloviTim1();
                grupa = utakmica.getGrupa();
            }
        }
        poeni = pobeda * 3 + neresenih;
        return new Tim(tim, pobeda, neresenih, poraz, datih, primljenih, poeni, grupa);
    }

    private void loadMatches()
    {
        try
        {
            File file = new File("sp.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine())
            {
                String grupa = scanner.nextLine();
                for (int i = 0; i < 6; i++) {
                    String[] args = scanner.nextLine().split(",");
                    String[] timovi = args[0].split("-");
                    String[] golovi = args[1].split(":");
                    utakmice.add(new Utakmica(timovi[0],timovi[1], Integer.parseInt(golovi[0]), Integer.parseInt(golovi[1]), Grupa.valueOf(grupa)));
                    //mainView.getTableGornji().getItems().add(new Utakmica(timovi[0],timovi[1], Integer.parseInt(golovi[0]), Integer.parseInt(golovi[1]), Grupa.valueOf(grupa)));
                }
            }
            //mainView.getTableGornji().refresh();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public List<Utakmica> getUtakmice() {
        return utakmice;
    }

    public List<Tim> getTimovi() {
        return timovi;
    }
}
