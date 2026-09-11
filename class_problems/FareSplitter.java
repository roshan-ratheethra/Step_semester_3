public class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (tripId == null || tripId.trim().isEmpty()) {
            throw new IllegalArgumentException("Trip ID cannot be empty");
        }
        if (totalFare < 0) {
            throw new IllegalArgumentException("Total fare cannot be negative");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive");
        }

        this.tripId = tripId.trim();
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {
        double[] shares = new double[passengerCount];
        if (totalFare == 0.0) {
            return shares;
        }

        long totalPaise = Math.round(totalFare * 100.0);
        long baseSharePaise = totalPaise / passengerCount;
        long remainderPaise = totalPaise % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            long currentPaise = baseSharePaise;
            if (i == passengerCount - 1) {
                currentPaise += remainderPaise;
            }
            shares[i] = currentPaise / 100.0;
        }

        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        double[] b1 = new FareSplitter("TRIP001", 100000, 3).fareBreakdown();
        System.out.print("[");
        for (int i = 0; i < b1.length; i++) {
            System.out.printf("%.2f%s", b1[i], (i < b1.length - 1 ? ", " : ""));
        }
        System.out.println("]");

        double[] b2 = new FareSplitter("TRIP003").fareBreakdown();
        System.out.print("[");
        for (int i = 0; i < b2.length; i++) {
            System.out.printf("%.1f%s", b2[i], (i < b2.length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}
