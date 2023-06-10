package Tic_Tac_Game;

import java.util.Scanner;
public class TicTacToeApp {
    // private static int i=0;
    // private static int j=0;
    private static String graph[][];
    // private static Scanner sc = new Scanner(System.in);
    
    public static void play(){
        try(Scanner sc = new Scanner(System.in)){
            System.out.println("Enter PLayer1 Name");
            String Player1 = sc.next();
            System.out.println("Enter PLayer2 Name");
            String Player2 = sc.next();
            while(true){
                graph = new String[3][3];
                System.out.println("For starting Tic-Toe Game enter 'S'");
                String line = sc.next();
                if(line.toLowerCase().equals("s")){

                    int k=1;
                    for(int i=0; i<graph.length; i++){
                        for(int j=0; j<graph.length; j++){
                            graph[i][j] = String.valueOf(k++);
                        }
                    }

                    int i=1;
                    String player1 = "X";
                    String player2 = "O";
                    printGraph();
                    while(i<10){
                            if(i%2!=0) System.out.println(Player1.toUpperCase()+" chance with X");
                            else System.out.println(Player2.toUpperCase()+" chance with O");
                            String num = sc.next();
                            int pairs[];
                            try{
                                pairs = rowAndcol(Integer.parseInt(num));
                                if(pairs==null)continue;
                            }
                            catch(Exception e){
                                System.out.println("Please enter a number between 1-9 and try again.");
                                continue;
                            }
            
                            int row =pairs[0];
                            int col =pairs[1];
 
                            String player;
                            if(i%2!=0) player = player1;
                            else player = player2;

                            if(graph[row][col].equals(player1) || graph[row][col].equals(player2)){
                                System.out.println();
                                System.out.println("This is space already allocated please check graph");
                                printGraph();
                                i--;
                            }
                            else{    
                                graph[row][col] = player;
                                printGraph();
                                System.out.println();
                                if(i>4){
                                    if(check()){
                                        System.out.println("Won!!!!");
                                        break;
                                    }
                                }
                                if(i>7){
                                    System.out.println("If want a rematch enter 'R'");
                                    System.out.println("Or want to end match enter 'E'");
                                    String rematch = sc.next();

                                    if(rematch.toLowerCase().equals("r")){
                                        break;
                                    }
                                    else return;
                                }
                            }
                            i++;
                    }
                    if(i==9) System.out.println("It's a draw.");
                    System.out.println("Game Over!");
                    if(i<7)
                    System.out.println("To Exit enter 'E'");
                }
                else break;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    private static boolean check() {

        if(graph[0][0].equals(graph[1][1]) && graph[1][1].equals(graph[2][2]) &&  !graph[2][2].equals("1")){
            // int o= (graph[0][0].equals("X") ? i++ : j++);
            return true;}
        if(graph[0][2].equals(graph[1][1]) && graph[1][1].equals(graph[2][0]) &&  !graph[1][1].equals("1")){return true;}
        if(graph[0][0].equals(graph[0][1]) && graph[0][1].equals(graph[0][2]) &&  !graph[0][0].equals("1")){return true;}
        
        if(graph[1][0].equals(graph[1][1]) && graph[1][1].equals(graph[1][2]) &&  !graph[1][0].equals("1")){return true;}
        if(graph[2][0].equals(graph[2][1]) && graph[2][1].equals(graph[2][2]) &&  !graph[2][0].equals("1")){return true;}

        if(graph[0][0].equals(graph[1][0]) && graph[0][0].equals(graph[2][0]) &&  !graph[0][0].equals("1")){return true;}
        if(graph[0][2].equals(graph[2][2]) && graph[1][2].equals(graph[2][2]) &&  !graph[0][2].equals("1")){return true;}
        if(graph[0][1].equals(graph[1][1]) && graph[2][1].equals(graph[0][1]) &&  !graph[0][1].equals("1")){return true;}

        return false;
    }

    private static int[] rowAndcol(int a){
        int []res = new int[2];
        switch(a){
            case 1: res[0]=0; res[1]=0; break;
            case 2: res[0]=0; res[1]=1; break;
            case 3: res[0]=0; res[1]=2; break;
            case 4: res[0]=1; res[1]=0; break;
            case 5: res[0]=1; res[1]=1; break;
            case 6: res[0]=1; res[1]=2; break;
            case 7: res[0]=2; res[1]=0; break;
            case 8: res[0]=2; res[1]=1; break;
            case 9: res[0]=2; res[1]=2; break;
            default: System.out.println("Enter value between 1-9"); return null;
        }
        return res;
    }
    
    // @SuppressWarnings("unused")
    // private static String[][] assign(String[][] graph,int row,int col,String player){
    //     if(graph[row][col]!=null){
    //         System.out.println("This is space allocated please check graph");
    //         return graph;
    //     }
    //     graph[row][col] = player;
    //     return graph;
    // }


    public static void printGraph(){
        System.out.println("|-----------|");
        System.out.println("| "+graph[0][0]+" | "+graph[0][1]+" | "+graph[0][2]+" |");
        System.out.println("|-----------|");
        System.out.println("| "+graph[1][0]+" | "+graph[1][1]+" | "+graph[1][2]+" |");
        System.out.println("|-----------|");
        System.out.println("| "+graph[2][0]+" | "+graph[2][1]+" | "+graph[2][2]+" |");
        System.out.println("|-----------|");
    }
    public static void main(String[] args) {
        play();
    }


}
