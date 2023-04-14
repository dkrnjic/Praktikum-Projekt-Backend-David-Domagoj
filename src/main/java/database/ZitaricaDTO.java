package database;

public class ZitaricaDTO {
    private String ime;
    private String tip;
    private float kupovna;
    private float prodajna;

    public ZitaricaDTO() {
    }

    public ZitaricaDTO(String ime, String tip, float kupovna, float prodajna) {
        this.ime = ime;
        this.tip = tip;
        this.kupovna = kupovna;
        this.prodajna = prodajna;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public float getKupovna() {
        return kupovna;
    }

    public void setKupovna(float kupovna) {
        this.kupovna = kupovna;
    }

    public float getProdajna() {
        return prodajna;
    }

    public void setProdajna(float prodajna) {
        this.prodajna = prodajna;
    }
}
