package com.xsw.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.vo.UserResult;
import com.xsw.sys.service.ExecutiveService;

@Controller
@RequestMapping("examine")
public class ExecutiveController {

	@Autowired
	private ExecutiveService executiveService;

	@RequestMapping("examineTask")
	@ResponseBody
	@CrossOrigin
	public UserResult examineTask(@RequestBody String params) {

		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] arr = params.split("&");

		String id = arr[0].substring(arr[0].indexOf("=") + 1);
		String examine = arr[10].substring(arr[10].indexOf("=") + 1);
		String reason = arr[11].substring(arr[11].indexOf("=") + 1);
		String examineName = params.split("examineName=")[1];

		SysTaskValue sysTaskValue = new SysTaskValue();
		sysTaskValue.setId(id);
		sysTaskValue.setReason(reason);

		UserResult userResult = executiveService.examineTask(sysTaskValue, examine, examineName);

		return userResult;
	}

	@RequestMapping("examineLoan")
	@ResponseBody
	@CrossOrigin
	public UserResult examineLoan(@RequestBody String params) {

		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] arr = params.split("&");

		String id = arr[0].substring(arr[0].indexOf("=") + 1);
		String examine = arr[6].substring(arr[6].indexOf("=") + 1);
		String reason = arr[7].substring(arr[7].indexOf("=") + 1);
		String examineName = params.split("examineName=")[1];

		SysLoanValue sysLoanValue = new SysLoanValue();
		sysLoanValue.setId(id);
		sysLoanValue.setReason(reason);

		UserResult userResult = executiveService.examineLoan(sysLoanValue, examine, examineName);

		return userResult;
	}

	@RequestMapping("examinePurch")
	@ResponseBody
	@CrossOrigin
	public UserResult examinePurch(@RequestBody String params) {

		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] arr = params.split("&");

		String id = arr[0].substring(arr[0].indexOf("=") + 1);
		String examine = arr[7].substring(arr[7].indexOf("=") + 1);
		String reason = arr[8].substring(arr[8].indexOf("=") + 1);
		String examineName = params.split("examineName=")[1];

		SysPurchValue sysPurchValue = new SysPurchValue();
		sysPurchValue.setId(id);
		sysPurchValue.setReason(reason);

		UserResult userResult = executiveService.examinePurch(sysPurchValue, examine, examineName);

		return userResult;
	}

	@RequestMapping("examineReim")
	@ResponseBody
	@CrossOrigin
	public UserResult examineReim(@RequestBody String params) {

		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] arr = params.split("&");

		String id = arr[0].substring(arr[0].indexOf("=") + 1);
		String examine = arr[7].substring(arr[7].indexOf("=") + 1);
		String reason = arr[8].substring(arr[8].indexOf("=") + 1);
		String examineName = params.split("examineName=")[1];

		SysReimValue sysReimValue = new SysReimValue();
		sysReimValue.setId(id);
		sysReimValue.setReason(reason);

		UserResult userResult = executiveService.examineReim(sysReimValue, examine, examineName);

		return userResult;
	}

}
