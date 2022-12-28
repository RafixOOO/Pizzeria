package Pizza.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String nazwa;
    private double cena;
    private String opis;
    private boolean visible;

    @OneToMany
    private Set<ZamowioneDania> zamowioneDania;

    public Menu() {
    }

    public Menu(String nazwa, double cena, String opis, Set<ZamowioneDania> zamowioneDania) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.opis = opis;
        this.zamowioneDania = zamowioneDania;
        this.visible=true;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Set<ZamowioneDania> getZamowioneDanias() {
        return zamowioneDania;
    }

    public void setZamowioneDanias(Set<ZamowioneDania> zamowioneDania) {
        this.zamowioneDania = zamowioneDania;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                ", opis='" + opis + '\'' +
                ", zamowioneDanias=" + zamowioneDania +
                '}';
    }
}