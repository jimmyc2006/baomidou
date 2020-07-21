package com.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;

@Data
@TableName("t_user")
public class User {
  private Long id;
  private String name;
  private Integer age;
  private String email;
  private Long managerId;
  private LocalDateTime createTime;
}
