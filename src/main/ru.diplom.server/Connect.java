import com.mysql.jdbc.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {
    Connection connection = null;
    static final String USER = "root";
    static final String PASS = "root";
    static final String URL = "jdbc:mysql://localhost:3306/mydbtest";

    public Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, PASS, PASS);
            return connection;
        } catch (Exception e) {
            return null;
        }
    }
}