package pl.it.camp.sklep.core;

import pl.it.camp.sklep.model.user.User;

public interface IAuthenticator {
    User logIn();

    void signIn();
}
