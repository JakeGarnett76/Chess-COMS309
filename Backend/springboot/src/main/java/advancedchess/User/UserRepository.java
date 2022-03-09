package advancedchess.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(int id);
    User findByUsername(String username);
    void deleteById(int id);
}
