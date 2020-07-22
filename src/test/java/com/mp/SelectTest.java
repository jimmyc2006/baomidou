package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
public class SelectTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void select() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.likeRight("name", "王").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
    List<User> userList = userMapper.selectList(queryWrapper);
    userList.forEach(System.out::println);
  }

  // 创建日期为2019年2月14日并且直属上级为名字为王姓
  // date_format(create_time, '%Y-%m-%d') and manager_id in(select id from user where name like '王%')
  @Test
  public void select2() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.apply("date_format(create_time, '%Y-%m-%d')={0}", "2019-02-14")
            .inSql("manager_id", "select id from t_user where name like '王%'");
    List<User> userList = userMapper.selectList(queryWrapper);
    userList.forEach(System.out::println);
  }

  /**
   * name like '王%' and (age < 40 or email is not null)
   */
  @Test
  public void select3() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));
    List<User> userList = userMapper.selectList(queryWrapper);
    userList.forEach(System.out::println);
  }

  /**
   * (age < 40 or email is not null ) and name like '王%'
   */
  @Test
  public void select4() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.nested(qw -> qw.lt("age", 40).or().isNotNull("email")).likeRight("name", "王%");
    List<User> userList = userMapper.selectList(queryWrapper);
    userList.forEach(System.out::println);
  }

  /**
   * 只查询某些字段
   */
  @Test
  public void select5() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("id", "name");
    queryWrapper.nested(qw -> qw.lt("age", 40).or().isNotNull("email")).likeRight("name", "王%");
    List<User> userList = userMapper.selectList(queryWrapper);
    userList.forEach(System.out::println);
  }

  /**
   * 只排除查询某些字段
   */
  @Test
  public void select6() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.nested(qw -> qw.lt("age", 40).or().isNotNull("email")).likeRight("name", "王%")
      .select(User.class, info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id"));
    List<User> userList = userMapper.selectList(queryWrapper);
    userList.forEach(System.out::println);
  }

  // 条件condition
  @Test
  public void selectCondition() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    String name = "王";
    String email ="";
    queryWrapper.like(StringUtils.isNotEmpty(name), "name", name)
            .like(StringUtils.isNotEmpty(email), "email", email);
    List<User> userList = userMapper.selectList(queryWrapper);
    userList.forEach(System.out::println);
  }
}
