package advancedchess.User;

import advancedchess.GameLogs.GameLog;
import advancedchess.Leaderboard.LeaderBoard;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    //test
    @Id
    @ApiModelProperty(notes = "Unique ID of the User",name="id",required=true,value="Unique ID of the User")
    private int id;

    @ApiModelProperty(notes = "The name of the User",name="username",required=true,value="The name of the User")
    private String username;

    @ApiModelProperty(notes = "Password of user, used in login process",name="password",required=true,value="Password of user, used in login process")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lb_id")
    private LeaderBoard leaderBoard;


    @OneToMany(cascade = {CascadeType.ALL})
    private List<GameLog> gameLogs;

    /*May need an is ifActive later */
    //private boolean ifActive;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        gameLogs = new ArrayList<>();

    }
    public User(){

    }



    public int getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public LeaderBoard getLeaderBoard(){ return leaderBoard;}

    public void setId(int newId){
        id = newId;
    }

    public void setUsername(String newUsername){
        username = newUsername;
    }

    public void setPassword(String newPassword){
        password = newPassword;
    }

    public void setLeaderBoard(){
        leaderBoard = new LeaderBoard();
    }

    public List<GameLog> getGameLogs(){
        return gameLogs;
    }

    public void setPhones(List<GameLog> gameLogs) {
        this.gameLogs = gameLogs;
    }

    public void addGameLog(GameLog gameLog){
        this.gameLogs.add(gameLog);
    }

/*
    @Bean
    CommandLineRunner initUser(UserRepository userRepository) {
        return args -> {
            User user1 = new User("John", "john@somemail.com");
            User user2 = new User("Jane", "jane@somemail.com");
            User user3 = new User("Justin", "justin@somemail.com");
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);


        };
    }

*/
}
