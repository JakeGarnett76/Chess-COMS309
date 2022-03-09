package advancedchess.GameState;


import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStateRepository extends JpaRepository<GameState, Long> {
    GameState findByBoardId(int id);
    GameState findByBoardName(String boardName);
    GameState findByBoard(String board);
    void deleteByBoardId(int id);
}