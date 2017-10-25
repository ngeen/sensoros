package com.shodom.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexWeb {

	@RequestMapping("/")
	public String ipAna(Model model) {
		//return "main";
		return "redirect:/machinesPing";
	}
	
	@RequestMapping("/machines")
	public String machines(Model model) {
		return "machines";
		//return "redirect:/aktifKullanicilar";
	}
	
	@RequestMapping("/machinesPing")
	public String machinesPing(Model model) {
		return "machinesPing";
		//return "redirect:/aktifKullanicilar";
	}
}
