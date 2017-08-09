package com.jy;

import javax.persistence.*;

@Table(name = "test_girl")
@Entity
public class Girl {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "cup_Size")
    private String cupSize;

    @Column(name = "age")
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
