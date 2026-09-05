class EmployeeBase {
    private String empId;
    private String empName;
    private double salary;
    
    public EmployeeBase(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }
    public double getSalary() { return salary; }
}

class ManagerBase extends EmployeeBase {
    private double teamBonus;
    public ManagerBase(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }
    public double effectiveSalary() { return getSalary() + teamBonus; }
}

class ParkingSpot {
    String slotNo;
    int capacity;
    int occupiedCount;
    
    public ParkingSpot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }
}

class CompanyEmployeeRecord {
    String name;
    String empId;
    EmployeeBase employee;
    ParkingSpot slot;
    
    static int totalRecords = 0;
    
    public CompanyEmployeeRecord(String name, String empId, EmployeeBase employee) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        totalRecords++;
    }
    
    public void assignParking(ParkingSpot slot) {
        this.slot = slot;
    }
    
    public void fullProfile() {
        double pay = 0;
        if (employee instanceof ManagerBase) {
            pay = ((ManagerBase) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }
        
        String slotInfo = (slot == null) ? "no parking assigned" : slot.slotNo;
        
        System.out.println(name + " | Pay: Rs " + pay + " | Slot: " + slotInfo);
    }
}

public class CapstoneSystem {
    public static void main(String[] args) {
        ParkingSpot slotA1 = new ParkingSpot("A1", 1, 0);
        ParkingSpot slotA2 = new ParkingSpot("A2", 1, 0);
        
        ManagerBase divya = new ManagerBase("E01", "Divya", 70000, 8000);
        EmployeeBase karan = new EmployeeBase("E02", "Karan", 40000);
        EmployeeBase meera = new EmployeeBase("E03", "Meera", 10000);
        
        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "E01", divya);
        r1.assignParking(slotA1);
        
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E02", karan);
        r2.assignParking(slotA2);
        
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "E03", meera);
        
        r1.fullProfile();
        r2.fullProfile();
        r3.fullProfile();
        
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
