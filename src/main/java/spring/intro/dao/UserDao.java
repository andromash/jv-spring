package spring.intro.dao;

import spring.intro.model.User;
import java.util.List;

public interface UserDao {
    User add(User user);

    List<User> getAll();
}
