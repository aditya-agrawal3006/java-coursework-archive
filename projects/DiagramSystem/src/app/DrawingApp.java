package app;

import java.util.Random;

public class DrawingApp {
	/*
	 * For every method remove the line with "throw ..." and implement the method.
	 * We are using "throw..." so your code does not display any compilation errors
	 * when you import the zip file. Also, if you don't implement a method you will
	 * see a white square (instead of green) in the submit server
	 */
	public static String getRectangle(int maxRows, int maxCols, char symbol) {
		// Ensuring that there is at least 1 row and column
		if(maxRows < 1 || maxCols < 1) {
			return null;
		}
		
		StringBuilder rect = new StringBuilder();
		
		// Appended rect object using a nested for loop
		for(int row = 0; row < maxRows; row++) {
			for(int col = 0; col < maxCols; col++) {
				rect.append(symbol);
			}
			
			// Adding new row for every output besides last one
			if (row < maxRows - 1) {
	            rect.append("\n");
	        }
		}
		
		return rect.toString();
	}

	
	
	
	public static String getFlag(int size, char color1, char color2, char color3) {
		// Ensuring that there is at least 1 row and column
		if(size < 3) {
			return null;
		}
		
		StringBuilder flag = new StringBuilder();
		
		// First half of flag
		for(int row = 1; row <= size; row++) {
			
			for(int col = 1; col <= size * 5; col++) {
				
				if (col <= row && col <= size) {
					flag.append(color1);
				} else if(row == 1 || row == size) {
					flag.append(color2);
				} else {
					flag.append(color3);
				}
				
				
			}
			
			// Adding new row for every output besides last one
			if (row <= size) {
	            flag.append("\n");
	        }
		}
		
		// Second half of the flag
		for(int row = size; row >= 1; row--) {
			
			for(int col = 1; col <= size * 5; col++) {
				
				if (col <= row && col <= size) {
					flag.append(color1);
				} else if(row == 1 || row == size) {
					flag.append(color2);
				} else {
					flag.append(color3);
				}
				
				
			}
			
			// Adding new row for every output besides last one
			if (row > 1) {
	            flag.append("\n");
	        }
		}		
		
		return flag.toString();		
	}

	
	
	public static String getHorizontalBars(int maxRows, int maxCols, int bars, char color1, char color2, char color3) {
		
		int size = maxRows / bars;
		
		// Ensuring that there is at least 1 row and column and size above 1
		if(maxRows < 1 || maxCols < 1 || size < 1) {
			return null;
		}
		
		StringBuilder rect = new StringBuilder();
		
		// Cycle through the different color bars
		for(int i = 0; i < bars; i++) {
			
			char currentColor;
			
			//changing the current color of the bar
			if(i % 3 == 0) {
				currentColor = color1;
			} else if(i % 3 == 1) {
				currentColor = color2;
			} else {
				currentColor = color3;
			}
			
			
			// Creating the bars and the rect
			for(int row = 0; row < size; row++) {
				for(int col = 0; col < maxCols; col++) {
					rect.append(currentColor);
				}
				
				// Adding new row for every output besides last one
				if (!(i == bars - 1 && row == size - 1)) {
		            rect.append("\n");
		        }
			}
		}
		
		
		return rect.toString();
		
	}
	
	
	

	public static String getVerticalBars(int maxRows, int maxCols, int bars, char color1, char color2, char color3) {
		// Ensure the validity of inputs
	    if (maxRows < 1 || maxCols < 1 || bars < 1) {
	        return null;
	    }

	    // Calculate the width of each bar
	    int width = maxCols / bars;
	    if (width < 1) {
	        return null;
	    }

	    StringBuilder rect = new StringBuilder();

	    // Cycle through rows
	    for (int row = 0; row < maxRows; row++) {
	    	
	        // Cycle through bars and colors
	        for (int bar = 0; bar < bars; bar++) {
	        	
	            char currentColor;
	            if (bar % 3 == 0) {
	                currentColor = color1;
	            } else if (bar % 3 == 1) {
	                currentColor = color2;
	            } else {
	                currentColor = color3;
	            }

	            // Fill the width of each bar
	            for (int col = 0; col < width; col++) {
	                rect.append(currentColor);
	            }
	        }

	        // Add new line to every row besides last
	        if (row < maxRows - 1) {
	            rect.append("\n");
	        }
	    }

	    return rect.toString();
	}

	
	
	
	
	public static char getRandomColor(Random random) {
		
		// Generate Random int between 0 and 5
		int num = random.nextInt(6);
		
		// Return the corresponding Char to the random int value
		if (num == 0) {
			return 'R';
		} else if (num == 1) {
			return 'G';
		} else if (num == 2) {
			return 'B';
		} else if (num == 3) {
			return 'Y';
		} else if (num == 4) {
			return '*';
		} else if (num == 5) {
			return '.';
		} else {
			return 'X'; // Should never happen
		}
	}

	
	
	
	private static boolean isValidColor(char color) {
		// Return true if any of the following is an input
		return color == 'R' || color == 'G' || color == 'B' || color == 'Y' || 
			color == '*' || color == '.';
		
	}
}