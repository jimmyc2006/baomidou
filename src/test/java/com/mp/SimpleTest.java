package com.mp;

import com.mp.dao.UserMapper;
import com.mp.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {
  @Autowired
  private UserMapper userMapper;

  @Test
  public void select() {
    List<User> users = this.userMapper.selectList(null);
    for (User u : users) {
      System.out.println(u);
    }
  }
}
