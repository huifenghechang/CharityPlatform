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
@RequestMapping("/column")
public class ColumnController {


    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @GetMapping("")
    public ModelAndView columnAll(Model model) {

        List<Project> selectProject = projectServiceImpl.listProjects();
        model.addAttribute("projectList1", selectProject);


        return new ModelAndView("/view_all", "projectModel", model);
    }

    @GetMapping("/{id}")
    public ModelAndView column(@PathVariable("id") Long id , Model model) {
        List<Project> beSelected1= projectServiceImpl.listProjectsByStatusAndCategory(id,(long)3);
        model.addAttribute("projectList1", beSelected1);
        model.addAttribute("projectID", id);

        return new ModelAndView("/view_all", "projectModel", model);
    }


}