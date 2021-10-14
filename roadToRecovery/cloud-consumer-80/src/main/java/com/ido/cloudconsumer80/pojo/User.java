package com.ido.cloudconsumer80.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("userjwt")
public class User {
    @TableField(value = "username")
    private String username;

    @TableField(value = "pwd")
    private String pwd;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @TableField(fill = FieldFill.INSERT)
    private Integer id;
}
