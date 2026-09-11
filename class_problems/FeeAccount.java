public class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = Math.max(0, totalFee);
        this.amountPaid = Math.max(0, amountPaid);
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    public double getDue() {
        return Math.max(0, totalFee - amountPaid);
    }

    public String getRegNo() {
        return regNo;
    }

    public static class HostelFeeAccount extends FeeAccount {
        public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        public void payInTwoInstallments(double amount) {
            if (amount > 0) {
                pay(amount / 2.0);
                pay(amount / 2.0);
            }
        }
    }

    public static class ScholarshipFeeAccount extends FeeAccount {
        private double scholarshipPercent;

        public ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
            super(regNo, totalFee, amountPaid);
            this.scholarshipPercent = Math.max(0, Math.min(100, scholarshipPercent));
        }

        public double effectiveDue() {
            double rawDue = getDue();
            return rawDue * (1.0 - (scholarshipPercent / 100.0));
        }
    }

    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA01", 150000, 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("RA02", 200000, 60000);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("RA03", 180000, 0, 20);

        FeeAccount[] accounts = {plain, hostel, scholarship};

        for (int i = 0; i < accounts.length; i++) {
            FeeAccount acc = accounts[i];
            if (acc instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount s = (ScholarshipFeeAccount) acc;
                System.out.println("Scholarship account effective due: Rs " + s.effectiveDue());
            } else if (acc instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + acc.getDue());
            } else {
                System.out.println("Plain account due: Rs " + acc.getDue());
            }
        }
    }
}
