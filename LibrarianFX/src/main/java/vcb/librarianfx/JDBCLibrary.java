package vcb.librarianfx;

import com.mysql.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCLibrary
{

    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://127.0.0.1/";
    static final String USER = "root";
    static final String PASS = "";
    Connection conn = null;
    Statement stm = null;
    ResultSet result = null;
    FXMLController fxml;

    public void getConnection()
    {
        try {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e) {
            e.getMessage();
        }
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
        catch (SQLException e) {
            e.getStackTrace();
        }

        if (conn != null) {
            System.out.println("Connection established...");
        }
        else {
            System.out.println("Failed to make connection!");
        }
    }

    public void closeConnection()
    {
        try {
            conn.close();
            System.out.println("Connection closed...");
        }
        catch (SQLException se) {
            se.getStackTrace();
        }
    }

    public String getAdminUser()
    {
//        System.out.println("Executing query...");
        try {
            stm = (Statement) conn.createStatement();
            String adminuser = "SELECT librarysql.users.username\n"
                    + "FROM librarysql.users \n"
                    + "WHERE librarysql.users.id = 1";

            result = stm.executeQuery(adminuser);
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
        String resToString = null;
        try {
            while (result.next()) {
                resToString = result.getString(1);
            }
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
        return resToString;
    }

    public String getAdminPass()
    {
//        System.out.println("Executing query...");
        try {
            stm = (Statement) conn.createStatement();
            String adminpass = "SELECT librarysql.users.passwrd \n"
                    + "FROM librarysql.users \n"
                    + "WHERE librarysql.users.id = 1";
            result = stm.executeQuery(adminpass);
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }

        String resToString = null;
        try {
            while (result.next()) {
                resToString = result.getString(1);
            }
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
        return resToString;
    }

    public void addLibrarianToDB(String user, String pass, String email, String address, String city, String contact)
    {
        String addLibrarian = "insert into librarysql.users (users.username, users.passwrd, users.email, users.address, users.city, users.contact)\n"
                + "values("
                + "'" + user + "'" + ","
                + "'" + pass + "'" + ","
                + "'" + email + "'" + ","
                + "'" + address + "'" + ","
                + "'" + city + "'" + ","
                + "'" + contact + "'" + ")";

        try {
            stm = (Statement) conn.createStatement();
            stm.executeUpdate(addLibrarian);
        }
        catch (SQLException ex) {
            Logger.getLogger(JDBCLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
