package com.example.handler.returnvaluedemo.pojo;

import lombok.Data;

@Data
public class Teacher {
    private Integer id;
    private Integer age;
    private String name;

    public Teacher(){}

    public Teacher(Integer id, Integer age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}