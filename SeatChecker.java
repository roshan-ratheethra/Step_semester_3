import java.util.Scanner;

public class SeatChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of seats: ");
        int n = scanner.nextInt();
        
        int[] seats = new int[n];
        System.out.println("Enter the " + n + " seat numbers (press Enter after each):");
        for (int i = 0; i < n; i++) {
            seats[i] = scanner.nextInt();
        }
        
        checkDuplicateSeats(seats);
        scanner.close();
    }

    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean duplicateFound = false;
        
        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    duplicateFound = true;
                    // We can break here to avoid printing the same duplicate twice
                    break; 
                }
            }
        }
        
        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }
}