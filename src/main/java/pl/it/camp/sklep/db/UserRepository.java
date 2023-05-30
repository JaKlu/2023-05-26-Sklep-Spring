package pl.it.camp.sklep.db;

import org.springframework.stereotype.Component;
import pl.it.camp.sklep.model.user.User;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository implements IUserRepository {

    private final Map<String, User> users = new HashMap<>();

/*    public UserRepository() {
        this.users.put("admin", new Admin("admin", "6bdbb8e3b90a36d5b3245d2d9a1b4a9f"));        //pass: 12345
        this.users.put("Kuba", new Admin("Kuba", "1f56201436ad0377399bdfe0ee2b1279"));          //pass: 456
        this.users.put("Tomek", new Customer("Tomek", "cf9fac80c86b9714c697d4204829e9af"));     //pass: 23456
        this.users.put("Olek", new Customer("Olek", "1e7cc5f664c541e5a4ee57eab6b73abb"));       //pass: 34567
        this.users.put("Wojtek", new Customer("Wojtek", "44cf3a7e1def7261e426cbda3f936ec4"));   //pass: 45678
    }*/
    @Override
    public boolean addUser(String function, String login, String password) {
        User user = findUserByLogin(login);
        if (user == null) {
            this.users.put(login, new User(function, login, password));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void writeUserToDb(String[] vars) {
        this.users.put(vars[1], new User(vars[0], vars[1], vars[2]));
    }

    @Override
    public boolean changeUserFunction(String login, String function) {
        User user = findUserByLogin(login);
        if (user == null || user == this.users.get("admin")) {  // deleting first default User - >admin< account - is prohibited
            return false;
        }
        user.setFunction(function);
        return true;
    }

    @Override
    public User findUserByLogin(String login) {
        return users.get(login);
    }

    @Override
    public Map<String, User> getUsers() {
        return users;
    }
}
