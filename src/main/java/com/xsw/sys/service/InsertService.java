package com.xsw.sys.service;

import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.entry.SysUserValue;
import com.xsw.common.vo.UserResult;

public interface InsertService {

	UserResult addUser(SysUserValue sysUserValue, String name);

	UserResult taskAdd(SysTaskValue sysTaskValue);

	UserResult reimAdd(SysReimValue sysReimValue);

	UserResult purchAdd(SysPurchValue sysPurchValue);

	void insertPictureSrc(SysReimValue selectReimByIdValue);

	UserResult loanAdd(SysLoanValue sysLoanValue);

	void insertTaskPrintCount(String id);

	void insertReimPrintCount(String id);

}
