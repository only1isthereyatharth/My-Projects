package Calculator;
import java.util.Scanner;

class App{

    public static void start(){
        try (Scanner sc = new Scanner(System.in)){
            System.err.println("Enter values");
            String value = new String(); // To remember previous calculated value.
            while(true){
                
                String line = sc.nextLine();
                if(line.equals("e") || line.equals("E")) {   // Check input line for terminating program
                    System.out.println("Calculating terminated");
                    break;
                }
                if(line.equals("n") || line.equals("N")){   // Check input line for continuing with new calculation
                    value="";
                    continue;
                }

                if(line.equals("c") || line.equals("C")){   // Check input line in continuation of previous calculation.
                    System.out.print(value + "");
                    continue;           
                }

                line = value+line;

                String str[] = line.split("[-*+/%]+");  /* very very important note!!
                                            "-" should be first character to removed because it's used for ranging */
                String ch = line.replaceAll("[.0-9]+", ""); // placing "." because of double number error
                try {
                    System.out.println(Calculate(str[0],ch,str[1]));  // will be divided into 3 parts 1st and 3rd will be 
                                                                      // numbers and 2nd part will be operator.
                } catch (Exception e) {
                    System.out.println("Please enter a valid operation String");
                    continue;
                }
               
                value = String.valueOf(Calculate(str[0], ch, str[1])); // for storing previously calculated value for 
                                                                       //  performing new calculations on it.

                System.out.println("To start new calculation press 'N' or 'n' else press 'c' or 'c' ");
                System.out.println("To stop the application enter 'E' or 'e' ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static double Calculate(String a1, String c, String b1) {
        a1 = a1.replace(" ", ""); // so to remove any leading spaces that user put it in line string
        b1 = b1.replace(" ", "");
        c = c.replace(" ", "");
        try{
            double a = Double.parseDouble(a1);
            // System.out.println(a);
            double b = Double.parseDouble(b1);
            // System.out.println(b);
            switch(c.charAt(0)){
                case '*': return a*b;
                case '+': return a+b;
                case '-': return a-b;
                case '/': return a/b;
                case '%': return a%b;
            }
        }
        catch(Exception E){
            System.out.println("Error! must input only integer or double values");
        }
        // if(c.equals("+")) return a+b;
        // else if(c.equals("-")) return a-b;
        // else if(c.equals("*")) return a*b;
        // else if(c.equals("/")) return a/b;
        // else if(c.equals("%")) return a%b;


        System.out.println("Error! cannot procces with Infinity or please try again with proper values");
        return -1/12;
    }
    public static void main(String[] args) {
        start();
    }
}