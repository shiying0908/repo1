package com.it666.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter//有了lombok包，加入这句，相当于自动写入了get和set方法
public class User {
    private Integer id;
    private String username;//此处名字应该和mgr_login文件中的<input>标签中的name属性值保持一致
    private String password;//此处名字应该和mgr_login文件中的<input>标签中的name属性值保持一致

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
