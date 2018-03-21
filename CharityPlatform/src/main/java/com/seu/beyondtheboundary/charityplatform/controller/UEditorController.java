package com.seu.beyondtheboundary.charityplatform.controller;

import com.seu.beyondtheboundary.charityplatform.domain.Project;
import com.seu.beyondtheboundary.charityplatform.domain.User;
import com.seu.beyondtheboundary.charityplatform.service.ProjectService;
import com.seu.beyondtheboundary.charityplatform.ueditor.ActionEnter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ldb on 2017/4/9.
 */
@Controller
public class UEditorController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/ueditor_index")
    private String showPage(HttpServletRequest request, HttpServletResponse response){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            //使用request对象的getSession()获取session，如果session不存在则创建一个
            HttpSession session = request.getSession();
            //将数据存储到session中
            User user = (User) session.getAttribute("user");
            System.out.println(user.getVerified());
        if(user.getVerified() == 1)
            return "ueditor_index";
        else
            return "redirect:/personal_center";
    }

    //这里，返回的是项目的编辑页面，用来方便管理员的修改。
    @GetMapping("/u/projects/edit")
    public ModelAndView editProject(@RequestParam(value="id") Long id,
                                    Model model) {
        Project project = projectService.getProjectById(id);
        System.out.println(project.getContent());
        model.addAttribute("project", project);
        return new ModelAndView("ueditor_index_complete", "projectModel", model);
    }




    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
