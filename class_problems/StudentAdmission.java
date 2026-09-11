public class StudentAdmission {

    static class BrokenStudent {
        static String name;
        static String regNo;
        static int attendance;

        public BrokenStudent(String n, String r, int a) {
            name = n;
            regNo = r;
            attendance = a;
        }
    }

    static class SrmStudentFixed {
        private static String university = "SRM University";
        private static int admissionCount = 10;

        private String name;
        private String regNo;
        private int attendance;

        public SrmStudentFixed(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            admissionCount++;
            this.regNo = "RA2311003010" + admissionCount;
        }

        public void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        public static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " + (admissionCount - 10));
        }
    }

    public static void main(String[] args) {
        BrokenStudent s1 = new BrokenStudent("Ravi", "RA01", 80);
        BrokenStudent s2 = new BrokenStudent("Meera", "RA02", 85);

        System.out.println(BrokenStudent.name);
        System.out.println(BrokenStudent.name);

        SrmStudentFixed f1 = new SrmStudentFixed("Ravi", 80);
        SrmStudentFixed f2 = new SrmStudentFixed("Meera", 85);

        f1.printIdCard();
        f2.printIdCard();
        SrmStudentFixed.printTotalAdmissions();
    }
}
