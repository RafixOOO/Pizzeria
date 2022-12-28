package Pizza.repos;

import Pizza.models.Pracownik;
import Pizza.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PracownikRepository extends JpaRepository<Pracownik, Integer> {
    Pracownik findByid(Integer id);
    List<Pracownik> findByUser(Users user);

    Pracownik findBynazwisko(String nazwisko);
    Integer countAllBy();

}