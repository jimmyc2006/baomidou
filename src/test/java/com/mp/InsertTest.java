package com.mp;

import com.mp.dao.UserMapper;
import com.mp.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void insert() {
    User user = new User();
    user.setName("向前");
    user.setAge(26);
    user.setManagerId(1088248166370832385L);
    user.setCreateTime(LocalDateTime.now());
    user.setRemark("remamamam");
    System.out.println(user);
    this.userMapper.insert(user);
    System.out.print(user);
  }
}
