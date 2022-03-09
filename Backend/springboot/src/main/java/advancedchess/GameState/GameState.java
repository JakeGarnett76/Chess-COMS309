package advancedchess.GameState;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameState {

    @Id
    private int boardId;

    private int count;

    private String boardName;

    private String board;

    public GameState(){

    }

    public GameState(String boardName, String board, int boardId){
        this.boardId = boardId;
        this.boardName = boardName;
        this.board = board;
        count = 0;

    }

    public int getBoardId(){
        return boardId;
    }

    public String getBoardName(){
        return boardName;
    }

    public String getBoard(){
        return board;
    }

    public int getCount() {
        return count;
    }

    public void setBoardId(int newPieceId){
        boardId = newPieceId;
    }

    public void setBoardName(String newPieceName){
        boardName = newPieceName;
    }

    public void setBoard(String board){
        this.board = board;
    }


}
