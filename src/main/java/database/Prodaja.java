package database;

public class Prodaja {
    private int id;
    private String ime;
    private String tip;
    private int kolicina;
    private String vrijeme;
    private float cijena;

    public Prodaja(int id, String ime, String tip, int kolicina, String vrijeme, float cijena) {
        this.id = id;
        this.ime = ime;
        this.tip = tip;
        this.kolicina = kolicina;
        this.vrijeme = vrijeme;
        this.cijena = cijena;
    }

    // getters and setters
}