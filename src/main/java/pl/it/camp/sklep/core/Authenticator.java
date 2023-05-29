package pl.it.camp.sklep.core;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.it.camp.sklep.db.IUserRepository;
import pl.it.camp.sklep.gui.IGUI;
import pl.it.camp.sklep.model.user.User;

@Component
public class Authenticator implements IAuthenticator {
    @Autowired
    private IUserRepository users;
    @Autowired
    private IGUI gui;
    private static final String seed = "&OY74PZHvIm!Y7K*0!jQWjbzjjIiFY0b";

    @Override
    public User logIn() {
        int counter = 0;
        while (counter < 3) {
            User userFromGui = gui.readLoginAndPassword();
            User userFromDb = users.findUserByLogin(userFromGui.getLogin());

            if (userFromDb != null
                    && userFromDb.getPassword().equals(
                    DigestUtils.md5Hex(userFromGui.getPassword() + seed))) {
                System.out.println("-- Zalogowany użytkownik: " + userFromDb.getLogin() + " --");
                return userFromDb;
            }
            System.out.println("Błędny login lub hasło");
            counter++;
        }
        return null;
    }

    @Override
    public void signIn() {
        String login = gui.readLogin();
        if (users.findUserByLogin(login) != null) {
            System.out.println("Użytkownik o podanym loginie już istnieje");
            return;
        }
        if (users.addUser("Customer", login, hashPassword(gui.readPassword()))) {
            System.out.println("Użytkownik \"" + login + "\" został zarejestrowany i może się zalogować.");
        } else {
            System.out.println("Błąd przy rejestracji nowego użytkownika");
        }
    }

    private String hashPassword(String password) {
        return DigestUtils.md5Hex(password + seed);
    }
}
