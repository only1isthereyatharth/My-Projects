package ShopingCart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<Product> cartItems = new ArrayList<>();

    
    private Product getProductById(int pid){
        Product product = null;
        List<Product> items = new Items().getItems();
        for(Product p : items){
            if(p.getPid()==pid){
                return p;
            }
        }
        return product;
    }
    
    public void addToCart(Product product){
        cartItems.add(product);
    }
    
    public void addProductInCart(int pid){
        Product product = getProductById(pid);
        addToCart(product);
    }

    public void removeProductById(int pid){
        Product product = getProductById(pid);
        cartItems.remove(product);
    }

    public void printCartItems(){
        for(Product p: cartItems){ 
            System.out.print(p.getName()+" ");
        }
    }
    
}
