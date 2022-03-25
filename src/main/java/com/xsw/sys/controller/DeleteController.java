package com.xsw.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsw.common.vo.UserResult;
import com.xsw.sys.service.DeleteService;

@Controller
@RequestMapping("delete")
public class DeleteController {

	@Autowired
	private DeleteService deleteService;

	@RequestMapping("deleteUser")
	@ResponseBody
	@CrossOrigin
	public UserResult deleteUser(String id) {

		UserResult str = deleteService.deleteUser(id);

		return str;
	}

	@RequestMapping("deleteTask")
	@ResponseBody
	@CrossOrigin
	public UserResult deleteTask(String id) {

		UserResult str = deleteService.deleteTask(id);

		return str;
	}

	@RequestMapping("deletePurch")
	@ResponseBody
	@CrossOrigin
	public UserResult deletePurch(String id) {

		UserResult str = deleteService.deletePurch(id);

		return str;
	}

	@RequestMapping("deleteLoan")
	@ResponseBody
	@CrossOrigin
	public UserResult Loan(String id) {

		UserResult str = deleteService.deleteLoan(id);

		return str;
	}

	@RequestMapping("deleteReim")
	@ResponseBody
	@CrossOrigin
	public UserResult deleteReim(String id) {

		UserResult str = deleteService.deleteReim(id);

		return str;
	}

	@RequestMapping("deletePicSrc")
	@ResponseBody
	@CrossOrigin
	public UserResult deletePicSrc(@RequestBody String params) {

		String id = params.split("=")[0];

		UserResult str = deleteService.deletePicSrc(id);

		return str;
	}

}
