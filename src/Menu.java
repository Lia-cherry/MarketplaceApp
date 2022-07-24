import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Menu {
    public static void printMenu(String[] options){
        for (String option: options) {
            System.out.println(option);
        }
        System.out.println("Choose your option: ");
    }

    public Menu(){
        scanOption();
    }

    private String[] createMenu(){
        return new String[]{
                "1. Display list of all users",
                        "2. Display list of all products",
                        "3. Buy product",
                        "4. Display list of user products by user id",
                        "5. Display list of users that bought product by product id",

                "6. Add new user",
                "7. Add new product",
                "8. Exit"
        };
    }

    private void scanOption(){
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        int userIdOption = -1;
        int productIdOption = -1;
        while (option != 4) {
            printMenu(createMenu());
            try{
                option = scanner.nextInt();
                switch (option){
                    case 1:
                        displayList(User.getUsers());;
                        scanOption();
                        break;
                    case 2:
                        displayList(Product.getProducts());
                        scanOption();
                        break;
                    case 3:
                        User.buyProduct();
                        scanOption();
                        break;
                    case 4:
                        System.out.println("Enter UserId to get list of purchased products: \n");
                        userIdOption = scanner.nextInt();
                        User.getUser(userIdOption).getPurchased(userIdOption);
                        scanOption();
                        break;
                    case 5:
                        System.out.println("Enter ProductId: \n");
                        productIdOption = scanner.nextInt();
                        Product.getProduct(productIdOption).getClients();
                        scanOption();
                        break;
                    case 6:
                        System.out.println("Type name of new product:");
                        String name = scanner.nextLine();
                        System.out.println("Type price of new product:");
                        double price = scanner.nextDouble();
                        Product.addProduct(name, price);
                        scanOption();
                        break;
                    case 7:
                        System.out.println("Type first name of new user:");
                        String fn = scanner.nextLine();
                        System.out.println("Type last name of new user:");
                        String ln = scanner.nextLine();
                        System.out.println("Type amount of money of new user:");
                        double money = scanner.nextDouble();
                        User.addUser(fn, ln, money);
                        scanOption();
                        break;
                    case 8:
                        break;
                }

            }catch (Exception e){
                System.out.println("Please enter intager value betweeen 1 and " + createMenu().length);
            };
        }
    }

    public static void displayList(Map<Integer,Object> map){
        System.out.println(
                map.keySet().stream()
                .map(key -> "* " +  map.get(key)) //map(key -> key + "=" +  User.getUsers().get(key)) 0=User{id=0, First Name='Ivan', Last Name='Er', Amount of money=5000}
                .collect(Collectors.joining("\n", "List of users: \n", "\n")
                )
        );
    }

    private static void option2(){

    }
}
