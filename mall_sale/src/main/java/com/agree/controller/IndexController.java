package com.agree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value="/goto_login")
	public String goto_login(){
		return "login";
	}

	@RequestMapping(value="/index")
	public String index(){
		return "index";
	}

	@RequestMapping(value="/goto_list")
	public String goto_list(int flbh2){

		return "login";
	}
}
