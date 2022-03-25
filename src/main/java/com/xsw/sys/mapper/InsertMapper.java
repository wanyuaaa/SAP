package com.xsw.sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysOperationValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.entry.SysUserValue;

public interface InsertMapper {

	int insertSysUserValue(SysUserValue sysUserValue);

	int insertSysTaskValue(SysTaskValue sysTaskValue);

	int insertSysReimValue(SysReimValue sysReimValue);

	int insertSysPurchValue(SysPurchValue sysPurchValue);

	int insertOperationValue(SysOperationValue sysOperationValue);

	int insertUserCount(@Param("allUserCount") int allUserCount, @Param("datetime") String datetime);

	int insertSysLoanValue(SysLoanValue sysLoanValue);

}
