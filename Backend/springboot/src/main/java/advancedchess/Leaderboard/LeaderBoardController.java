package advancedchess.Leaderboard;

import advancedchess.User.User;
import advancedchess.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    public class LeaderBoardController {

        @Autowired
        LeaderBoardRepository lbRepository;

        @Autowired
        UserRepository userRepository;

        private String success = "{\"message\":\"success\"}";
        private String failure = "{\"message\":\"failure\"}";

        //get all Leaderboards
        @GetMapping(path = "/leaderboards")
        List<LeaderBoard> getAllLeaderBoards(){
            return lbRepository.findAll();
        }

        //get leaderboard by Id
        @GetMapping(path = "/leaderboards/{id}")
        LeaderBoard getLeaderBoardById(@PathVariable int id){
            return lbRepository.findById(id);
        }

        //get leaderboard by UserId
        @GetMapping(path = "/leaderboards/{userId}/user")
        LeaderBoard getLeaderBoardByUserId(@PathVariable int userId){
            return lbRepository.findByUserId(userId);
        }

        //get wins by Id
        @GetMapping(path = "/leaderboards/{id}/wins")
        int getLeaderBoardWinsById(@PathVariable int id){
            return lbRepository.findById(id).getWins();
        }

        //get losses by Id
        @GetMapping(path = "/leaderboards/{id}/losses")
        int getLeaderBoardLossesById(@PathVariable int id){
            return lbRepository.findById(id).getLosses();
        }

        //get wins by UserId
        @GetMapping(path = "/leaderboards/{userId}/wins/user")
        int getLeaderBoardWinsByUserId(@PathVariable int userId){
            return lbRepository.findByUserId(userId).getWins();
        }

        //get losses by UserId
        @GetMapping(path = "/leaderboards/{userId}/losses/user")
        int getLeaderBoardLossesByUserId(@PathVariable int userId){
            return lbRepository.findByUserId(userId).getLosses();
        }

        //create a leaderboard
        @PostMapping(path = "/leaderboards")
        String createLeaderBoard(@RequestBody LeaderBoard LeaderBoard){
            if (LeaderBoard == null)
                return failure;
            lbRepository.save(LeaderBoard);
            return success;
        }

        //update Leaderboard by id
        @PutMapping(path = "/leaderboards/{id}")
        LeaderBoard updateLeaderBoard(@PathVariable int id, @RequestBody LeaderBoard request){
            LeaderBoard lb = lbRepository.findById(id);
            if(lb == null)
                return null;
            lb.setWins(request.getWins());
            lb.setLosses(request.getLosses());
            lb.setU_id(request.getU_id());
            lbRepository.save(lb);
            return lbRepository.findById(id);
        }

        //update Leaderboard by UserId
        @PutMapping(path = "/leaderboards/{userId}/user")
        LeaderBoard updateLeaderBoardByUserId(@PathVariable int userId, @RequestBody LeaderBoard request){
            LeaderBoard lb = lbRepository.findByUserId(userId);
            if(lb == null)
                return null;
            lb.setWins(request.getWins());
            lb.setLosses(request.getLosses());
            lb.setU_id(request.getU_id());
            lbRepository.save(lb);
            return lbRepository.findByUserId(userId);
        }
    }


