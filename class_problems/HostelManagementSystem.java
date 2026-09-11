public class HostelManagementSystem {

    static class FeeAccount {
        private double totalFee;
        private double amountPaid;

        public FeeAccount(double totalFee, double amountPaid) {
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
    }

    static class HostelFeeAccount extends FeeAccount {
        public HostelFeeAccount(double totalFee, double amountPaid) {
            super(totalFee, amountPaid);
        }
    }

    static class HostelRoom {
        private String roomNo;

        public HostelRoom(String roomNo) {
            this.roomNo = roomNo;
        }

        public String getRoomNo() {
            return roomNo;
        }
    }

    static class SrmStudent {
        public static int totalStudents = 0;

        private String name;
        private String regNo;
        private HostelFeeAccount feeAccount;
        private HostelRoom room;

        public SrmStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = room;
            totalStudents++;
        }

        public String fullStatus() {
            String roomStatus = (room != null) ? room.getRoomNo() : "unallotted";
            double due = (feeAccount != null) ? feeAccount.getDue() : 0.0;
            return name + " | Due: Rs " + due + " | Room: " + roomStatus;
        }
    }

    public static void main(String[] args) {
        HostelFeeAccount f1 = new HostelFeeAccount(200000, 60000);
        HostelFeeAccount f2 = new HostelFeeAccount(200000, 20000);
        HostelFeeAccount f3 = new HostelFeeAccount(200000, 0);

        f3.pay(-500);

        SrmStudent s1 = new SrmStudent("Ravi", "RA01", f1, new HostelRoom("C-214"));
        SrmStudent s2 = new SrmStudent("Anitha", "RA02", f2, new HostelRoom("C-507"));
        SrmStudent s3 = new SrmStudent("Karthik", "RA03", f3, null);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
