package pl.it.camp.sklep.gui;

import pl.it.camp.sklep.db.IProductRepository;
import pl.it.camp.sklep.model.user.User;

public interface IGUI {
    String printInitialMenu();

    String printInternalMenu(User user);

    void printProductList(User user);

    void buyProduct(IProductRepository products, String code, int quantity);

    String readProductCode();

    void supplyProduct(String code, int quantity);

    void printUserList();

    void changeFunction();

    User readLoginAndPassword();

    String readLogin();

    String readPassword();

    String readFunction();

    int readProductQuantity();
}
