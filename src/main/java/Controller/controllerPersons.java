package Controller;

import Controller.exceptions.PersonException;
import Model.Customer;
import Model.DAOSQL;
import Model.Employee;
import Model.Person;
import java.sql.SQLException;
import java.util.ArrayList;

public class controllerPersons {

    public static void startDB() throws PersonException, SQLException {
        new DAOSQL().connect();
    }

    public static String numberOfPersons() throws PersonException {
        return String.valueOf(new DAOSQL().countALL());
    }

    public static void addPerson(Person p) throws PersonException {
        DAOSQL dao;
        dao = new DAOSQL();
        if (dao.searchId(p.getID()) == 1) {
            throw new PersonException("ID REPEATED PERSON");
        } else {
            if (p instanceof Employee) {
                Employee e = (Employee) p;
                if (dao.searchIdEmployee(e.getIDEMPLOYEE()) == 1) {
                    throw new PersonException("ID REPEATED EMPLOYEE");
                } else {
                    dao.insert(p);
                }
            }
            if (p instanceof Customer) {
                Customer c = (Customer) p;
                if (dao.searchIdCustomer(c.getIDCUSTOMER()) == 1) {
                    throw new PersonException("ID REPEATED CUSTOMER");
                } else {
                    dao.insert(p);
                }
            }
        }
    }
    
    public static String[][] moveIdAndNameToView () throws PersonException {
        return new DAOSQL().readPersonsIdAndName();
    }
    
    public static Person getPersonById (int idToSearch) throws PersonException {
        return new DAOSQL().searchPersonById(idToSearch);
    }

    public static void deletePerson(int idToDelete) throws PersonException {
        new DAOSQL().delete(new DAOSQL().searchPersonById(idToDelete));
    }
    
    public static ArrayList<Person> getListOfPersonsByName(String name) throws PersonException{
        return new DAOSQL().searchPersonByName(name);
    }
    
    public static void updatePerson(Person p) throws PersonException {
        new DAOSQL().update(p);
    }
}
