package com.seu.beyondtheboundary.charityplatform.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seu.beyondtheboundary.charityplatform.domain.OrderItem;
import com.seu.beyondtheboundary.charityplatform.domain.Project;
import com.seu.beyondtheboundary.charityplatform.domain.User;
import com.seu.beyondtheboundary.charityplatform.service.OrderItemServiceImpl;
import com.seu.beyondtheboundary.charityplatform.service.ProjectServiceImpl;
import com.seu.beyondtheboundary.charityplatform.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seu.beyondtheboundary.charityplatform.domain.PaySaPi;
import com.seu.beyondtheboundary.charityplatform.util.PayUtil;

@Controller
@RequestMapping("/pays")
public class PayController {
    @Autowired
    private ProjectServiceImpl projectServiceImpl;
    @Autowired
    private OrderItemServiceImpl orderItemService;
	@Autowired
	private UserServiceImpl userService;
	@RequestMapping("")
	public ModelAndView  pays(Model model,@RequestParam(value="id") Long id,HttpServletRequest request) {
		Project project = projectServiceImpl.getProjectById(id);
        model.addAttribute("message", id);
		model.addAttribute("project", project);
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){return new ModelAndView("redirect:/login");}
		return new ModelAndView("user_donate", "projectModel", model);
    }

	@RequestMapping("/pay")
	@ResponseBody
	public Map<String, Object> pay(HttpServletRequest request, float price, int istype,Long id) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> remoteMap = new HashMap<String, Object>();

        HttpSession session = request.getSession();
        User user1 = (User) session.getAttribute("user");

        Project project = projectServiceImpl.getProjectById(id);
        String orderId = PayUtil.getOrderIdByUUId();
        OrderItem orderItem = new OrderItem(price,orderId,project,user1);
        orderItemService.saveOrderItemService(orderItem);

		DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p=decimalFormat.format(price);//format 返回的是字符串

		remoteMap.put("price",p);
		remoteMap.put("istype", istype);
		remoteMap.put("orderid", orderId);
		remoteMap.put("orderuid", user1.getId());
		resultMap.put("data", PayUtil.payOrder(remoteMap));

		return resultMap;
	}

	@RequestMapping("/notifyPay")
	public void notifyPay(HttpServletRequest request, HttpServletResponse response, PaySaPi paySaPi) throws UnsupportedEncodingException {
		// 保证密钥一致性
		if (PayUtil.checkPayKey(paySaPi)) {
			OrderItem orderItem = orderItemService.getOrderItemByOrderId(paySaPi.getOrderid());
			orderItem.setStatus(1L);
			orderItemService.saveOrderItemService(orderItem);
			//用户积分增加
			User user = orderItem.getUser();
			user.setIntegral((long) (user.getIntegral()+orderItem.getPrice()));
			userService.saveUser(user);
			//项目已筹金额,捐款人数增加
			Project project = orderItem.getProject();
			project.setAlreadyDonation(project.getAlreadyDonation()+orderItem.getPrice());
			project.setDonatePeopleCounter((project.getDonatePeopleCounter()+1));
			projectServiceImpl.saveProject(project);
		} else {

		}
	}

	@RequestMapping("/returnPay")
	public ModelAndView returnPay(HttpServletRequest request, HttpServletResponse response, String orderid, Model model) {

//		OrderItem orderItem = orderItemService.getOrderItemByOrderId(orderid);
//		orderItem.setStatus(1L);
//		orderItemService.saveOrderItemService(orderItem);
//		//用户积分增加
//		User user = orderItem.getUser();
//		user.setIntegral((long) (user.getIntegral()+orderItem.getPrice()));
//		userService.saveUser(user);
//		//项目已筹金额,捐款人数增加
//		Project project = orderItem.getProject();
//		project.setAlreadyDonation((long) (project.getAlreadyDonation()+orderItem.getPrice()));
//		project.setDonatePeopleCounter((project.getDonatePeopleCounter()+1));
//		projectServiceImpl.saveProject(project);
		// 根据订单号查找相应的记录:根据结果跳转到不同的页面
		OrderItem orderItem = orderItemService.getOrderItemByOrderId(orderid);
		float price = orderItem.getPrice();
		Long prjectid = orderItem.getProject().getId();
		model.addAttribute("price", price);
		model.addAttribute("prjectid", prjectid);
		return new ModelAndView("person/success_donate", "Model", model);


	}
}
