package Pizza.repos;

import Pizza.models.Dodatki;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DodatkiRepository extends JpaRepository<Dodatki, Integer> {
    Integer countAllBy();
    Dodatki findById(int id);
    Dodatki findByNazwa(String nazwa);
}