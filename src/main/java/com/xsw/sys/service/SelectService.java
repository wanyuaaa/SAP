package com.xsw.sys.service;

import com.xsw.common.vo.JsonResult;
import com.xsw.common.vo.UserResult;

public interface SelectService {

	UserResult selectAllUser(Integer page, Integer limit, String name);

	UserResult selectUserLogin(String username, String password, String ip, String address);

	UserResult selectAllTask(Integer page, Integer limit, String name, String startTime, String endTime);

	UserResult selectAllReim(Integer page, Integer limit, String name, String startTime, String endTime);

	UserResult selectAllPurch(Integer page, Integer limit, String name, String startTime, String endTime);

	String showPhoto(String id);

	UserResult selectExecutiveTask(Integer page, Integer limit, String name, String startTime, String endTime,
			String username);

	UserResult selectExecutivePurch(Integer page, Integer limit, String name, String startTime, String endTime,
			String username);

	UserResult selectExecutiveReim(Integer page, Integer limit, String name, String startTime, String endTime,
			String username);

	JsonResult selectUser(String name);

	JsonResult selectNum(String name);

	UserResult selectAllTaskLast(String name, Integer page, Integer limit, String username, String startTime,
			String endTime);

	UserResult selectAllPurchLast(String name, Integer page, Integer limit, String username, String startTime,
			String endTime);

	UserResult selectAllReimLast(String name, Integer page, Integer limit, String username, String startTime,
			String endTime);

	UserResult selectOperationList(String name, Integer page, Integer limit, String startTime, String endTime,
			String username);

	UserResult selectAllLoan(Integer page, Integer limit, String name, String startTime, String endTime);

	UserResult selectExecutiveLoan(Integer page, Integer limit, String name, String startTime, String endTime,
			String username);

	UserResult selectAllLoanLast(String name, Integer page, Integer limit, String username, String startTime,
			String endTime);

}
