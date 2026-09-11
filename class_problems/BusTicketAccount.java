public class BusTicketAccount {
    private static double basePenaltyFloor;

    static {
        basePenaltyFloor = 1.0;
    }

    private String bookingId;
    private double ticketFare;

    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = Math.max(0, ticketFare);
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getTicketFare() {
        return ticketFare;
    }

    public void setTicketFare(double ticketFare) {
        this.ticketFare = Math.max(0, ticketFare);
    }

    public final double calculatePenalty(int minutesLate) {
        if (minutesLate <= 0 || ticketFare <= 0) {
            return 0.0;
        }

        int t1 = Math.min(minutesLate, 5);
        int t2 = Math.min(Math.max(0, minutesLate - 5), 10);
        int t3 = Math.max(0, minutesLate - 15);

        double tiered = (t1 * 0.005 + t2 * 0.01 + t3 * 0.02) * ticketFare;
        double floor = (basePenaltyFloor / 100.0) * ticketFare;

        return Math.max(tiered, floor);
    }

    public static class Sleeper extends BusTicketAccount {
        public Sleeper(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }

        public Sleeper(String bookingId) {
            super(bookingId);
        }
    }

    public static double processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null) {
            return 0.0;
        }
        account.setTicketFare(amount);
        double penalty = account.calculatePenalty(minutesLate);
        if (account instanceof Sleeper) {
            penalty *= 0.50;
        }
        return penalty;
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null) {
            return;
        }

        if (accounts.length != amounts.length || accounts.length != minutesLateArray.length) {
            throw new IllegalArgumentException("Array lengths do not match");
        }

        int processed = 0;
        int skipped = 0;
        int sleeper = 0;
        int regular = 0;
        double totalPenalties = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            BusTicketAccount acc = accounts[i];
            if (acc == null) {
                skipped++;
                continue;
            }

            double p = processAccount(acc, amounts[i], minutesLateArray[i]);
            totalPenalties += p;
            processed++;

            if (acc instanceof Sleeper) {
                sleeper++;
            } else {
                regular++;
            }
        }

        System.out.printf("%d processed | %d null skipped | %d sleeper | %d regular | grand total penalties = Rs %.2f%n",
                processed, skipped, sleeper, regular, totalPenalties);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new Sleeper("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}
