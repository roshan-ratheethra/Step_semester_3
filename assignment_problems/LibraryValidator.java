import java.util.Scanner;

public class LibraryValidator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter ISBN code: ");
        String rawCode = scanner.nextLine();
        rawCode = rawCode.replace("\"", "");
        
        String normalized = normalizeCode(rawCode);
        String finalResult = validateAndFormat(normalized);
        
        System.out.println(finalResult);
        scanner.close();
    }

    public static String normalizeCode(String raw) {
        String text = raw.trim();
        
        if (text.length() < 3) {
            return text.toUpperCase();
        }
        
        String letters = text.substring(0, 3).toUpperCase();
        String numbers = text.substring(3);
        
        return letters + numbers;
    }

    public static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }
        
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }
        
        StringBuilder formatted = new StringBuilder();
        formatted.append("[");
        formatted.append(code.substring(0, 3));
        formatted.append("] YEAR: ");
        formatted.append(code.substring(3, 7));
        formatted.append(" | CATALOG: ");
        formatted.append(code.substring(7));
                 
        return formatted.toString();
    }
}