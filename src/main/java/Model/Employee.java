package Model;

import java.util.Objects;

public class Employee extends Person{
    final private int IDEMPLOYEE;
    private double salary;

    public Employee(int IDEMPLOYEE, double salary, int ID, String name, char gender, int age, String address, Vehicle v) {
        super(ID, name, gender, age, address, v);
        this.IDEMPLOYEE = IDEMPLOYEE;
        this.salary = salary;
    }

    public Employee(int IDEMPLOYEE, int ID) {
        super(ID);
        this.IDEMPLOYEE = IDEMPLOYEE;
    }

    public int getIDEMPLOYEE() {
        return IDEMPLOYEE;
    }
    
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return         
        "| ID EMPLOYEE - " + getIDEMPLOYEE() + "\n" +
        "| SALARY - " + getSalary()+ "\n" + super.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        return Objects.equals(this.IDEMPLOYEE, other.IDEMPLOYEE);
    }
}
