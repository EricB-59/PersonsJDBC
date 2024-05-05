package Model;

import java.time.LocalDate;
import java.util.Objects;

public class Customer extends Person{
    final private int IDCUSTOMER;
    private LocalDate dateRegister;
    private boolean vip;

    public Customer(int IDCUSTOMER, LocalDate dateRegister, boolean vip, int ID, String name, char gender, int age, String address, Vehicle v) {
        super(ID, name, gender, age, address, v);
        this.IDCUSTOMER = IDCUSTOMER;
        this.dateRegister = dateRegister;
        this.vip = vip;
    }

    public Customer(int IDCUSTOMER, int ID) {
        super(ID);
        this.IDCUSTOMER = IDCUSTOMER;
    }

    public int getIDCUSTOMER() {
        return IDCUSTOMER;
    }
    
    public LocalDate getDateRegister() {
        return dateRegister;
    }
    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

    public boolean isVip() {
        return vip;
    }
    public void setVip(boolean vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return   
        "| ID CUSTOMER - " + getIDCUSTOMER() + "\n" +
        "| DATE REGISTER - " + getDateRegister()+ "\n" +
        "| VIP - " + isVip()+ "\n" + super.toString();
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
        final Customer other = (Customer) obj;
        return Objects.equals(this.IDCUSTOMER, other.IDCUSTOMER);
    }
}
