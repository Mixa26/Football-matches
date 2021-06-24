package Module;

public class Utakmica {
    private String tim1;
    private String tim2;
    private int goloviTim1;
    private int goloviTim2;
    private Grupa grupa;

    public Utakmica()
    {

    }

    public Utakmica(String tim1, String tim2, int goloviTim1, int goloviTim2, Grupa grupa) {
        this.tim1 = tim1;
        this.tim2 = tim2;
        this.goloviTim1 = goloviTim1;
        this.goloviTim2 = goloviTim2;
        this.grupa = grupa;
    }

    @Override
    public String toString() {
        int sub = this.goloviTim1 + this.goloviTim2;
        return this.tim1 + "-" + this.tim2 + " - " + sub;
    }

    public String getTim1() {
        return tim1;
    }

    public String getTim2() {
        return tim2;
    }

    public int getGoloviTim1() {
        return goloviTim1;
    }

    public int getGoloviTim2() {
        return goloviTim2;
    }

    public Grupa getGrupa() {
        return grupa;
    }

    public void setGoloviTim1(int goloviTim1) {
        this.goloviTim1 = goloviTim1;
    }

    public void setGoloviTim2(int goloviTim2) {
        this.goloviTim2 = goloviTim2;
    }
}
