package com.example.handler.returnvaluedemo.controller;

import com.example.handler.returnvaluedemo.annotation.ResponseJson;
import com.example.handler.returnvaluedemo.pojo.Teacher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.zip.ZipException;

/**
 * @创建人 liuzhihui
 * @创建时间 2019/11/26
 * @描述
 */
@RestController
public class HandlerController {
    @ResponseJson
    @GetMapping("/test")
    public Teacher test(@RequestParam(value="name") String name){
    // 处理逻辑
    Teacher student  = new Teacher();
    student.setName(name);
    return student;
    }
}
