package com.seu.beyondtheboundary.charityplatform.controller;

import com.seu.beyondtheboundary.charityplatform.domain.Project;
import com.seu.beyondtheboundary.charityplatform.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping("/column")
public class ColumnController {


    @Autowired
    private ProjectServiceImpl projectServiceImpl;


    @GetMapping("/{id}")
    public ModelAndView column(HttpServletRequest request, Model model,
                               @PathVariable("id") Long id ,
                               @RequestParam(value="order",required=false,defaultValue="new") String order) {
        List<Project> beSelected1 = null;
        String search = request.getParameter("search");
        search=search==null?"":search;
        if(id==0){
            if (order.equals("more")) { // 金额排序
                beSelected1=projectServiceImpl.listProjectsByTitleLikeOrderByAlreadyDonationAsc(search);}
            else if (order.equals("new")) { // 最新查询
                beSelected1=projectServiceImpl.listProjectsByTitleLikeOrderByIdDesc(search);}
        } else{
            if (order.equals("more")) { // 金额排序
                beSelected1=projectServiceImpl.listProjectsByStatusAndCategoryOrderAlreadyDonation(id,3L,search);}
            else if (order.equals("new")) { // 最新查询
                beSelected1=projectServiceImpl.listProjectsByStatusAndCategoryOrderId(id,3L,search);}
            }
        model.addAttribute("projectList1", beSelected1);
        model.addAttribute("projectID", id);
        model.addAttribute("order", order);
        return new ModelAndView("view_all", "projectModel", model);
    }

    @RequestMapping("/{id}/search")
    public ModelAndView search(Model model,
                               @PathVariable("id") Long id ,
                               @RequestParam(value="order",required=false,defaultValue="new") String order,
                               @RequestParam(value="search",required=false,defaultValue="") String search) {
        List<Project> beSelected1 = null;
        search=search==null?"":search;
        if(id!=0){
            if (order.equals("more")) { // 金额排序
                beSelected1=projectServiceImpl.listProjectsByStatusAndCategoryOrderAlreadyDonation(id,3L,search);}
            else if (order.equals("new")) { // 最新查询
                beSelected1=projectServiceImpl.listProjectsByStatusAndCategoryOrderId(id,3L,search);}
        }else{
            if (order.equals("more")) { // 金额排序
                beSelected1=projectServiceImpl.listProjectsByTitleLikeOrderByAlreadyDonationAsc(search);}
            else if (order.equals("new")) { // 最新查询
                beSelected1=projectServiceImpl.listProjectsByTitleLikeOrderByIdDesc(search);}
        }
        model.addAttribute("projectList1", beSelected1);
        model.addAttribute("projectID", id);
        model.addAttribute("projectSearch", search);
        model.addAttribute("order", order);
        return new ModelAndView("view_all_search", "projectModel", model);
    }


}