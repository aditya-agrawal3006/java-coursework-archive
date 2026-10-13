package programs;
import java.util.Scanner;
import java.util.Random;
public class ThrowDie {
	
	
	public static void main(String[] args) {
        // Create a Scanner object
        Scanner sc = new Scanner(System.in);

        // Ask user for the number of times to throw the die
        System.out.print("How many times to throw a die?: ");
        int n = sc.nextInt();

        // Ask user for the seed value
        System.out.print("Enter seed: ");
        int seed = sc.nextInt();

        // Create a Random object with the provided seed
        Random random = new Random(seed);

        // Use for loop to simulate n throws of the die
        for (int i = 1; i <= n; i++) {
        	
            System.out.println("Throw #" + i);

            // Generate the die face (1 to 6)
            int dieFace = random.nextInt(6) + 1;

            // Display the die face by calling on printDieFace method
            printDieFace(dieFace);
        }
    }

    // Method to print the die face pattern
    public static void printDieFace(int face) {
        switch (face) {
            case 1:
                System.out.println("...");
                System.out.println(".0.");
                System.out.println("...");
                break;
            case 2:
                System.out.println("0..");
                System.out.println("...");
                System.out.println("..0");
                break;
            case 3:
                System.out.println("0..");
                System.out.println(".0.");
                System.out.println("..0");
                break;
            case 4:
                System.out.println("0.0");
                System.out.println("...");
                System.out.println("0.0");
                break;
            case 5:
                System.out.println("0.0");
                System.out.println(".0.");
                System.out.println("0.0");
                break;
            case 6:
                System.out.println("0.0");
                System.out.println("0.0");
                System.out.println("0.0");
                break;
            default:
                break;
        }
    }
}
