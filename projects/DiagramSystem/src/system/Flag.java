package system;

import app.DrawingApp;
import gui.BoardCell;

public class Flag implements Diagram{
	// Instance Variables
    private char[][] board;
    private int animationType;
    
    // Constructor
    public Flag(int size, char color1, char color2, char color3, int animationType) {
        // Initialize animationType
        this.animationType = animationType;

        // Generate the flag diagram string using DrawingApp
        String diagramString = DrawingApp.getFlag(size, color1, color2, color3);

        // Convert the string into a 2D char array
        this.board = BoardCell.getCharArray(diagramString);
    }
    
    // getBoard Method
    @Override
    public char[][] getBoard() {
        return board;
    }

    // nextAnimationStep Method
    @Override
    public char[][] nextAnimationStep() {
        if (animationType == 1) {
            // Rotate the columns to the left
            TwoDimArrayUtil.rotateLeftOneColumn(board);
        } else if (animationType == 2) {
            // Rotate the rows upwards
            TwoDimArrayUtil.rotateTopOneRow(board);
        }
        return board;
    }

    // getNumberRows Method
    @Override
    public int getNumberRows() {
        return board.length; // Number of rows in the board
    }

    // getNumberCols Method
    @Override
    public int getNumberCols() {
        return board[0].length; // Number of columns in the first row
    }
}
