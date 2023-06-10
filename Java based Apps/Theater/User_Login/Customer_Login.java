package Theater.User_Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Theater.ConnectToDB;

public class Customer_Login  {

    public static boolean getLoginInfo(String email, String password) throws Exception{

        Pattern pt = Pattern.compile("^[a-zA-Z0-9_._]+@[a-zA-Z0-9_._]+$+");
        Matcher mch = pt.matcher(email);

        if(mch.matches()){
        try (Connection  conn = ConnectToDB.connect();){
            PreparedStatement psm = conn.prepareStatement("select first_name,last_name from theater.Customer where email='"+ email+"' and password='"+ password+"';");
            ResultSet rs = psm.executeQuery();
            if(rs.next()){
                System.out.print("Welcome : \t");
                System.out.println(rs.getString(1)+" "+rs.getString(2));
                return true;
            }
            else{
                System.out.println("You are not a registerd member. Register with us to book ");
                if(Customer_Login.createAccount()){
                    return true;
                }
                else return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    else {
        System.out.println("Please enter valid Email address: ");
        return false;
    }
    return false;
    }

    public static boolean createAccount() throws SQLException, Exception{
        try(Scanner sc = new Scanner(System.in);
        Connection con = ConnectToDB.connect()){
            System.out.println("From creating new account enter 1 else enter 0");
            int check = sc.nextInt();
            if(check!=1)return false;   // will return that it doesn

            //inputs from customer
            String first_name = sc.next();
            String last_name = sc.next();
            String email = sc.next();
            String password = sc.next();
            String phone = sc.next();
            
            PreparedStatement psm = con.prepareStatement("INSERT INTO `theater`.`customer` (`first_name`, `last_name`, `email`, `password`, `phone`) VALUES(?,?,?,?,?)");
            psm.setObject(1, first_name);
            psm.setObject(2, last_name);
            psm.setObject(3, email);
            psm.setObject(4, password); // setObject because we can typecast to requird field.
            psm.setObject(5, "+91"+phone);
            psm.execute();
            return true;
        }
    }

    public static int getCusID(String email, String password) {
        try(Connection con = ConnectToDB.connect();
        PreparedStatement psm = con.prepareStatement("select customer_id from theater.customer where email='"+ email+"' and password='"+ password+"';");
        ) {
            ResultSet rs = psm.executeQuery();
            if(rs.next())return rs.getInt(1);
                
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
   
}   
