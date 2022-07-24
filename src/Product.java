import java.util.*;

public class Product {
    private int id;
    private String name;
    private double price;
    private static final Map<Integer,Object> products = new HashMap<>();
    private final List<User> clients = new ArrayList<>();


    public Product(String name, double price) {
        this.id = nextID(products);
        this.name = name;
        this.price = price;
        products.put(id, Product.this);
    }

    public Integer nextID(Map<Integer, Object> map){
        return map.keySet().stream()
                .mapToInt(n->n).max().orElse(-1) +1;
    }

    public static Map<Integer, Object> getProducts() {
        return products;
    }

    public static void setClients(int prodId, int userId){
      Product.getProduct(prodId).clients.add(User.getUser(userId));
 }
    public void getClients() {

        if (this != null)
            System.out.println(this.clients.toString());
        else System.out.println( "No user with such id");
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", Price=" + price +
                '}';
    }

    public double getPrice() {
        return this.price;
    }

    public static Product getProduct(int id){
        return (Product)products.get(id);
    }

    public static String addProduct(String name,double price){
        return new Product(name,price).toString();
    }
}
