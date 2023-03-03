package database;

public class Zitarice {
    
    private int id;
    private String ime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String setName() {
        return  ime;
    }

    public void setName(String ime) {
        this.ime = ime;
    }

    public String toString() {
        return String.format("[id: %d - name: %s ]", id, ime);
    }

}
