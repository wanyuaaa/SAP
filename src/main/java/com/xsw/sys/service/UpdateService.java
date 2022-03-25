package com.xsw.sys.service;

import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.entry.SysUserUpdateValue;
import com.xsw.common.entry.SysUserValue;
import com.xsw.common.vo.JsonResult;
import com.xsw.common.vo.UserResult;

public interface UpdateService {

	UserResult updateUser(SysUserValue sysUserValue, String name2);

	UserResult updateTask(SysTaskValue sysTaskValue);

	UserResult updateReim(SysReimValue sysReimValue);

	UserResult updatePurch(SysPurchValue sysPurchValue);

	JsonResult updateUserbyUser(SysUserUpdateValue sysUserUpdateValue);

	UserResult updateLoan(SysLoanValue sysLoanValue);

}
