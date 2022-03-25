package com.xsw.sys.service;

import com.xsw.common.vo.UserResult;

public interface SelectDataService {

	UserResult selectTaskDataList(String name, Integer page, Integer limit, String startTime, String endTime,
			String username);

	UserResult selectReimDataList(String name, Integer page, Integer limit, String startTime, String endTime,
			String username);

	UserResult selectLoanDataList(String name, Integer page, Integer limit, String startTime, String endTime,
			String username);

}
