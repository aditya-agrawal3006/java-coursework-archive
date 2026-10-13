package system;

public class TwoDimArrayUtil {

    public TwoDimArrayUtil() {
        // Default constructor
    }

    
    /*
     Returns true if the array is a ragged array and false otherwise.
    */
    public static boolean isRagged(char[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array parameter is null");
        }
        if (array.length == 0) {
            return false; // An empty array is not considered ragged
        }

        int firstRowLength = array[0].length;
        for (int i = 1; i < array.length; i++) {
            if (array[i].length != firstRowLength) {
                return true; // Found a row with different length
            }
        }
        return false; // All rows have the same length
    }

    
    
    /*
     Rotates the array by shifting rows one position up (second row becomes the 
     first row, etc.)
    */
    public static void rotateTopOneRow(char[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array parameter is null");
        }
        if (isRagged(array)) {
            throw new IllegalArgumentException("Array is ragged");
        }
        if (array.length <= 1) {
            return; // No rotation needed for arrays with one or zero rows
        }

        char[] firstRow = array[0];
        // Shift rows up
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = firstRow; // Move first row to last position
    }

    
    
    /*
     Rotates the array by shifting columns one position to the left (second 
     column becomes first).
     The first column becomes the last column.
     No processing takes place if the array has one column.
     */
    public static void rotateLeftOneColumn(char[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array parameter is null");
        }
        if (isRagged(array)) {
            throw new IllegalArgumentException("Array is ragged");
        }
        if (array[0].length <= 1) {
            return; // No rotation needed for arrays with one or zero columns
        }

        int numRows = array.length;
        int numCols = array[0].length;

        // Shift columns left
        for (int i = 0; i < numRows; i++) {
            char firstElement = array[i][0];
            for (int j = 0; j < numCols - 1; j++) {
                array[i][j] = array[i][j + 1];
            }
            // Move first column to last position
            array[i][numCols - 1] = firstElement;
        }
    }

    
    
    /*
     Returns a new two-dimensional array where the 'top' array appears above the
      'bottom' array.
    */
    public static char[][] appendTopBottom(char[][] top, char[][] bottom) {
        if (top == null || bottom == null) {
            throw new IllegalArgumentException("Array parameter is null");
        }

        int totalRows = top.length + bottom.length;

        // Create the new array
        char[][] result = new char[totalRows][];

        // Copy rows from the top array
        for (int i = 0; i < top.length; i++) {
            result[i] = top[i];
        }

        // Copy rows from the bottom array
        for (int i = 0; i < bottom.length; i++) {
            result[i + top.length] = bottom[i];
        }

        return result;
    }

    /*
     Returns a new two-dimensional array where corresponding rows from 'left' 
     and 'right' arrays are combined side by side.
     */
    public static char[][] appendLeftRight(char[][] left, char[][] right) {
    	
    	// Determine the number of rows in the new array (use the longer array)
        int maxRows = Math.max(left.length, right.length);

        // Create the new array
        char[][] result = new char[maxRows][];

        // Combine rows
        for (int i = 0; i < maxRows; i++) {
            // Get rows from left and right (or empty row if out of bounds)
            char[] leftRow = i < left.length ? left[i] : new char[0];
            char[] rightRow = i < right.length ? right[i] : new char[0];

            // Create combined row
            char[] comRow = new char[leftRow.length + rightRow.length];

            // Copy elements from the left row to the combined row
            for (int j = 0; j < leftRow.length; j++) {
                comRow[j] = leftRow[j];
            }

            // Copy elements from the right row to the combined row
            for (int j = 0; j < rightRow.length; j++) {
                comRow[leftRow.length + j] = rightRow[j];
            }

            // Add combined row to the result
            result[i] = comRow;
        }

        return result;
        
    }
    
}
