import java.util.Arrays;

public class LatePenaltyAudit {

    public static class RaceEntry {
        private String bibNumber;
        private double entryFee;
        private double amountPaid;
        private double[] lateFeeHistory;
        private int historyCount;

        public RaceEntry(String bibNumber, double entryFee) {
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0.0;
            this.lateFeeHistory = new double[10];
            this.historyCount = 0;
        }

        public void pay(double amount) {
            if (amount > 0) {
                this.amountPaid += amount;
            }
        }

        public double getBalanceDue() {
            return Math.max(0.0, entryFee - amountPaid);
        }

        protected void applyLateFee(double amount) {
            if (amount > 0) {
                this.entryFee += amount;
                if (historyCount < lateFeeHistory.length) {
                    lateFeeHistory[historyCount++] = amount;
                }
            }
        }

        public double[] getLateFeeHistory() {
            double[] copy = new double[historyCount];
            for (int i = 0; i < historyCount; i++) {
                copy[i] = lateFeeHistory[i];
            }
            return copy;
        }
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

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;
        System.out.println(Arrays.toString(r.getLateFeeHistory()));
    }
}
