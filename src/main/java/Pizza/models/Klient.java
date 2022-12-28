package Pizza.models;

import javax.persistence.*;

@Entity
@Table(name = "klient")
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String imie;
    private String nazwisko;
    @Column(length = 9)
    private int numer;
    private String ulica;
    private String miejscowosc;

    @OneToOne
    private Zamowienie zamowienia;


    public Klient() {
    }

    public Klient(String imie, String nazwisko, int numer, String ulica, String miejscowosc, Zamowienie zamowienia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numer = numer;
        this.ulica = ulica;
        this.miejscowosc = miejscowosc;
        this.zamowienia = zamowienia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        nazwisko = nazwisko;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public Zamowienie getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(Zamowienie zamowienia) {
        this.zamowienia = zamowienia;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", numer=" + numer +
                ", ulica='" + ulica + '\'' +
                ", miejscowosc='" + miejscowosc + '\'' +
                ", zamowienia=" + zamowienia +
                '}';
    }
}