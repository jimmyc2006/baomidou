package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class SelectPageTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void select() {
    Page<User> userPage = new Page<>(1, 2);
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.ge("age", 25).orderByDesc("age");
    IPage<User> userIPage = userMapper.selectPage(userPage, queryWrapper);
    System.out.println("总页数:" + userIPage.getPages());
    System.out.println("总记录数:" + userIPage.getTotal());
    userIPage.getRecords().forEach(t -> System.out.println(t));
  }
}