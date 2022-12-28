package Pizza.repos;

import Pizza.models.Klient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlientRepository extends JpaRepository<Klient, Integer> {
    Integer countAllBy();
    Klient findByNumer(int numer);

}