package com.seu.beyondtheboundary.charityplatform.controller;

import java.io.UnsupportedEncodingException;
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

	@RequestMapping("")
	public String  pays(Model model,@RequestParam(value="id") Long id) {
        model.addAttribute("message", id);
        return "pay";
    }

	@RequestMapping("/pay")
	@ResponseBody
	public Map<String, Object> pay(HttpServletRequest request, float price, int istype,Long id) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> remoteMap = new HashMap<String, Object>();

        HttpSession session = request.getSession();
        User user1 = (User) session.getAttribute("user");
        Project project = projectServiceImpl.getProjectById(id);
        String orderID = PayUtil.getOrderIdByUUId();
        OrderItem orderItem = new OrderItem(price,orderID,project,user1);
        orderItemService.saveOrderItemService(orderItem);

		remoteMap.put("price", price);
		remoteMap.put("istype", istype);
		remoteMap.put("orderid", orderID);
		remoteMap.put("orderuid", user1.getId());
		remoteMap.put("goodsname", "您自己的商品名称");
		resultMap.put("data", PayUtil.payOrder(remoteMap));
		return resultMap;
	}

	@RequestMapping("/notifyPay")
	public void notifyPay(HttpServletRequest request, HttpServletResponse response, PaySaPi paySaPi) throws UnsupportedEncodingException {
		// 保证密钥一致性
		if (PayUtil.checkPayKey(paySaPi)) {
            orderItemService.getOrderItemByOrderID(paySaPi.getOrderid()).setStatus(1L);
		} else {
			// TODO 该怎么做就怎么做
		}
	}

	@RequestMapping("/returnPay")
	public ModelAndView returnPay(HttpServletRequest request, HttpServletResponse response, String orderid) {
		boolean isTrue = false;
		ModelAndView view = null;
		// 根据订单号查找相应的记录:根据结果跳转到不同的页面
		if (isTrue) {
			view = new ModelAndView("redirect:/index");
		} else {
			view = new ModelAndView("redirect:/index");
		}
		return view;
	}
}
