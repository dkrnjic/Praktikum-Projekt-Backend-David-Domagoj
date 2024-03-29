package database;

public class PoljoprivrednikDTO {
    private String ime;
    private String prezime;
    private String adresa;

    public PoljoprivrednikDTO() {
    }

    public PoljoprivrednikDTO(String ime, String prezime, String adresa) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
