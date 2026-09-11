public class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Dish name cannot be empty");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDishName() {
        return dishName;
    }

    public void markDelivered() {
        if (!delivered) {
            delivered = true;
            System.out.println("Order for " + studentName + " (" + dishName + ") marked delivered.");
        } else {
            System.out.println("Warning: Order for " + studentName + " was already delivered!");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        if (rawOrders != null) {
            for (int i = 0; i < rawOrders.length; i++) {
                if (rawOrders[i] == null || rawOrders[i].length < 2) {
                    rejected++;
                    continue;
                }
                try {
                    new FoodOrder(rawOrders[i][0], rawOrders[i]);
                    valid++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        FoodOrder.processBatch(rawOrders);

        FoodOrder order = new FoodOrder("Ravi", "Paneer Butter Masala");
        order.markDelivered();
        order.markDelivered();
        }
}
