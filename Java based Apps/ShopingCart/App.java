package ShopingCart;

import java.util.Scanner;

public class App {
    Cart cart = new Cart();
    private int ch = 0;

    public App(){
        menu();
    }

    public int getUserInput(){
        try (Scanner scanner = new Scanner(System.in)){
            ch = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println();
        }
        return ch;
    }

    private final void menu() {
        do{
            selectAction();
            getUserInput();

            switch(ch){
                case 1:
                
            }
        }
        while(ch!=0);
    }

    public void selectAction(){
        System.out.println("1. Display Store Items");
        System.out.println("2. Display Cart");
        System.out.println("0. Exit");
    }

    public void storeItemMenu(){
        System.out.println("1. Add to cart");
        System.out.println("2. Remove from cart");
        System.out.println("0. Exit");
    }
}
