package com.local.domain.comtrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.local.domain.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/order/P1234/2")
	public String process(Model model) {
		orderService.processOrder("P1234", 2l);
		return "redirect:/products/all";
	}
}
