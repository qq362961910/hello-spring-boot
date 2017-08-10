package com.jy.entity;

import com.jy.controller.valid.anno.Password;
import com.jy.controller.valid.group.Insert;
import com.jy.controller.valid.group.Update;
import com.jy.constants.ErrorCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "test_girl")
@Entity
public class Girl {

    @NotNull(message = ErrorCode.PRIMARY_KEY_NULL,groups = {Update.class})
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = ErrorCode.CUP_SIZE_NULL, groups = {Insert.class, Update.class})
    @Column(name = "cup_Size")
    private String cupSize;

    @NotNull(message = ErrorCode.AGE_NULL, groups = {Insert.class, Update.class})
    @Column(name = "age")
    private Integer age;

    @Password(message = ErrorCode.PASSWORD_PATTERN, groups = {Insert.class, Update.class})
    @Column(name = "password")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
