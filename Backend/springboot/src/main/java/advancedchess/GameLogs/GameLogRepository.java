package advancedchess.GameLogs;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameLogRepository extends JpaRepository<GameLog, Long> {
    GameLog findById(int id);
    GameLog findByUserId(int userId);
    List<GameLog> findAllByUserId(int userId);
}

