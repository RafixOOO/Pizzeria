package Pizza.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "username", nullable = false)
    private String username;
    private String password;
    private boolean enabled;

    @OneToOne
    private Pracownik pracownik;

    public Users() {
    }

    public Users(String username, String password, boolean enabled, Pracownik pracownik) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.pracownik = pracownik;
    }

    public String getLogin() {
        return username;
    }

    public void setLogin(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    @Override
    public String toString() {
        return "Users{" +
                ", login='" + username + '\'' +
                ", password='" + password + '\'' +
                ", pracownik=" + pracownik +
                '}';
    }
}