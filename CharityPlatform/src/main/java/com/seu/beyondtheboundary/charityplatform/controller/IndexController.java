package com.seu.beyondtheboundary.charityplatform.controller;

import com.seu.beyondtheboundary.charityplatform.domain.Project;
import com.seu.beyondtheboundary.charityplatform.domain.User;
import com.seu.beyondtheboundary.charityplatform.service.ProjectServiceImpl;
import com.seu.beyondtheboundary.charityplatform.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }



    @GetMapping("/admins")
    public String admins() {
        return "manager/manage_center";
    }
    //index页面


    @GetMapping("/index")
    public ModelAndView index(Model model) {
        List<Project> beSelected1= new ArrayList<>();
        List<Project> beSelected2= new ArrayList<>();
        List<Project> beSelected3= new ArrayList<>();
        List<Project> beSelected4= new ArrayList<>();
        List<Project> selectProject = projectServiceImpl.listProjects();
        for (Project select : selectProject){
            {
                Long a = select.getCategory();
                Long b = select.getStatus();
                if (a == 1 && b==3) {beSelected1.add(select);}
            }
        }
        for (Project select : selectProject){
            {
                Long a = select.getCategory();
                Long b = select.getStatus();
                if (a == 2 && b==3) {beSelected2.add(select);}
            }
        }
        for (Project select : selectProject){
            {
                Long a = select.getCategory();
                Long b = select.getStatus();
                if (a == 3 && b==3) {beSelected3.add(select);}
            }
        }
        for (Project select : selectProject){
            {
                Long a = select.getCategory();
                Long b = select.getStatus();
                if (a == 4 && b==3) {beSelected4.add(select);}
            }
        }
        model.addAttribute("projectList1", beSelected1);
        model.addAttribute("projectList2", beSelected2);
        model.addAttribute("projectList3", beSelected3);
        model.addAttribute("projectList4", beSelected4);
        return new ModelAndView("index", "projectModel", model);
    }



    //注册页面
    @GetMapping("/register")
    public String register(){
        return "login_register/register";
    }

    //登录页面
    @PostMapping("/login")
    public String login(User user,Model model){
        String username = user.getUsername();
        String password = user.getPassword();

        if (userServiceImpl.findMeet(username,password)==true){
            return "redirect:/index";
        }else {
            model.addAttribute("loginError", true);
            model.addAttribute("errorMsg", "登陆失败，账号或者密码错误！");
            return "login_register/login";

        }

    }

    @GetMapping("/login")
    public String login(){
        return "login_register/login";
    }


    //注册方法
    @PostMapping("/register")
    public String register(User user){
        userServiceImpl.saveUser(user);
        return "redirect:/login";

    }

    @GetMapping("/personal_center")
    public String personal_center(){
        return "/person/personal_center";
    }




}
