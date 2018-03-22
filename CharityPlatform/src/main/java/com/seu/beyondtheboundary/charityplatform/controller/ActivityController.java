package com.seu.beyondtheboundary.charityplatform.controller;

import com.seu.beyondtheboundary.charityplatform.domain.Project;
import com.seu.beyondtheboundary.charityplatform.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/activity")
public class ActivityController {


    @Autowired
    private ProjectServiceImpl projectServiceImpl;


    @GetMapping("/{id}")
    public ModelAndView jump(@PathVariable("id") Long id , Model model){
        Project pro = projectServiceImpl.getProjectById(id);


        model.addAttribute("project", pro);
        return new ModelAndView("activity", "projectModel", model);
    }


}