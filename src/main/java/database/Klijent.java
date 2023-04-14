package database;

public class Klijent {
    private int id;
    private String ime;
    private String prezime;
    private String address;
    private float bought;

    public Klijent(int id, String ime, String prezime, String address, float bought) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.address = address;
        this.bought = bought;
    }

    // getters and setters
}