package Module;

import java.util.Random;

public class Tim implements Comparable<Tim>{
    private String tim;
    private int pobeda;
    private int neresenih;
    private int poraza;
    private int dati;
    private int primljeni;
    private String datiPrimljeni;
    private int poeni;
    private Grupa grupa;

    public Tim ()
    {

    }

    public Tim(String tim, int pobeda, int neresenih, int poraza, int dati, int primljeni, int poeni, Grupa grupa) {
        this.tim = tim;
        this.pobeda = pobeda;
        this.neresenih = neresenih;
        this.poraza = poraza;
        this.dati = dati;
        this.primljeni = primljeni;
        this.poeni = poeni;
        this.grupa = grupa;
        datiPrimljeni = String.valueOf(dati) + ":" + String.valueOf(primljeni);
    }

    @Override
    public int compareTo(Tim o) {
        if (this.poeni > o.poeni)
        {
            return -1;
        }
        else if (this.poeni < o.poeni)
        {
            return 1;
        }
        else
        {
            if ((this.dati - this.primljeni) > (o.dati - o.primljeni))
            {
                return -1;
            }
            else if ((this.dati - this.primljeni) < (o.dati - o.primljeni))
            {
                return 1;
            }
            else
            {
                if (this.dati > o.dati)
                {
                    return -1;
                }
                if (this.dati < o.dati)
                {
                    return 1;
                }
                else
                {
                    for (Utakmica utakmice : Database.getInstance().getMatches())
                    {
                        if (utakmice.getTim1().equals(this.tim) && utakmice.getTim2().equals(o.tim))
                        {
                            if (utakmice.getGoloviTim1() > utakmice.getGoloviTim2())
                            {
                                return -1;
                            }
                            else if (utakmice.getGoloviTim1() < utakmice.getGoloviTim2())
                            {
                                return 1;
                            }
                            else
                            {
                                Random rand = new Random();
                                if (rand.nextInt(2) == 0)
                                {
                                    return -1;
                                }
                                return 0;
                            }
                        }
                        else if (utakmice.getTim2().equals(this.tim) && utakmice.getTim1().equals(o.tim))
                        {
                            if (utakmice.getGoloviTim1() < utakmice.getGoloviTim2())
                            {
                                return -1;
                            }
                            else if (utakmice.getGoloviTim1() > utakmice.getGoloviTim2())
                            {
                                return 1;
                            }
                            else
                            {
                                Random rand = new Random();
                                if (rand.nextInt(2) == 0)
                                {
                                    return -1;
                                }
                                return 0;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return this.tim + "-" + this.dati;
    }

    public String getTim() {
        return tim;
    }

    public int getPobeda() {
        return pobeda;
    }

    public int getNeresenih() {
        return neresenih;
    }

    public int getPoraza() {
        return poraza;
    }

    public String getDatiPrimljeni() {
        return datiPrimljeni;
    }

    public int getPoeni() {
        return poeni;
    }

    public Grupa getGrupa() {
        return grupa;
    }

    public void setDati(int dati) {
        this.dati = dati;
    }

    public int getDati() {
        return dati;
    }
}
