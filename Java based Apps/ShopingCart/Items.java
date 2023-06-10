package ShopingCart;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private final List<Product> items  = new ArrayList<>();

    public Items(){
        this.initStoreItems();
    }

    public List<Product> getItems(){return items;}

    private void initStoreItems() {
        String[] itemNames = {"LUX soap","Fair n Lovely", "MTR"};
        double[] itemPrice = {40.00d, 60.00d, 30.00d};
        Integer[] stock = {10,6,10};

        for(int i=0; i<itemNames.length; i++){
            this.items.add(new Product(i+i, itemNames[i], itemPrice[i], stock[i]));
        }

    }
}
