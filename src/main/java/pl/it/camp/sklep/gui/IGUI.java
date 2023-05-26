package pl.it.camp.sklep.gui;

import pl.it.camp.sklep.db.IProductRepository;
import pl.it.camp.sklep.db.IUserRepository;
import pl.it.camp.sklep.model.product.Product;
import pl.it.camp.sklep.model.user.User;


import java.util.List;


public interface IGUI {
    String printInitialMenu();

    String printInternalMenu(User user);

    void printProductList(List<Product> products, User user);

    void buyProduct(IProductRepository products, String code, int quantity);

    String readProductCode();

    void supplyProduct(IProductRepository products, String code, int quantity);

    void printUserList(IUserRepository users);

    void changeFunction();

    User readLoginAndPassword();

    String readLogin();

    String readPassword();

    String readFunction();

    int readProductQuantity();
}
