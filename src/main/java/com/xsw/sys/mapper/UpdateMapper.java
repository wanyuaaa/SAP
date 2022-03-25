package com.xsw.sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.entry.SysUserUpdateValue;
import com.xsw.common.entry.SysUserValue;

public interface UpdateMapper {

	int updateUser(SysUserValue sysUserValue);

	int updateTask(SysTaskValue sysTaskValue);

	int updateReim(SysReimValue sysReimValue);

	int updatePurch(SysPurchValue sysPurchValue);

	int updataPictureSrc(SysReimValue sysReimValue);

	int updateExamineTask(SysTaskValue sysTaskValue);

	int updateExamineTaskPass(SysTaskValue sysTaskValue);

	int updateExaminePurch(SysPurchValue sysPurchValue);

	int updateExaminePurchPass(SysPurchValue sysPurchValue);

	int updateExamineReim(SysReimValue sysReimValue);

	int updateExamineReimPass(SysReimValue sysReimValue);

	int updateUserbyUser(SysUserUpdateValue sysUserUpdateValue);

	int updataPictureSrcDelete(@Param("id") String id, @Param("address") String address,
			@Param("progress") String progress, @Param("status") String status);

	int updateUserCount(@Param("allUserCount") int allUserCount, @Param("datetime") String datetime);

	int updataAllTitalprice(@Param("allTaskTitalprice") double allTaskTitalprice,
			@Param("allReimTitalprice") double allReimTitalprice,
			@Param("allPurchTitalprice") double allPurchTitalprice,
			@Param("allLoanTitalprice") double allLoanTitalprice, @Param("datetime") String datetime);

	int updateLoan(SysLoanValue sysLoanValue);

	int updateExamineLoan(SysLoanValue sysLoanValue);

	int updateExamineLoanPass(SysLoanValue sysLoanValue);

	void insertTaskPrintCount(@Param("id") String id, @Param("count") String count);

	void insertReimPrintCount(@Param("id") String id, @Param("count") String count);

}
