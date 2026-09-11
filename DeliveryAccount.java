public class DeliveryAccount {
    private static double baseSurgeFloor;

    static {
        baseSurgeFloor = 1.0;
    }

    private String studentId;
    private double orderValue;

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = (orderValue < 0) ? 0.0 : orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public String getStudentId() {
        return studentId;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(double orderValue) {
        this.orderValue = (orderValue < 0) ? 0.0 : orderValue;
    }

    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes <= 0 || orderValue <= 0) {
            return 0.0;
        }

        int t1 = Math.min(delayMinutes, 5);
        int t2 = Math.min(Math.max(0, delayMinutes - 5), 10);
        int t3 = Math.max(0, delayMinutes - 15);

        double tiered = (t1 * 0.005 + t2 * 0.01 + t3 * 0.02) * orderValue;
        double floor = (baseSurgeFloor / 100.0) * orderValue;

        return Math.max(tiered, floor);
    }

    public static class Premium extends DeliveryAccount {
        public Premium(String studentId, double orderValue) {
            super(studentId, orderValue);
        }

        public Premium(String studentId) {
            super(studentId);
        }
    }

    public static double processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            return 0.0;
        }

        account.setOrderValue(amount);
        double fee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof Premium) {
            fee = fee * 0.5;
        }

        return fee;
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null) {
            return;
        }

        if (accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException("Array lengths do not match");
        }

        int processed = 0;
        int skipped = 0;
        int premium = 0;
        int regular = 0;
        double totalFees = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            DeliveryAccount acc = accounts[i];
            if (acc == null) {
                skipped++;
                continue;
            }

            double fee = processAccount(acc, amounts[i], delayMinutesArray[i]);
            totalFees += fee;
            processed++;

            if (acc instanceof Premium) {
                premium++;
            } else {
                regular++;
            }
        }

        System.out.printf("%d processed | %d null skipped | %d premium | %d regular | grand total surge fees = Rs %.2f%n",
                processed, skipped, premium, regular, totalFees);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        DeliveryAccount.processBatch(accounts, amounts, delayMinutesArray);
    }
}
