package com.neo;

import com.neo.domain.User;
import com.neo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userDao;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setId(6l);
        user.setUserName("小华");
        user.setPassWord("password123456");
        userDao.saveUser(user);
    }

    @Test
    public void findUserByUserName() {
        User user = userDao.findUserByUserName("小明");
        System.out.println("user is " + user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("修改id为2l的数据行！");
        userDao.updateUser(user);
    }

    @Test
    public void deleteUserById() {
        userDao.deleteUserById(6l);
    }
}
