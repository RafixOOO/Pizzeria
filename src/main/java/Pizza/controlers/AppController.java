package Pizza.controlers;

import Pizza.models.*;
import Pizza.repos.*;
import Pizza.service.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.*;


@Controller
public class AppController {

    @Autowired
    public DodatkiRepository dodatkiRepository;
    @Autowired
    public MenuRepository menuRepository;
    @Autowired
    public PracownikRepository pracownikRepository;
    @Autowired
    public UsersRepository usersRepository;
    @Autowired
    public UserRoleRepository roleRepository;
    @Autowired
    public ZamowienieRepository zamowienieRepository;
    @Autowired
    public ZamowioneDaniaRepository zamowioneDaniaRepository;
    @Autowired
    public KlientRepository klientRepository;

    @Autowired
    public Email email;

    String ID;
    Klient klient = new Klient();
    Zamowienie zamowienie = new Zamowienie();
    String randomNumber;

    @RequestMapping("/")
        public String index(Model model) {
            model.addAttribute("menu", menuRepository.findAll());
            FormCommand formCommand = new FormCommand();
            model.addAttribute("command", formCommand);
            return "Index";
        }

    @RequestMapping("/Index")
    public String index1(Model model) {
        model.addAttribute("menu", menuRepository.findAll());
        FormCommand formCommand = new FormCommand();
        model.addAttribute("command", formCommand);
        return "Index";
    }
    @RequestMapping("/Panel")
    public String adminPage() {
        return "Panel";
    }

    @RequestMapping("/Pracownicy")
    public String count(Model model) {
        model.addAttribute("pracownik", pracownikRepository.findByUser(null));
        FormCommand formCommand = new FormCommand();
        model.addAttribute("command", formCommand);
        return "Pracownicy";
    }

    @RequestMapping("/Modify")
    public String modify(Model model, @RequestParam(name = "id", required = false, value = "") String id) {
        FormCommand formcommand = new FormCommand();
        model.addAttribute("command", formcommand);
        ID = id;
        return "Modyfikujpracownika";
    }

    @RequestMapping("/Usun")
    public String usun(Model model, @RequestParam(name = "id", required = false, value = "") String id) {
        pracownikRepository.deleteById(Integer.parseInt(id));
        return "Panel";
    }


    @RequestMapping(value = "/Zmiana", method = RequestMethod.GET)
    public String modify(Model model, FormCommand command) {
        Pracownik prac = pracownikRepository.findByid(Integer.parseInt(ID));
        String imie = command.getTextField();
        String nazwisko = command.getTextareaField();
        String stanowisko = command.getDatetimeField();

        if (imie.equals("") && nazwisko.equals("") && stanowisko.equals("")) {
            return "Panel";
        } else if (imie.equals("") && nazwisko.equals("")) {
            prac.setStanowisko(stanowisko);
        } else if (imie.equals("") && stanowisko.equals("")) {
            prac.setNazwisko(nazwisko);
        } else if (nazwisko.equals("") && stanowisko.equals("")) {
            prac.setImie(imie);
        } else if (imie.equals("")) {
            prac.setNazwisko(nazwisko);
            prac.setStanowisko(stanowisko);
        } else if (nazwisko.equals("")) {
            prac.setImie(imie);
            prac.setStanowisko(stanowisko);
        } else if (stanowisko.equals("")) {
            prac.setImie(imie);
            prac.setNazwisko(nazwisko);
        } else {
            prac.setImie(imie);
            prac.setNazwisko(nazwisko);
            prac.setStanowisko(stanowisko);
        }
        pracownikRepository.save(prac);
        return "Panel";
    }

    public static <T> List<T> convertSetToList(Set<T> set)
    {
        List<T> list = new ArrayList<>();

        for (T t : set)
            list.add(t);

        return list;
    }

    @RequestMapping(value = "/Dodaj", method = RequestMethod.GET)
    public String dodaj(Model model, FormCommand command) {
        String imie = command.getTextField();
        String nazwisko = command.getTextareaField();
        String stanowisko = command.getDatetimeField();
        Pracownik prac = new Pracownik(imie, nazwisko, stanowisko, null);
        pracownikRepository.save(prac);
        return "Panel";
    }

    @RequestMapping(value = "/Dodajmenu", method = RequestMethod.GET)
    public String dodajmenu(Model model, FormCommand command) {
        String nazwa = command.getTextField();
        int cena = Integer.parseInt(command.getTextareaField());
        String opis = command.getDatetimeField();
        Menu menu = new Menu(nazwa, cena, opis, null);
        menuRepository.save(menu);
        return "Panel";
    }

    @RequestMapping("/Usunmenu")
    public String usunmenu(Model model, @RequestParam(name = "id", required = false, value = "") String id) {
        Menu menu = menuRepository.findByid(Integer.parseInt(id));
        menu.setVisible(false);
        menuRepository.save(menu);
        return "Panel";
    }

    @RequestMapping("/Pokazmenu")
    public String pokazmenu(Model model, @RequestParam(name = "id", required = false, value = "") String id) {
        Menu menu = menuRepository.findByid(Integer.parseInt(id));
        menu.setVisible(true);
        menuRepository.save(menu);
        return "Panel";
    }

    @RequestMapping("/Modifymenu")
    public String modifymenu(Model model, @RequestParam(name = "id", required = false, value = "") String id) {
        FormCommand formcommand = new FormCommand();
        model.addAttribute("command", formcommand);
        ID = id;
        return "Modyfikujmenu";
    }

    @RequestMapping(value = "/Zmianamenu", method = RequestMethod.GET)
    public String modifymenu(Model model, FormCommand command) {
        Menu menu = menuRepository.findByid(Integer.parseInt(ID));
        String nazwa = command.getTextField();
        double cena = 0;
        String cenatxt = "";
        try {
            cena = Double.parseDouble(command.getTextareaField());
            cenatxt = command.getTextareaField();
        } catch (NumberFormatException | NullPointerException ex) {
            cenatxt = "brak";
        }
        String opis = command.getDatetimeField();
        if (nazwa.equals("") && cenatxt.equals("brak") && opis.equals("")) {
            return "Panel";
        } else if (nazwa.equals("") && cenatxt.equals("brak")) {
            menu.setOpis(opis);
        } else if (nazwa.equals("") && opis.equals("")) {
            menu.setCena(cena);
        } else if (cenatxt.equals("brak") && opis.equals("")) {
            menu.setNazwa(nazwa);
        } else if (nazwa.equals("")) {
            menu.setCena(cena);
            menu.setOpis(opis);
        } else if (cenatxt.equals("brak")) {
            menu.setNazwa(nazwa);
            menu.setOpis(opis);
        } else if (opis.equals("")) {
            menu.setNazwa(nazwa);
            menu.setCena(cena);
        } else {
            menu.setNazwa(nazwa);
            menu.setCena(cena);
            menu.setOpis(opis);
        }

        menuRepository.save(menu);
        return "Panel";
    }

    @RequestMapping("/Menu")
    public String meny(Model model) {
        model.addAttribute("menu", menuRepository.findAll());
        FormCommand formCommand = new FormCommand();
        model.addAttribute("command", formCommand);
        return "Menu";
    }

    @RequestMapping("/Zamowienia")
    public String zamowienia(Model model) {
        model.addAttribute("zamowienia", zamowienieRepository.findByStatusNot("Dostarczone"));
        model.addAttribute("dostarczone", zamowienieRepository.findByStatus("Dostarczone"));
        return "Zamowienia";

    }

    @RequestMapping(value = "/Zrealizuj")
    public String zrealizuj(Model model, @RequestParam(name = "id", required = false, value = "") String id) {
        Zamowienie zam = zamowienieRepository.findByid(Integer.parseInt(id));
        String status1 = "Nowe";
        String status2 = "W przygotowaniu";
        String status3 = "Oczekiwanie";
        String status4 = "W drodze";
        String status5 = "Dostarczone";
        if(zam.getStatus().equals(status1)){
            zam.setStatus(status2);
        }
        else if(zam.getStatus().equals(status2)){
            zam.setStatus(status3);
        }
        else if(zam.getStatus().equals(status3)){
            zam.setStatus(status4);
        }
        else if(zam.getStatus().equals(status4)){
            zam.setStatus(status5);
        }
        else{
        }
        zamowienieRepository.save(zam);
        model.addAttribute("zamowienia", zamowienieRepository.findByStatusNot("Dostarczone"));
        model.addAttribute("dostarczone", zamowienieRepository.findByStatus("Dostarczone"));
        return "Zamowienia";
    }

    @RequestMapping(value = "/Zrealizuj1")
    public String zrealizuj1(Model model, @RequestParam(name = "id", required = false, value = "") String id) {
        Zamowienie zam = zamowienieRepository.findByid(Integer.parseInt(id));
        String status1 = "Nowe";
        String status2 = "W przygotowaniu";
        String status3 = "Oczekiwanie";
        String status4 = "W drodze";
        String status5 = "Dostarczone";
        if(zam.getStatus().equals(status5)){
            zam.setStatus(status4);
        }
        else if(zam.getStatus().equals(status4)){
            zam.setStatus(status3);
        }
        else if(zam.getStatus().equals(status3)){
            zam.setStatus(status2);
        }
        else if(zam.getStatus().equals(status2)){
            zam.setStatus(status1);
        }
        else{
        }
        zamowienieRepository.save(zam);
        model.addAttribute("zamowienia", zamowienieRepository.findByStatusNot("Dostarczone"));
        model.addAttribute("dostarczone", zamowienieRepository.findByStatus("Dostarczone"));
        return "Zamowienia";
    }

    @RequestMapping(value="/Check")
    public String check(Model model, @RequestParam(name = "id", required = false, value = "") String id){
        model.addAttribute("dania", zamowioneDaniaRepository.findByZamowienie_Id(Integer.parseInt(id)));
        return "Check";
    }

    @RequestMapping("/Info")
    public String info(Model model){
        model.addAttribute("dane", zamowioneDaniaRepository.find());
        return "Info";
    }

    @RequestMapping("/Dane")
    public String dane(Model model, FormCommand command){
        FormCommand formCommand = new FormCommand();
        model.addAttribute("command", formCommand);
        return "Dane";
    }

    @RequestMapping(value = "/dan", method = RequestMethod.GET)
    public String mail(Model model, FormCommand command) {

        model.addAttribute("menu1", menuRepository.findByVisible(true));
        return "Order";
    }

    @RequestMapping(value = "/da", method = RequestMethod.GET)
    public String klient(Model model, FormCommand command) throws MessagingException{
        klient = new Klient(command.getTextField(),command.getTextareaField(),Integer.parseInt(command.getDatetimeField()),command.getColorField(),command.getRadioButtonSelectedValue(),null);
        Random r = new Random();

        randomNumber = String.format("%04d", (Object) Integer.valueOf(r.nextInt(1001)));
        email.sendEmail(command.getColorField1(),""+randomNumber,"Kod do autoryzacji");
        model.addAttribute("number", randomNumber);
        return "Ver";
    }

    @RequestMapping(value="/Dodajpizze")
    public String pizza(Model model, @RequestParam(name = "id", required = false, value = "") String id){
        FormCommand formCommand = new FormCommand();
        model.addAttribute("command",formCommand);
        ZamowioneDania dania = new ZamowioneDania();
        dania.setMenu(null);
        dania = new ZamowioneDania(null,null,null,menuRepository.findByid(Integer.parseInt(id)));
        zamowioneDaniaRepository.save(dania);
        ID=""+dania.getId();
        model.addAttribute("dodatki", dodatkiRepository.findAll());
        return "Dodatki";
    }

    @RequestMapping(value="/Koszyk")
    public String koszyk(Model model){
        model.addAttribute("dania",zamowioneDaniaRepository.findByZamowienie(null));
        List<ZamowioneDania> cena = zamowioneDaniaRepository.findByZamowienie(null);
        double cenapizz = 0;
        double cenadodatkow = 0;
        for(int i = 0;i<cena.size();i++){
            ZamowioneDania dania = cena.get(0);
            Menu menu = dania.getMenu();
            cenapizz=cenapizz+menu.getCena();
            List<Dodatki> dodatki = convertSetToList(dania.getDodatki());
            for(int j=0;j<dodatki.size();j++){
                Dodatki dodatek = dodatki.get(j);
                cenadodatkow=cenadodatkow+dodatek.getCena();
            }
        }
        double cenaglowna=cenapizz+cenadodatkow;
        model.addAttribute("cena",cenaglowna);
        return "Koszyk";
    }

    @RequestMapping("/Order")
    public String order(Model model){
        model.addAttribute("menu1", menuRepository.findByVisible(true));
        return "Order";
    }

    @RequestMapping(value="/Dodajdodatki", method = RequestMethod.GET)
    public String dodatki(Model model, FormCommand command){
        String [] multiCheckboxSelectedValues = command.getMultiCheckboxSelectedValues();
        ZamowioneDania dania = zamowioneDaniaRepository.findById(Integer.parseInt(ID));
        HashSet<Dodatki> dodatki = new HashSet<Dodatki>();
        dodatki.clear();
        for(int i=0; i<multiCheckboxSelectedValues.length; i++){
            Dodatki dodatek = dodatkiRepository.findByNazwa(multiCheckboxSelectedValues[i]);
            dodatki.add(dodatek);
        }
        dania.setDodatki(dodatki);
        dania.setUwagi(command.getTextField());
        zamowioneDaniaRepository.save(dania);
        model.addAttribute("menu1", menuRepository.findByVisible(true));
        return "Order";
    }

    @RequestMapping("/Zamow")
    public String zamow(){
        List<ZamowioneDania> dania =zamowioneDaniaRepository.findByZamowienie(null);
        klientRepository.save(klient);
        zamowienie.setKlient(klient);
        zamowienie.setStatus("Nowe");
        HashSet<ZamowioneDania> da = new HashSet<ZamowioneDania>();
        da.clear();
        for(int i=0;i<dania.size();i++) {
            ZamowioneDania dan = dania.get(i);
            da.add(dan);
        }
        zamowienie.setDania(da);
        zamowienieRepository.save(zamowienie);
        List<ZamowioneDania> danie = zamowioneDaniaRepository.findByZamowienie(null);
        for(int i=0; i<danie.size();i++){
            ZamowioneDania dan = danie.get(i);
            dan.setZamowienie(zamowienie);
            zamowioneDaniaRepository.save(dan);
        }
        return "Przyjecie";
    }

    @RequestMapping("/Przyjecie")
    public String przyjecie(){
        return "Przyjecie";
    }

    @RequestMapping("/Password")
    public String password(Model model){
        FormCommand formCommand = new FormCommand();
        model.addAttribute("command", formCommand);
        return "Password";
    }

    @RequestMapping(value = "/Zmianahaslaadmina", method = RequestMethod.GET)
    public String Zmianahaslaadmina(Model model, FormCommand command){
        Pracownik pracownik = pracownikRepository.findBynazwisko("Admina");
        Users user = usersRepository.findByPracownik(pracownik);
        String haslo1 = command.getTextField()+"";
        String haslo2 = command.getTextareaField()+"";
        if(haslo1.equals(haslo2)){
            user.setPassword(new BCryptPasswordEncoder().encode(haslo1));
            usersRepository.save(user);
            return "Panel";
        }
        return "Panel";
    }

    @RequestMapping(value = "/Zmianahaslapracownika", method = RequestMethod.GET)
    public String Zmianahaslpracownika(Model model, FormCommand command){

        Pracownik pracownik = pracownikRepository.findBynazwisko("Pracownika");
        Users user = usersRepository.findByPracownik(pracownik);

        String haslo1 = command.getDatetimeField()+"";
        String haslo2 = command.getDatetimeField1()+"";

        if(haslo1.equals(haslo2)){
            user.setPassword(new BCryptPasswordEncoder().encode(haslo1));
            usersRepository.save(user);
            return "Panel";
        }
        return "Panel";
    }



    @ExceptionHandler
    public String handlerException(Model model, Exception exception) {
        return "Error";
    }
}
