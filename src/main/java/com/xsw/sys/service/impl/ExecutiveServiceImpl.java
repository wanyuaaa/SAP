package com.xsw.sys.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysOperationValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.vo.UserResult;
import com.xsw.sys.mapper.InsertMapper;
import com.xsw.sys.mapper.SelectMapper;
import com.xsw.sys.mapper.UpdateMapper;
import com.xsw.sys.service.ExecutiveService;

@Service
public class ExecutiveServiceImpl implements ExecutiveService {

	@Autowired
	private SelectMapper selectMapper;

	@Autowired
	private UpdateMapper upadteMapper;

	@Autowired
	private InsertMapper insertMapper;

	@Override
	public UserResult examineLoan(SysLoanValue sysLoanValue, String examine, String examineName) {
		UserResult userResult = new UserResult();
		userResult.setMsg("审核成功！");
		String member = selectMapper.selectExecutiveByName(examineName);

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		String reason = sysLoanValue.getReason();

		SysLoanValue sysLoanValue2 = selectMapper.selectLoanByIdValue(sysLoanValue.getId());
		String status = sysLoanValue2.getStatus();
		String manager_time = sysLoanValue2.getManager_time();
		String manager_value = sysLoanValue2.getManager_value();
		String finance_time = sysLoanValue2.getFinance_time();
		String finance_value = sysLoanValue2.getFinance_value();

		sysLoanValue.setManager_time(manager_time);
		sysLoanValue.setManager_value(manager_value);
		sysLoanValue.setFinance_time(finance_time);
		sysLoanValue.setFinance_value(finance_value);

		if (examine.equals("1")) {
			if (status.equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核驳回失败，该审核已完成！");
				return userResult;
			}

			sysLoanValue.setStatus("2");
			sysLoanValue.setManager("0");
			sysLoanValue.setFinance("0");
			sysLoanValue.setProgress("0");
			if (reason.equals(""))
				reason = "申请驳回！";

			switch (member) {
			case "0":
				sysLoanValue.setManager_value(reason);
				sysLoanValue.setManager_time(datetime);
				break;
			case "3":
				sysLoanValue.setFinance_value(reason);
				sysLoanValue.setFinance_time(datetime);
				break;
			}
			sysLoanValue.setReason("");

			int count = upadteMapper.updateExamineLoan(sysLoanValue);
			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("审核驳回失败，请重试！");
				return userResult;
			}

			SysOperationValue sysOperationValue = new SysOperationValue();
			sysOperationValue.setCreateTime(datetime);
			sysOperationValue.setName(examineName);

			int max = 99999999, min = 10000000;
			long randomNum = System.currentTimeMillis();
			int ran3 = (int) (randomNum % (max - min) + min);

			String title = "用户：" + examineName + ";于" + datetime + "驳回了用户：" + sysLoanValue2.getName() + ";id："
					+ sysLoanValue2.getId() + "的" + sysLoanValue2.getTitle();
			String id = "SX_XSW_OPER_" + ran3;

			sysOperationValue.setId(id);
			sysOperationValue.setTitle(title);
			sysOperationValue.setType("驳回补助申请");
			count = insertMapper.insertOperationValue(sysOperationValue);

			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("日志记录失败，请确认！");
				return userResult;
			}
			return userResult;
		}

		if (reason.equals(""))
			reason = "审核通过";

		sysLoanValue.setStatus("0");
		switch (member) {
		case "0":
			if (sysLoanValue2.getManager().equals("1") || sysLoanValue2.getStatus().equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核已通过，请勿重复通过！");
				return userResult;
			}
			sysLoanValue.setManager("1");
			sysLoanValue.setFinance("0");
			sysLoanValue.setProgress("50%");
			sysLoanValue.setManager_time(datetime);
			sysLoanValue.setManager_value(reason);
			break;
		case "3":
			if (sysLoanValue2.getFinance().equals("1") || sysLoanValue2.getStatus().equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核已通过！");
				return userResult;
			}
			sysLoanValue.setManager("1");
			sysLoanValue.setFinance("1");
			sysLoanValue.setProgress("100%");
			sysLoanValue.setFinance_time(datetime);
			sysLoanValue.setFinance_value(reason);
			sysLoanValue.setStatus("1");
			break;
		}

		sysLoanValue.setReason("");
		// 修改sql
		int updateExamineTask = upadteMapper.updateExamineLoanPass(sysLoanValue);
		if (updateExamineTask == 0) {
			userResult.setCode("erro");
			userResult.setMsg("审核失败，请重试！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(examineName);

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		String title = "用户：" + examineName + ";于" + datetime + "通过了用户：" + sysLoanValue2.getName() + ";id："
				+ sysLoanValue2.getId() + "的" + sysLoanValue2.getTitle();
		String id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("通过借款申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public UserResult examineTask(SysTaskValue sysTaskValue, String examine, String examineName) {
		UserResult userResult = new UserResult();
		userResult.setMsg("审核成功！");
		String member = selectMapper.selectExecutiveByName(examineName);

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		String reason = sysTaskValue.getReason();

		SysTaskValue sysTaskValue2 = selectMapper.selectTaskByIdValue(sysTaskValue.getId());
		String status = sysTaskValue2.getStatus();
		String executive_time = sysTaskValue2.getExecutive_time();
		String executive_value = sysTaskValue2.getExecutive_value();
		String accounting_time = sysTaskValue2.getAccounting_time();
		String accounting_value = sysTaskValue2.getAccounting_value();
		String manager_time = sysTaskValue2.getManager_time();
		String manager_value = sysTaskValue2.getManager_value();
		String finance_time = sysTaskValue2.getFinance_time();
		String finance_value = sysTaskValue2.getFinance_value();

		if (status.equals("2")) {
			if (member.equals("0")) {
				if (!finance_value.equals("暂未审核，请等待！")) {
					userResult.setCode("erro");
					userResult.setMsg("审核已驳回，请等待重新提交！");
					return userResult;
				}
			}
		}

		sysTaskValue.setExecutive_time(executive_time);
		sysTaskValue.setExecutive_value(executive_value);
		sysTaskValue.setAccounting_time(accounting_time);
		sysTaskValue.setAccounting_value(accounting_value);
		sysTaskValue.setManager_time(manager_time);
		sysTaskValue.setManager_value(manager_value);
		sysTaskValue.setFinance_time(finance_time);
		sysTaskValue.setFinance_value(finance_value);

		if (examine.equals("1")) {
			if (status.equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核驳回失败，该审核已完成！");
				return userResult;
			}

			sysTaskValue.setStatus("2");
			sysTaskValue.setExecutive("0");
			sysTaskValue.setManager("0");
			sysTaskValue.setAccounting("0");
			sysTaskValue.setFinance("0");
			sysTaskValue.setProgress("0");
			if (reason.equals(""))
				reason = "申请驳回！";

			switch (member) {
			case "0":
				sysTaskValue.setManager_value(reason);
				sysTaskValue.setManager_time(datetime);
				break;
			case "1":
				sysTaskValue.setExecutive_value(reason);
				sysTaskValue.setExecutive_time(datetime);
				break;
			case "2":
				sysTaskValue.setAccounting_value(reason);
				sysTaskValue.setAccounting_time(datetime);
				break;
			case "3":
				sysTaskValue.setFinance_value(reason);
				sysTaskValue.setFinance_time(datetime);
				break;
			}
			sysTaskValue.setReason("");

			int count = upadteMapper.updateExamineTask(sysTaskValue);
			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("审核驳回失败，请重试！");
				return userResult;
			}

			SysOperationValue sysOperationValue = new SysOperationValue();
			sysOperationValue.setCreateTime(datetime);
			sysOperationValue.setName(examineName);

			int max = 99999999, min = 10000000;
			long randomNum = System.currentTimeMillis();
			int ran3 = (int) (randomNum % (max - min) + min);

			String title = "用户：" + examineName + ";于" + datetime + "驳回了用户：" + sysTaskValue2.getName() + ";id："
					+ sysTaskValue2.getId() + "的" + sysTaskValue2.getTitle();
			String id = "SX_XSW_OPER_" + ran3;

			sysOperationValue.setId(id);
			sysOperationValue.setTitle(title);
			sysOperationValue.setType("驳回补助申请");
			count = insertMapper.insertOperationValue(sysOperationValue);

			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("日志记录失败，请确认！");
				return userResult;
			}
			return userResult;
		}

		if (reason.equals(""))
			reason = "审核通过";

		sysTaskValue.setStatus("0");
		switch (member) {
		case "0":
			if (sysTaskValue2.getManager().equals("1") || sysTaskValue2.getStatus().equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核已通过，请勿重复通过！");
				return userResult;
			}
			sysTaskValue.setExecutive("1");
			sysTaskValue.setManager("1");
			sysTaskValue.setAccounting("1");
			sysTaskValue.setFinance("0");
			sysTaskValue.setProgress("75%");
			sysTaskValue.setManager_time(datetime);
			sysTaskValue.setManager_value(reason);
			break;
		case "1":
			if (sysTaskValue2.getExecutive().equals("1") || sysTaskValue2.getStatus().equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核已通过！");
				return userResult;
			}
			sysTaskValue.setManager("0");
			sysTaskValue.setExecutive("1");
			sysTaskValue.setAccounting("0");
			sysTaskValue.setFinance("0");
			sysTaskValue.setProgress("25%");
			sysTaskValue.setExecutive_time(datetime);
			sysTaskValue.setExecutive_value(reason);
			break;
		case "2":
			if (sysTaskValue2.getAccounting().equals("1") || sysTaskValue2.getStatus().equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核已通过！");
				return userResult;
			}
			sysTaskValue.setManager("0");
			sysTaskValue.setExecutive("1");
			sysTaskValue.setAccounting("1");
			sysTaskValue.setFinance("0");
			sysTaskValue.setProgress("50%");
			sysTaskValue.setAccounting_time(datetime);
			sysTaskValue.setAccounting_value(reason);
			break;
		case "3":
			if (sysTaskValue2.getFinance().equals("1") || sysTaskValue2.getStatus().equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核已通过！");
				return userResult;
			}
			sysTaskValue.setManager("1");
			sysTaskValue.setExecutive("1");
			sysTaskValue.setAccounting("1");
			sysTaskValue.setFinance("1");
			sysTaskValue.setProgress("100%");
			sysTaskValue.setFinance_time(datetime);
			sysTaskValue.setFinance_value(reason);
			sysTaskValue.setStatus("1");
			break;
		}

		sysTaskValue.setReason("");
		// 修改sql
		int updateExamineTask = upadteMapper.updateExamineTaskPass(sysTaskValue);
		if (updateExamineTask == 0) {
			userResult.setCode("erro");
			userResult.setMsg("审核失败，请重试！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(examineName);

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		String title = "用户：" + examineName + ";于" + datetime + "通过了用户：" + sysTaskValue2.getName() + ";id："
				+ sysTaskValue2.getId() + "的" + sysTaskValue2.getTitle();
		String id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("通过补助申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public UserResult examinePurch(SysPurchValue sysPurchValue, String examine, String examineName) {
		UserResult userResult = new UserResult();
		userResult.setMsg("审核成功！");

		String member = selectMapper.selectExecutiveByName(examineName);

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		String reason = sysPurchValue.getReason();

		SysPurchValue sysPurchValue2 = selectMapper.selectPurchByIdValue(sysPurchValue.getId());
		String status = sysPurchValue2.getStatus();
		String manager_time = sysPurchValue2.getManager_time();
		String manager_value = sysPurchValue2.getManager_value();
		String executive_time = sysPurchValue2.getExecutive_time();
		String executive_value = sysPurchValue2.getExecutive_value();

		if (status.equals("2") && member.equals("0") && !executive_value.equals("暂未审核，请等待！")) {
			userResult.setCode("erro");
			userResult.setMsg("审核已驳回，请等待重新提交！");
			return userResult;
		}

		sysPurchValue.setManager_time(manager_time);
		sysPurchValue.setManager_value(manager_value);
		sysPurchValue.setExecutive_time(executive_time);
		sysPurchValue.setExecutive_value(executive_value);

		if (examine.equals("1")) {
			if (status.equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核驳回失败，该审核已完成！");
				return userResult;
			}

			sysPurchValue.setStatus("2");
			sysPurchValue.setExecutive("0");
			sysPurchValue.setManager("0");
			sysPurchValue.setProgress("0");
			if (reason.equals(""))
				reason = "申请驳回！";

			switch (member) {
			case "0":
				sysPurchValue.setManager_value(reason);
				sysPurchValue.setManager_time(datetime);
				break;
			case "1":
				sysPurchValue.setExecutive_value(reason);
				sysPurchValue.setExecutive_time(datetime);
				break;
			}
			sysPurchValue.setReason("");

			int count = upadteMapper.updateExaminePurch(sysPurchValue);
			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("审核驳回失败，请重试！");
				return userResult;
			}

			SysOperationValue sysOperationValue = new SysOperationValue();
			sysOperationValue.setCreateTime(datetime);
			sysOperationValue.setName(examineName);

			int max = 99999999, min = 10000000;
			long randomNum = System.currentTimeMillis();
			int ran3 = (int) (randomNum % (max - min) + min);

			String title = "用户：" + examineName + ";于" + datetime + "驳回了用户：" + sysPurchValue2.getName() + ";id："
					+ sysPurchValue2.getId() + "的" + sysPurchValue2.getTitle();
			String id = "SX_XSW_OPER_" + ran3;

			sysOperationValue.setId(id);
			sysOperationValue.setTitle(title);
			sysOperationValue.setType("驳回采购申请");
			count = insertMapper.insertOperationValue(sysOperationValue);

			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("日志记录失败，请确认！");
				return userResult;
			}
			return userResult;
		} else if (examine.equals("2")) {
			if (reason.equals(""))
				reason = "申请采购中！";

			sysPurchValue.setStatus("0");

			switch (member) {
			case "0":
				userResult.setCode("erro");
				userResult.setMsg("无法采购，请勿勾选！");
				return userResult;
			case "1":
				if (sysPurchValue2.getExecutive().equals("1") || sysPurchValue2.getStatus().equals("1")
						|| sysPurchValue2.getProgress().equals("66%")) {
					userResult.setCode("erro");
					userResult.setMsg("采购或审核已通过，请勿重复提交！");
					return userResult;
				}
				sysPurchValue.setManager("1");
				sysPurchValue.setExecutive("0");
				sysPurchValue.setProgress("66%");
				sysPurchValue.setStatus("3");
				sysPurchValue.setExecutive_time(datetime);
				sysPurchValue.setExecutive_value(reason);
				break;
			}
			sysPurchValue.setReason("");

			int count = upadteMapper.updateExaminePurch(sysPurchValue);
			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("审核采购失败，请重试！");
				return userResult;
			}

			SysOperationValue sysOperationValue = new SysOperationValue();
			sysOperationValue.setCreateTime(datetime);
			sysOperationValue.setName(examineName);

			int max = 99999999, min = 10000000;
			long randomNum = System.currentTimeMillis();
			int ran3 = (int) (randomNum % (max - min) + min);

			String title = "用户：" + examineName + ";于" + datetime + "通过了用户：" + sysPurchValue2.getName() + ";id："
					+ sysPurchValue2.getId() + "的" + sysPurchValue2.getTitle() + "申请,正在采购...";
			String id = "SX_XSW_OPER_" + ran3;

			sysOperationValue.setId(id);
			sysOperationValue.setTitle(title);
			sysOperationValue.setType("通过采购申请");
			count = insertMapper.insertOperationValue(sysOperationValue);

			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("日志记录失败，请确认！");
				return userResult;
			}
			return userResult;
		}

		if (reason.equals(""))
			reason = "审核通过";

		sysPurchValue.setStatus("0");

		switch (member) {
		case "0":
			if (sysPurchValue2.getManager().equals("1") || sysPurchValue2.getStatus().equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核已通过，请勿重复通过！");
				return userResult;
			}
			sysPurchValue.setExecutive("0");
			sysPurchValue.setManager("1");
			sysPurchValue.setProgress("33%");
			sysPurchValue.setManager_time(datetime);
			sysPurchValue.setManager_value(reason);
			break;
		case "1":
			if (sysPurchValue2.getExecutive().equals("1") || sysPurchValue2.getStatus().equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核已通过！");
				return userResult;
			}

			if (sysPurchValue2.getProgress().equals("33%")) {
				userResult.setCode("erro");
				userResult.setMsg("采购未进行，请先执行采购操作！");
				return userResult;
			}
			sysPurchValue.setManager("1");
			sysPurchValue.setExecutive("1");
			sysPurchValue.setProgress("100%");
			sysPurchValue.setStatus("1");
			sysPurchValue.setExecutive_time(datetime);
			sysPurchValue.setExecutive_value(reason);
			break;
		}

		sysPurchValue.setReason("");
		int updateExaminePurch = upadteMapper.updateExaminePurchPass(sysPurchValue);
		if (updateExaminePurch == 0) {
			userResult.setCode("erro");
			userResult.setMsg("审核失败，请重试！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(examineName);

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		String title = "用户：" + examineName + ";于" + datetime + "通过了用户：" + sysPurchValue2.getName() + ";id："
				+ sysPurchValue2.getId() + "的" + sysPurchValue2.getTitle();
		String id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("完成采购申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;

	}

	@Override
	public UserResult examineReim(SysReimValue sysReimValue, String examine, String examineName) {
		UserResult userResult = new UserResult();
		userResult.setMsg("审核成功！");
		String member = selectMapper.selectExecutiveByName(examineName);

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		String reason = sysReimValue.getReason();

		SysReimValue sysReimValue2 = selectMapper.selectReimByIdValue(sysReimValue.getId());
		String status = sysReimValue2.getStatus();
		String accounting_time = sysReimValue2.getAccounting_time();
		String accounting_value = sysReimValue2.getAccounting_value();
		String finance_time = sysReimValue2.getFinance_time();
		String finance_value = sysReimValue2.getFinance_value();
		String manager_time = sysReimValue2.getManager_time();
		String manager_value = sysReimValue2.getManager_value();

		if (status.equals("2")) {

			if (member.equals("2")) {
				if (!manager_value.equals("尚未审核，请等待！")) {
					userResult.setCode("erro");
					userResult.setMsg("审核已驳回，请等待重新提交！");
					return userResult;
				}
			} else if (member.equals("0")) {
				if (!finance_value.equals("尚未审核，请等待！")) {
					userResult.setCode("erro");
					userResult.setMsg("审核已驳回，请等待重新提交！");
					return userResult;
				}
			}

		}

		sysReimValue.setAccounting_time(accounting_time);
		sysReimValue.setAccounting_value(accounting_value);
		sysReimValue.setManager_time(manager_time);
		sysReimValue.setManager_value(manager_value);
		sysReimValue.setFinance_time(finance_time);
		sysReimValue.setFinance_value(finance_value);

		if (examine.equals("1")) {
			if (status.equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核驳回失败，该审核已完成！");
				return userResult;
			}

			sysReimValue.setStatus("2");
			sysReimValue.setManager("0");
			sysReimValue.setAccounting("0");
			sysReimValue.setFinance("0");
			sysReimValue.setProgress("0");
			if (reason.equals(""))
				reason = "申请驳回！";

			switch (member) {
			case "0":
				sysReimValue.setManager_value(reason);
				sysReimValue.setManager_time(datetime);
				break;
			case "2":
				sysReimValue.setAccounting_value(reason);
				sysReimValue.setAccounting_time(datetime);
				break;
			case "3":
				sysReimValue.setFinance_value(reason);
				sysReimValue.setFinance_time(datetime);
				break;
			}
			sysReimValue.setReason("");

			int count = upadteMapper.updateExamineReim(sysReimValue);
			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("审核驳回失败，请重试！");
				return userResult;
			}

			SysOperationValue sysOperationValue = new SysOperationValue();
			sysOperationValue.setCreateTime(datetime);
			sysOperationValue.setName(examineName);

			int max = 99999999, min = 10000000;
			long randomNum = System.currentTimeMillis();
			int ran3 = (int) (randomNum % (max - min) + min);

			String title = "用户：" + examineName + ";于" + datetime + "驳回了用户：" + sysReimValue2.getName() + ";id："
					+ sysReimValue2.getId() + "的" + sysReimValue2.getTitle();
			String id = "SX_XSW_OPER_" + ran3;

			sysOperationValue.setId(id);
			sysOperationValue.setTitle(title);
			sysOperationValue.setType("驳回报销申请");
			count = insertMapper.insertOperationValue(sysOperationValue);

			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("日志记录失败，请确认！");
				return userResult;
			}
			return userResult;
		}

		if (reason.equals(""))
			reason = "审核通过";

		sysReimValue.setStatus("0");
		switch (member) {
		case "0":
			if (sysReimValue2.getManager().equals("1") || sysReimValue2.getStatus().equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核已通过！");
				return userResult;
			}
			sysReimValue.setManager("1");
			sysReimValue.setAccounting("1");
			sysReimValue.setFinance("0");
			sysReimValue.setProgress("66%");
			sysReimValue.setManager_time(datetime);
			sysReimValue.setManager_value(reason);
			break;
		case "2":
			if (sysReimValue2.getAccounting().equals("1") || sysReimValue2.getStatus().equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核已通过！");
				return userResult;
			}
			sysReimValue.setManager("0");
			sysReimValue.setAccounting("1");
			sysReimValue.setFinance("0");
			sysReimValue.setProgress("33%");
			sysReimValue.setAccounting_time(datetime);
			sysReimValue.setAccounting_value(reason);
			break;
		case "3":
			if (sysReimValue2.getFinance().equals("1") || sysReimValue2.getStatus().equals("1")) {
				userResult.setCode("erro");
				userResult.setMsg("审核已通过！");
				return userResult;
			}
			sysReimValue.setManager("1");
			sysReimValue.setAccounting("1");
			sysReimValue.setFinance("1");
			sysReimValue.setProgress("100%");
			sysReimValue.setStatus("1");
			sysReimValue.setFinance_time(datetime);
			sysReimValue.setFinance_value(reason);
			break;
		}

		sysReimValue.setReason("");
		int updateExamineReim = upadteMapper.updateExamineReimPass(sysReimValue);
		if (updateExamineReim == 0) {
			userResult.setCode("erro");
			userResult.setMsg("审核失败，请重试！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(examineName);

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		String title = "用户：" + examineName + ";于" + datetime + "通过了用户：" + sysReimValue2.getName() + ";id："
				+ sysReimValue2.getId() + "的" + sysReimValue2.getTitle();
		String id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("通过报销申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

}
