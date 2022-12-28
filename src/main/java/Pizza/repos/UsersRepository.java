package Pizza.repos;

import Pizza.models.Pracownik;
import Pizza.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByPracownik(Pracownik pracownik);
    Integer countAllBy();
}