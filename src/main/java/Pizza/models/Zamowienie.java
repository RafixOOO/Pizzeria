package Pizza.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "zamowienie")
public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    String status;

    @OneToOne
    private Klient klient;

    @OneToMany
    private Set<ZamowioneDania> dania;

    public Zamowienie() {
    }

    public Zamowienie(Klient klient, Set<ZamowioneDania> dania) {
        this.status="Nowe";
        this.klient = klient;
        this.dania = dania;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Set<ZamowioneDania> getDania() {
        return dania;
    }

    public void setDania(Set<ZamowioneDania> dania) {
        this.dania = dania;
    }

    @Override
    public String toString() {
        return "Zamowienie{" +
                "id=" + id +
                ", klient=" + klient +
                ", dania=" + dania +
                '}';
    }
}