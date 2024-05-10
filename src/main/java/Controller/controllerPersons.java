package Controller;

import Controller.exceptions.PersonException;
import Model.Customer;
import Model.Employee;
import Model.Person;

public class controllerPersons {
    public static void addPersonToArray(Person p) throws PersonException {
        switch (p.getClass().getSimpleName()) {
            case "Employee" -> {
                Employee e = (Employee) p;

            }
            case "Customer" -> {
                Customer c = (Customer) p;

            }
        }

    }

    public static void deletePerson(Person p) throws PersonException {
//        System.out.println(p);
//        if (!arrayListPersons.contains(p)) {
//            throw new PersonException(p.getName() + " no existe");
//        } else {
//            System.out.println("DELETE");
//            System.out.println(p);
//            arrayListPersons.remove(p);
//            escrituraFichero();
//        }
    }
}
