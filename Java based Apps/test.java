import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import java.sql.ResultSetMetaData;

public class test {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "1234");
        Scanner sc=  new Scanner(System.in)){
            PreparedStatement psm = con.prepareStatement("insert into dbtesting.testing(col_1,col_2,col_3) values(?,?,?)");
            // psm.execute("create database dbtesting");
            // psm.execute("use dbtesting");
            // psm.execute("create table testing(col_1 int primary key, col_2 varchar(45), col_3 varchar(45))");
            // psm.executeUpdate("'1','Testing Col_1' , 'Testing Col_2'", new String[]{"col_1","col_2","col_3"});
            // psm.execute("insert into dbtesting.testing(col_1,col_2,col_3) values(?,?,?)");
            psm.setString(1, "1");
            psm.setString(2,"Testing Col_1");
            psm.setString(3,"Testing Col_2");
            psm.execute("select * from dbtesting.testing");
            ResultSet rs = psm.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()){
                for(int i = 0; i <rsmd.getColumnCount(); i++){
                    System.out.print(rs.getObject(i)+" ");
                }
                System.out.println();
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
