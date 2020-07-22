package com.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;

@Data
@TableName("t_user")
public class User {
  @TableId
  private Long id;
  private String name;
  private Integer age;
  private String email;
  private Long managerId;
  private LocalDateTime createTime;
  @TableField(exist = false)
  private String remark;
}
