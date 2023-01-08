package Pizza.models;

import javax.persistence.*;
import java.util.Set;
import java.time.LocalDate;

@Entity
@Table(name = "zamowione_dania")
public class ZamowioneDania {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String uwagi;

    private LocalDate Date;

    @ManyToOne
    private Zamowienie zamowienie;

    @ManyToMany
    private Set<Dodatki> dodatki;

    @ManyToOne
    private Menu menu;

    public ZamowioneDania() {
    }

    public ZamowioneDania(String uwagi, Zamowienie zamowienie, Set<Dodatki> dodatki, Menu menu) {
        this.uwagi = uwagi;
        this.zamowienie = zamowienie;
        this.Date= LocalDate.now();
        this.dodatki = dodatki;
        this.menu = menu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate(){return Date;}

    public void setDate(LocalDate Date){this.Date = Date;}

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    public Set<Dodatki> getDodatki() {
        return dodatki;
    }

    public void setDodatki(Set<Dodatki> dodatki) {
        this.dodatki = dodatki;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "ZamowioneDania{" +
                "id=" + id +
                ", uwagi='" + uwagi + '\'' +
                ", zamowienie=" + zamowienie +
                ", dodatki=" + dodatki +
                ", menu=" + menu +
                '}';
    }
}