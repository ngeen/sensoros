package com.shodom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String ipAna(Model model) {
		return "index";
		//return "redirect:/aktifKullanicilar";
	}
}
