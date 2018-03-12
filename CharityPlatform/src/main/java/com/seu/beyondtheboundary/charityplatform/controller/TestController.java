package com.seu.beyondtheboundary.charityplatform.controller;

import com.seu.beyondtheboundary.charityplatform.domain.Project;
import com.seu.beyondtheboundary.charityplatform.vo.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("test")
    public String test() {
        return "person/test";
    }

    @PostMapping("/testpost")
    public String recieveMsg(String title ,String summary, String content){
        System.out.println(title+"||||||"+summary+"||||||"+content);
//        System.out.println(project.getSummary());
        return "person/hello";
    }


}
