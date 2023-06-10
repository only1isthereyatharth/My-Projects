package Theater.CustomerPage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

import Theater.ConnectToDB;
import Theater.Ticket.Ticket;

import java.sql.Statement;

// @SuppressWarnings("unused")
public class MovieTickectApp extends Ticket{

    public static void bookTicket(){
       
        try (Connection con = ConnectToDB.connect(); 
            Scanner sc = new Scanner(System.in) ){
                System.out.println("Welcome🥳 it's us Family Theater\n"+"Here are available movies on our screens 🤩\n");

            PreparedStatement psmt = con.prepareStatement("select * from theater.movieadmin");
            ResultSet rs = psmt.executeQuery();
            
            while(true){ 
                System.out.println("|SNo|  Name  | Rating | Ticket | Description ");
                printData(rs);
                // column name bhi daalna hai
                
                while(true){
                    System.out.print("Select serial number for description of movie : ");
                    
                    Statement smt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    rs = smt.executeQuery("select *from theater.movieadmin");
                    String user_choice = sc.nextLine();
                    if(rs.absolute(Integer.parseInt(user_choice))){
                        System.out.print("\nMovie Name: "+rs.getString(2));
                        System.out.println("\n.............................................................................\n");
                        System.out.print("Rating: "+rs.getFloat(3));
                        System.out.println("\n.............................................................................\n");
                        System.out.print("Ticket Price "+rs.getInt(4));
                        System.out.println("\n.............................................................................\n");
                        System.out.print("Description: "+rs.getString(5));
                        System.out.println("\n.............................................................................\n");
                        System.out.print("Cast: "+rs.getString(6));
                        System.out.println("\n.............................................................................\n");
                        // System.out.println();
                        break;
                    }
                    else{
                        System.out.println("Sorry ☹️, please select only available in the list");
                        continue;
                    }
                }
                System.out.println("For viewing list again just enter /");
                System.out.println("For Booking Ticket just enter b ");
                String decide = sc.next().toLowerCase();
                if(decide.equals("/"))bookTicket();

                if(decide.equals("b")){
                    while(true){
                        System.out.println("\nWhere you want to find seats\n");
                        System.out.println("Ground Floor select G");
                        System.out.println("First Floor select F");
                        System.out.println("VIP Floor select V\n");
                        String floor = sc.next().toUpperCase();
                        Ticket t = new Ticket();
                        if(floor.contains("G")){
                            System.out.println("You selected GroundFloor");
                            if(t.getTicketLeft()>0)
                            t.showAvailableSeats("groundf_seats");
                            else{ 
                                System.out.println("Sorry no Available seats available");
                                continue;
                            }
                            System.out.print("\nSelect number of seats you want to select : ");
                            Integer num = sc.nextInt();
                            if(t.buyTicket(num, "groundf_seats"))break; // how we will define customer id???
                            System.out.println("Please select different floor");
                        }
                        else if(floor.contains("F")){
                            if(t.getTicketLeft()>0)
                            t.showAvailableSeats("firstf_seats\n");
                            else{ 
                                System.out.println("Sorry no Available seats available");
                                continue;
                            }
                            System.out.println("\nSelect number of seats you want to select");
                            Integer num = sc.nextInt();
                            t.buyTicket(num, "firstf_seats"); // how we will define customer id???
                        }
                        else if(floor.contains("V")){
                            if(t.getTicketLeft()>0)
                            t.showAvailableSeats("vipf_seats");
                            else{ 
                                System.out.println("Sorry no Available seats available");
                                continue;
                            }
                            System.out.println("\nSelect number of seats you want to select");
                            Integer num = sc.nextInt();
                            t.buyTicket(num, "vipf_seats"); // how we will define customer id???
                        }
                        else{
                            System.out.println("Please enter only v or f or g only");
                        }
                    }
                } 
            }    
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void printData(ResultSet rs) throws Exception{   // must declare our own exception class here
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()){
            System.out.print("| ");
            for(int i=1; i<=rsmd.getColumnCount(); i++){
                System.out.print(rs.getObject(i)+ " | "); // now it doesn't matter what column datatype is selected.
            }
            System.out.println("\n.............................................................................\n");
        }
    }


}
