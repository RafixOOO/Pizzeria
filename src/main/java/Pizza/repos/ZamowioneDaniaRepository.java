package Pizza.repos;

import Pizza.models.Zamowienie;
import Pizza.models.ZamowioneDania;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ZamowioneDaniaRepository extends JpaRepository<ZamowioneDania, Integer> {
    List<ZamowioneDania> findByZamowienie_Id(Integer id);
    Integer countAllBy();
    ZamowioneDania findById(int id);
    List<ZamowioneDania> findByZamowienie(Zamowienie zamowienie);

    @Query("select sum(z.menu.cena) from ZamowioneDania z group by z.date order by z.date")
    ArrayList<?> find();

}