public class SrmStudent {
    private String name;
    private String regNo;
    private int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public boolean isEligible() {
        return attendance >= 75;
    }

    public String getName() {
        return name;
    }

    public int getAttendance() {
        return attendance;
    }

    public static double classAverage(SrmStudent[] students) {
        if (students == null || students.length == 0) {
            return 0.0;
        }
        int sum = 0;
        for (int i = 0; i < students.length; i++) {
            sum += students[i].getAttendance();
        }
        return (double) sum / students.length;
    }

    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA01", 82),
            new SrmStudent("Anitha", "RA02", 68),
            new SrmStudent("Karthik", "RA03", 91),
            new SrmStudent("Meera", "RA04", 74),
            new SrmStudent("Suresh", "RA05", 60)
        };

        for (int i = 0; i < students.length; i++) {
            String status = students[i].isEligible() ? "Eligible" : "Detained";
            System.out.println(students[i].getName() + " " + students[i].getAttendance() + "% " + status);
        }

        System.out.printf("Class average: %.1f%%%n", classAverage(students));
    }
}
