package Theater;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDB {
    public static Connection connect() throws Exception{
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost", "root", "1234");
        return conn;
    }
}
