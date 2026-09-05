import java.util.Scanner;

public class SignalAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the traffic signal log (e.g., RRGGGYRR): ");
        String log = scanner.nextLine();
        
        findLongestStreak(log);
        scanner.close();
    }

    public static void findLongestStreak(String signalLog) {
        if (signalLog.length() == 0) return;
        
        char maxChar = signalLog.charAt(0);
        int maxLen = 1;
        
        char currentChar = signalLog.charAt(0);
        int currentLen = 1;
        
        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentChar) {
                currentLen++;
            } else {
                currentChar = signalLog.charAt(i);
                currentLen = 1;
            }
            
            // Check if our current streak beat the record
            if (currentLen > maxLen) {
                maxLen = currentLen;
                maxChar = currentChar;
            }
        }
        
        System.out.println("Longest Streak: '" + maxChar + "' repeated " + maxLen + " times");
    }
}