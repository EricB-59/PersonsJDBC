package Model;

//import Exceptions.DAO_Excep;
import Controller.exceptions.PersonException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

//import Exceptions.Write_SQL_DAO_Excep;
//import Exceptions.Read_SQL_DAO_Excep;
//import Models.Student;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
/**
 *
 * @author Fran Perez
 */
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
    private final String SQL_SELECT_ALL = "SELECT * FROM " + JDBC_DDBB_TABLE + ";";
    private final String SQL_SELECT_ALL_COUNT = "SELECT count(*) FROM " + JDBC_DDBB_TABLE + ";";
    private final String SQL_SELECT = "SELECT * FROM " + JDBC_DDBB_TABLE + " WHERE (name = ";
    private final String SQL_SELECT2 = "SELECT * FROM " + JDBC_DDBB_TABLE + " WHERE (age = ";
    private final String SQL_INSERT = "INSERT INTO " + JDBC_DDBB_TABLE + " (name, age) VALUES (?, ?);";
    private final String SQL_INSERT2 = "INSERT INTO " + JDBC_DDBB_TABLE2 + " (id, licensePlate, color) VALUES (?, ?, ?);";
    private final String SQL_UPDATE = "UPDATE " + JDBC_DDBB_TABLE + " SET age = ? WHERE (name = ?);";
    private final String SQL_DELETE = "DELETE FROM " + JDBC_DDBB_TABLE + " WHERE (name = ";
    private final String SQL_DELETE_ALL = "DELETE FROM " + JDBC_DDBB_TABLE + ";";
    private final String SQL_RESET_AGES = "UPDATE " + JDBC_DDBB_TABLE + " SET age = 0 WHERE (name = ?);";

    public Connection connect() throws PersonException, SQLException {
        Connection conn = null;
        try {
            //getConnection necesita la BBDD, el usuario y la contraseña
            conn = DriverManager.getConnection(JDBC_URL + JDBC_COMMU_OPT, JDBC_USER, JDBC_PASSWORD);
            createDB(conn);
            createTable(conn);
        } catch (SQLException ex) {
            throw new PersonException("Can not connect or create database with tables: " + JDBC_DDBB);
        }
        return conn;
    }
//

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
//

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

//
    public void disconnect(Connection conn) throws PersonException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new PersonException("Can not disconnect from database " + JDBC_DDBB);
            }
        }
    }
//

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
            }
        } catch (SQLException ex) {
            throw new PersonException("Can not write to database (DAO_Controller.DAOSQL.insert)");
        }
        return registers;
    }

//    @Override
//    public List<Student> readALL() throws DAO_Excep {
//        List<Student> students = new ArrayList<>();
//        Connection conn = null;
//        Statement instruction = null;
//        ResultSet rs = null;
//        try {
//            conn = connect();
//            instruction = conn.createStatement();
//            rs = instruction.executeQuery(SQL_SELECT_ALL);
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String nombre = rs.getString("name");
//                int edad = rs.getInt("age");
//                students.add(new Student(id, nombre, edad));
//            }
//        } catch (SQLException ex) {
//            //ex.printStackTrace(System.out);
//            throw new Read_SQL_DAO_Excep("Can not read from database - readAll");
//        } finally {
//            try {
//                rs.close();
//                instruction.close();
//                disconnect(conn);
//            } catch (SQLException ex) {
//                //ex.printStackTrace(System.out);
//                throw new Read_SQL_DAO_Excep("Can not read from database - readAll");
//            }
//        }
//        return students;
//    }
//
//    @Override
//    public List<Student> read(Student s) throws DAO_Excep {
//        ArrayList<Student> students = new ArrayList<>();
//        Student student = null;
//        Connection conn = null;
//        Statement instruction = null;
//        ResultSet rs = null;
//        try {
//            conn = connect();
//            String query = SQL_SELECT + "'" + s.getName() + "'" + ");";
//            instruction = conn.createStatement();
//            rs = instruction.executeQuery(query);
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String nam = rs.getString("name");
//                int age = rs.getInt("age");
//                student = new Student(id, nam, age);
//                students.add(student);
//            }
//        } catch (SQLException ex) {
//            //ex.printStackTrace(System.out);
//            throw new Read_SQL_DAO_Excep("Can not read from database (DAO_COntroller.DAOSQL.read)");
//        } finally {
//            try {
//                rs.close();
//                instruction.close();
//                disconnect(conn);
//            } catch (SQLException ex) {
//                //ex.printStackTrace(System.out);
//                throw new Read_SQL_DAO_Excep("Can not close database read process (DAO_COntroller.DAOSQL.read)");
//            }
//        }
//        return students;
//    }
//
//    @Override
//    public List<Student> readByAge(Student s) throws DAO_Excep {
//        ArrayList<Student> students = new ArrayList<>();
//        Student student = null;
//        Connection conn = null;
//        Statement instruction = null;
//        ResultSet rs = null;
//        try {
//            conn = connect();
//            String query = SQL_SELECT2 + "'" + s.getAge() + "'" + ");";
//            System.out.println(query);
//            instruction = conn.createStatement();
//            rs = instruction.executeQuery(query);
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String nam = rs.getString("name");
//                int age = rs.getInt("age");
//                student = new Student(id, nam, age);
//                students.add(student);
//            }
//        } catch (SQLException ex) {
//            //ex.printStackTrace(System.out);
//            throw new Read_SQL_DAO_Excep("Can not read from database (DAO_COntroller.DAOSQL.read)");
//        } finally {
//            try {
//                rs.close();
//                instruction.close();
//                disconnect(conn);
//            } catch (SQLException ex) {
//                //ex.printStackTrace(System.out);
//                throw new Read_SQL_DAO_Excep("Can not close database read process (DAO_COntroller.DAOSQL.read)");
//            }
//        }
//        return students;
//    }
//
//    @Override
//    public int insert(Student student) throws DAO_Excep {
//        Connection conn = null;
//        //La clase PreparedStatement también permite ejecutar sentencias SQL
//        //pero con mayor flexibilidad
//        PreparedStatement instruction = null;
//        int registers = 0;
//        try {
//            conn = connect();
//            instruction = conn.prepareStatement(SQL_INSERT);
//            instruction.setString(1, student.getName());
//            instruction.setInt(2, student.getAge());
//            registers = instruction.executeUpdate();
//        } catch (SQLException ex) {
//            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)");
//        } finally {
//            try {
//                instruction.close();
//                disconnect(conn);
//            } catch (SQLException ex) {
//                //ex.printStackTrace(System.out);
//                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)");
//            }
//        }
//        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
//        return registers;
//    }
//
//    @Override
//    public int update(Student student) throws DAO_Excep {
//        Connection conn = null;
//        PreparedStatement instruction = null;
//        int registers = 0;
//        try {
//            conn = connect();
//            instruction = conn.prepareStatement(SQL_UPDATE);
//            instruction.setInt(1, student.getAge());
//            instruction.setString(2, student.getName());
//            //cada vez que modificamos una base de datos llamamos a executeUpdate()
//            registers = instruction.executeUpdate();
//        } catch (SQLException ex) {
//            //ex.printStackTrace(System.out);
//            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.update)");
//        } finally {
//            try {
//                instruction.close();
//                disconnect(conn);
//            } catch (SQLException ex) {
//                //ex.printStackTrace(System.out);
//                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.update)");
//            }
//        }
//        //Devolvemos la cantidad de registros afectados
//        return registers;
//    }
//
//    @Override
//    public int delete(Student student) throws DAO_Excep {
//        Connection conn = null;
//        PreparedStatement instruccion = null;
//        int registers = 0;
//        try {
//            conn = connect();
//            String query = SQL_DELETE + "'" + student.getName() + "'" + ");";
//            instruccion = conn.prepareStatement(query);
//            //cada vez que modificamos una base de datos llamamos a executeUpdate()
//            registers = instruccion.executeUpdate();
//        } catch (SQLException ex) {
//            //ex.printStackTrace(System.out);
//            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)");
//
//        } finally {
//            try {
//                instruccion.close();
//                disconnect(conn);
//            } catch (SQLException ex) {
//                ex.printStackTrace(System.out);
//                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)");
//            }
//        }
//        //Devolvemos la cantidad de registros afectados
//        return registers;
//    }
//
//    @Override
//    public int deleteALL() throws DAO_Excep {
//        Connection conn = null;
//        PreparedStatement instruccion = null;
//        int registers = 0;
//        try {
//            conn = connect();
//            instruccion = conn.prepareStatement(SQL_DELETE_ALL);
//            //cada vez que modificamos una base de datos llamamos a executeUpdate()
//            registers = instruccion.executeUpdate();
//        } catch (SQLException ex) {
//            //ex.printStackTrace(System.out);
//            throw new Write_SQL_DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.deleteAll)");
//        } finally {
//            try {
//                instruccion.close();
//                disconnect(conn);
//            } catch (SQLException ex) {
//                ex.printStackTrace(System.out);
//                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.deleteAll)");
//            }
//        }
//        //Devolvemos la cantidad de registros afectados
//        return registers;
//    }
//
//    @Override
//    public int resetAges() throws DAO_Excep {
//        //Esta operación se podría hacer con una única consulta SQL
//        //pero no lo hacemos así porque es un ejemplo de transacción
//        Connection conn = null;
//        PreparedStatement instruction = null;
//        int registers = 0;
//        try {
//            List<Student> students = readALL();
//            conn = connect();
//            conn.setAutoCommit(false);
//            if (!students.isEmpty()) {
//                for (Student a : students) {
//                    instruction = conn.prepareStatement(SQL_RESET_AGES);
//                    instruction.setString(1, a.getName());
//                    //cada vez que modificamos una base de datos llamamos a executeUpdate()
//                    registers += instruction.executeUpdate(); 
//                    //Activar para comprobar el funcionamiento del rollback
//                    //Debe haber más de un estudiante en la Base de datos (*)
////                    throw new SQLException();
//                }
//            }
//        } catch (SQLException ex) {
//            if(conn != null){
//                try {
//                    conn.rollback();
//                } catch (SQLException ex1) {
//                    System.out.println("ROLLBACK");
//                }
//                //(*)
////                registers=0;
//            }
//        } finally {
//            try {
//                instruction.close();
//                conn.setAutoCommit(true);
//                disconnect(conn);
//            } catch (SQLException ex) {
//                ex.printStackTrace(System.out);
//                throw new Write_SQL_DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.deleteAll)");
//            }
//        }
//        //Devolvemos la cantidad de registros afectados
//        return registers;
//    }
}
