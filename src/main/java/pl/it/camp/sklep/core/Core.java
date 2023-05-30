package pl.it.camp.sklep.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.it.camp.sklep.db.*;
import pl.it.camp.sklep.gui.IGUI;
import pl.it.camp.sklep.model.user.User;

import java.io.IOException;

@Component
public class Core implements ICore {
    @Autowired
    private IGUI gui;
    @Autowired
    private IAuthenticator authenticator;
    @Autowired
    private IProductRepository products;
    @Autowired
    private IUserRepository users;
    @Autowired
    private IFileLoader fileLoader;

    @Override
    public void start() {
        try {
            fileLoader.readDataFromFile();
        } catch (IOException e) {
            System.out.println("BŁĄD podczas odczytu bazy danych");
            return;
        }
        programLoop:
        while (true) {
            switch (gui.printInitialMenu()) {
                case "1":
                    User loggedUser = authenticator.logIn();
                    if (loggedUser == null) break programLoop;
                    loggedUserLoop:
                    while (true) {
                        switch (gui.printInternalMenu(loggedUser)) {
                            case "1":
                                gui.printProductList(loggedUser);
                                break;
                            case "2":
                                gui.buyProduct(products, gui.readProductCode(), gui.readProductQuantity());
                                break;
                            case "3":
                                break loggedUserLoop;
                            case "4":
                                if (loggedUser.getFunction().equals("Administrator")) {
                                    gui.supplyProduct(gui.readProductCode(), gui.readProductQuantity());
                                }
                                break;
                            case "5":
                                if (loggedUser.getFunction().equals("Administrator")) {
                                    gui.changeFunction();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case "2":
                    authenticator.signIn();
                    break;
                case "3":
                    try {
                        fileLoader.saveDataToFile();
                    } catch (IOException e) {
                        System.out.println("BŁĄD podczas zapisu bazy danych");
                    }
                    break programLoop;
                default:
                    break;
            }
        }
    }
}
