package advancedchess.Leaderboard;

import advancedchess.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class LeaderBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int wins;
    private int losses;

    private int userId;


/**
    @OneToOne
    @JsonIgnore
    private User user;
**/

    public LeaderBoard(){
        wins = 0;
        losses = 0;
        userId = 0;

    }


    public int getId(){
        return id;
    }

    public int getWins(){
        return wins;
    }

    public int getLosses(){
        return losses;
    }

    public void setId(int newId){
        id = newId;
    }

    public void setWins(int newWins){
        wins = newWins;
    }

    public void setLosses(int newLosses){
        losses = newLosses;
    }

    public int getU_id(){
        return userId;
    }

    public void setU_id(int newId){
        userId = newId;
    }

}
