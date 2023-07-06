
package uas_parkpbo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UAS_prakpbo {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/todolist";
    static final String USER = "root";
    static final String PASS = "";
    
    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            todoList tdlist = new todoList(conn, stmt);
            tdlist.showMenu();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
   
