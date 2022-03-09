package com.example.reglogin.net_utils;

/**
 * The Piece class implements a lot of the basic functionality for the 6 different chess piece types.
 * The main components are the Piece constructor and the valMoves function.
 *
 * @author Ian Swaner
 */
public class Piece {

    /**
     * int coX- the current x coordinate of the piece
     * int coY- the current y coordinate of the piece
     * int player- which player the piece belongs to
     * String type- a string representing the type of piece. B means Bishop for example
     * char typeChar- a character representing the type of piece for comparisons with the board.
     * int dir- mainly used for pawns for now. shows which way they can move.
     */
    public int coX;
    public int coY;
    public int player;
    public String type;
    public char typeChar;
    public int dir;

    /**
     * This is the Piece Constructor.
     * @param x- stores the x coordinate
     * @param y- stores the y coordinate
     * @param t- the character representing the type of piece
     * @param playerNum- which player the piece belongs to
     */
    public Piece(int x, int y, char t, int playerNum){
        coX = x;
        coY = y;
        player = playerNum;
        type = Character.toString(t);
        typeChar = t;
        dir = setDir(player);
    }

    /**
     * setDir takes the player # and determines which way a pawn can move. In the future may be used more in the 4 player version
     * @param player- which player, 1 or 2, the piece belongs to
     * @return returns a positive or negative 1 to show which way the pawn can move
     */
    public int setDir(int player){
        switch(player){
            case 1:
                return -1;
            case 2:
                return 1;
        }
        return 0;
    }

    /**
     * Used to get the x coordinate of the piece
     * @return the x coordinate
     */
    public int getX(){
        return coX;
    }

    /**
     * Used to get the y coordinate of the piece
     * @return the y coordinate
     */
    public int getY(){
        return coY;
    }

    /**
     * Used to get the type of the piece
     * @return the String type
     */
    public String getStr(){
        return type;
    }

    public char getChar(){
        return typeChar;
    }

    /**
     * Used to get which player the piece belongs to
     * @return the int player
     */
    public int getPlayer(){
        return player;
    }

    /**
     * The valMoves method is used to see if the coordinates of a move are valid.
     * It takes in the coordinates of the chosen move and based on the piece tells if it is valid.
     *
     * @param xn- the x coordinate of the proposed move
     * @param yn- the y coordinate of the proposed move
     * @return returns val, a boolean which is true if the move is valid.
     */
    public boolean valMoves(int  xn,int  yn){
        boolean val = false;
        int diffX = Math.max(getX(),xn)-Math.min(getX(),xn);
        int diffY = Math.max(getY(),yn)-Math.min(getY(),yn);
        //must also check if piece is occupied by a friendly
        //Something like if(board[xn][yn].getPiece.getCase != p.getCase
        if((xn < 0 || xn > 7) || (yn < 0 || yn > 7))
            return false;
        else {
            switch (getStr().toUpperCase()) {
                case ("N"):
                    if ((diffX + diffY == 3) && xn <= 7)
                        val = true;
                    break;
                case ("P"):
                    if(xn == (getX() + dir))
                        val = true;
                    break;
                case ("B"):
                    if(diffX == diffY)
                        val = true;
                    break;
                case ("Q"):
                    if((diffX == diffY) || (diffX == 0 && diffY != 0) || (diffX != 0 && diffY == 0))
                        val = true;
                    break;
                case ("K"):
                    if(diffX + diffY == 1 || (diffX + diffY == 2 && diffX != 2 && diffY != 2))
                        val = true;
                    break;
                case ("R"):
                    if((diffX == 0 && diffY != 0) || (diffX != 0 && diffY == 0))
                        val = true;
                    break;
            }
        }
        return val;
    }
}
