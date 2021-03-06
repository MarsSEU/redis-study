package com.mars.redis.controller;

import com.mars.redis.model.Student;
import com.mars.redis.service.RedisCacheService;
import com.mars.redis.service.StuInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mars on 16-9-28.
 */
@Controller
@RequestMapping(value = "/stu")
public class StuSvcController {
    private static final Logger  LOGGER = LoggerFactory.getLogger(StuSvcController.class);

    @Autowired
    private StuInfoService stuInfoService;

    @Autowired
    private RedisCacheService<Student> redisCacheService;

    @RequestMapping(value = "/get", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> queryStus(@RequestParam(value = "id", required = false) Integer id){
        List<Student> studentList = new ArrayList<>();
        if (id != null){
            Student stu = redisCacheService.getCacheObject(String.valueOf(id));
            if ( stu != null)
                studentList.add(stu);
            else {
                studentList = stuInfoService.getStuById(id);
                redisCacheService.setCacheObject(String.valueOf(id), studentList.get(0));
            }
        } else {
            studentList = stuInfoService.getStuById(id);
        }
        return studentList;
    }

    @RequestMapping(value = "/testadd", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public Integer testAdd(@RequestParam(value = "num", required = false) Integer num){
        return stuInfoService.testAdd(num);
    }



}
