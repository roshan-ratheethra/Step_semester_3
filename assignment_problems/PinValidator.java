import java.util.Scanner;

public class PinValidator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        
        checkPinLength(pin);
        scanner.close();
    }

    public static void checkPinLength(String pin) {
        // Removes quotes just in case you accidentally copy/paste them
        pin = pin.replace("\"", ""); 
        
        if (pin.length() == 4) {
            System.out.println("PIN length OK.");
        } else {
            System.out.println("Invalid PIN must be exactly 4 digits.");
        }
    }
}