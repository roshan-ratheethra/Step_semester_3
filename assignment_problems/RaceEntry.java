public class RaceEntry {
    private String bibNumber;
    private double entryFee;
    private double amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("bibNumber must be at least 4 characters");
        }
        if (entryFee < 0) {
            throw new IllegalArgumentException("entryFee cannot be negative");
        }
        this.bibNumber = bibNumber.trim();
        this.entryFee = entryFee;
        this.amountPaid = 0.0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    public double getBalanceDue() {
        return Math.max(0.0, entryFee - amountPaid);
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public static class RunnerEntry extends RaceEntry {
        private String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        public String getCategory() {
            return category;
        }
    }

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        if (bibNumbers == null) {
            return "Registered: 0 Rejected: 0";
        }

        int registered = 0;
        int rejected = 0;

        for (int i = 0; i < bibNumbers.length; i++) {
            try {
                new RaceEntry(bibNumbers[i], entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " Rejected: " + rejected;
    }

    public static void main(String[] args) {
        try {
            new RaceEntry("B1", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        System.out.println(r.getBalanceDue());

        String[] batch = {"BIB1", "B1", "BIB2"};
        System.out.println(registerBatch(batch, 80));
    }
}
