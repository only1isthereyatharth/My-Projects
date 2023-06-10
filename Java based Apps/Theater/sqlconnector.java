package Theater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Statement;
import java.util.Scanner;
// import com.mysql.cj.jdbc.result.ResultSetMetaData;
// @SuppressWarnings("unused")
public class sqlconnector {

    public static void main(String[] args) throws SQLException {
        // String sql = "jdbc:mysql://localhost/3306";
        // int i=1;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/market", "root", "1234");
        Scanner sc = new Scanner(System.in);
        ) {
            // try(Connection conn = DriverManager.)
            String sql = "select * from market.bookshop";
            // Statement stmt = con.createStatement();
            PreparedStatement ps = con.prepareStatement("insert into market.bookshop(BookID,Title,Author_ID,PublicationDate,Price_Ind) values(?,?,?,?,?);");
            //'B107','Testing', '32','2022-05-05', '34.43'
            ps.setString(1, "B107");
            ps.setString(2, "Testing");
            ps.setString(3, "32");
            ps.setString(4, "2022-05-05");
            ps.setString(5, "34.43");
            // ps.setString(0, "Test");
            ps.execute();
            ResultSet rs = ps.executeQuery(sql);
            // ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            // ResultSet rs = ps.executeQuery();
            // System.out.println(rs); 
            // System.out.println(con);
            // int colNum = rsmd.getColumnCount();

             while(rs.next()){
                String id = rs.getString("BookID");
                String title = rs.getString("Title");
                int author_id = rs.getInt("Author_ID");
                String publication_Date = rs.getString("PublicationDate");
                double price = rs.getDouble("Price_Ind");
            
                System.out.println("------------------------------------------------------------------");
                System.out.format("| %-4s | %-26s | %-3s | %-12s | %-5s |", id, title,author_id,publication_Date,price);
                System.out.println();
                // System.out.println(id+title+" "+author_id+" "+publication_Date+" "+ price);

             }
             System.out.println("------------------------------------------------------------------");
        // ConnectDB conn = new ConnectDB();
        // PreparedStatement psmt = (PreparedStatement)conn.connect(sql);
        // ResultSet rs = psmt.executeQuery();
        // Statement st =conn.connect(sql);
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
