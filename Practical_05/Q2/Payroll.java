import java.util.Scanner;

abstract class Employee {
    protected String name;
    protected int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract double monthlySalary();

    public void printDetails() {
        System.out.printf("ID: %d | Name: %s | Salary: %.2f%n", id, name, monthlySalary());
    }
}

class FullTime extends Employee {
    private double fixedSalary;

    public FullTime(String name, int id, double fixedSalary) {
        super(name, id);
        this.fixedSalary = fixedSalary;
    }

    public double monthlySalary() {
        return fixedSalary;
    }
}

class PartTime extends Employee {
    private double hours;
    private double rate;

    public PartTime(String name, int id, double hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    public double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends Employee {
    private double stipend;

    public Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    public double monthlySalary() {
        return stipend;
    }
}

public class Payroll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();
        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEmployee " + (i + 1) + " type: 1. FullTime 2. PartTime 3. Intern");
            int type = sc.nextInt();
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter ID: ");
            int id = sc.nextInt();

            switch (type) {
                case 1:
                    System.out.print("Enter Fixed Salary: ");
                    double salary = sc.nextDouble();
                    employees[i] = new FullTime(name, id, salary);
                    break;
                case 2:
                    System.out.print("Enter Hours Worked: ");
                    double hours = sc.nextDouble();
                    System.out.print("Enter Hourly Rate: ");
                    double rate = sc.nextDouble();
                    employees[i] = new PartTime(name, id, hours, rate);
                    break;
                case 3:
                    System.out.print("Enter Stipend: ");
                    double stipend = sc.nextDouble();
                    employees[i] = new Intern(name, id, stipend);
                    break;
                default:
                    System.out.println("Invalid type, defaulting to Intern with 0 stipend.");
                    employees[i] = new Intern(name, id, 0);
            }
        }

        System.out.println("\n--- Payroll Summary ---");
        double total = 0;
        for (Employee emp : employees) {
            emp.printDetails();
            total += emp.monthlySalary();
            if (emp instanceof Intern) {
                System.out.println("Note: Interns are temporary staff.");
            }
        }
        System.out.printf("Total payroll: %.2f%n", total);
        sc.close();
    }
}
