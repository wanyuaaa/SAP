package com.xsw.sys.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsw.common.vo.UserResult;
import com.xsw.sys.service.SelectDataService;

@Controller
@RequestMapping("selectData")
public class SelectDataControllert {

	@Autowired
	private SelectDataService selectDataService;

	@RequestMapping("selectTaskDataList")
	@ResponseBody
	@CrossOrigin
	public UserResult selectTaskDataList(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectDataService.selectTaskDataList(name, page, limit, startTime, endTime, username);

		return userResult;

	}

	@RequestMapping("selectReimDataList")
	@ResponseBody
	@CrossOrigin
	public UserResult selectReimDataList(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectDataService.selectReimDataList(name, page, limit, startTime, endTime, username);

		return userResult;

	}

	@RequestMapping("selectLoanDataList")
	@ResponseBody
	@CrossOrigin
	public UserResult selectLoanDataList(@Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("username") String username) {

		UserResult userResult = selectDataService.selectLoanDataList(name, page, limit, startTime, endTime, username);

		return userResult;

	}
}
