package com.seu.beyondtheboundary.charityplatform.controller;

import com.seu.beyondtheboundary.charityplatform.domain.OrderItem;
import com.seu.beyondtheboundary.charityplatform.domain.Project;
import com.seu.beyondtheboundary.charityplatform.domain.User;
import com.seu.beyondtheboundary.charityplatform.repository.OrderItemRepository;
import com.seu.beyondtheboundary.charityplatform.repository.ProjectRepository;
import com.seu.beyondtheboundary.charityplatform.repository.UserRepository;
import com.seu.beyondtheboundary.charityplatform.service.OrderItemServiceImpl;
import com.seu.beyondtheboundary.charityplatform.service.ProjectServiceImpl;
import com.seu.beyondtheboundary.charityplatform.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admins")
public class AdminsController {


    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private OrderItemServiceImpl orderItemService;

    @GetMapping("/cancle_project_verified/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){

        Project project = projectRepository.findOne(id);
        project.setStatus((long)0);
        projectRepository.save(project);
        return new ModelAndView("redirect:/admins/published");
    }
    @GetMapping("/cancle_user_verified/{id}")
    public ModelAndView cancle_user_verified(@PathVariable("id") Long id){
        User user = userRepository.findById(id);
        user.setVerified(0);
        userRepository.save(user);

        return new ModelAndView("redirect:/admins/vip_verified");
    }

    @GetMapping("/deleteAdmin/{id}")
    public ModelAndView deleteAdmin(@PathVariable("id") Long id){
        userService.removeUser(id);
        return new ModelAndView("redirect:/admins/edit_admin");
    }

    @GetMapping("/change1to0/{id}")
    public ModelAndView change1to0(@PathVariable("id") Long id){
        projectServiceImpl.getProjectById(id).setStatus((long)0);
        projectServiceImpl.saveProject(projectServiceImpl.getProjectById(id));
        return new ModelAndView("redirect:/admins/to_verify");
    }

    @GetMapping("/change1to2/{id}")
    public ModelAndView change1to2(@PathVariable("id") Long id){
        projectServiceImpl.getProjectById(id).setStatus((long)2);
        projectServiceImpl.saveProject(projectServiceImpl.getProjectById(id));
        return new ModelAndView("redirect:/admins/to_verify");
    }

    @GetMapping("/change2to3/{id}")
    public ModelAndView change2to3(@PathVariable("id") Long id){
        projectServiceImpl.getProjectById(id).setStatus((long)3);
        projectServiceImpl.saveProject(projectServiceImpl.getProjectById(id));
        return new ModelAndView("redirect:/admins/to_publish");
    }

    @GetMapping("/to_publish")
    public ModelAndView to_publish(Model model) {

        List<Project> beSelected= new ArrayList<>();
        List<Project> selectProject = projectServiceImpl.listProjects();
        for (Project select : selectProject){
            {
                Long a = select.getStatus();
                if (a == (long)2) {beSelected.add(select);}
            }
        }
        model.addAttribute("projectList", beSelected);
        return new ModelAndView("manager/to_publish", "projectModel", model);

    }


    @GetMapping("/to_verify")
    public ModelAndView to_verify(Model model) {
        List<Project> beSelected= new ArrayList<>();
        List<Project> selectProject = projectServiceImpl.listProjects();
        for (Project select : selectProject){
            {
                Long a = select.getStatus();
                if (a == (long)1) {beSelected.add(select);}
            }
        }
        model.addAttribute("projectList", beSelected);
        return new ModelAndView("/manager/to_verify", "projectModel", model);
    }

    @GetMapping("/published")
    public ModelAndView published(Model model) {
        List<Project> beSelected= new ArrayList<>();
        List<Project> selectProject = projectServiceImpl.listProjects();
        for (Project select : selectProject){
            {
                Long a = select.getStatus();
                if (a == (long)3) {beSelected.add(select);}
            }
        }
        model.addAttribute("projectList", beSelected);
        return new ModelAndView("manager/published", "projectModel", model);

    }
    @GetMapping("/vip_to_verify")
    public ModelAndView vip_to_verify_show(Model model) {

        List<User> selectUser = userService.userNotVerified();
        model.addAttribute("userList", selectUser);

        return new ModelAndView("/manager/vip_to_verify", "userModel", model);
    }

    @PostMapping("/vip_to_verify")
    public ModelAndView  vip_to_verify_update(User user, Model model) {

        userService.userCheckPass(user.getId(), user.getVerified());

        List<User> selectUser = userService.userNotVerified();
        model.addAttribute("userList", selectUser);

        return new ModelAndView("/manager/vip_to_verify", "userModel", model);
    }
    @GetMapping("/vip_verified")
    public ModelAndView vip_verified(Model model) {

        List<User> selectUser = userService.userHasVerified();
        model.addAttribute("userList", selectUser);

        return new ModelAndView("/manager/vip_verified", "userModel", model);
    }
    @GetMapping("/vipcertificate")
    public String showCertificate(Model model) {

        return "/manager/vip_get_certificate";
    }

    @PostMapping("/vipcertificate")
    public ModelAndView showCertificate1(User user, Model model) {

        model.addAttribute("imgsrc", user.getConfirmation_link().split(";"));

        return new ModelAndView("/manager/vip_get_certificate", "imgModel", model);
    }

    @PostMapping("/project_certificate")
    public ModelAndView showCertificate1(Project project, Model model) {

        model.addAttribute("pro_imgsrc", project.getPro_confirmation_link().split(";"));

        return new ModelAndView("/manager/pro_get_certificate", "pro_imgModel", model);
    }

    @GetMapping("/edit_admin")
    public ModelAndView editadmin(Model model) {
        List<User> selectAdmins = userService.findAdmins();
        model.addAttribute("adminList", selectAdmins);
        return new ModelAndView("manager/update_admin", "adminModel", model);
    }



    @GetMapping("/complete_admin_info")
    public ModelAndView complete_user_info(@RequestParam(value="id") Long id,Model model) {
        model.addAttribute("id",id);
        return new ModelAndView("manager/complete_admin_info", "adminModel", model);
    }


    @PostMapping("/complete_admin_info")
    public String complete_user_info1(@RequestParam(value="id") Long id , User user, HttpServletRequest request, HttpServletResponse response) {

        User user1 = userRepository.findById(id);
        System.out.println(user1.getId());
        user1.setSex(user.isSex());
        if(user.getTel() != "")
            user1.setTel(user.getTel());
        if(user.getAddress() != "")
            user1.setAddress(user.getAddress());
        if(user.getUser_id_card() != "")
            user1.setUser_id_card(user.getUser_id_card());
        if(user.getRealname() != "")
            user1.setRealname(user.getRealname());
        if(user.getEmail() != "")
            user1.setEmail(user.getEmail());

        userRepository.save(user1);
        return "redirect:/admins/edit_admin";
    }

    @GetMapping("/apply_for_refund")
    public String apply_for_refund() {
        return "/manager/apply_for_refund";
    }

    @GetMapping("/admin_order")
    public ModelAndView order_show(Model model) {

        List<OrderItem> validOrder = orderItemService.getOrderItemByStatus((long)1);

        model.addAttribute("orderList", validOrder);

        return new ModelAndView("manager/order_information", "orderModel", model);
    }

}