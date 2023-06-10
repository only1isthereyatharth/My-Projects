package Theater.Ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.Scanner;

import Theater.ConnectToDB;
import Theater.User_Login.Customer_Login;

@SuppressWarnings("unused")
public class Ticket {

    private final static int tickets = 12;
    static int bought=0;    // need to fetch ticktes from database.
    private static String transation_id ="PVR"+LocalDate.now()+"ID";

    public int getTicketLeft(){
        return tickets;
    }

    public void showAvailableSeats(String floor){

        try(Connection con = ConnectToDB.connect()) {
            PreparedStatement psm = con.prepareStatement("Select seat_name from theater."+floor+" where status is NULL;");
            ResultSet rs = psm.executeQuery();
            // ResultSetMetaData rsmd = rs.getMetaData();
            // while(rs.next()) {
            //     for(int i=1; i<=rsmd.getColumnCount(); i++) {        // Using this function we can print in spaces using %.
                                                                        //if(i%20)S.O.P(" ");
            //         System.out.print("| "+rs.getObject(i)+" | ");
            //     }
            //     System.out.println(" |");
            // }
            if(!rs.next())System.out.println("All seats are Booked");
            if(rs.next()) System.out.print("| ");
            while(rs.next()){
                System.out.print(rs.getString(1)+ " | ");
        }


        } catch (Exception e) {
            System.out.println("Sorry we only have Ground, First and VIP floors");
            e.printStackTrace();
        }
    }

    public boolean buyTicket(int t, String floor){

        if(bought+t>tickets){
            if(getTicketLeft()==0){
                System.out.println("Sorry we are full!");
                showAvailableSeats(floor);
            }
            else{
                System.out.println("We have only "+getTicketLeft()+" tickets");
                showAvailableSeats(floor);
            }
            return false ;
        }
        
        else{
            try(Scanner sc  = new Scanner(System.in);
            Connection con = ConnectToDB.connect()){
                String str[] = new String[t];
                int i=-1;
                PreparedStatement psm = con.prepareStatement("Select seat_name from theater."+floor+" where status is NULL; ");
                ResultSet rs = psm.executeQuery();
                // if(psm.execute("Select seat_name from theater."+floor+" where status is NULL; ")) System.out.println("boolean works");
                while(rs.next()){
                    String seat_name = rs.getString(1);
                    System.out.print(seat_name+ " ");
            }
            System.out.println();

            String trans = transation_id+bought;

            System.out.println("Select seats you want: ");

            while(++i<t)
                str[i] = sc.next().toUpperCase();  
                
            System.out.println("Please login first to payement.");
            String email = sc.next();
            String  password = sc.next();

            if(Customer_Login.getLoginInfo(email, password)){
                int cus_id = Customer_Login.getCusID(email, password);
                while(--i>=0){
                    int count = psm.executeUpdate("UPDATE `theater`.`"+floor+"` SET status = 'Filled' WHERE (seat_name = '"+str[i]+"');");
                if(count>0){
                psm.execute("UPDATE `theater`.`"+floor+"` SET customer_id = '"+ cus_id +"' WHERE (seat_name = '"+str[i]+"');");
                psm.execute("UPDATE `theater`.`"+floor+"` SET transaction_id = '"+ trans +"' WHERE (seat_name = '"+str[i]+"');");
                bought++;
                System.out.println("Seat_Name = '"+str[i]+"'is booked with transation_id = '"+ trans + "' ");
                }   // w569322@g
                    // else{
                    //     i--;
                    //     System.out.println("Please select from the contained list only!");
                    // }
                }
                return true;
            }
            return false;

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
