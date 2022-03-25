package com.xsw.sys.mapper;

import java.util.List;

import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysTaskValue;

public interface StatisticsMapper {

	int selectAllTaskCount();

	int selectAllReimCount();

	int selectAllPurchCount();

	List<SysTaskValue> selectAllTaskTitalprice();

	List<SysReimValue> selectAllReimTitalprice();

	List<SysPurchValue> selectAllPurchTitalprice();

	int selectCountById(String datetime);

	List<SysTaskValue> selectAllTaskLastMouth(String datetime);

	List<SysReimValue> selectAllReimLastMouth(String datetime);

	List<SysPurchValue> selectAllPurchLastMouth(String datetime);

	String selectTaskCount(String id);

	String selectReimCount(String id);

	String selectPurchCount(String id);

	int selectAllLoanCount();

	List<SysLoanValue> selectAllLoanTitalprice();

	String selectLoanCount(String string);

	List<SysLoanValue> selectAllLoanLastMouth(String datetime);

}
