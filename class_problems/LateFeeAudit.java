import java.util.Arrays;

public class LateFeeAudit {

    public static class EventTicket {
        private double basePrice;
        private double amountPaid;
        private double[] lateFeeHistory;
        private int historyCount;

        public EventTicket(double basePrice) {
            this.basePrice = basePrice;
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
            return Math.max(0.0, basePrice - amountPaid);
        }

        protected void applyLateFee(double amount) {
            if (amount > 0) {
                this.basePrice += amount;
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

    public static class WorkshopTicket extends EventTicket {
        public WorkshopTicket(double basePrice) {
            super(basePrice);
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);
        w.pay(1200);
        w.applyLateFee(100);
        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;
        System.out.println(Arrays.toString(w.getLateFeeHistory()));
    }
}
