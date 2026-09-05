import java.util.Scanner;

public class InventoryBalancer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of item categories: ");
        int n = scanner.nextInt();
        
        int[] sectionA = new int[n];
        System.out.println("Enter " + n + " quantities for Section A:");
        for (int i = 0; i < n; i++) sectionA[i] = scanner.nextInt();
        
        int[] sectionB = new int[n];
        System.out.println("Enter " + n + " quantities for Section B:");
        for (int i = 0; i < n; i++) sectionB[i] = scanner.nextInt();
        
        analyzeInventory(sectionA, sectionB);
        scanner.close();
    }

    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int sumA = 0;
        int sumB = 0;
        
        int maxQuantity = -1;
        String maxSection = "";
        int maxItemNumber = -1;
        
        // Scan Section A
        for (int i = 0; i < sectionA.length; i++) {
            sumA += sectionA[i];
            if (sectionA[i] > maxQuantity) {
                maxQuantity = sectionA[i];
                maxSection = "Section A";
                maxItemNumber = i + 1; // 1-based indexing for the output
            }
        }
        
        // Scan Section B
        for (int i = 0; i < sectionB.length; i++) {
            sumB += sectionB[i];
            if (sectionB[i] > maxQuantity) {
                maxQuantity = sectionB[i];
                maxSection = "Section B";
                maxItemNumber = i + 1;
            }
        }
        
        String status = (sumA == sumB) ? "Balanced" : "Not Balanced";
        
        System.out.println("Section A Total: " + sumA + " | Section B Total: " + sumB + 
                           " | Status: " + status + " | Highest Quantity: " + maxQuantity + 
                           " (" + maxSection + ", Item " + maxItemNumber + ")");
    }
}