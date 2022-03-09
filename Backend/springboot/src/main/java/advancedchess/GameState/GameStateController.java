package advancedchess.GameState;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameStateController {
        @Autowired
        GameStateRepository gamestateRepository;

        private String success = "{\"message\":\"success\"}";
        private String failure = "{\"message\":\"failure\"}";

        @GetMapping(path = "/boards")
        List<GameState> getAllBoards(){
            return gamestateRepository.findAll();
        }

        //get board by Id
        @GetMapping(path = "/boards/{boardId}")
        String getBoard(@PathVariable int boardId){
            return gamestateRepository.findByBoardId(boardId).getBoard();
        }

        //make a board
        @PostMapping(path = "/boards")
        String createBoard(@RequestBody GameState board){
            if (board == null)
                return failure;
            gamestateRepository.save(board);
            return success;
        }

        //update a board
        @PutMapping("/boards/{boardId}")
        GameState updateBoard(@PathVariable int boardId, @RequestBody GameState request) {
            GameState gamestate = gamestateRepository.findByBoardId(boardId);
            if (gamestate == null)
                return null;
            String newBoard = request.getBoard();
            gamestate.setBoard(newBoard);
            gamestateRepository.save(gamestate);
            return gamestateRepository.findByBoardId(boardId);
        }

        //get user count
        @GetMapping(path = "/boards/count/{boardId}")
            int getCount(@PathVariable int boardId){
                return gamestateRepository.findByBoardId(boardId).getCount();
            }


        //update a user Count
        @PutMapping("/boards/count/{boardId}")
        GameState updateCount(@PathVariable int boardId, @RequestBody GameState request) {
            GameState gamestate = gamestateRepository.findByBoardId(boardId);
            if (gamestate == null)
                return null;
            gamestateRepository.save(request);
            return gamestateRepository.findByBoardId(boardId);
        }

    }