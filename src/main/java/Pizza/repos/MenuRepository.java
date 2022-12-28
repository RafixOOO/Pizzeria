package Pizza.repos;

import Pizza.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Menu findByid(Integer id);
    Integer countAllBy();
    List<Menu> findByVisible(boolean vis);

}