package database;

public class Zitarica {
    private int id;
    private String ime;
    private String tip;
    private float kupovna;
    private float prodajna;

    public Zitarica(int id, String ime, String tip, float kupovna, float prodajna) {
        this.id = id;
        this.ime = ime;
        this.tip = tip;
        this.kupovna = kupovna;
        this.prodajna = prodajna;
    }

    // getters and setters
}