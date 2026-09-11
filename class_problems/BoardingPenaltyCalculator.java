public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException("Minimum penalty percent cannot be negative");
        }
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Values cannot be negative");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        int t1 = Math.min(minutesLate, 5);
        int t2 = Math.min(Math.max(0, minutesLate - 5), 10);
        int t3 = Math.max(0, minutesLate - 15);

        double tiered = (t1 * 0.005 + t2 * 0.01 + t3 * 0.02) * ticketFare;
        double floor = (minimumPenaltyPercent / 100.0) * ticketFare;

        return Math.max(tiered, floor);
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1.0);

        System.out.println("Rs " + calc.calculatePenalty(1000, 0));
        System.out.println("Rs " + calc.calculatePenalty(1000, 1));
        System.out.println("Rs " + calc.calculatePenalty(1000, 16));
    }
}
