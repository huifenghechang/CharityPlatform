package com.seu.beyondtheboundary.charityplatform.controller;

import com.seu.beyondtheboundary.charityplatform.domain.OrderItem;
import com.seu.beyondtheboundary.charityplatform.domain.Project;
import com.seu.beyondtheboundary.charityplatform.domain.User;
import com.seu.beyondtheboundary.charityplatform.service.OrderItemServiceImpl;
import com.seu.beyondtheboundary.charityplatform.service.ProjectServiceImpl;
import com.seu.beyondtheboundary.charityplatform.service.UserServiceImpl;
import com.seu.beyondtheboundary.charityplatform.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class IndexController {
    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private OrderItemServiceImpl orderItemService;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/admins")
    public String admins(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        if(session.getAttribute("user")==null){return "redirect:/login";}
        User user = (User) session.getAttribute("user");
        if(user.isAdmin()){
            return "manager/manage_center";}
        else {
            return "redirect:/index";
        }
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
        List<Project> projects = projectServiceImpl.listProjects();
        Long sumDonatePeopleCounter = 0L;
        Long sumAlreadyDonation = 0L;
        for (Project project : projects){
            sumDonatePeopleCounter += project.getDonatePeopleCounter();
            sumAlreadyDonation += (long)project.getAlreadyDonation();
        }
        model.addAttribute("sumDonatePeopleCounter", sumDonatePeopleCounter);
        model.addAttribute("sumAlreadyDonation", sumAlreadyDonation);
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
    public String login(User user, Model model, HttpServletRequest request, HttpServletResponse response){
        String username = user.getUsername();
        String password = user.getPassword();

        password = MD5.EncoderByMd5(password);

        User user1 = userServiceImpl.findMeet(username,password);

        if (userServiceImpl.findMeet(username,password) == null){
            model.addAttribute("loginError", true);
            model.addAttribute("errorMsg", "登陆失败，账号或者密码错误！");
            return "login_register/login";
        } else {

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            //使用request对象的getSession()获取session，如果session不存在则创建一个
            HttpSession session = request.getSession();
            //将数据存储到session中
            session.setAttribute("user", user1);
            return "redirect:/index";
        }

    }


    @GetMapping("/login")
    public String login(){
        return "login_register/login";
    }
    @GetMapping("/loginout")
    public String loginout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("user");
        //从定向到index.jsp
        return "redirect:/index";
    }

    //注册方法

//    public String register(User user){
//        user.setPassword(MD5.EncoderByMd5(user.getPassword()));
//        userServiceImpl.saveUser(user);
//        return "redirect:/login";
//
//    }

    //用户登录
    @PostMapping("/register")
    @ResponseBody
    private boolean register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//防止乱码
        String username = request.getParameter("username");
        String password = MD5.EncoderByMd5(request.getParameter("password"));
        List<User> users = userServiceImpl.listUsers();
        boolean flag = false;//默认登录不成功
        for(User user:users){
            if(username.equals(user.getUsername())) {
                return false;
            }
        }

        User user = new User(username,password);
        userServiceImpl.saveUser(user);
        flag = true;


        response.setContentType("text/html;charset=utf-8");

        return flag;
    }




    @GetMapping("/register_admin")
    public String register_admin(){
        return "login_register/register_admin";
    }

    @PostMapping("/register_admin")
    public String register_admin(User user){
        user.setPassword(MD5.EncoderByMd5(user.getPassword()));
        user.setAdmin(true);
        userServiceImpl.saveUser(user);
        return "login_register/register_admin";

    }

    @GetMapping("/personal_center")
    public ModelAndView personal_center(Model model, HttpServletRequest request, HttpServletResponse response){

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        User user = (User) session.getAttribute("user");

        List<OrderItem> validOrder = orderItemService.getOrderItemByRefund_status(0L, 1L);

        Set<Project> selectProject = new HashSet<>();

        List<OrderItem> selectOrder = new ArrayList<>();

        for (OrderItem order:
             validOrder) {
            if(order.getUser().getId() == user.getId()) {
                selectProject.add(order.getProject());
                selectOrder.add(order);
            }
        }

        List<List<OrderItem>> order_sort_by_project = new ArrayList<>();

        for (Project project:
             selectProject) {

            List<OrderItem> projectOrder = new ArrayList<>();
            float sum = 0;

            for (OrderItem orderItem:
                 selectOrder) {
                if(orderItem.getProject() == project) {
                    projectOrder.add(orderItem);
                    sum += orderItem.getPrice();
                }
            }
            //额外声明一个对象存捐款总和
            OrderItem saveSum = new OrderItem();
            saveSum.setPrice(sum);
            projectOrder.add(saveSum);

            order_sort_by_project.add(projectOrder);
        }

        model.addAttribute("orderSortList", order_sort_by_project);
        return new ModelAndView("person/personal_center", "pcModel", model);
    }

    @GetMapping("/contact_us")
    public String contact_us(){
        return "bottom_page/contact_us";
    }

    @GetMapping("/about_us")
    public String about_us(){
        return "bottom_page/about_us";
    }

    @GetMapping("/feedback")
    public String feedback(){
        return "bottom_page/feedback";
    }

    @GetMapping("/map")
    public String map(){
        return "bottom_page/map";
    }






}
