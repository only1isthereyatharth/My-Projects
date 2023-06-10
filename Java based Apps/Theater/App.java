package Theater;

import java.util.Scanner;

import Theater.AdminPage.AccountDAO;
import Theater.CustomerPage.MovieTickectApp;
import Theater.User_Login.Admin_Login;
import Theater.User_Login.Customer_Login;

@SuppressWarnings("unused")
public class App {

    public static void main(String[] args) throws Exception{
        System.out.println("..................Movie Ticket App..................");
        try(Scanner sc = new Scanner(System.in)){
            System.out.println("Admin press 1 else for customer anything");
            int decide = sc.nextInt();
            if(decide==1){
                AccountDAO.updateMovieList();
            }
            else {
                MovieTickectApp.bookTicket();
            }
        }
            // System.out.println(Customer_Login.getLoginInfo("yatharthchutku@gmail.com", "w569322@g"));
            // System.out.println(Customer_Login.getCusID("yatharthchutku@gmail.com", "w569322@g"));
            // System.out.println(Customer_Login.getLoginInfo("lashkarimeyjind@gmail.com", "12334"));
            // System.out.println(Customer_Login.createAccount());
    }
}
