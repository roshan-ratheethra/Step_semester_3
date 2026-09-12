public class RaceSettlementEngine {

    public static class RaceEntry {
        private static int counter = 0;

        private final String entryCode;
        private String bibNumber;
        private double entryFee;
        private double amountPaid;

        public RaceEntry(String bibNumber, double entryFee) {
            counter++;
            this.entryCode = "ENTRY-" + counter;
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0.0;
        }

        public String getEntryCode() {
            return entryCode;
        }

        public String getBibNumber() {
            return bibNumber;
        }

        public double getEntryFee() {
            return entryFee;
        }

        public double getBalanceDue() {
            return Math.max(0.0, entryFee - amountPaid);
        }

        public void pay(double amount) {
            if (amount > 0) {
                this.amountPaid += amount;
            }
        }

        public void pay(double amount, String mode) {
            System.out.println("Paying via " + mode);
            pay(amount);
        }

        public static boolean isValidDiscountCode(String code) {
            if (code == null || code.length() != 5) {
                return false;
            }
            if (code.charAt(0) != 'M') {
                return false;
            }
            for (int i = 1; i <= 3; i++) {
                if (!Character.isDigit(code.charAt(i))) {
                    return false;
                }
            }
            if (!Character.isUpperCase(code.charAt(4))) {
                return false;
            }
            return true;
        }

        public static int getBibCounter() {
            return counter;
        }
    }

    public static class RelayTeamEntry extends RaceEntry {
        private int teamSize;

        public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
            super(bibNumber, entryFee);
            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }
    }

    public static class EliteRunnerEntry extends RaceEntry {
        public EliteRunnerEntry(String bibNumber, double entryFee) {
            super(bibNumber, entryFee);
        }
    }

    public static String settleNight(RaceEntry[] entries) {
        if (entries == null) {
            return "0 processed | 0 null skipped | 0 relay | 0 individual";
        }

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (int i = 0; i < entries.length; i++) {
            RaceEntry e = entries[i];
            if (e == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (e instanceof RelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + relay + " relay | " + individual + " individual";
    }

    public static void main(String[] args) {
        System.out.println(RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(RaceEntry.isValidDiscountCode("X123A"));

        RaceEntry r = new RaceEntry("BIB1001", 100);
        r.pay(10, "UPI");

        EliteRunnerEntry eliteEntry = new EliteRunnerEntry("BIB3001", 150);
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        RaceEntry[] entries = {eliteEntry, null, relayEntry};
        System.out.println(settleNight(entries));

        System.out.println(RaceEntry.getBibCounter());
    }
}
