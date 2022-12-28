package Pizza.controlers;


import Pizza.models.*;
import Pizza.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader implements ApplicationRunner {

    public DodatkiRepository dodatkiRepository;
    public MenuRepository menuRepository;
    public PracownikRepository pracownikRepository;
    public UsersRepository usersRepository;
    public UserRoleRepository roleRepository;
    public ZamowienieRepository zamowienieRepository;
    public ZamowioneDaniaRepository zamowioneDaniaRepository;
    public KlientRepository klientRepository;

    @Autowired
    public DataLoader(DodatkiRepository dodatkiRepository, MenuRepository menuRepository,
                      PracownikRepository pracownikRepository, UsersRepository usersRepository,
                      UserRoleRepository roleRepository, ZamowienieRepository zamowienieRepository,
                      ZamowioneDaniaRepository zamowioneDaniaRepository, KlientRepository klientRepository){
        this.dodatkiRepository = dodatkiRepository;
        this.menuRepository = menuRepository;
        this.pracownikRepository = pracownikRepository;
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.zamowienieRepository = zamowienieRepository;
        this.zamowioneDaniaRepository = zamowioneDaniaRepository;
        this.klientRepository = klientRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Menu> pizze = new ArrayList<>(Arrays.asList(
                new Menu("Margherita", 16,"sos pomidorowy, mozzarella" ,null),
                new Menu("Wegetariańska", 20,"sos pomidorowy, mozzarella, pieczarki, kukurydza, papryka" ,null),
                new Menu("Peperoni", 20.50,"sos pomidorowy, mozzarella, ostra papryka, peperoni, oregano" ,null),
                new Menu("Wiejska", 23,"sos pomidorowy, mozzarella, pieczarki, cebula, kiełbasa wiejska" ,null),
                new Menu("MIX", 22,"sos pomidorowy, mozzarella, szynka, boczek, salami, pieczarki, kukurydza" ,null)));

        menuRepository.save(pizze.get(0));
        menuRepository.save(pizze.get(1));
        menuRepository.save(pizze.get(2));
        menuRepository.save(pizze.get(3));
        menuRepository.save(pizze.get(4));

        List<Dodatki> dodatki = new ArrayList<>(Arrays.asList(
                new Dodatki("Bekon", 2, null),
                new Dodatki("Karkówka", 2, null),
                new Dodatki("Kiełbasa", 2, null),
                new Dodatki("Kurczak", 2, null),
                new Dodatki("Pieczarki", 4, null),
                new Dodatki("Salami", 2, null),
                new Dodatki("Ser", 4, null),
                new Dodatki("Ser Feta", 4, null),
                new Dodatki("Ser Pleśniowy", 4, null),
                new Dodatki("Szynka", 2, null),
                new Dodatki("Tuńczyk", 4, null)));

        dodatkiRepository.save(dodatki.get(0));
        dodatkiRepository.save(dodatki.get(1));
        dodatkiRepository.save(dodatki.get(2));
        dodatkiRepository.save(dodatki.get(3));
        dodatkiRepository.save(dodatki.get(4));
        dodatkiRepository.save(dodatki.get(5));
        dodatkiRepository.save(dodatki.get(6));
        dodatkiRepository.save(dodatki.get(7));
        dodatkiRepository.save(dodatki.get(8));
        dodatkiRepository.save(dodatki.get(9));
        dodatkiRepository.save(dodatki.get(10));

        Pracownik pracownik = new Pracownik("Konto", "Admina","Admin" ,null);
        Pracownik pracownik1 = new Pracownik("Konto", "Pracownika","Pracownik", null);
        Pracownik pracownik2 = new Pracownik("Marek", "Nowak","Kucharz", null);
        Pracownik pracownik3 = new Pracownik("Izabela", "Wołacz","Kelnerka", null);

        pracownikRepository.save(pracownik);
        pracownikRepository.save(pracownik1);
        pracownikRepository.save(pracownik2);
        pracownikRepository.save(pracownik3);

        Users user =  new Users("BOSS9909", new BCryptPasswordEncoder().encode("Boss1111"),true , pracownik);
        Users user1 =  new Users("PRACOWNIK7722", new BCryptPasswordEncoder().encode("Pracownik2222"),true , pracownik1);

        usersRepository.save(user);
        usersRepository.save(user1);

        pracownik.setUser(user);
        pracownik1.setUser(user1);

        pracownikRepository.save(pracownik);
        pracownikRepository.save(pracownik1);

        UserRole role = new UserRole(user, "Role_Admin");
        UserRole role1 = new UserRole(user1, "Role_Staff");
        roleRepository.save(role);
        roleRepository.save(role1);
        roleRepository.save(role1);

    }
}
