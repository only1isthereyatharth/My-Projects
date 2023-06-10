package Theater.AdminPage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.ResultSetMetaData;

import Theater.ConnectToDB;
import Theater.User_Login.Admin_Login;

public class AccountDAO {
	public static boolean checkCredentitals(String root, String password){
		if(Admin_Login.confirmLogin(root, password))return true;
		return false;
	}
	public static void updateMovieList() {
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("First Login to visit page.");
            System.out.print("Enter you root_email :");
            String root = sc.nextLine();
            System.out.print("Enter your password : ");
            String password = sc.nextLine();

		if(!AccountDAO.checkCredentitals(root, password)) {
			System.out.println("If want to retry login enter 'R' ");
			String s = sc.nextLine().toLowerCase();
			if(s.equals("r"))
			updateMovieList();
			return;
		}
		}
		try (Connection connection = ConnectToDB.connect();
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = statement.executeQuery("select *from theater.movieadmin");
				Scanner sc=new Scanner(System.in);){
			System.out.println("------------------------------------------------------------------");
			System.out.format("| %-2s | %-40s | %-6s | %-5s |","SN","Movie Name","Rating","Price");
			System.out.println();
			while (rs.next()) {
				System.out.println("------------------------------------------------------------------");
				System.out.format("| %-2s | %-40s | %-6s | %-5s |",rs.getInt(1),rs.getString(2),rs.getFloat(3)+"/5","Rs"+rs.getInt(4));
				System.out.println();
			}
			System.out.println("------------------------------------------------------------------");
			
		 	System.out.print("How many total number of  column do you want to Update: ");
	        int z = sc.nextInt();

			for(int i=0;i<z;i++){
				System.out.print("Enter the Serial number of  column do you want to Update: ");
		        int n1 = sc.nextInt(); 
				System.out.print("Enter Movie Name: ");
				sc.nextLine(); // Is their any other way to solve this problem? It's only scanning for interger not any other inputs
				String  a=sc.nextLine();
				System.out.print("Enter Movie Rating: ");
				Float b =sc.nextFloat();
					if(b>5.0f){
						System.out.println("Rating should be between 0 to 5. \n please try again");
						i--; 
						continue;
					} // Want to continue from here onwards.
				System.out.print("Enter Movie Description: ");
				sc.nextLine();
				String  d=sc.nextLine();
				System.out.print("Enter Movie Ticket Price: ");
				Integer c =sc.nextInt();
				System.out.print("Enter Movie Cast: ");
				sc.next();
				String  e=sc.nextLine();
			
				String query = "UPDATE theater.movieadmin SET moviename='"+a+"',rating='"+(b)+"',ticketprice='"+(c)+"',description='"+d+"',cast='"+e+"'  WHERE SN='"+n1+"'";
					//count will give you how many records got updated
				int count = statement.executeUpdate(query);
				System.out.println(count + " records updated.\n");

				/***this method should be available to customer only***/

				System.out.print("Enter the Serial Number of movie Which you want to select: ");
				int z1 = sc.nextInt(); 
				printAbsolute(rs,z1);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void printData(ResultSet rs) throws Exception{   // must declare our own exception class here
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()){
            for(int i=1; i<=rsmd.getColumnCount(); i++){
                System.out.print(rs.getObject(i)); // now it doesn't matter what column datatype is selected.
            }
            System.out.println();
        }
    }

	public static void printAbsolute(ResultSet rs, int row) throws SQLException{
		if(rs.absolute(row)){
			System.out.print("Movie Name: "+rs.getString(2)+"\n");
		    System.out.print("Rating: "+rs.getFloat(3)+"\n");
			System.out.print("Ticket Price "+rs.getInt(4)+"\n");
			System.out.print("Description: "+rs.getString(5));
			System.out.print("Cast: "+rs.getString(6));
		}
		
	}
}
