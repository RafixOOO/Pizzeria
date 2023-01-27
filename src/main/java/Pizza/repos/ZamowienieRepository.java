package Pizza.repos;

import Pizza.models.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZamowienieRepository extends JpaRepository<Zamowienie, Integer> {
    List<Zamowienie> findByStatus(String status);
    List<Zamowienie> findByStatusNot(String status);
    Zamowienie findByid(Integer id);
    Integer countAllBy();
}