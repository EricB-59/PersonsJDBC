package Model;

import Controller.exceptions.PersonException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DAOSQL {

    //Variables para la conexión segura contra el servidor (sin especificar DDBB)
    private final String JDBC_URL = "jdbc:mysql://localhost:3306";
    private final String JDBC_COMMU_OPT = "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "";

    //Especificamos la base de Datos
    private final String JDBC_DDBB = "PersonsBBDD";
    private final String JDBC_TABLE = "persons";
    private final String JDBC_TABLE2 = "vehicles";
    private final String JDBC_DDBB_TABLE = JDBC_DDBB + "." + JDBC_TABLE;
    private final String JDBC_DDBB_TABLE2 = JDBC_DDBB + "." + JDBC_TABLE2;

    //Variables para las consultas SQL
    private final String SQL_SELECT_ALL_COUNT = "SELECT count(*) FROM " + JDBC_DDBB_TABLE + ";";
    private final String SQL_DELETE = "DELETE FROM " + JDBC_DDBB_TABLE + " WHERE (idPerson = ";

    public Connection connect() throws PersonException, SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL + JDBC_COMMU_OPT, JDBC_USER, JDBC_PASSWORD);
            createDB(conn);
            createTable(conn);
        } catch (SQLException ex) {
            throw new PersonException("Can not connect or create database with tables: " + JDBC_DDBB);
        }
        return conn;
    }

    private void createDB(Connection conn) throws SQLException {
        //Sentencia SQL que crea la BBDD si no existe en el servidor
        String instruction = "create database if not exists " + JDBC_DDBB + ";";
        Statement stmt = null;
        stmt = conn.createStatement();
        //La clase Statemen nos permite ejecutar sentencias SQL
        stmt.executeUpdate(instruction);
        //Liberamos los recursos de la comunicación   
        stmt.close();
    }

    private void createTable(Connection conn) throws SQLException {
        String query = "create table if not exists " + JDBC_DDBB + "." + JDBC_TABLE + "("
                + "id Bigint primary key auto_increment, "
                + "idPerson int,"
                + "name varchar(50),"
                + "gender char,"
                + "age int,"
                + "address varchar(50),"
                + "vehicleId Bigint,"
                + "idemployee int,"
                + "salary int,"
                + "idcustomer int,"
                + "date varchar(20),"
                + "vip boolean,"
                + "FOREIGN KEY (vehicleId) REFERENCES " + JDBC_DDBB + "." + JDBC_TABLE2 + "(id)"
                + ");";

        String query2 = "create table if not exists " + JDBC_DDBB + "." + JDBC_TABLE2 + "("
                + "id Bigint primary key auto_increment,"
                + "licensePlate varchar(7), "
                + "color varchar(20));";

        Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate(query2);
        stmt.executeUpdate(query);
        stmt.close();
    }

    public void disconnect(Connection conn) throws PersonException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new PersonException("Can not disconnect from database " + JDBC_DDBB);
            }
        }
    }

    public int countALL() throws PersonException {
        Connection conn = null;
        Statement instruction = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = connect();
            instruction = conn.createStatement();
            rs = instruction.executeQuery(SQL_SELECT_ALL_COUNT);
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException ex) {
            throw new PersonException("Can not read from database - readAll");
        } finally {
            try {
                rs.close();
                instruction.close();
                disconnect(conn);
            } catch (SQLException ex) {
                throw new PersonException("Can not read from database - readAll");
            }
        }
        return count;
    }

    public int searchId(int idToCheck) throws PersonException {
        Connection conn = null;
        Statement instruction = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = connect();
            instruction = conn.createStatement();
            rs = instruction.executeQuery("SELECT count(idPerson) FROM " + JDBC_DDBB_TABLE + " WHERE idPerson = " + idToCheck + ";");
            while (rs.next()) {
                id = rs.getInt("count(idPerson)");
            }
        } catch (SQLException ex) {
            throw new PersonException("Can not read from database - readAll");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instruction != null) {
                    instruction.close();
                }
                disconnect(conn);
            } catch (SQLException ex) {
                throw new PersonException("Can not read from database - readAll");
            }
        }
        return id;
    }

    public int searchIdEmployee(int idToCheck) throws PersonException {
        Connection conn = null;
        Statement instruction = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = connect();
            instruction = conn.createStatement();
            rs = instruction.executeQuery("SELECT count(idemployee) FROM " + JDBC_DDBB_TABLE + " WHERE idemployee = " + idToCheck + ";");
            while (rs.next()) {
                id = rs.getInt("count(idemployee)");
            }
        } catch (SQLException ex) {
            throw new PersonException("Can not read from database - readAll");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instruction != null) {
                    instruction.close();
                }
                disconnect(conn);
            } catch (SQLException ex) {
                throw new PersonException("Can not read from database - readAll");
            }
        }
        return id;
    }

    public int searchIdCustomer(int idToCheck) throws PersonException {
        Connection conn = null;
        Statement instruction = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = connect();
            instruction = conn.createStatement();
            rs = instruction.executeQuery("SELECT count(idcustomer) FROM " + JDBC_DDBB_TABLE + " WHERE idcustomer = " + idToCheck + ";");
            while (rs.next()) {
                id = rs.getInt("count(idcustomer)");
            }
        } catch (SQLException ex) {
            throw new PersonException("Can not read from database - readAll");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instruction != null) {
                    instruction.close();
                }
                disconnect(conn);
            } catch (SQLException ex) {
                throw new PersonException("Can not read from database - readAll");
            }
        }
        return id;
    }

    public int searchIdByLicensePlate(String licensePlate) throws PersonException {
        Connection conn = null;
        Statement instruction = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = connect();
            instruction = conn.createStatement();
            rs = instruction.executeQuery("SELECT id FROM " + JDBC_DDBB_TABLE2 + " WHERE licensePlate LIKE '" + licensePlate + "'");
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            throw new PersonException("Can not read from database - readAll");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instruction != null) {
                    instruction.close();
                }
                disconnect(conn);
            } catch (SQLException ex) {
                throw new PersonException("Can not read from database - readAll");
            }
        }
        return id;
    }

    public int insert(Person p) throws PersonException {
        String SQL_INSERT_EMPLOYEE = "INSERT INTO " + JDBC_DDBB_TABLE + " (idPerson, name, gender, age, address, vehicleId, idemployee, salary) VALUES ";
        String SQL_INSERT_CUSTOMER = "INSERT INTO " + JDBC_DDBB_TABLE + " (idPerson, name, gender, age, address, vehicleId, idcustomer, date, vip) VALUES ";
        String SQL_INSERT_VEHICLE = "INSERT INTO " + JDBC_DDBB_TABLE2 + " (licensePlate, color) VALUES ";

        int registers = 0;

        try (Connection conn = connect()) {
            if (p.getV() != null) {
                System.out.println("getv es null");
                try (PreparedStatement instruction = conn.prepareStatement(SQL_INSERT_VEHICLE + "('" + p.getV().getLicensePlate() + "','" + p.getV().getColor() + "');")) {
                    registers += instruction.executeUpdate();
                    instruction.close();
                    disconnect(conn);
                }
                if (p instanceof Employee) {
                    try (Connection conn2 = connect()) {
                        Employee e = (Employee) p;
                        try (PreparedStatement instruction = conn2.prepareStatement(SQL_INSERT_EMPLOYEE + "(" + p.getID() + ",'" + p.getName() + "','" + p.getGender() + "'," + p.getAge() + ",'" + p.getAddress() + "'," + searchIdByLicensePlate(p.getV().getLicensePlate()) + "," + e.getIDEMPLOYEE() + "," + ((int) e.getSalary()) + ");")) {
                            registers += instruction.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.insert)");
                    }
                }
                if (p instanceof Customer) {
                    try (Connection conn2 = connect()) {
                        Customer c = (Customer) p;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        try (PreparedStatement instruction = conn2.prepareStatement(SQL_INSERT_CUSTOMER + "(" + p.getID() + ",'" + p.getName() + "','" + p.getGender() + "'," + p.getAge() + ",'" + p.getAddress() + "'," + searchIdByLicensePlate(p.getV().getLicensePlate()) + "," + c.getIDCUSTOMER() + ",'" + c.getDateRegister().format(formatter) + "'," + (c.isVip() ? 1 : 0) + ");")) {
                            registers += instruction.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.insert)");
                    }
                }
            } else {
                if (p instanceof Employee) {
                    try (Connection conn2 = connect()) {
                        Employee e = (Employee) p;
                        try (PreparedStatement instruction = conn2.prepareStatement(SQL_INSERT_EMPLOYEE + "(" + p.getID() + ",'" + p.getName() + "','" + p.getGender() + "'," + p.getAge() + ",'" + p.getAddress() + "'," + null + "," + e.getIDEMPLOYEE() + "," + ((int) e.getSalary()) + ");")) {
                            registers += instruction.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.insert)");
                    }
                }
                if (p instanceof Customer) {
                    try (Connection conn2 = connect()) {
                        Customer c = (Customer) p;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        try (PreparedStatement instruction = conn2.prepareStatement(SQL_INSERT_CUSTOMER + "(" + p.getID() + ",'" + p.getName() + "','" + p.getGender() + "'," + p.getAge() + ",'" + p.getAddress() + "'," + null + "," + c.getIDCUSTOMER() + ",'" + c.getDateRegister().format(formatter) + "'," + (c.isVip() ? 1 : 0) + ");")) {
                            registers += instruction.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.insert)");
                    }
                }
            }
        } catch (SQLException ex) {
            throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.insert)");
        }
        return registers;
    }

    public String[][] readPersonsIdAndName() throws PersonException {
        Connection conn = null;
        Statement instruction = null;
        ResultSet rs = null;
        String[][] idAndName = null;

        try {
            conn = connect();
            instruction = conn.createStatement();
            rs = instruction.executeQuery("SELECT idPerson, name FROM " + JDBC_DDBB_TABLE + ";");

            // Use ArrayList to temporarily store the data
            ArrayList<String[]> tempList = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("idPerson");
                String name = rs.getString("name");
                tempList.add(new String[]{String.valueOf(id), name});
            }

            // Convert the ArrayList to a 2D array
            idAndName = new String[tempList.size()][2];
            for (int i = 0; i < tempList.size(); i++) {
                idAndName[i] = tempList.get(i);
            }

        } catch (SQLException ex) {
            throw new PersonException("Can not read from database - readAll");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instruction != null) {
                    instruction.close();
                }
                disconnect(conn);
            } catch (SQLException ex) {
                throw new PersonException("Can not read from database - readAll");
            }
        }

        return idAndName;
    }

    public Vehicle searchVehicleById(int idToSearch) throws PersonException {
        Connection conn = null;
        Statement instruction = null;
        ResultSet rs = null;
        Vehicle vehicle = null;
        try {
            conn = connect();
            instruction = conn.createStatement();
            rs = instruction.executeQuery("SELECT * FROM " + JDBC_DDBB_TABLE2 + " WHERE id = " + idToSearch + ";");
            while (rs.next()) {
                vehicle = new Vehicle(rs.getString("licensePlate"), rs.getString("color"));
            }
        } catch (SQLException ex) {
            throw new PersonException("Can not read from database - readAll");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instruction != null) {
                    instruction.close();
                }
                disconnect(conn);
            } catch (SQLException ex) {
                throw new PersonException("Can not read from database - readAll");
            }
        }
        return vehicle;
    }

    public Person searchPersonById(int idToSearch) throws PersonException {
        Connection conn = null;
        PreparedStatement instruction = null;
        ResultSet rs = null;
        Person p = null;
        try {
            conn = connect();
            String query = "SELECT * FROM " + JDBC_DDBB_TABLE + " WHERE idPerson = ?";
            instruction = conn.prepareStatement(query);
            instruction.setInt(1, idToSearch);
            rs = instruction.executeQuery();

            if (rs.next()) {  // Mueve el cursor al primer registro
                // Verifica si es un empleado
                int idCustomer = rs.getInt("idcustomer");
                if (rs.wasNull()) {  // Comprueba si idcustomer es NULL
                    // Es un empleado
                    p = new Employee(
                            rs.getInt("idemployee"),
                            rs.getInt("salary"),
                            rs.getInt("idPerson"),
                            rs.getString("name"),
                            rs.getString("gender").charAt(0),
                            rs.getInt("age"),
                            rs.getString("address"),
                            searchVehicleById(rs.getInt("vehicleId"))
                    );
                } else {
                    // Es un cliente
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    p = new Customer(
                            rs.getInt("idcustomer"),
                            LocalDate.parse(rs.getString("date"), formatter),
                            rs.getInt("vip") != 0,
                            rs.getInt("idPerson"),
                            rs.getString("name"),
                            rs.getString("gender").charAt(0),
                            rs.getInt("age"),
                            rs.getString("address"),
                            searchVehicleById(rs.getInt("vehicleId"))
                    );
                }
            }
        } catch (SQLException ex) {
            throw new PersonException("Can not read from database - searchPersonById");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instruction != null) {
                    instruction.close();
                }
                disconnect(conn);
            } catch (SQLException ex) {
                throw new PersonException("Can not read from database - searchPersonById");
            }
        }
        return p;
    }

    public int delete(Person p) throws PersonException {
        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;
        try {
            conn = connect();
            String query = SQL_DELETE + "'" + p.getID() + "'" + ");";
            instruccion = conn.prepareStatement(query);
            //cada vez que modificamos una base de datos llamamos a executeUpdate()
            registers = instruccion.executeUpdate();
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.delete)");

        } finally {
            try {
                instruccion.close();
                disconnect(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                throw new PersonException("Can not close database write process (DAO_COntroller.DAOSQL.delete)");
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

    public ArrayList<Person> searchPersonByName(String name) throws PersonException {
        Connection conn = null;
        PreparedStatement instruction = null;
        ResultSet rs = null;
        Person p = null;
        ArrayList<Person> persons = new ArrayList<>();

        try {
            conn = connect();
            String query = "SELECT * FROM " + JDBC_DDBB_TABLE + " WHERE name = ?";
            instruction = conn.prepareStatement(query);
            instruction.setString(1, name);
            rs = instruction.executeQuery();

            while (rs.next()) {  // Mueve el cursor al primer registro
                // Verifica si es un empleado
                int idCustomer = rs.getInt("idcustomer");
                if (rs.wasNull()) {  // Comprueba si idcustomer es NULL
                    // Es un empleado
                    p = new Employee(
                            rs.getInt("idemployee"),
                            rs.getInt("salary"),
                            rs.getInt("idPerson"),
                            rs.getString("name"),
                            rs.getString("gender").charAt(0),
                            rs.getInt("age"),
                            rs.getString("address"),
                            searchVehicleById(rs.getInt("vehicleId"))
                    );
                } else {
                    // Es un cliente
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    p = new Customer(
                            rs.getInt("idcustomer"),
                            LocalDate.parse(rs.getString("date"), formatter),
                            rs.getInt("vip") != 0,
                            rs.getInt("idPerson"),
                            rs.getString("name"),
                            rs.getString("gender").charAt(0),
                            rs.getInt("age"),
                            rs.getString("address"),
                            searchVehicleById(rs.getInt("vehicleId"))
                    );
                }
                persons.add(p);
            }
        } catch (SQLException ex) {
            throw new PersonException("Can not read from database - searchPersonById");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instruction != null) {
                    instruction.close();
                }
                disconnect(conn);
            } catch (SQLException ex) {
                throw new PersonException("Can not read from database - searchPersonById");
            }
        }
        return persons;
    }

    public int update(Person p) throws PersonException {
        String SQL_UPDATE_EMPLOYEE = "UPDATE " + JDBC_DDBB_TABLE + " SET ";
        String SQL_UPDATE_CUSTOMER = "UPDATE " + JDBC_DDBB_TABLE + " SET ";
        String SQL_INSERT_VEHICLE = "INSERT INTO " + JDBC_DDBB_TABLE2 + " (licensePlate, color) VALUES ";

        int registers = 0;

        try (Connection conn = connect()) {
            if (p.getV() != null) {
                try (PreparedStatement instruction = conn.prepareStatement(SQL_INSERT_VEHICLE + "('" + p.getV().getLicensePlate() + "','" + p.getV().getColor() + "');")) {
                    registers += instruction.executeUpdate();
                    instruction.close();
                    disconnect(conn);
                }
                if (p instanceof Employee) {
                    try (Connection conn2 = connect()) {
                        Employee e = (Employee) p;
                        System.out.println((SQL_UPDATE_EMPLOYEE
                                + "name = '" + p.getName() + "', "
                                + "gender = '" + p.getGender() + "', "
                                + "age = " + p.getAge() + ", "
                                + "address = '" + p.getAddress() + "', "
                                + "vehicleId = " + searchIdByLicensePlate(p.getV().getLicensePlate()) + ", "
                                + "idemployee = " + e.getIDEMPLOYEE() + ", "
                                + "salary = " + ((int) e.getSalary()) + " "
                                + "WHERE idPerson = " + p.getID() + ";"));
                        try (PreparedStatement instruction = conn2.prepareStatement(SQL_UPDATE_EMPLOYEE
                                + "name = '" + p.getName() + "', "
                                + "gender = '" + p.getGender() + "', "
                                + "age = " + p.getAge() + ", "
                                + "address = '" + p.getAddress() + "', "
                                + "vehicleId = " + searchIdByLicensePlate(p.getV().getLicensePlate()) + ", "
                                + "idemployee = " + e.getIDEMPLOYEE() + ", "
                                + "salary = " + ((int) e.getSalary()) + " "
                                + "WHERE idPerson = " + p.getID() + ";")) {
                            registers += instruction.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.insert)");
                    }
                }
                if (p instanceof Customer) {
                    try (Connection conn2 = connect()) {
                        Customer c = (Customer) p;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        try (PreparedStatement instruction = conn2.prepareStatement(SQL_UPDATE_CUSTOMER
                                + "name = '" + p.getName() + "', "
                                + "gender = '" + p.getGender() + "', "
                                + "age = " + p.getAge() + ", "
                                + "address = '" + p.getAddress() + "', "
                                + "vehicleId = " + searchIdByLicensePlate(p.getV().getLicensePlate()) + ", "
                                + "idcustomer = " + c.getIDCUSTOMER() + ", "
                                + "vip = " + (c.isVip() ? 1 : 0) + ", "
                                + "date = '" + c.getDateRegister().format(formatter) + "' "
                                + "WHERE idPerson = " + p.getID() + ";")) {
                            registers += instruction.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.insert)");
                    }
                }
            } else {
                if (p instanceof Employee) {
                    try (Connection conn2 = connect()) {
                        Employee e = (Employee) p;
                        try (PreparedStatement instruction = conn2.prepareStatement(SQL_UPDATE_EMPLOYEE
                                + "name = '" + p.getName() + "', "
                                + "gender = '" + p.getGender() + "', "
                                + "age = " + p.getAge() + ", "
                                + "address = '" + p.getAddress() + "', "
                                + "vehicleId = " + null + ", "
                                + "idemployee = " + e.getIDEMPLOYEE() + ", "
                                + "salary = " + ((int) e.getSalary()) + " "
                                + "WHERE idPerson = " + p.getID() + ";")) {
                            registers += instruction.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.insert)");
                    }
                }
                if (p instanceof Customer) {
                    try (Connection conn2 = connect()) {
                        Customer c = (Customer) p;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        try (PreparedStatement instruction = conn2.prepareStatement(SQL_UPDATE_CUSTOMER
                                + "name = '" + p.getName() + "', "
                                + "gender = '" + p.getGender() + "', "
                                + "age = " + p.getAge() + ", "
                                + "address = '" + p.getAddress() + "', "
                                + "vehicleId = " + null + ", "
                                + "idcustomer = " + c.getIDCUSTOMER() + ", "
                                + "vip = " + (c.isVip() ? 1 : 0) + ", "
                                + "date = '" + c.getDateRegister().format(formatter) + "' "
                                + "WHERE idPerson = " + p.getID() + ";")) {
                            registers += instruction.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.insert)");
                    }
                }
            }
        } catch (SQLException ex) {
            throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.insert)");
        }

        return registers;
    }
}
