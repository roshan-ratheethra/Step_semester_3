import java.util.Scanner;

public class TypingChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter original passage: ");
        String original = scanner.nextLine();
        
        System.out.print("Enter typed passage: ");
        String typed = scanner.nextLine();
        
        checkTypingAccuracy(original, typed);
        scanner.close();
    }

    public static void checkTypingAccuracy(String original, String typed) {
        int matchCount = 0;
        int firstMismatchIndex = -1;
        
        int length = original.length();
        
        for (int i = 0; i < length; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matchCount++;
            } else if (firstMismatchIndex == -1) {
                // Record the very first mismatch position
                firstMismatchIndex = i;
            }
        }
        
        double accuracy = ((double) matchCount / length) * 100;
        
        if (firstMismatchIndex == -1) {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | No Mismatches\n", 
                              matchCount, length, accuracy);
        } else {
            // Adding 1 to index to match the 1-based position in the sample output
            int position = firstMismatchIndex + 1;
            char expected = original.charAt(firstMismatchIndex);
            char actual = typed.charAt(firstMismatchIndex);
            
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d ('%c' vs '%c')\n", 
                              matchCount, length, accuracy, position, expected, actual);
        }
    }
}