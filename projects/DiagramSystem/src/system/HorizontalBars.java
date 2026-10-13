package system;

import app.DrawingApp;
import gui.BoardCell;

public class HorizontalBars implements Diagram {

    // Instance variables
    private int animationType;
    private char[][] board;

    //Constructor
    public HorizontalBars(int maxRows, int maxCols, int bars, char color1,
    					  char color2, char color3, int animationType) {
        this.animationType = animationType;

        // Get the diagram string from DrawingApp.getHorizontalBars()
        String diagram = DrawingApp.getHorizontalBars(maxRows, maxCols, bars, 
        		                                      color1, color2, color3);

        // Convert the string to a two-dimensional char array using 
        // BoardCell.getCharArray()
        this.board = BoardCell.getCharArray(diagram);
    }

    // returns board
    @Override
    public char[][] getBoard() {
        return board; // Direct reference to the board (no deep copy)
    }

    /* 
     Returns a two-dimensional array of characters representing the next 
     animation step. If the animationType is 1, the board instance variable will
     be updated by rotating the top row. No other animationType value needs to 
     be handled.
     */
    @Override
    public char[][] nextAnimationStep() {
        if (animationType == 1) {
            // Rotate the top row using TwoDimArrayUtil
            TwoDimArrayUtil.rotateTopOneRow(board);
        }

        return board;
    }

    // Returns the number of rows associated with the diagram.
    @Override
    public int getNumberRows() {
        return board.length;
    }

    // Returns the number of columns associated with the diagram.
    @Override
    public int getNumberCols() {
        if (board.length > 0) {
            return board[0].length;
        } else {
            return 0; // Return 0 if the board has no rows
        }
    }
}
