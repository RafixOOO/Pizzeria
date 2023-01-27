package Pizza.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Set;
import java.time.YearMonth;

@Entity
@Table(name = "zamowione_dania")
public class ZamowioneDania {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String uwagi;

    private LocalDate date;

    @ManyToOne
    private Zamowienie zamowienie;

    @ManyToMany
    private Set<Dodatki> dodatki;

    @ManyToOne
    private Menu menu;

    public ZamowioneDania() {
    }
    public ZamowioneDania(String uwagi, Zamowienie zamowienie,Set<Dodatki> dodatki, Menu menu) {

        YearMonth y = YearMonth.now();
        LocalDate date = LocalDate.of(y.get(ChronoField.YEAR),  y.get(ChronoField.MONTH_OF_YEAR), 1);
        this.uwagi = uwagi;
        this.zamowienie = zamowienie;
        this.date= date;
        this.dodatki = dodatki;
        this.menu = menu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate(){return date;}


    public void setDate(LocalDate date){this.date = date;}


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