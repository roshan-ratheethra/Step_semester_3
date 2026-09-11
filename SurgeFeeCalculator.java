public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException("Minimum surge percent cannot be negative");
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Values cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        int t1 = Math.min(delayMinutes, 5);
        int t2 = Math.min(Math.max(0, delayMinutes - 5), 10);
        int t3 = Math.max(0, delayMinutes - 15);

        double tieredFee = (t1 * 0.005 + t2 * 0.01 + t3 * 0.02) * orderValue;
        double minFloor = (minimumSurgePercent / 100.0) * orderValue;

        return Math.max(tieredFee, minFloor);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1.0);

        System.out.println("Rs " + calc.calculateSurgeFee(500, 0));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 1));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 16));
    }
}
