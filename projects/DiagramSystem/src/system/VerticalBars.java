package system;

import app.DrawingApp;
import gui.BoardCell;

public class VerticalBars implements Diagram{

    // Instance Variables
    private char[][] board;
    private int animationType;
    
    // Constructor
    public VerticalBars(int maxRows, int maxCols, int bars, char color1, 
    		            char color2, char color3, int animationType) {
        // Initialize animationType
        this.animationType = animationType;

        // Generate the diagram string using DrawingApp
        String diagramString = DrawingApp.getVerticalBars(maxRows, maxCols, 
        		                                  bars, color1, color2, color3);

        // Convert the string into a 2D character array
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
            // Rotate the columns to the left using TwoDimArrayUtil
            TwoDimArrayUtil.rotateLeftOneColumn(board);
        }
        return board;
    }
	
    // getNumberRows Method
    @Override
    public int getNumberRows() {
        return board.length;
    }

    // getNumberCols Method
    @Override
    public int getNumberCols() {
        return board[0].length;
    }

    
}
