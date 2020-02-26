package mk.ukim.finki.emt.sports_shop.repository.jpa;

import mk.ukim.finki.emt.sports_shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByusername(String username);
}
