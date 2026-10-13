package sysImplementation;

public class Utilities {
	
	public static int[] filter(int[] array, int lowerLimit, int upperLimit) {
		
		// Exceptions
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (lowerLimit > upperLimit) {
            throw new IllegalArgumentException("Lower limit cannot be greater"
            		                          + " than upper limit");
        }

        // First, determine the size of the result array
        int count = 0;
        for (int x : array) {
            if (x >= lowerLimit && x <= upperLimit) {
                count++;
            }
        }

        // Create the result array
        int[] result = new int[count];
        int index = 0;
        for (int x : array) {
            if (x >= lowerLimit && x <= upperLimit) {
                result[index++] = x;
            }
        }
        return result;
    }
	
	
	public static String getArrayString(int[] array, char separator) {
		
		// Exception
        if (array == null) {
            throw new IllegalArgumentException("Array can't be null");
        }
        if (array.length == 0) {
        	// Return an empty string if array has no elements
            return "";
        }

        StringBuilder arr = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            arr.append(array[i]);
            if (i < array.length - 1) {
            	// Add separator after each element except the last one
                arr.append(separator); 
            }
        }
        
        return arr.toString();
    }
	
	
	public static StringBuffer[] getArrayStringsLongerThan(StringBuffer[] array,
																int length) {
		// Exception
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        int count = 0;
        for (StringBuffer arr : array) {
            if (arr.length() > length) {
                count++;
            }
        }

        // Create result array with all the StringBuffers that meet criteria
        StringBuffer[] result = new StringBuffer[count];
        int index = 0;
        for (StringBuffer arr : array) {
            if (arr.length() > length) {
                result[index++] = new StringBuffer(arr.toString()); // Create a copy
            }
        }
        
        return result;
    }
	
	
	public static int getInstances(int[] array, int lowerLimit, int upperLimit) {
        // Exception
		if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        int count = 0;
        for (int x : array) {
            if (x >= lowerLimit && x <= upperLimit) {
                count++;
            }
        }
        return count;
    }
	
	
	public static void rotate(int[] array, boolean leftRotation, int positions) {
	    if (array == null) {
	        throw new IllegalArgumentException("Array cannot be null");
	    }
	    if (array.length < 2 || positions % array.length == 0) {
	        // No rotation needed for arrays with less than 2 elements or 
	    	// positions multiple of array length
	    	return;
	    }

	    positions = positions % array.length; // Optimize the number of rotation

	    if (leftRotation) {
	        // Left rotation
	        for (int i = 0; i < positions; i++) {
	            int first = array[0];
	            for (int j = 0; j < array.length - 1; j++) {
	                array[j] = array[j + 1];
	            }
	            array[array.length - 1] = first;
	        }
	    } else {
	        // Right rotation
	        for (int i = 0; i < positions; i++) {
	            int last = array[array.length - 1];
	            for (int j = array.length - 1; j > 0; j--) {
	                array[j] = array[j - 1];
	            }
	            array[0] = last;
	        }
	    }
	}
	
	
}