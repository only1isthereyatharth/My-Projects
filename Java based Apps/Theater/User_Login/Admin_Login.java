package Theater.User_Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import Theater.ConnectToDB;

public class Admin_Login {
    public static boolean confirmLogin(String root, String password){
        try(Connection con = ConnectToDB.connect();
        Scanner sc = new Scanner(System.in)) {
            PreparedStatement psm = con.prepareStatement("SELECT * FROM theater.admin_credentials where username = '"+root+"' and password = '"+password+"' ; ");
            if(psm.execute()){
                return true;
            }
            else{
                System.out.println("Wrong Infromation provided please try again");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
