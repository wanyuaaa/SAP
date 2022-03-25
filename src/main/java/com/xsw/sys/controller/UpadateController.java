package com.xsw.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.entry.SysUserUpdateValue;
import com.xsw.common.entry.SysUserValue;
import com.xsw.common.vo.JsonResult;
import com.xsw.common.vo.UserResult;
import com.xsw.sys.service.UpdateService;

@Controller
@RequestMapping("update")
public class UpadateController {

	@Autowired
	private UpdateService updateService;

	@RequestMapping("updatePurch")
	@ResponseBody
	@CrossOrigin
	public UserResult updatePurch(@RequestBody String params) {
		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] arr = params.split("&");

		String id = arr[0].substring(arr[0].indexOf("=") + 1);
		String goods = arr[1].substring(arr[1].indexOf("=") + 1);
		String price = arr[2].substring(arr[2].indexOf("=") + 1);
		String place = arr[3].substring(arr[3].indexOf("=") + 1);
		String project = arr[4].substring(arr[4].indexOf("=") + 1);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = df.format(new Date());

		SysPurchValue sysPurchValue = new SysPurchValue();

		sysPurchValue.setId(id);
		sysPurchValue.setCreateTime(createTime);
		sysPurchValue.setGoods(goods);
		sysPurchValue.setPlace(place);
		sysPurchValue.setPrice(price);
		sysPurchValue.setProject(project);

		UserResult userResult = updateService.updatePurch(sysPurchValue);

		return userResult;

	}

	@RequestMapping("updateLoan")
	@ResponseBody
	@CrossOrigin
	public UserResult updateLoan(@RequestBody String params) {
		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] arr = params.split("&");

		String id = arr[0].substring(arr[0].indexOf("=") + 1);
		String price = arr[1].substring(arr[1].indexOf("=") + 1);
		String place = arr[2].substring(arr[2].indexOf("=") + 1);
		String project = arr[3].substring(arr[3].indexOf("=") + 1);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = df.format(new Date());

		SysLoanValue sysLoanValue = new SysLoanValue();

		sysLoanValue.setId(id);
		sysLoanValue.setCreateTime(createTime);
		sysLoanValue.setPlace(place);
		sysLoanValue.setPrice(price);
		sysLoanValue.setProject(project);

		UserResult userResult = updateService.updateLoan(sysLoanValue);

		return userResult;

	}

	@RequestMapping("updateReim")
	@ResponseBody
	@CrossOrigin
	public UserResult updateReim(@RequestBody String params) {
		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] arr = params.split("&");
		String id = arr[0].substring(arr[0].indexOf("=") + 1);
		String useTime = arr[1].substring(arr[1].indexOf("=") + 1);
		String goods = arr[2].substring(arr[2].indexOf("=") + 1);
		String price = arr[3].substring(arr[3].indexOf("=") + 1);
		String place = arr[4].substring(arr[4].indexOf("=") + 1);
		String project = arr[5].substring(arr[5].indexOf("=") + 1);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = df.format(new Date());

		SysReimValue sysReimValue = new SysReimValue();
		sysReimValue.setId(id);
		sysReimValue.setCreateTime(createTime);
		sysReimValue.setGoods(goods);
		sysReimValue.setUseTime(useTime);
		sysReimValue.setPlace(place);
		sysReimValue.setPrice(price);
		sysReimValue.setProject(project);

		UserResult userResult = updateService.updateReim(sysReimValue);

		return userResult;
	}

	@RequestMapping("updateTask")
	@ResponseBody
	@CrossOrigin
	public UserResult updateTask(@RequestBody String params) {
		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] arr = params.split("&");

		String id = arr[0].substring(arr[0].indexOf("=") + 1);
		String goTime = arr[1].substring(arr[1].indexOf("=") + 1);
		String backTime = arr[2].substring(arr[2].indexOf("=") + 1);
		String place = arr[3].substring(arr[3].indexOf("=") + 1);
		String objective = arr[4].substring(arr[4].indexOf("=") + 1);
		String unitPrice = arr[5].substring(arr[5].indexOf("=") + 1);
		String day = arr[6].substring(arr[6].indexOf("=") + 1);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = df.format(new Date());

		SysTaskValue sysTaskValue = new SysTaskValue();
		sysTaskValue.setId(id);
		sysTaskValue.setCreateTime(createTime);
		sysTaskValue.setGoTime(goTime);
		sysTaskValue.setBackTime(backTime);
		sysTaskValue.setPlace(place);
		sysTaskValue.setObjective(objective);
		sysTaskValue.setUnitPrice(unitPrice);
		sysTaskValue.setDay(day);

		UserResult userResult = updateService.updateTask(sysTaskValue);

		return userResult;
	}

	@RequestMapping("updateUser")
	@ResponseBody
	@CrossOrigin
	public UserResult updateUser(@RequestBody String params) {

		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println(params);

		String name2 = params.split("examineName=")[1];

		String[] arr = params.split("&");
		String id = arr[0].substring(arr[0].indexOf("=") + 1);
		String username = arr[1].substring(arr[1].indexOf("=") + 1);
		String name = arr[2].substring(arr[2].indexOf("=") + 1);
		String phone = arr[3].substring(arr[3].indexOf("=") + 1);
		String password = arr[4].substring(arr[4].indexOf("=") + 1);
		String role = arr[5].substring(arr[5].indexOf("=") + 1);
		String member = arr[6].substring(arr[6].indexOf("=") + 1);

		String status = "1";
		String sex = "0";

		if (arr.length == 9) {
			status = "0";
			sex = arr[8].substring(arr[8].indexOf("=") + 1);
		} else {
			sex = arr[7].substring(arr[7].indexOf("=") + 1);
		}

		SysUserValue sysUserValue = new SysUserValue();
		sysUserValue.setId(id);
		sysUserValue.setUsername(username);
		sysUserValue.setName(name);
		sysUserValue.setPhone(phone);
		sysUserValue.setPassword(password);
		sysUserValue.setRole(role);
		sysUserValue.setMember(member);
		sysUserValue.setStatus(status);
		sysUserValue.setSex(sex);

		UserResult userResult = updateService.updateUser(sysUserValue, name2);

		return userResult;
	}

	@RequestMapping("updateUserbyUser")
	@ResponseBody
	@CrossOrigin
	public JsonResult updateUserbyUser(@RequestBody JSONObject params) {

		SysUserUpdateValue sysUserUpdateValue = JSON.toJavaObject(params, SysUserUpdateValue.class);
		JsonResult jsonResult = updateService.updateUserbyUser(sysUserUpdateValue);

		return jsonResult;
	}
}
