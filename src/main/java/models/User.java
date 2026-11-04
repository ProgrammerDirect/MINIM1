package models;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class User {

    private  String nif;
    private  String name;
    private String surname;
    private String mail;

    private List<Order> ordersUser;

    public User(String nif, String name, String surname, String mail)
    {
        this.nif = nif;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.ordersUser = new LinkedList<>();

    }

    public String getNif()
    {
        return nif;
    }


    public List<Order> orders()
    {
        return this.ordersUser;
    }

    public int numOrders()
    {
        return ordersUser.size();
    }

    public void addOrder(Order order)
    {
        ordersUser.add(order);
    }




}
