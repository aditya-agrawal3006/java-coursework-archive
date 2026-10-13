package programs;
import java.util.Scanner;
public class Access {
 
	
	public static void main(String[] args) {
        // Create a Scanner
        Scanner sc = new Scanner(System.in);

        // Correct credentials
        String correctPassword = "terps";
        int correctNum = 1847;


        // Loop and ask the user for 3 attempts
        for (int i = 0; i < 3; i++) {
            // Ask for the password
            System.out.print("Enter password: ");
            String password = sc.next();

            // Check if the user entered "quit"
            if (password.equals("quit")) {
                System.out.println("Access Denied");
                
             // Exit the program when "quit" is entered
                return; 
            }

            // Prompt for the number
            System.out.print("Enter number: ");
            int num = sc.nextInt();

            // Check if both password and number are correct
            if (password.equals(correctPassword) && num == correctNum) {
                System.out.println("Access Granted");
                
                // Exit the program when access is granted
                return;
            } else {
                System.out.println("Wrong credentials");
            }
        }

        // If the user reaches max attempts and fails
        System.out.println("Access Denied");
    }
	
}
