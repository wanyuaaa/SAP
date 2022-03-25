package com.xsw.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsw.common.vo.JsonResult;
import com.xsw.common.vo.UserResult;
import com.xsw.sys.service.SelectService;

@Controller
@RequestMapping("select")
public class SelectControllert {

	@Autowired
	private SelectService selectService;

	@RequestMapping("selectOperationList")
	@ResponseBody
	@CrossOrigin
	public UserResult selectOperationList(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectService.selectOperationList(name, page, limit, startTime, endTime, username);

		return userResult;

	}

	@RequestMapping("showPhoto")
	@ResponseBody
	@CrossOrigin
	public String showPhoto(String id) {

		String str = selectService.showPhoto(id);

		return str;

	}

	@RequestMapping("selectUser")
	@ResponseBody
	@CrossOrigin
	public JsonResult selectUser(String name) {

		JsonResult jsonResult = selectService.selectUser(name);

		return jsonResult;

	}

	@RequestMapping("selectNum")
	@ResponseBody
	@CrossOrigin
	public JsonResult selectNum(String name) {

		JsonResult jsonResult = selectService.selectNum(name);

		return jsonResult;

	}

	@RequestMapping("selectAllUser")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllUser(@Param("page") Integer page, @Param("limit") Integer limit,
			@Param("name") String name) {

		UserResult userResult = selectService.selectAllUser(page, limit, name);

		return userResult;

	}

	@RequestMapping("selectAllTask")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllTask(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime) {

		UserResult userResult = selectService.selectAllTask(page, limit, name, startTime, endTime);

		return userResult;

	}

	@RequestMapping("selectAllTaskLast")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllTaskLast(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectService.selectAllTaskLast(name, page, limit, username, startTime, endTime);

		return userResult;

	}

	@RequestMapping("selectAllReimLast")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllReimLast(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectService.selectAllReimLast(name, page, limit, username, startTime, endTime);

		return userResult;

	}

	@RequestMapping("selectAllPurchLast")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllPurchLast(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectService.selectAllPurchLast(name, page, limit, username, startTime, endTime);

		return userResult;

	}

	@RequestMapping("selectAllLoanLast")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllLoanLast(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectService.selectAllLoanLast(name, page, limit, username, startTime, endTime);

		return userResult;

	}

	@RequestMapping("selectExecutiveTask")
	@ResponseBody
	@CrossOrigin
	public UserResult selectExecutiveTask(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectService.selectExecutiveTask(page, limit, name, startTime, endTime, username);

		return userResult;

	}

	@RequestMapping("selectExecutivePurch")
	@ResponseBody
	@CrossOrigin
	public UserResult selectExecutivePurch(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectService.selectExecutivePurch(page, limit, name, startTime, endTime, username);

		return userResult;

	}

	@RequestMapping("selectExecutiveLoan")
	@ResponseBody
	@CrossOrigin
	public UserResult selectExecutiveLoan(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectService.selectExecutiveLoan(page, limit, name, startTime, endTime, username);

		return userResult;

	}

	@RequestMapping("selectExecutiveReim")
	@ResponseBody
	@CrossOrigin
	public UserResult selectExecutiveReim(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectService.selectExecutiveReim(page, limit, name, startTime, endTime, username);

		return userResult;

	}

	@RequestMapping("selectAllReim")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllReim(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime) {

		UserResult userResult = selectService.selectAllReim(page, limit, name, startTime, endTime);

		return userResult;

	}

	@RequestMapping("selectAllPurch")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllPurch(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime) {

		UserResult userResult = selectService.selectAllPurch(page, limit, name, startTime, endTime);

		return userResult;

	}

	@RequestMapping("selectAllLoan")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllLoan(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime) {

		UserResult userResult = selectService.selectAllLoan(page, limit, name, startTime, endTime);

		return userResult;

	}

	@RequestMapping("selectUserLogin")
	@ResponseBody
	@CrossOrigin
	public UserResult selectUserLogin(@RequestBody String params) {
		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] arr = params.split("&");
		String username = arr[0].substring(arr[0].indexOf("=") + 1);
		String password = arr[1].substring(arr[1].indexOf("=") + 1);
		String ip = arr[3].substring(arr[3].indexOf("=") + 1);
		String address = arr[4].substring(arr[4].indexOf("=") + 1);

		UserResult userResult = selectService.selectUserLogin(username, password, ip, address);

		return userResult;

	}
}
