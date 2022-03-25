package com.xsw.sys.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysOperationValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.entry.SysUserValue;
import com.xsw.common.vo.UserResult;
import com.xsw.sys.mapper.InsertMapper;
import com.xsw.sys.mapper.SelectMapper;
import com.xsw.sys.mapper.UpdateMapper;
import com.xsw.sys.service.InsertService;

@Service
public class InsertServiceImpl implements InsertService {

	@Autowired
	private InsertMapper insertMapper;
	@Autowired
	private SelectMapper selectMapper;
	@Autowired
	private UpdateMapper updataMapper;

	@Override
	public UserResult addUser(SysUserValue sysUserValue, String name2) {
		UserResult userResult = new UserResult();
		userResult.setMsg("添加人员成功！");

		String id = sysUserValue.getId();
		int idInt = selectMapper.selectUserById(id);
		if (idInt != 0) {
			int max = 9999, min = 1000;
			long randomNum = System.currentTimeMillis();
			int ran3 = (int) (randomNum % (max - min) + min);
			id = "SX_XSW_" + ran3;
			sysUserValue.setId(id);
		}

		String username = sysUserValue.getUsername();
		int usernameInt = selectMapper.selectUserByUsername(username);
		if (usernameInt != 0) {
			userResult.setCode("erro");
			userResult.setMsg("添加失败，用户已存在1！");
			return userResult;
		}

		String name = sysUserValue.getName();
		int nameInt = selectMapper.selectUserByName(name);
		if (nameInt != 0) {
			userResult.setCode("erro");
			userResult.setMsg("添加失败，用户已存在2！");
			return userResult;
		}

		int sysUserValueInt = insertMapper.insertSysUserValue(sysUserValue);
		if (sysUserValueInt == 0) {
			userResult.setCode("erro");
			userResult.setMsg("添加失败，用户已存在3！");
			return userResult;
		}

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);
		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(name2);

		String title = "用户：" + name2 + ";于" + datetime + "添加了id：" + id + "的" + name + "的账号";
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("添加用户");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public UserResult taskAdd(SysTaskValue sysTaskValue) {
		UserResult userResult = new UserResult();
		userResult.setMsg("补助申请成功！");

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);
		String id = "SX_XSW_TASK_" + ran3;
		sysTaskValue.setId(id);

		String name = sysTaskValue.getName();
		String goTime = sysTaskValue.getGoTime();
		String[] split = goTime.split(" ");
		goTime = split[0];
		String backTime = sysTaskValue.getBackTime();
		String[] split2 = backTime.split(" ");
		backTime = split2[0];
		String place = sysTaskValue.getPlace();
		String title = name + "  " + goTime + "至" + backTime + place + "  出差补助申请";
		sysTaskValue.setTitle(title);

		sysTaskValue.setStatus("0");
		sysTaskValue.setProgress("0");

		sysTaskValue.setExecutive_value("暂未审核，请等候！");
		sysTaskValue.setExecutive_time(datetime);
		sysTaskValue.setManager_time(datetime);
		sysTaskValue.setManager_value("暂未审核，请等候！");
		sysTaskValue.setAccounting_time(datetime);
		sysTaskValue.setAccounting_value("暂未审核，请等候！");
		sysTaskValue.setFinance_time(datetime);
		sysTaskValue.setFinance_value("暂未审核，请等候！");
		sysTaskValue.setExecutive("0");
		sysTaskValue.setAccounting("0");
		sysTaskValue.setManager("0");
		sysTaskValue.setFinance("0");

		String unitPrice = sysTaskValue.getUnitPrice();
		String day = sysTaskValue.getDay();
		String totalPrice = "0";
		double d1 = Double.valueOf(unitPrice);
		double d2 = Double.valueOf(day);

		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		double doubleValue = b1.multiply(b2).doubleValue();

		totalPrice = (doubleValue + "");
		sysTaskValue.setTotalPrice(totalPrice);

		int insertSysTaskValueInt = insertMapper.insertSysTaskValue(sysTaskValue);
		if (insertSysTaskValueInt == 0) {
			userResult.setCode("erro");
			userResult.setMsg("添加补助申请失败！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(name);

		title = "用户：" + name + ";于" + datetime + "提交了id：" + id + "的" + title;
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("添加补助申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public UserResult reimAdd(SysReimValue sysReimValue) {
		UserResult userResult = new UserResult();
		userResult.setMsg("报销申请成功！");

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);
		String id = "SX_XSW_REIM_" + ran3;
		sysReimValue.setId(id);

		String name = sysReimValue.getName();
		String place = sysReimValue.getPlace();
		String project = sysReimValue.getProject();
		String goods = sysReimValue.getGoods();
		String title = name + " 用于 " + place + project + "  " + goods + "  的报销申请";
		sysReimValue.setTitle(title);
		sysReimValue.setProgress("0");
		sysReimValue.setManager("0");
		sysReimValue.setManager_time(datetime);
		sysReimValue.setManager_value("尚未审核，请等候！");
		sysReimValue.setAccounting_time(datetime);
		sysReimValue.setAccounting_value("尚未审核，请等候！");
		sysReimValue.setFinance_time(datetime);
		sysReimValue.setFinance_value("尚未审核，请等候！");
		sysReimValue.setExecutive("0");
		sysReimValue.setAccounting("0");
		sysReimValue.setFinance("0");
		sysReimValue.setStatus("0");
		sysReimValue.setAddress("/image/123.jpg");

		int insertSysReimValueInt = insertMapper.insertSysReimValue(sysReimValue);
		if (insertSysReimValueInt == 0) {
			userResult.setCode("erro");
			userResult.setMsg("报销申请失败！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(name);

		title = "用户：" + name + ";于" + datetime + "提交了id：" + id + "的" + title;
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("添加报销申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public UserResult purchAdd(SysPurchValue sysPurchValue) {
		UserResult userResult = new UserResult();
		userResult.setMsg("报销申请成功！");

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);
		String id = "SX_XSW_PURCH_" + ran3;
		sysPurchValue.setId(id);

		String name = sysPurchValue.getName();
		String place = sysPurchValue.getPlace();
		String project = sysPurchValue.getProject();
		String goods = sysPurchValue.getGoods();
		String title = name + " 用于 " + place + project + "  " + goods + "  的采购申请";
		sysPurchValue.setTitle(title);

		sysPurchValue.setManager("0");
		sysPurchValue.setExecutive("0");
		sysPurchValue.setExecutive_value("暂未审核，请等候！");
		sysPurchValue.setExecutive_time(datetime);
		sysPurchValue.setManager_time(datetime);
		sysPurchValue.setManager_value("暂未审核，请等候！");
		sysPurchValue.setStatus("0");
		sysPurchValue.setProgress("0");

		int insertSysPurchValueInt = insertMapper.insertSysPurchValue(sysPurchValue);
		if (insertSysPurchValueInt == 0) {
			userResult.setCode("erro");
			userResult.setMsg("添加失败！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(name);

		title = "用户：" + name + ";于" + datetime + "提交了id：" + id + "的" + title;
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("添加采购申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public UserResult loanAdd(SysLoanValue sysLoanValue) {
		UserResult userResult = new UserResult();
		userResult.setMsg("借款申请成功！");

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);
		String id = "SX_XSW_LOAN_" + ran3;
		sysLoanValue.setId(id);

		String name = sysLoanValue.getName();
		String place = sysLoanValue.getPlace();
		String project = sysLoanValue.getProject();
		String price = sysLoanValue.getPrice();
		String title = name + " 用于 " + place + project + "  的" + price + "元的借款申请";
		sysLoanValue.setTitle(title);

		sysLoanValue.setManager("0");
		sysLoanValue.setFinance("0");
		sysLoanValue.setFinance_value("暂未审核，请等候！");
		sysLoanValue.setFinance_time(datetime);
		sysLoanValue.setManager_time(datetime);
		sysLoanValue.setManager_value("暂未审核，请等候！");
		sysLoanValue.setStatus("0");
		sysLoanValue.setProgress("0");

		int insertSysLoanValueInt = insertMapper.insertSysLoanValue(sysLoanValue);
		if (insertSysLoanValueInt == 0) {
			userResult.setCode("erro");
			userResult.setMsg("添加失败！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(name);

		title = "用户：" + name + ";于" + datetime + "提交了id：" + id + "的" + title;
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("添加借款申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;

	}

	@Override
	public void insertPictureSrc(SysReimValue sysReimValue) {
		updataMapper.updataPictureSrc(sysReimValue);

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(sysReimValue.getName());

		String title = "用户：" + sysReimValue.getName() + ";于" + datetime + "添加报销图片于id：" + sysReimValue.getId() + "的"
				+ sysReimValue.getTitle();
		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);
		String id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("添加报销图片");
		insertMapper.insertOperationValue(sysOperationValue);

	}

	@Override
	public void insertTaskPrintCount(String id) {

		String count = selectMapper.selectTaskPrintCount(id);
		if (count == null || count == "")
			count = "0";

		Integer valueOf = Integer.valueOf(count);
		count = valueOf + 1 + "";

		updataMapper.insertTaskPrintCount(id, count);

	}

	@Override
	public void insertReimPrintCount(String id) {

		String count = selectMapper.selectReimPrintCount(id);
		if (count == null || count == "")
			count = "0";

		Integer valueOf = Integer.valueOf(count);
		count = valueOf + 1 + "";

		updataMapper.insertReimPrintCount(id, count);

	}

}
