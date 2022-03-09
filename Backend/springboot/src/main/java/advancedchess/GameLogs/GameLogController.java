package advancedchess.GameLogs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameLogController {


    @Autowired
    GameLogRepository gameLogRepository;
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/gamelogs")
    List<GameLog> getAllGameLogs(){
        return gameLogRepository.findAll();
    }

    @GetMapping(path = "/gamelogs/{id}")
    GameLog getGameLogById( @PathVariable int id){
        return gameLogRepository.findById(id);
    }

    @GetMapping(path = "/gamelogs/{userId}/logs")
        List<GameLog> getGameLogsByUserId(@PathVariable int userId){
            return gameLogRepository.findAllByUserId(userId);
        }

    @PostMapping(path = "/gamelogs")
    String createGameLog(@RequestBody GameLog gameLog){
        if (gameLog == null)
            return failure;
        gameLogRepository.save(gameLog);
        return success;
    }

    @PutMapping("/gamelog/{id}")
    GameLog updateGameLog(@PathVariable int id, @RequestBody GameLog request){
        GameLog gameLog = gameLogRepository.findById(id);
        if(gameLog == null){
            return null;
        }
        gameLog.setDate(request.getDate());
        gameLog.setUserId(request.getUserId());
        gameLog.setResults(request.getResults());
        gameLogRepository.save(gameLog);
        return gameLogRepository.findById(id);
    }



}
