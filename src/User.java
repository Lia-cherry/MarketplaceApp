import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private  double moneyAmount; //BigDecimal
    private final List<Product> purchased;

    private static final Map<Integer, Object> users = new HashMap<>();

    public User(String firstName, String lastName, double moneyAmount) {
        this.id = nextID(users);
        this.firstName = firstName;
        this.lastName = lastName;
        this.moneyAmount = moneyAmount;
        this.purchased = new ArrayList<>();
        users.put(id, User.this);
    }

    public static User getUser(int id){
        return (User)users.get(id);
    }

    public void getPurchased(int id) {
        int identification = id;

        if (User.checkUserExists(identification))
            System.out.println(getUser(identification).purchased.toString());
        else System.out.println( "No user with such id");
    }

    public Integer nextID(Map<Integer, Object> map){
        return map.keySet().stream()
                .mapToInt(n-> n).max().orElse(-1) +1;
    }


    public void setPurchased(Product prod, int prodId, int userId) {
        this.purchased.add(prod);
        Product.setClients(prodId,userId);
    }

    public static Map<Integer,Object> getUsers() {
        return users;
    }

    public static String addUser(String fn, String ln, double money){
        return new User(fn,ln,money).toString();
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", Amount of money=" + moneyAmount +
                '}';
    }

    public double getMoneyAmount() {
        return this.moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public static void buyProduct() throws Exception {

        Scanner scanner = new Scanner(System.in);
        int userIdOption = -1;
        int productIdOption = -1;

        System.out.println("Send your userID: ");
        userIdOption = scanner.nextInt();

        if (User.checkUserExists(userIdOption)) {
            Menu.displayList(Product.getProducts());

            System.out.println("Print the ID of product you want to buy: ");
            productIdOption = scanner.nextInt();

            if (Product.getProducts().get(productIdOption) != null) {
                double mon = User.getUser(userIdOption).getMoneyAmount() - Product.getProduct(productIdOption).getPrice();
                if (mon >= 0) {
                    User.getUser(userIdOption).setPurchased(Product.getProduct(productIdOption), productIdOption, userIdOption);
                    User.getUser(userIdOption).setMoneyAmount(mon);
                    System.out.println("Added successfully!");
                } else {
                    System.out.println("Not enough money");
                    throw new IllegalArgumentException();

                }
            } else {
                throw new IllegalArgumentException("Sorry. No user with this ID exists. Try more.");
            }
            ;

        }
    }

private static boolean checkUserExists(int id){
    return User.getUsers().get(id) != null;
}

}
