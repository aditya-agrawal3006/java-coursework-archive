package sysImplementation;

import java.util.ArrayList;

public class Utilities {

	// Adds a delimeter between each character in str
    // return first char + delimiter + recursion on remainder
	public static String addDelimiter(String str, char delimeter) {
		// str guaranteed not null or empty per spec
        if (str.length() == 1) {
            return str;
        }
        
        char first = str.charAt(0);
        String rest = str.substring(1);
        
        return first + ("" + delimeter) + addDelimiter(rest, delimeter);
	}

	
	
	// Returns a string with the digits (if any) present in the str parameter
	// Extracts digits from str using recursion
	public static String getDigits(String str) {
		// base case
		if (str.isEmpty()) {
            return "";
        }
		
        char c = str.charAt(0);
        String rest = str.substring(1);
        
        if (Character.isDigit(c)) {
            return c + getDigits(rest);
        } else {
            return getDigits(rest);
        }
	}

	
	
	// Replaces the target character in the array with the specified replacement
	// Uses one helper method starting at position zero
	public static void replaceCharacter(char[] array, char target, char replacement) {
		replaceCharacterAux(array, target, replacement, 0);
	}
	
	// Helper replaceCharacter
    private static void replaceCharacterAux(char[] arr, char t, char r, int pos) {
    	// Base case: if pos reaches end, stop
    	if (pos == arr.length) {
            return;
        }
        if (arr[pos] == t) {
            arr[pos] = r; // Replace current char
        }
        replaceCharacterAux(arr, t, r, pos + 1); // Move to next position
    }
    
    
    // Sums even values in array using recursion.
    // Uses one helper method starting at position zero
	public static int getSumEven(int[] array) {
		return getSumEvenAux(array, 0);
	}
	
	// Helper getSumEven
    private static int getSumEvenAux(int[] arr, int pos) {
    	// Base case: if pos equals length, return 0
        if (pos == arr.length) {
            return 0;
        }
        int val = arr[pos];
        if (val % 2 == 0) {
            return val + getSumEvenAux(arr, pos + 1);
        } else {
            return getSumEvenAux(arr, pos + 1);
        }
    }

    
    // This method returns an ArrayList with the indices of rows of the 
    // two-dimensional array having a length that corresponds to rowLength.
	public static ArrayList<Integer> getListRowIndices(int[][] array, int rowLength) {
		ArrayList<Integer> result = new ArrayList<>();
        getListRowIndicesAux(array, rowLength, 0, result);
        return result;
    }
	
	// Helper getListRowIndices
    private static void getListRowIndicesAux(int[][] arr, int length, int pos, ArrayList<Integer> result) {
        if (pos == arr.length) {
            return;
        }
        
        if (arr[pos].length == length) {
            result.add(pos); // Row length matches, record index
        }
        
        getListRowIndicesAux(arr, length, pos + 1, result);
    }
    
    
    
    // This method replaces a character and instances of the character that are 
    // adjacent to it, with a replacement character.
    // Processing: The method determines whether a character at position x,y of 
    // the array corresponds to a target character.
	public static int replaceCells(char[][] array, int x, int y, char target, char replacement) {
        return replaceCellsAux(array, x, y, target, replacement);
	}
	
	// Helper replaceCells
    private static int replaceCellsAux(char[][] arr, int x, int y, char target, char replacement) {
    	if (x < 0 || x >= arr.length) {
            return 0; // Outside vertical range
        }
        if (y < 0 || y >= arr[x].length) {
            return 0; // Outside horizontal range
        }
        if (arr[x][y] != target) {
            return 0; // Not a match
        }
        
        arr[x][y] = replacement; // Replace current
        int count = 1;
        
        count += replaceCellsAux(arr, x - 1, y, target, replacement);
        count += replaceCellsAux(arr, x + 1, y, target, replacement);
        count += replaceCellsAux(arr, x, y - 1, target, replacement);
        count += replaceCellsAux(arr, x, y + 1, target, replacement);
        
        return count;
    }
	
	
	
}
