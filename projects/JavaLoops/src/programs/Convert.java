package programs;
import java.util.Scanner;

public class Convert {
	public static void main(String[] args) {
        // Create a Scanner object
        Scanner sc = new Scanner(System.in);
        
        // Ask the user for a decimal number
        System.out.print("Enter decimal number: ");
        int num = sc.nextInt();
        
        // Include the case for 0
        if (num == 0) {
            System.out.println("Octal value: 0");
            return;
        }

        // Variable to store the octal conversion
        String octal = "";
        
        // Using a for loop to convert decimal to octal
        for (int i = num; i > 0; i /= 8) {
            int remainder = i % 8;
            
            
            // Insert remainder at the beginning of the result
            octal = remainder + octal; 
            
        }
        
        // Display the octal value
        System.out.println("Octal value: " + octal);
    }
}
