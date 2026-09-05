class Employee {
    private String empId;
    private String empName;
    private double salary;
    
    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }
    
    public double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;
    
    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }
    
    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;
    
    public InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }
    
    public double effectiveSalary() {
        if (getSalary() < stipendCap) {
            return getSalary();
        }
        return stipendCap;
    }
}

public class HREmployeeSystem {
    public static void main(String[] args) {
        Employee plain = new Employee("E1", "John", 40000);
        ManagerEmployee manager = new ManagerEmployee("M1", "Sarah", 70000, 8000);
        InternEmployee intern = new InternEmployee("I1", "Mike", 12000, 10000);
        
        printPay(plain);
        printPay(manager);
        printPay(intern);
    }
    
    public static void printPay(Employee emp) {
        if (emp instanceof ManagerEmployee) {
            ManagerEmployee m = (ManagerEmployee) emp;
            System.out.println("Manager effective pay: Rs " + m.effectiveSalary());
        } else if (emp instanceof InternEmployee) {
            InternEmployee i = (InternEmployee) emp;
            System.out.println("Intern effective pay: Rs " + i.effectiveSalary());
        } else {
            System.out.println("Plain employee pay: Rs " + emp.getSalary());
        }
    }
}