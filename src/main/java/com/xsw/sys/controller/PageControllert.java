package com.xsw.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PageControllert {

	@RequestMapping("indexRole")
	@CrossOrigin
	public String indexRole() {
		return "indexRole";
	}

	@RequestMapping("indexAdmin")
	@CrossOrigin
	public String indexAdmin() {
		return "indexAdmin";
	}

	@RequestMapping("indexExamine")
	@CrossOrigin
	public String indexExamine() {
		return "indexExamine";
	}

	@RequestMapping("indexExamineE")
	@CrossOrigin
	public String indexExamineE() {
		return "indexExamineE";
	}

	@RequestMapping("indexExamineA")
	@CrossOrigin
	public String indexExamineA() {
		return "indexExamineA";
	}

	@RequestMapping("indexExamineF")
	@CrossOrigin
	public String indexExamineF() {
		return "indexExamineF";
	}

	@RequestMapping("login")
	@CrossOrigin
	public String login() {
		return "login";
	}
}
