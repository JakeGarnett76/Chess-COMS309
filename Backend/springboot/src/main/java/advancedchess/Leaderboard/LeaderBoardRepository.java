package advancedchess.Leaderboard;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface LeaderBoardRepository extends JpaRepository<LeaderBoard, Long> {
        LeaderBoard findById(int id);
        LeaderBoard findByUserId(int userId);
        @Transactional
        void deleteById(int id);
}

