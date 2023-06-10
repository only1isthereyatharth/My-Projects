package ShopingCart;

import java.util.Objects;

class Product {
    private Integer pid;
    private String name;
    private Double price;
    private Integer stock;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Product(){}

    public Product (Integer pid, String name, Double price, Integer stock){
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23*hash + Objects.hashCode(this.pid);
        hash = 23*hash + Objects.hashCode(this.name);
        hash = 23*hash + Objects.hashCode(this.price);
        hash = 23*hash + Objects.hashCode(this.stock);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)return true;

        if(obj==null)return false;

        if(getClass()!=obj.getClass())return false;

        final Product other = (Product)obj;

        if(!Objects.equals(this.name, other.name))return false;

        if(!Objects.equals(this.price, other.price))return false;

        if(!Objects.equals(this.pid, other.pid))return false;

        if(!Objects.equals(this.stock, other.stock))return false;

        return true;
    }

    
}
