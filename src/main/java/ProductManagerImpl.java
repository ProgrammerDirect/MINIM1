import models.*;

import java.util.*;

public class ProductManagerImpl implements ProductManager {

    private static ProductManager instance;

    private List<Product> productList;
    private Queue<Order> orderQueue;
    private HashMap<String, User> users;



    private ProductManagerImpl()
    {
        productList = new ArrayList<>();
        orderQueue = new LinkedList<>();
        users = new HashMap<>();
    }

    public static ProductManager getInstance() //Implementació Singleton
    {
        if (instance == null) instance = new ProductManagerImpl();
        return instance;
    }

    @Override
    public void clear() {
        this.productList.clear();
        this.orderQueue.clear();
        this.users.clear();
    }


    @Override
    public void addProduct(String id, String name, double price)
    {

        productList.add(new Product(id, name, price));
    }

    @Override
    public List<Product> getProductsByPrice()
    {

        productList.sort(Comparator.comparing(Product::getPrice).reversed());

        return productList;
    }

    @Override
    public List<Product> getProductsBySales()
    {
        productList.sort(Comparator.comparing(Product::getSales).reversed());

        return  productList;

    }

    @Override
    public void addOrder(Order order)
    {
        orderQueue.add(order);

    }

    @Override
    public int numOrders()
    {
        if(orderQueue.isEmpty())
            return 0;

        return orderQueue.size();

    }

    @Override
    public int numUsers()
    {
        if (users.isEmpty())
            return 0;
        else
            return users.size();
    }

    @Override
    public Order deliverOrder() throws UserNotFoundException, EmptyQueueException {

        Order order = orderQueue.poll();

        if(order != null)
        {
           User user = users.get(order.getDni());

           if(user != null)
           {
               order.setUser(user);
               user.addOrder(order);

               HashMap<String, Integer> listaProductosMap = order.getProductList();

               for (String producto : listaProductosMap.keySet())
               {
                   int cantidad =  listaProductosMap.get(producto);

                   Product product = getProduct(producto);

                   product.setSales(product.getSales() + cantidad);
               }
           }

           else
           {
               throw  new UserNotFoundException("User not found");
           }
        return order;

        }

        else
        {
            throw  new EmptyQueueException("Empty queue");
        }


    }


    public Product getProduct(String id) {
        /*
        for (Product product : productsList) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
        */

        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);


    }

    @Override
    public User getUser(String number)
    {
        if (users.containsKey(number))
        {
            return users.get(number);
        }

        return null;
    }

    @Override
    public int numProducts() {
        return productList.size();
    }

    @Override
    public void addUser(String nif, String name, String surname, String mail)
    {
        users.put(nif, new User(nif, name, surname, mail));

    }


}
