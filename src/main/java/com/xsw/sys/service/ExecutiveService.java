package com.xsw.sys.service;

import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.vo.UserResult;

public interface ExecutiveService {

	UserResult examineTask(SysTaskValue sysTaskValue, String examine, String examineName);

	UserResult examinePurch(SysPurchValue sysPurchValue, String examine, String examineName);

	UserResult examineReim(SysReimValue sysReimValue, String examine, String examineName);

	UserResult examineLoan(SysLoanValue sysLoanValue, String examine, String examineName);

}
