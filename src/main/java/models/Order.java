package models;

import java.util.HashMap;

public class Order {

    private String dni;
    private HashMap<String, Integer> productList;
    private User user;

    public Order(String dni)
    {
        this.productList = new HashMap<>();
        this.dni = dni;

    }

    public String getDni()
    {
        return dni;
    }

    public void setDni(String dni)
    {
        this.dni = dni;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void addLP(int q, String p)
    {
        productList.merge(p, q, Integer::sum);

    }



    public User getUser()
    {
        return user;

    }

    public HashMap<String, Integer> getProductList()
    {
        return productList;
    }

}
