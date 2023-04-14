package database;

public class Otkup {
    private int id;
    private String ime;
    private String tip;
    private int kolicina;
    private String vrijeme;
    private float primjesa;
    private float vlaga;
    private float cijena;

    public Otkup(int id, String ime, String tip, int kolicina, String vrijeme, float primjesa, float vlaga,
            float cijena) {
        this.id = id;
        this.ime = ime;
        this.tip = tip;
        this.kolicina = kolicina;
        this.vrijeme = vrijeme;
        this.primjesa = primjesa;
        this.vlaga = vlaga;
        this.cijena = cijena;
    }

    // getters and setters
}