import java.util.Scanner;

public class InventoryParser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter CSV line: ");
        String csvLine = scanner.nextLine();
        
        parseInventoryRecord(csvLine);
        scanner.close();
    }

    public static void parseInventoryRecord(String csvLine) {
        // Clean up quotes if they are accidentally pasted in
        csvLine = csvLine.replace("\"", "");
        
        String[] fields = csvLine.split(",");
        
        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            String product = fields[0].trim();
            String sku = fields[1].trim();
            String qty = fields[2].trim();
            
            System.out.println("Product: " + product + " | SKU: " + sku + " | Qty: " + qty);
        }
    }
}