package Pizza.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dodatki")
public class Dodatki {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String nazwa;
    private double cena;

    @ManyToMany
    private Set<ZamowioneDania> zamowioneDania;

    public Dodatki() {
    }

    public Dodatki(String nazwa, double cena, Set<ZamowioneDania> zamowioneDania) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.zamowioneDania = zamowioneDania;
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

    public Set<ZamowioneDania> getZamowioneDania() {
        return zamowioneDania;
    }

    public void setZamowioneDania(Set<ZamowioneDania> zamowioneDania) {
        this.zamowioneDania = zamowioneDania;
    }

    @Override
    public String toString() {
        return "Dodatki{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                ", zamowioneDania=" + zamowioneDania +
                '}';
    }
}