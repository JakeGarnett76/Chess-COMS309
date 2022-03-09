package com.example.reglogin;

import com.example.reglogin.net_utils.Piece;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

public class TwoPlayerChessboardTest {

    @Mock private Piece testPiece;
    @Mock private TwoPlayerChessboard testBoard;

    private char[][] sampleBoard = {{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
                                    {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                                    {'-', '-', '-', '-', '-', '-', '-', '-'},
                                    {'-', '-', '-', '-', '-', '-', '-', '-'},
                                    {'-', '-', '-', '-', '-', '-', '-', '-'},
                                    {'-', '-', '-', '-', '-', '-', '-', '-'},
                                    {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                                    {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}};

    private String sampleBoardString = "RNBQKBNRPPPPPPPP-------------------------------pppppppprnbqkbnr";

    @Before
    public void setUp() throws Exception {

        testPiece = mock(Piece.class);
        testBoard = mock(TwoPlayerChessboard.class);

    }

    @Test
    public void pathOccupied() {
        //This doesn't work yet so this is not complete
    }

    @Test
    public void detectPiece() {
        int pieceID = -2;


    }

    @Test
    public void testPOST() throws JSONException {

    }

    @Test
    public void testPUT(){

    }

    @Test
    public void testGET() throws JSONException{

        JSONObject response = new JSONObject();

    }

    @Test
    public void movePiece() {
    }

    @Test
    public void detectWinCondition() {
    }

    @Test
    public void onClick() {
    }
}