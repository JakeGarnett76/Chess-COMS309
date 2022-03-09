package com.example.reglogin;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.reglogin.app.AppController;
import com.example.reglogin.net_utils.Const;
import com.example.reglogin.net_utils.Piece;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

/**
 * This class initializes and plays out a game of normal chess. It contains all of the pieces,
 * implements the Piece class for piece logic, and manages all movements and requests related to
 * those movements. This version also implements a win condition for the game.
 *
 * @author Theron Gale
 */
public class TwoPlayerChessboard extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = TwoPlayerChessboard.class.getSimpleName();

    private static final int COLS = 8;
    private static final int ROWS = 8;

    private int turn; // 1 = lowercase, 2 = uppercase

    //Utilized for identifying the selected piece before a move
    public Piece selectedPiece;

    //Board for use from FrontEnd to BackEnd
    private char[][] board;

    //Board for use from FrontEnd to the User
    private ImageView[][] boardImages;

    //Used when posting the starting board to collect the room ID
    private String roomID;

    //Utilized for the request queue, if needed
    private final String tag_json_obj = "jobj_req";

    //Boolean used to determine the onClick settings between selecting a piece and moving a piece
    private boolean selected;

    private WebSocketClient wscChess;
    Button sendMsg;
    EditText chessChat;
    TextView chessChatOutput;

    RequestQueue Queue;

    /**
     * Creates an instance of a normal two player game of chess.
     * From here all variables are initialized, either here or in helper methods, along with
     * sending the starting board in a POST request.
     * @param savedInstanceState - The state from which the chessboard will be made
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twoplayerchess);

        roomID = new String();
        turn = 1; //lowercase first (white)
        selected = false;

        chessChat = (EditText) findViewById(R.id.chatBox);
        sendMsg = (Button) findViewById(R.id.btnSendMsg);
        chessChatOutput = (TextView) findViewById(R.id.chatOutput);

        Draft[] drafts = {
                new Draft_6455()
        };

        //String uri = "ws://localhost:8080/websocket/temp";
        String uriF = "ws://coms-309-022.cs.iastate.edu:8080/websocket/tempuser";

        try{
            Log.d("Socket:", "Trying socket");
            wscChess = new WebSocketClient(new URI(uriF), (Draft) drafts[0]) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onMessage(String msg) {
                    Log.d("", "run() returned: " + msg);
                    String s = chessChatOutput.getText().toString() + "\n";
                    chessChatOutput.setText(s + " Server:" + msg);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.d("CLOSE", "onClose() returned: " + s);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("Exception: ", e.toString());
                }
            };

            wscChess.connect();

        }catch (URISyntaxException e){
            Log.d("Exception: ", e.getMessage().toString());
            e.printStackTrace();
        }



        sendMsg.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    String chat = chessChat.getText().toString();
                    wscChess.send(chat);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        board = new char[ROWS][COLS];

        setupBoard();

        boardImages = new ImageView[ROWS][COLS];

        initializeTiles();

        Queue = Volley.newRequestQueue(this);

        sendStartingBoard();

    }

    /**
     * This method is a helper to declutter the onCreate method, as it puts all pieces on the
     * character array board.
     */
    private void setupBoard(){
        //pawns
        for(int i = 0; i < COLS; i++){
            board[1][i] = 'P';
            board[6][i] = 'p';
        }

        //Remainder of black pieces

        //Rooks
        board[0][0] = 'R';
        board[0][7] = 'R';

        //Knights
        board[0][1] = 'N';
        board[0][6] = 'N';

        //Bishops
        board[0][2] = 'B';
        board[0][5] = 'B';

        //Queen
        board[0][3] = 'Q';

        //King
        board[0][4] = 'K';


        //Remainder of white pieces

        //Rooks
        board[7][0] = 'r';
        board[7][7] = 'r';

        //Knights
        board[7][1] = 'n';
        board[7][6] = 'n';

        //Bishops
        board[7][2] = 'b';
        board[7][5] = 'b';

        //Queen
        board[7][3] = 'q';

        //King
        board[7][4] = 'k';

        //Rest of Board
        for (int i = 2; i < 6; i++){
            for (int j = 0; j < COLS; j++){
                board[i][j] = '-';
            }
        }
    }

    /**
     * This method initializes all imageviews used in this game
     */
    private void initializeTiles(){
        boardImages[0][0] = findViewById(R.id.A1);
        boardImages[0][1] = findViewById(R.id.A2);
        boardImages[0][2] = findViewById(R.id.A3);
        boardImages[0][3] = findViewById(R.id.A4);
        boardImages[0][4] = findViewById(R.id.A5);
        boardImages[0][5] = findViewById(R.id.A6);
        boardImages[0][6] = findViewById(R.id.A7);
        boardImages[0][7] = findViewById(R.id.A8);

        boardImages[1][0] = findViewById(R.id.B1);
        boardImages[1][1] = findViewById(R.id.B2);
        boardImages[1][2] = findViewById(R.id.B3);
        boardImages[1][3] = findViewById(R.id.B4);
        boardImages[1][4] = findViewById(R.id.B5);
        boardImages[1][5] = findViewById(R.id.B6);
        boardImages[1][6] = findViewById(R.id.B7);
        boardImages[1][7] = findViewById(R.id.B8);

        boardImages[2][0] = findViewById(R.id.C1);
        boardImages[2][1] = findViewById(R.id.C2);
        boardImages[2][2] = findViewById(R.id.C3);
        boardImages[2][3] = findViewById(R.id.C4);
        boardImages[2][4] = findViewById(R.id.C5);
        boardImages[2][5] = findViewById(R.id.C6);
        boardImages[2][6] = findViewById(R.id.C7);
        boardImages[2][7] = findViewById(R.id.C8);

        boardImages[3][0] = findViewById(R.id.D1);
        boardImages[3][1] = findViewById(R.id.D2);
        boardImages[3][2] = findViewById(R.id.D3);
        boardImages[3][3] = findViewById(R.id.D4);
        boardImages[3][4] = findViewById(R.id.D5);
        boardImages[3][5] = findViewById(R.id.D6);
        boardImages[3][6] = findViewById(R.id.D7);
        boardImages[3][7] = findViewById(R.id.D8);

        boardImages[4][0] = findViewById(R.id.E1);
        boardImages[4][1] = findViewById(R.id.E2);
        boardImages[4][2] = findViewById(R.id.E3);
        boardImages[4][3] = findViewById(R.id.E4);
        boardImages[4][4] = findViewById(R.id.E5);
        boardImages[4][5] = findViewById(R.id.E6);
        boardImages[4][6] = findViewById(R.id.E7);
        boardImages[4][7] = findViewById(R.id.E8);

        boardImages[5][0] = findViewById(R.id.F1);
        boardImages[5][1] = findViewById(R.id.F2);
        boardImages[5][2] = findViewById(R.id.F3);
        boardImages[5][3] = findViewById(R.id.F4);
        boardImages[5][4] = findViewById(R.id.F5);
        boardImages[5][5] = findViewById(R.id.F6);
        boardImages[5][6] = findViewById(R.id.F7);
        boardImages[5][7] = findViewById(R.id.F8);

        boardImages[6][0] = findViewById(R.id.G1);
        boardImages[6][1] = findViewById(R.id.G2);
        boardImages[6][2] = findViewById(R.id.G3);
        boardImages[6][3] = findViewById(R.id.G4);
        boardImages[6][4] = findViewById(R.id.G5);
        boardImages[6][5] = findViewById(R.id.G6);
        boardImages[6][6] = findViewById(R.id.G7);
        boardImages[6][7] = findViewById(R.id.G8);

        boardImages[7][0] = findViewById(R.id.H1);
        boardImages[7][1] = findViewById(R.id.H2);
        boardImages[7][2] = findViewById(R.id.H3);
        boardImages[7][3] = findViewById(R.id.H4);
        boardImages[7][4] = findViewById(R.id.H5);
        boardImages[7][5] = findViewById(R.id.H6);
        boardImages[7][6] = findViewById(R.id.H7);
        boardImages[7][7] = findViewById(R.id.H8);
    }

    /*private void connectWebsocket(){
        URI uri;
        try{
            //uri = new URI("ws://10.0.2.2:8080/example");
            uri = new URI("wss://echo.websocket.org");
        } catch(URISyntaxException e){
            e.printStackTrace();
            return;
        }

        wscChess = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket","Opened");
            }

            @Override
            public void onMessage(String s) {
                Log.i("Websocket", "Message Received");
                chessChatOutput.append("\n" + s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };

        wscChess.connect();

    }*/

    /**
     * This method is the JSON POST request to send the initial board state.
     */
    private void sendStartingBoard() {

            String stringBoard = Character.toString(board[0][0]);

            for(int i = 0; i < ROWS; i++){
                for(int j = 1; j < COLS; j++){
                    stringBoard += Character.toString(board[i][j]);
                }
            }

            Random rand = new Random();
            String randID = String.valueOf(rand.nextInt(9));

            for(int k = 0; k < 5; k++){
                randID += String.valueOf(rand.nextInt(9));
            }

            System.out.println(randID);

            JSONObject boardData = new JSONObject();
            try{
                boardData.put("board", stringBoard);
                boardData.put("boardId", randID);
            } catch(JSONException e){
                e.printStackTrace();
            }


            final String requestBody = boardData.toString();

            JsonObjectRequest postBoardReq = new JsonObjectRequest(Request.Method.POST,
                    Const.URL_BOARD, boardData,
                    new Response.Listener<JSONObject>() {

                        /**
                         * Register post response from the database showcasing if the post was successful
                         * @param response The response generated from the database.
                         */
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                        }
                    }, new Response.ErrorListener() {

                /**
                 * Register post request error catcher. This responds with an error message if the post request fails.
                 * @param error The error that is thrown
                 */
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    error.printStackTrace();
                }
            });
            // Cancelling request
            // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);

            roomID = randID;

            Queue.add(postBoardReq);
    }

    /**
     * This method is the JSON PUT request to update the board after every move
     */
    private void updateBoard() {

        String url = Const.URL_BOARD + "/" + roomID;

        String stringBoard = Character.toString(board[0][0]);

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(i == 0 && j == 0){
                    continue; //skips the first character to avoid duplicates
                } else if (i == 7 && j == 7){
                    stringBoard += board[i][j];
                    continue;
                } else {
                    stringBoard += board[i][j];
                }
            }
        }

        JSONObject boardData = new JSONObject();

        try{
            boardData.put("board", stringBoard);
        } catch(JSONException e){
            e.printStackTrace();
        }


        final String requestBody = boardData.toString();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT,
                url, boardData,
                new Response.Listener<JSONObject>() {

                    /**
                     * Register post response from the database showcasing if the post was successful
                     * @param response The response generated from the database.
                     */
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {

            /**
             * Register post request error catcher. This responds with an error message if the post request fails.
             * @param error The error that is thrown
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                error.printStackTrace();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);





        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);

    }

    /**
     * This method is the JSON GET request to get the updated board after every move
     */
    private void getBoard() {

        String url = Const.URL_BOARD + "/" + roomID;

        StringRequest getCurrentBoard = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response: " + response);


                        int counter = 0;

                        for(int i = 0; i < ROWS; i++){
                            for(int j = 0; j < COLS; j++){
                                if(i == 7 && j == 7){
                                    board[i][j] = response.charAt(counter);
                                    continue;
                                } else{
                                    board[i][j] = response.charAt(counter);
                                    counter++;
                                }
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                error.printStackTrace();
            }
        });



        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(getCurrentBoard,
                tag_json_obj);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj)



    }

    /**
     * Checks if a path from the selected piece to its destination is blocked by any piece
     * May merge with Piece class down the line
     * Doesn't work with rooks, bishops, nor queens yet
     * @param ox = X coordinate of the selected piece
     * @param oy = Y coordinate of the selected piece
     * @param nx = X coordinate of the selected piece's destination
     * @param ny = Y coordinate of the selected piece's destination
     * @return true if the spot is occupied by a piece, false otherwise
     */
    public boolean pathOccupied(int ox, int oy, int nx, int ny){
        String temp = selectedPiece.getStr();
        temp = temp.toUpperCase();

        int diffRow = Math.max(ox,nx)-Math.min(ox,nx);
        int diffCol = Math.max(oy,ny)-Math.min(oy,ny);

        int xOffset, yOffset;

        //Identify x direction
        if(ox < nx){
            xOffset = 1;
        }else{
            xOffset = -1;
        }

        //Identify y direction
        if(oy < ny){
            yOffset = 1;
        }else{
            yOffset = -1;
        }

        //Current Y parameter for diagonal movement
        int curY = oy + yOffset;

        switch(temp){
            case "P":
                //Checks for a piece obstructing the middle and for an enemy piece directly diagonal from the pawn selected
                if((detectPiece(nx,ny) == 0 && diffCol == 0) || (detectPiece(nx,ny) != 0 && diffRow == diffCol)){
                    return true;
                }
                break;

            case "R":
                //Rook moves vertically
                if(diffRow != 0){
                    for(int cur = 0; cur < diffRow; cur++){
                        //Checks if the current x coordinate matches either the old spot or the new spot
                        //Continues if it is, since both spots are valid regardless of what is there
                        if(Math.min(ox,nx) + cur == ox || Math.min(ox,nx) + cur == nx){
                            continue;
                        }
                        if(detectPiece(Math.min(ox,nx) + cur, oy) != 0){
                            return false;
                        }
                    }
                }

                //Rook moves horizontally
                else{
                    for(int cur = 0; cur < diffCol; cur++){
                        //Checks if the current y coordinate matches either the old spot or the new spot
                        //Continues if it is, since both spots are valid regardless of what is there
                        if(Math.min(oy,ny) + cur == oy || Math.min(oy,ny) + cur == ny){
                            continue;
                        }
                        if(detectPiece(ox, Math.min(oy,ny) + cur) != 0){
                            return false;
                        }
                    }
                }

                return true;

            case "B":

                //Check path; false if there is something in the way, true otherwise
                for(int curX = ox + xOffset; curX != nx; curX += xOffset){

                    if(detectPiece(curX,curY) != 0){
                        return false;
                    }

                    curY += yOffset;
                }

                return true;

            case "Q":
                //Queen moves vertically
                if(diffRow != 0 && diffCol == 0){
                    for(int cur = 0; cur < diffRow; cur++){
                        //Checks if the current x coordinate matches either the old spot or the new spot
                        //Continues if it is, since both spots are valid regardless of what is there
                        if(Math.min(ox,nx) + cur == ox || Math.min(ox,nx) + cur == nx){
                            continue;
                        }
                        if(detectPiece(Math.min(ox,nx) + cur, oy) != 0){
                            return false;
                        }
                    }
                }

                //Queen moves horizontally
                else if (diffCol != 0 && diffRow == 0){
                    for(int cur = 0; cur < diffCol; cur++){
                        //Checks if the current y coordinate matches either the old spot or the new spot
                        //Continues if it is, since both spots are valid regardless of what is there
                        if(Math.min(oy,ny) + cur == oy || Math.min(oy,ny) + cur == ny){
                            continue;
                        }
                        if(detectPiece(ox, Math.min(oy,ny) + cur) != 0){
                            return false;
                        }
                    }
                }

                //Queen moves diagonally

                //Check path; false if there is something in the way, true otherwise
                for(int curX = ox + xOffset; curX != nx; curX += xOffset){

                    if(detectPiece(curX,curY) != 0){
                        return false;
                    }

                    curY += yOffset;
                }

                return true;

            case "N":
            case "K":
                //Validity already checked by valMoves in the Piece class; always true
                return true;
        }

        return false;
    }

    /**
     * This short method finds the piece located at coordinates (x,y). Depending on what the piece
     * is located on the spot on the board, a positive number, a negative number, or 0 is returned.
     * @param x - The x coordinate of the spot being detected.
     * @param y - The y coordinate of the spot being detected.
     * @return
     *      0 when the spot is empty
     *      1 through 6 when the spot is occupied by a lowercase(white) piece
     *     -1 through -6 when the spot is occupied by an uppercase(black) piece
     */
    public int detectPiece(int x, int y){

        switch(board[x][y]){
            case 'p':
                return 1;
            case 'r':
                return 2;
            case 'n':
                return 3;
            case 'b':
                return 4;
            case 'q':
                return 5;
            case 'k':
                return 6;

            case 'P':
                return -1;
            case 'R':
                return -2;
            case 'N':
                return -3;
            case 'B':
                return -4;
            case 'Q':
                return -5;
            case 'K':
                return -6;

            default:
                return 0;
        }
    }

    /**
     * This method computes the actual movement of the pieces, updating both the char array board
     * and the imageview array boardImages to reflect these movements
     * @param ox - The x coordinate of the selectedPiece (Piece to move)
     * @param oy - The y coordinate of the selectedPiece (Piece to move)
     * @param nx - The x coordinate of the space selected (Piece will move to here)
     * @param ny - The y coordinate of the space selected (Piece will move to here)
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void movePiece(int ox, int oy, int nx, int ny){

        board[nx][ny] = board[ox][oy];
        board[ox][oy] = '-';

        boardImages[nx][ny].setForeground(boardImages[ox][oy].getForeground());
        boardImages[ox][oy].setForeground(null);

        String moveMessage = new String();

        switch(selectedPiece.getChar()){
            case('p'):
                moveMessage = "White Pawn";
                break;
            case('P'):
                moveMessage = "Black Pawn";
                break;
            case('r'):
                moveMessage = "White Rook";
                break;
            case('R'):
                moveMessage = "Black Rook";
                break;
            case('n'):
                moveMessage = "White Knight";
                break;
            case('N'):
                moveMessage = "Black Knight";
                break;
            case('b'):
                moveMessage = "White Bishop";
                break;
            case('B'):
                moveMessage = "Black Bishop";
                break;
            case('q'):
                moveMessage = "White Queen";
                break;
            case('Q'):
                moveMessage = "Black Queen";
                break;
            case('k'):
                moveMessage = "White King";
                break;
            case('K'):
                moveMessage = "Black King";
                break;
        }

        int strox = ox+1;
        int stroy = oy+1;
        int strnx = nx+1;
        int strny = ny+1;

        moveMessage = moveMessage + " at X: " + strox + " Y: " + stroy + " moved to X: " + strnx + " Y: " + strny;

        wscChess.onMessage(moveMessage);

    }

    /**
     * The detectWinCondition method is a simple check after each move that checks whether or not
     * a piece was captured. If no kings were captured, a 0 is returned, if the lowercase(white)
     * king was captured, -1 is returned, after which uppercase(black) is declared the winner, and
     * if the uppercase(black) king was captured, 1 is returned, after which lowercase(white) is
     * declared the winner. In any nonzero case, the game will end very shortly after.
     * @return
     *      0 = no kings captured
     *      1 = uppercase king (black) captured; lowercase (white) wins
     *     -1 = lowercase king (white) captured; uppercase (black) wins
     */
    public int detectWinCondition(){
        boolean lowercaseKing = false;
        boolean uppercaseKing = false;

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){

                if(board[i][j] == 'k'){
                    lowercaseKing = true;
                }
                else if(board[i][j] == 'K'){
                    uppercaseKing = true;
                }

                if(lowercaseKing == true && uppercaseKing == true){
                    return 0;
                }
            }
        }

        if(lowercaseKing == false){
            return -1;
        }
        else{
            return 1;
        }
    }

    /**
     * This method will find the space clicked on and depending on the conditions, the onClick method
     * will:
     *      - When no piece is selected - Select a piece if it's the correct turn and there is a
     *      piece at that location
     *      - When a piece is selected - Move the piece if the move is valid, or deselect the piece
     *      if the same piece is selected twice. Upon moving a piece, the board will update the board
     *      through a put request and get the updated board through a get request.
     *
     * @param v - The imageview clicked on to perform several actions based on the content of that imageview
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        for(int i = 0; i < ROWS ; i++){
            for(int j = 0; j < COLS ; j++){
                if(boardImages[i][j].getId() == v.getId()){
                    //System.out.println("Found space at" + i + " and " + j); Test line for identifying onClick spots


                    //getBoard();

                    if(selected == false){

                        //Empty slot was clicked, nothing happens
                        if(board[i][j] == '-'){
                            System.out.println("Empty slot");
                            break;
                        }

                        //An opponent's piece was clicked before the player's piece was clicked. Nothing happens.
                        else if((turn == 1 && board[i][j] < 91) || (turn == 2 && board[i][j] > 96)){
                            System.out.println("Opponent Piece");
                            break;
                        }

                        //The player clicked their own piece
                        else{
                            System.out.println("Your Piece");

                            selectedPiece = new Piece(i,j,board[i][j],turn);

                            selected = true;

                            break;
                        }
                    }

                    else{

                        //Cancels selection if the same piece is selected
                        if(i == selectedPiece.coX && j == selectedPiece.coY && board[i][j] == selectedPiece.getChar()){
                            selected = false;
                            break;
                        }

                        //Checks if where the piece plans on moving to is either empty or an opposing piece
                        else if(selectedPiece.valMoves(i, j) && ((turn == 1 && detectPiece(i,j) <= 0) || (turn == 2 && detectPiece(i,j) >= 0)) ){

                            if(pathOccupied(selectedPiece.coX, selectedPiece.coY, i, j) == false){
                                System.out.println("Invalid move/A piece is in the way!");
                                break;
                            }

                            movePiece(selectedPiece.coX, selectedPiece.coY, i, j);

                            updateBoard();

                            //Delay between the put and get requests needed to avoid errors with the get request
                            final Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getBoard();
                                }
                            }, 500);

                            //Detects if the king on either side has been captured
                            if(detectWinCondition() == 1){
                                System.out.println("Lowercase (White) wins");
                            }
                            else if(detectWinCondition() == -1){
                                System.out.println("Uppercase (Black) wins");
                            }

                            //Switches turn
                            if(turn == 2){
                                turn = 1;
                                System.out.println("White turn");
                            }
                            else{
                                turn = 2;
                                System.out.println("Black turn");
                            }


                            selected = false;
                            break;
                        }

                        else{
                            System.out.println("Invalid move");
                            /*System.out.println(selectedPiece.valMoves(i,j));
                            System.out.println(selectedPiece.coX);
                            System.out.println(selectedPiece.coY);
                            System.out.println(selectedPiece.dir);
                            System.out.println(selectedPiece.player);
                            System.out.println(selectedPiece.typeChar);

                            System.out.println(i);
                            System.out.println(j);

                            System.out.println(Math.max(selectedPiece.coX,i)-Math.min(selectedPiece.coX,i));
                            System.out.println(Math.max(selectedPiece.coY,j)-Math.min(selectedPiece.coY,j));*/

                            break;
                        }
                    }
                }
            }
        }

    }
}