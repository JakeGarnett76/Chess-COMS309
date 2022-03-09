package advancedchess.GameLogs;

import advancedchess.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class GameLog {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String date;

    private String results;

    private int userId;

    public GameLog(){

    }
    public GameLog(String date, String results, int userId){
        this.date = date;
        this.results = results;
        this.userId = userId;
    }

    public int getId(){
        return id;
    }

    public String getDate(){
        return date;
    }

    public String getResults(){
        return results;
    }

    public void setId(int newId){
        id = newId;
    }

    public void setDate(String newDate){
        date = newDate;
    }

    public void setResults(String newResults){
        results = newResults;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }
}
