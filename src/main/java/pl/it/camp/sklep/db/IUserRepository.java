package pl.it.camp.sklep.db;

import pl.it.camp.sklep.model.user.User;

import java.util.Map;

public interface IUserRepository {
    boolean addUser(String function, String login, String password);

    void writeUserToDb(String[] vars);

    boolean changeUserFunction(String login, String function);

    User findUserByLogin(String login);

    Map<String, User> getUsers();
}
