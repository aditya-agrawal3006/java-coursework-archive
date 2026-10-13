package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ClearCellGame extends Game {

	private Random random;
	private int score;
	
	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		score = 0;
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	public boolean isGameOver() {
		for(int i = 0; i < board[0].length; i++) {
			if(board[board.length - 1][i] != BoardCell.EMPTY) {
				return true;
			}
		}
		
		return false;
	}

	public int getScore() {
		return score;
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() {
		// check if last row is empty and if game is over
		if(isGameOver() == false) {
			// Create a copy of board
			BoardCell[][] copyBoard = new BoardCell [getMaxRows()][getMaxCols()];
			for(int i = 0; i < board.length - 1; i++) {
				copyBoard[i+1] = board[i];
			}
			
			// Create new random first row
			for(int i = 0; i < copyBoard[0].length; i++) {
				copyBoard[0][i] = BoardCell.getNonEmptyRandomBoardCell(random);
			}
			
			board = copyBoard;
		}
	}

	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	public void processCell(int rowIndex, int colIndex) {
		// Check if the game is over
	    int rows = getMaxRows();
	    int cols = getMaxCols();
	    
	    if (isGameOver()) {
	    	return;
	    }
		
	    // Get the target cell and do nothing if it's empty
	    BoardCell target = board[rowIndex][colIndex];
	    if (target == BoardCell.EMPTY) {
	        return;
	    }
	    
	    // Clear target and add to score
	    board[rowIndex][colIndex] = BoardCell.EMPTY;
	    score++;
	    
	    // Covering the eight directions
	    for (int directionRow = -1; directionRow <= 1; directionRow++) {
	        for (int directionCol = -1; directionCol <= 1; directionCol++) {
	        	
	            // Skip the center cell
	            if (directionRow == 0 && directionCol == 0) {
	                continue;
	            }
	            
	            int r = rowIndex + directionRow;
	            int c = colIndex + directionCol;
	            
	            while (r >= 0 && r < rows && c >= 0 && c < cols && 
	            										board[r][c] == target) {
	                board[r][c] = BoardCell.EMPTY;
	                score++;
	                r += directionRow;
	                c += directionCol;
	            }
	        }
	    }

	    // Move the not empty rows up and collapsing the board
	    BoardCell[][] copy = new BoardCell[rows][cols];
	    int newRow = 0;
	    
	    for (int r = 0; r < rows; r++) {
	        boolean isRowEmpty = true;
	        
	        for (int c = 0; c < cols; c++) {
	            if (board[r][c] != BoardCell.EMPTY) {
	                isRowEmpty = false;
	                break;
	            }
	        }
	        
	        if (!isRowEmpty) {
	            for (int c = 0; c < cols; c++) {
	                copy[newRow][c] = board[r][c];
	            }
	            newRow++;
	        }
	    }
	    
	    // Fill the remaining rows with blank cells
	    for (int r = newRow; r < rows; r++) {
	        for (int c = 0; c < cols; c++) {
	            copy[r][c] = BoardCell.EMPTY;
	        }
	    }
	    
	    board = copy;
	}
}