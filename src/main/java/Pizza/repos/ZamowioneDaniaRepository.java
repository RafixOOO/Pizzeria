package Pizza.repos;

import Pizza.models.Zamowienie;
import Pizza.models.ZamowioneDania;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZamowioneDaniaRepository extends JpaRepository<ZamowioneDania, Integer> {
    List<ZamowioneDania> findByZamowienie_Id(Integer id);
    Integer countAllBy();
    ZamowioneDania findById(int id);
    List<ZamowioneDania> findByZamowienie(Zamowienie zamowienie);
}