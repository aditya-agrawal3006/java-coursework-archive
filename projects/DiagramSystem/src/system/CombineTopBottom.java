package system;

public class CombineTopBottom implements Diagram{
	// Instance Variables
    private char[][] board;
    private int animationType;

    // Constructor
    public CombineTopBottom(Diagram top, Diagram bottom, int animationType) {
        // Ensure the number of columns in both diagrams match
        if (top.getNumberCols() != bottom.getNumberCols()) {
            throw new IllegalArgumentException("Top and bottom diagrams must "
            		                      + "have the same number of columns.");
        }

        // Initialize animationType
        this.animationType = animationType;

        // Combine the boards using TwoDimArrayUtil
        this.board = TwoDimArrayUtil.appendTopBottom(top.getBoard(), 
        		                                     bottom.getBoard());
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
        return board.length; // Number of rows in the combined board
    }

    // getNumberCols Method
    @Override
    public int getNumberCols() {
        return board[0].length; // Number of columns in the combined board
    }
}
