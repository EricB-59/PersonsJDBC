package Model;

import java.util.Objects;

public class Person {
    final private int ID;
    private String name;
    private char gender;
    private int age;
    private String address;
    private Vehicle v;

    public Person(int ID, String name, char gender, int age, String address, Vehicle v) {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.v = v;
    }

    public Person(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Vehicle getV() {
        return v;
    }

    public void setV(Vehicle v) {
        this.v = v;
    }

    @Override
    public String toString() {
        if (getV() == null) {
            return 
            "| ID - " + getID() + "| NAME - " + getName() + "\n" +
            "| GENDER - " + getGender() + "| AGE - " + getAge() + "\n" +
            "| ADDRESS - " + getAddress() + "\n" +
            "| VEHICLE - NULL" + "\n";
        }else{
            return 
            "| ID - " + getID() + "| NAME - " + getName() + "\n" +
            "| GENDER - " + getGender() + "| AGE - " + getAge() + "\n" +
            "| ADDRESS - " + getAddress() + "\n" +
            "| VEHICLE MATRICULA - " + getV().getLicensePlate() + "\n" +
            "| VEHICLE COLOR - " + getV().getColor() + "\n";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final Person other = (Person) obj;
        return Objects.equals(this.ID, other.ID);
    }
}
