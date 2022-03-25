package com.xsw.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysOperationValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysSelectValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.entry.SysUserValue;

public interface SelectMapper {

	int selectUserById(String id);

	int selectUserByUsername(String username);

	int selectUserByName(String name);

	List<SysUserValue> selectAllUser(@Param("rowNumMin") int rowNumMin, @Param("rowNumMax") int rowNumMax);

	int selectAllUserCount();

	SysUserValue selectUserByUsernameAll(String username);

	List<SysTaskValue> selectAllTask(SysSelectValue sysSelectValue);

	int selectAllTaskCount(String name);

	int selectTaskById(String id);

	List<SysReimValue> selectAllReim(SysSelectValue sysSelectValue);

	int selectAllReimCount(String name);

	List<SysReimValue> selectAllReimLast(SysSelectValue sysSelectValue);

	int selectAllReimCountLast(SysSelectValue sysSelectValue);

	int selectReimById(String id);

	SysReimValue selectReimByIdValue(String id);

	SysTaskValue selectTaskByIdValue(String id);

	List<SysPurchValue> selectAllPurch(SysSelectValue sysSelectValue);

	int selectAllPurchCount(String name);

	int selectAllPurchCountLast(SysSelectValue sysSelectValue);

	List<SysPurchValue> selectAllPurchLast(SysSelectValue sysSelectValue);

	int selectPurchById(String id);

	SysPurchValue selectPurchByIdValue(String id);

	String selectExecutiveByName(String name);

	List<SysTaskValue> selectExecutiveTaskByManager(SysSelectValue sysSelectValue);

	int selectExecutiveTaskCountByManager(SysSelectValue sysSelectValue);

	List<SysTaskValue> selectExecutiveTaskByExecutive(SysSelectValue sysSelectValue);

	int selectExecutiveTaskCountByExecutive(SysSelectValue sysSelectValue);

	List<SysTaskValue> selectExecutiveTaskByAccounting(SysSelectValue sysSelectValue);

	int selectExecutiveTaskCountByAccounting(SysSelectValue sysSelectValue);

	List<SysTaskValue> selectExecutiveTaskByFinance(SysSelectValue sysSelectValue);

	int selectExecutiveTaskCountByFinance(SysSelectValue sysSelectValue);

	List<SysPurchValue> selectExecutivePurchByManager(SysSelectValue sysSelectValue);

	int selectExecutivePurchCountByManager(SysSelectValue sysSelectValue);

	List<SysPurchValue> selectExecutivePurchByExecutive(SysSelectValue sysSelectValue);

	int selectExecutivePurchCountByExecutive(SysSelectValue sysSelectValue);

	List<SysReimValue> selectExecutiveReimByManager(SysSelectValue sysSelectValue);

	int selectExecutiveReimCountByManager(SysSelectValue sysSelectValue);

	List<SysReimValue> selectExecutiveReimByAccounting(SysSelectValue sysSelectValue);

	int selectExecutiveReimCountByAccounting(SysSelectValue sysSelectValue);

	List<SysReimValue> selectExecutiveReimByFinance(SysSelectValue sysSelectValue);

	int selectExecutiveReimCountByFinance(SysSelectValue sysSelectValue);

	SysUserValue selectUserByNameValue(String name);

	String selectUserPasswordById(String id);

	List<SysTaskValue> selectExecutiveTask(@Param("rowNumMin") int rowNumMin, @Param("rowNumMax") int rowNumMax);

	int selectExecutiveTaskCount();

	List<SysPurchValue> selectExecutivePurch(@Param("rowNumMin") int rowNumMin, @Param("rowNumMax") int rowNumMax);

	int selectExecutivePurchCount();

	List<SysReimValue> selectExecutiveReim(@Param("rowNumMin") int rowNumMin, @Param("rowNumMax") int rowNumMax);

	int selectExecutiveReimCount();

	List<SysTaskValue> selectAllTaskByRole(@Param("rowNumMin") int rowNumMin, @Param("rowNumMax") int rowNumMax);

	int selectAllTaskCountByRole(String name);

	List<SysReimValue> selectAllReimByRole(@Param("rowNumMin") int rowNumMin, @Param("rowNumMax") int rowNumMax);

	int selectAllReimCountByRole(String name);

	List<SysPurchValue> selectAllPurchByRole(@Param("rowNumMin") int rowNumMin, @Param("rowNumMax") int rowNumMax);

	int selectAllPurchCountByRole(String name);

	List<SysTaskValue> selectAllTaskLast(SysSelectValue sysSelectValue);

	int selectAllTaskCountLast(SysSelectValue sysSelectValue);

	List<SysOperationValue> selectOperationList(SysSelectValue sysSelectValue);

	int selectOperationListCount(SysSelectValue sysSelectValue);

	List<SysOperationValue> selectOperationListByName(SysSelectValue sysSelectValue);

	int selectOperationListCountByName(SysSelectValue sysSelectValue);

	List<SysTaskValue> selectAllTaskByTwoTime(SysSelectValue sysSelectValue);

	int selectAllTaskCountByTwoTime(SysSelectValue sysSelectValue);

	List<SysTaskValue> selectAllTaskByStartTime(SysSelectValue sysSelectValue);

	int selectAllTaskCountByStartTime(SysSelectValue sysSelectValue);

	List<SysTaskValue> selectAllTaskByEndTime(SysSelectValue sysSelectValue);

	int selectAllTaskCountByEndTime(SysSelectValue sysSelectValue);

	List<SysReimValue> selectAllReimByTwoTime(SysSelectValue sysSelectValue);

	int selectAllReimCountByTwoTime(SysSelectValue sysSelectValue);

	List<SysReimValue> selectAllReimByStartTime(SysSelectValue sysSelectValue);

	int selectAllReimCountByStartTime(SysSelectValue sysSelectValue);

	List<SysReimValue> selectAllReimByEndTime(SysSelectValue sysSelectValue);

	int selectAllReimCountByEndTime(SysSelectValue sysSelectValue);

	List<SysPurchValue> selectAllPurchByTwoTime(SysSelectValue sysSelectValue);

	int selectAllPurchCountByTwoTime(SysSelectValue sysSelectValue);

	List<SysPurchValue> selectAllPurchByStartTime(SysSelectValue sysSelectValue);

	int selectAllPurchCountByStartTime(SysSelectValue sysSelectValue);

	List<SysPurchValue> selectAllPurchByEndTime(SysSelectValue sysSelectValue);

	int selectAllPurchCountByEndTime(SysSelectValue sysSelectValue);

	List<SysTaskValue> selectExecutiveTaskByManagerByName(SysSelectValue sysSelectValue);

	int selectExecutiveTaskCountByManagerByName(SysSelectValue sysSelectValue);

	List<SysTaskValue> selectExecutiveTaskByExecutiveByName(SysSelectValue sysSelectValue);

	int selectExecutiveTaskCountByExecutiveByName(SysSelectValue sysSelectValue);

	List<SysTaskValue> selectExecutiveTaskByAccountingByName(SysSelectValue sysSelectValue);

	int selectExecutiveTaskCountByAccountingByName(SysSelectValue sysSelectValue);

	List<SysTaskValue> selectExecutiveTaskByFinanceByName(SysSelectValue sysSelectValue);

	int selectExecutiveTaskCountByFinanceByName(SysSelectValue sysSelectValue);

	List<SysReimValue> selectExecutiveReimByManagerByName(SysSelectValue sysSelectValue);

	int selectExecutiveReimCountByManagerByName(SysSelectValue sysSelectValue);

	List<SysReimValue> selectExecutiveReimByAccountingByName(SysSelectValue sysSelectValue);

	int selectExecutiveReimCountByAccountingByName(SysSelectValue sysSelectValue);

	List<SysReimValue> selectExecutiveReimByFinanceByName(SysSelectValue sysSelectValue);

	int selectExecutiveReimCountByFinanceByName(SysSelectValue sysSelectValue);

	List<SysPurchValue> selectExecutivePurchByManagerByName(SysSelectValue sysSelectValue);

	int selectExecutivePurchCountByManagerByName(SysSelectValue sysSelectValue);

	List<SysPurchValue> selectExecutivePurchByExecutiveByName(SysSelectValue sysSelectValue);

	int selectExecutivePurchCountByExecutiveByName(SysSelectValue sysSelectValue);

	List<SysTaskValue> selectAllTaskLastByName(SysSelectValue sysSelectValue);

	int selectAllTaskCountLastByName(SysSelectValue sysSelectValue);

	List<SysPurchValue> selectAllPurchLastByName(SysSelectValue sysSelectValue);

	int selectAllPurchCountLastByName(SysSelectValue sysSelectValue);

	List<SysReimValue> selectAllReimLastByName(SysSelectValue sysSelectValue);

	int selectAllReimCountLastByName(SysSelectValue sysSelectValue);

	int selectExecutiveReimCountByManagerA();

	int selectExecutivePurchCountByManagerA();

	int selectExecutiveTaskCountByManagerA();

	int selectExecutivePurchCountByExecutiveA();

	int selectExecutiveTaskCountByExecutiveA();

	int selectExecutiveReimCountByAccountingA();

	int selectExecutiveTaskCountByAccountingA();

	int selectExecutiveReimCountByFinanceA();

	int selectExecutiveTaskCountByFinanceA();

	List<SysLoanValue> selectAllLoanByTwoTime(SysSelectValue sysSelectValue);

	int selectAllLoanCountByTwoTime(SysSelectValue sysSelectValue);

	List<SysLoanValue> selectAllLoanByStartTime(SysSelectValue sysSelectValue);

	int selectAllLoanCountByStartTime(SysSelectValue sysSelectValue);

	List<SysLoanValue> selectAllLoanByEndTime(SysSelectValue sysSelectValue);

	int selectAllLoanCountByEndTime(SysSelectValue sysSelectValue);

	List<SysLoanValue> selectAllLoan(SysSelectValue sysSelectValue);

	int selectAllLoanCount(String name);

	int selectLoanById(String id);

	SysLoanValue selectLoanByIdValue(String id);

	List<SysLoanValue> selectExecutiveLoan(@Param("rowNumMin") int rowNumMin, @Param("rowNumMax") int rowNumMax);

	int selectExecutiveLoanCount();

	List<SysLoanValue> selectExecutiveLoanByManager(SysSelectValue sysSelectValue);

	int selectExecutiveLoanCountByManager(SysSelectValue sysSelectValue);

	List<SysLoanValue> selectExecutiveLoanByManagerByName(SysSelectValue sysSelectValue);

	int selectExecutiveLoanCountByManagerByName(SysSelectValue sysSelectValue);

	List<SysLoanValue> selectExecutiveLoanByFinance(SysSelectValue sysSelectValue);

	int selectExecutiveLoanCountByFinance(SysSelectValue sysSelectValue);

	List<SysLoanValue> selectExecutiveLoanByFinanceByName(SysSelectValue sysSelectValue);

	int selectExecutiveLoanCountByFinanceByName(SysSelectValue sysSelectValue);

	List<SysLoanValue> selectAllLoanLast(SysSelectValue sysSelectValue);

	int selectAllLoanCountLast(SysSelectValue sysSelectValue);

	List<SysLoanValue> selectAllLoanLastByName(SysSelectValue sysSelectValue);

	int selectAllLoanCountLastByName(SysSelectValue sysSelectValue);

	int selectExecutiveLoanCountByManagerA();

	int selectExecutiveLoanCountByFinanceA();

	int selectExecutivePurchCountByExecutiveB();

	List<String> selectAllUserName();

	List<String> selectTaskTotalPriceByName(SysSelectValue sysSelectValue);

	int selectTaskCount(SysSelectValue sysSelectValue);

	String selectUserName(String name);

	List<String> selectReimTotalPriceByName(SysSelectValue sysSelectValue);

	int selectReimCount(SysSelectValue sysSelectValue);

	List<String> selectLoanTotalPriceByName(SysSelectValue sysSelectValue);

	int selectLoanCount(SysSelectValue sysSelectValue);

	int selectTaskCountAll(@Param("e") int e, @Param("a") int a, @Param("m") int m, @Param("f") int f);

	int selectReimCountAll(@Param("a") int a, @Param("m") int m, @Param("f") int f);

	int selectPurchCountAll(@Param("m") int m, @Param("e") int e);

	int selectLoanCountAll(@Param("m") int m, @Param("f") int f);

	String selectTaskPrintCount(String id);

	String selectReimPrintCount(String id);

}
