package Theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.ResultSetMetaData;
// import java.sql.Statement;
// @SuppressWarnings("unused")
public class DatabaseQueries {

    // @SuppressWarnings("rawtypes")
    public void getData(String query) throws Exception{
        try (Connection con = ConnectToDB.connect()){
                if(query.contains("select*from")||  query.contains("selct * from")){PreparedStatement pstm = con.prepareStatement(query);
                // Statement smt =  con.createStatement();
                // ResultSet rs = ((java.sql.Statement) smt).executeQuery(query);
                ResultSet rsp = pstm.executeQuery();
                // ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                ResultSetMetaData rspmd = (ResultSetMetaData) rsp.getMetaData();

                while(rsp.next()){
                    for(int i=0; i<=rspmd.getColumnCount(); i++){
                        System.out.print(rsp.getString(i)+" ");
                        System.out.println();
                    }
                }
                // Second loop using Statement Reference.
            //     while(rs.next()){
            //         for(int i=0; i<=rsmd.getColumnCount(); i++){
            //             System.out.print(rs.getString(i)+" ");
            //         }
            //         System.out.println();
            //     }
            }
            
        } catch (SQLException e) {
            System.out.println();
        }
    }

    // public void

    
}
