package spring.intro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import spring.intro.config.AppConfig;
import spring.intro.dao.UserDao;
import spring.intro.model.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    /*private AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext(AppConfig.class);
    private UserDao userDao = context.getBean(UserDao.class);*/
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.getAll();
    }
}
