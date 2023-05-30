package pl.it.camp.sklep.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.it.camp.sklep.db.IProductRepository;
import pl.it.camp.sklep.db.IUserRepository;
import pl.it.camp.sklep.model.product.Product;
import pl.it.camp.sklep.model.user.User;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

@Component
public class GUI implements IGUI {
    private static final Scanner scanner = new Scanner(System.in);
    @Autowired
    private IProductRepository products;
    @Autowired
    private IUserRepository users;

    @Override
    public String printInitialMenu() {
        System.out.println("""
                1. Zaloguj
                2. Zarejestruj
                3. Wyjście""");
        return scanner.nextLine();
    }

    @Override
    public String printInternalMenu(User user) {
        System.out.println("""
                1. Wyświetl listę produktów
                2. Kup produkt
                3. Wyloguj""");
        if (user.getFunction().equals("Administrator")) {
            System.out.println("""
                    4. Uzupełnij produkt
                    5. Zmień rolę""");
        }
        return scanner.nextLine();
    }

    @Override
    public void printProductList(User user) {
        System.out.println("-".repeat(12));
        boolean isEmpty = true;
        for (Product element : products.getProducts()) {
            if (user.getFunction().equals("Administrator")) {
                System.out.println("| " + element);
            } else if (element.getQuantity() > 0) {
                System.out.println("| " + element);
                isEmpty = false;
            }
        }
        if (user.getFunction().equals("Customer") && isEmpty) {
            System.out.println("Brak produktów do wyświetlenia");
        }
        System.out.println("-".repeat(12));
    }

    @Override
    public void buyProduct(IProductRepository products, String code, int quantity) {
        System.out.println("-".repeat(8));
        if (products.buyProduct(code, quantity)) {
            Product product = products.findProductByCode(code);
            StringBuilder sb = new StringBuilder()
                    .append("Kupiono:\n")
                    .append(product.getType())
                    .append(" | ")
                    .append(product.getName())
                    .append(" | Ilość: ")
                    .append(quantity)
                    .append("\nWartość: ")
                    .append(product.getPrice() * quantity);
            System.out.println(sb);
        } else {
            System.out.println("Produkt niedostępny lub brak wystarczającej ilości produktu");
        }
        System.out.println("-".repeat(8));
    }

    @Override
    public String readProductCode() {
        System.out.println("Podaj kod produktu: ");
        return scanner.nextLine();
    }

    @Override
    public void supplyProduct(String code, int quantity) {
        System.out.println("-".repeat(7));
        if (products.supplyProduct(code, quantity)) {
            Product product = products.findProductByCode(code);
            StringBuilder sb = new StringBuilder()
                    .append("Dodano:\n")
                    .append(product.getName())
                    .append(" | Ilość: ")
                    .append(quantity);
            System.out.println(sb);
        } else {
            System.out.println("Produkt niedostępny lub wprowadzono niewłaściwą ilość produktu");
        }
        System.out.println("-".repeat(7));
    }

    @Override
    public void printUserList() {
        System.out.println("-".repeat(8));
        for (Map.Entry<String, User> entry : users.getUsers().entrySet()) {
            System.out.println(entry.getValue().getFunction() + " | " + entry.getValue().getLogin());
        }
        System.out.println("-".repeat(8));
    }

    @Override
    public void changeFunction() {
        printUserList();
        String login = readLogin();
        if (users.findUserByLogin(login) == null) {
            System.out.println("Błędna nazwa użytkownika");
            return;
        }

        String function = readFunction();
        if (users.changeUserFunction(login, function)) {
            StringBuilder sb = new StringBuilder()
                    .append("Zmieniono funkcję:\n")
                    .append(login)
                    .append(" --> ")
                    .append(function);
            System.out.println(sb);
        } else {
            System.out.println("Błąd lub nieuprawniona zmiana funkcji");
        }
    }

    @Override
    public User readLoginAndPassword() {
        return new User(readLogin(), readPassword());
    }

    @Override
    public String readLogin() {
        System.out.println("Login:");
        return scanner.nextLine();
    }

    @Override
    public String readPassword() {
        System.out.println("Hasło:");
        return scanner.nextLine();
    }

    @Override
    public String readFunction() {
        System.out.println("Nowa funkcja [Administrator/Customer]:");
        while (true) {
            switch (scanner.nextLine().toUpperCase()) {
                case "A":
                case "ADMIN":
                case "ADMINISTRATOR":
                    return "Administrator";
                case "C":
                case "CUST":
                case "CUSTOMER":
                    return "Customer";
                default:
                    System.out.println("Błędna nazwa funkcji");
                    break;
            }
        }
    }

    @Override
    public int readProductQuantity() {
        System.out.println("Podaj ilość: ");
        int quantity;

        while (true) {
            try {
                quantity = scanner.nextInt();
                scanner.nextLine();
                if (quantity <= 0) {
                    System.out.println("Podaj liczbę większą niż 0:");
                    continue;
                }
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Podaj liczbę większą niż 0:");
                scanner.nextLine();
            }
        }
        return quantity;
    }
}
