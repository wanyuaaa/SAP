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
import com.xsw.common.entry.SysUserUpdateValue;
import com.xsw.common.entry.SysUserValue;
import com.xsw.common.vo.JsonResult;
import com.xsw.common.vo.UserResult;
import com.xsw.sys.mapper.InsertMapper;
import com.xsw.sys.mapper.SelectMapper;
import com.xsw.sys.mapper.UpdateMapper;
import com.xsw.sys.service.UpdateService;

@Service
public class UpdateServiceImpl implements UpdateService {

	@Autowired
	private SelectMapper selectMapper;

	@Autowired
	private UpdateMapper updateMapper;

	@Autowired
	private InsertMapper insertMapper;

	@Override
	public UserResult updateUser(SysUserValue sysUserValue, String name2) {
		UserResult userResult = new UserResult();

		String id = sysUserValue.getId();
		int selectUserById = selectMapper.selectUserById(id);
		if (selectUserById == 0) {
			userResult.setCode("erro");
			userResult.setMsg("查无此条数据，修改失败！");
			return userResult;
		}

//		String username = sysUserValue.getUsername();
//		int usernameInt = selectMapper.selectUserByUsername(username);
//		if (usernameInt != 0) {
//			userResult.setCode("erro");
//			userResult.setMsg("修改失败，用户名已存在1！");
//			return userResult;
//		}
//
		String name = sysUserValue.getName();
//		int nameInt = selectMapper.selectUserByName(name);
//		if (nameInt != 0) {
//			userResult.setCode("erro");
//			userResult.setMsg("修改失败，用户名已存在2！");
//			return userResult;
//		}

		int updateUserInt = updateMapper.updateUser(sysUserValue);
		if (updateUserInt == 0) {
			userResult.setCode("erro");
			userResult.setMsg("修改失败！");
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

		String title = "用户：" + name2 + ";于" + datetime + "修改了id：" + id + "的" + name + "的账号";
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
	public UserResult updateTask(SysTaskValue sysTaskValue) {
		UserResult userResult = new UserResult();
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		String id = sysTaskValue.getId();
		int selectUserById = selectMapper.selectTaskById(id);
		if (selectUserById == 0) {
			userResult.setCode("erro");
			userResult.setMsg("查无此条数据，修改失败！");
			return userResult;
		}

		SysTaskValue selectTaskByIdValue = selectMapper.selectTaskByIdValue(id);
		String name = selectTaskByIdValue.getName();
		String goTime = sysTaskValue.getGoTime();
		String[] split = goTime.split(" ");
		goTime = split[0];
		String backTime = sysTaskValue.getBackTime();
		String[] split2 = backTime.split(" ");
		backTime = split2[0];
		String place = sysTaskValue.getPlace();
		String title = name + "  " + goTime + "至" + backTime + place + "  出差报销申请";
		sysTaskValue.setTitle(title);

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
		String progress = selectTaskByIdValue.getProgress();

		if (selectTaskByIdValue.getStatus().equals("2")) {
			sysTaskValue.setStatus("0");
			sysTaskValue.setReason("");
			sysTaskValue.setExecutive("0");
			sysTaskValue.setExecutive_time(datetime);
			sysTaskValue.setExecutive_value("暂未审核，请等待！");
			sysTaskValue.setAccounting("0");
			sysTaskValue.setAccounting_time(datetime);
			sysTaskValue.setAccounting_value("暂未审核，请等待！");
			sysTaskValue.setManager("0");
			sysTaskValue.setManager_time(datetime);
			sysTaskValue.setManager_value("暂未审核，请等待！");
			sysTaskValue.setFinance("0");
			sysTaskValue.setFinance_time(datetime);
			sysTaskValue.setFinance_value("暂未审核，请等待！");
		} else if (selectTaskByIdValue.getStatus().equals("1")) {
			userResult.setCode("erro");
			userResult.setMsg("审核已完成，请勿提交修改！");
			return userResult;
		} else {
			if (!progress.equals("0")) {
				userResult.setCode("erro");
				userResult.setMsg("审核中，请勿提交修改！");
				return userResult;
			}
			sysTaskValue.setStatus("0");
			sysTaskValue.setReason("");
			sysTaskValue.setExecutive("0");
			sysTaskValue.setExecutive_time(datetime);
			sysTaskValue.setExecutive_value("暂未审核，请等待！");
			sysTaskValue.setAccounting("0");
			sysTaskValue.setAccounting_time(datetime);
			sysTaskValue.setAccounting_value("暂未审核，请等待！");
			sysTaskValue.setManager("0");
			sysTaskValue.setManager_time(datetime);
			sysTaskValue.setManager_value("暂未审核，请等待！");
			sysTaskValue.setFinance("0");
			sysTaskValue.setFinance_time(datetime);
			sysTaskValue.setFinance_value("暂未审核，请等待！");
		}

		int updateTaskInt = updateMapper.updateTask(sysTaskValue);
		if (updateTaskInt == 0) {
			userResult.setCode("erro");
			userResult.setMsg("修改失败！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(name);

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		title = "用户：" + name + ";于" + datetime + "修改了id：" + id + "的" + title;
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("修改补助申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public UserResult updateReim(SysReimValue sysReimValue) {
		UserResult userResult = new UserResult();
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		String id = sysReimValue.getId();
		int selectReimById = selectMapper.selectReimById(id);
		if (selectReimById == 0) {
			userResult.setCode("erro");
			userResult.setMsg("查无此条数据，修改失败！");
			return userResult;
		}

		SysReimValue selectReimByIdValue = selectMapper.selectReimByIdValue(id);
		String name = selectReimByIdValue.getName();
		String place = sysReimValue.getPlace();
		String project = sysReimValue.getProject();
		String goods = sysReimValue.getGoods();

		String title = name + " 用于 " + place + project + "  " + goods + "  的报销申请";
		sysReimValue.setTitle(title);

		String progress = selectReimByIdValue.getProgress();
		if (selectReimByIdValue.getStatus().equals("2")) {
			sysReimValue.setStatus("0");
			sysReimValue.setReason("");
			sysReimValue.setAccounting("0");
			sysReimValue.setAccounting_time(datetime);
			sysReimValue.setAccounting_value("暂未审核，请等待！");
			sysReimValue.setManager("0");
			sysReimValue.setManager_time(datetime);
			sysReimValue.setManager_value("暂未审核，请等待！");
			sysReimValue.setFinance("0");
			sysReimValue.setFinance_time(datetime);
			sysReimValue.setFinance_value("暂未审核，请等待！");
		} else if (selectReimByIdValue.getStatus().equals("1")) {
			userResult.setCode("erro");
			userResult.setMsg("审核已完成，请勿提交修改！");
			return userResult;
		} else {
			if (!progress.equals("0")) {
				userResult.setCode("erro");
				userResult.setMsg("审核中，请勿提交修改！");
				return userResult;
			}
			// 修改审核信息
			sysReimValue.setStatus("0");
			sysReimValue.setReason("");
			sysReimValue.setAccounting("0");
			sysReimValue.setAccounting_time(datetime);
			sysReimValue.setAccounting_value("暂未审核，请等待！");
			sysReimValue.setManager("0");
			sysReimValue.setManager_time(datetime);
			sysReimValue.setManager_value("暂未审核，请等待！");
			sysReimValue.setFinance("0");
			sysReimValue.setFinance_time(datetime);
			sysReimValue.setFinance_value("暂未审核，请等待！");
		}

		// 修改sql语句
		int updateReimInt = updateMapper.updateReim(sysReimValue);
		if (updateReimInt == 0) {
			userResult.setCode("erro");
			userResult.setMsg("修改失败！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(name);

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		title = "用户：" + name + ";于" + datetime + "修改了id：" + id + "的" + title;
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("修改报销申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public UserResult updatePurch(SysPurchValue sysPurchValue) {
		UserResult userResult = new UserResult();
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		String id = sysPurchValue.getId();
		int selectPurchById = selectMapper.selectPurchById(id);
		if (selectPurchById == 0) {
			userResult.setCode("erro");
			userResult.setMsg("查无此条数据，修改失败！");
			return userResult;
		}

		SysPurchValue selectPurchByIdValue = selectMapper.selectPurchByIdValue(id);
		String name = selectPurchByIdValue.getName();
		String place = sysPurchValue.getPlace();
		String project = sysPurchValue.getProject();
		String goods = sysPurchValue.getGoods();

		String title = name + " 用于 " + place + project + "  " + goods + "  的采购申请";
		sysPurchValue.setTitle(title);

		String progress = selectPurchByIdValue.getProgress();
		if (selectPurchByIdValue.getStatus().equals("2")) {
			sysPurchValue.setStatus("0");
			sysPurchValue.setReason("");
		} else if (selectPurchByIdValue.getStatus().equals("1")) {
			userResult.setCode("erro");
			userResult.setMsg("审核已完成，请勿提交修改！");
			return userResult;
		} else {
			if (!progress.equals("0")) {
				userResult.setCode("erro");
				userResult.setMsg("审核中，请勿提交修改！");
				return userResult;
			}
			sysPurchValue.setStatus("0");
			sysPurchValue.setReason("");
			sysPurchValue.setManager("0");
			sysPurchValue.setManager_time(datetime);
			sysPurchValue.setManager_value("暂未审核，请等待！");
			sysPurchValue.setExecutive("0");
			sysPurchValue.setExecutive_time(datetime);
			sysPurchValue.setExecutive_value("暂未审核，请等待！");
		}

		int updatePurchInt = updateMapper.updatePurch(sysPurchValue);
		if (updatePurchInt == 0) {
			userResult.setCode("erro");
			userResult.setMsg("修改失败！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(name);

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		title = "用户：" + name + ";于" + datetime + "修改了id：" + id + "的" + title;
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("修改采购申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public UserResult updateLoan(SysLoanValue sysLoanValue) {
		UserResult userResult = new UserResult();
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		String id = sysLoanValue.getId();
		int selectLoanById = selectMapper.selectLoanById(id);
		if (selectLoanById == 0) {
			userResult.setCode("erro");
			userResult.setMsg("查无此条数据，修改失败！");
			return userResult;
		}

		SysLoanValue selectLoanByIdValue = selectMapper.selectLoanByIdValue(id);
		String name = selectLoanByIdValue.getName();
		String place = sysLoanValue.getPlace();
		String project = sysLoanValue.getProject();
		String price = sysLoanValue.getPrice();

		String title = name + " 用于 " + place + project + "  的" + price + "  元的借款申请";
		sysLoanValue.setTitle(title);

		String progress = selectLoanByIdValue.getProgress();
		if (selectLoanByIdValue.getStatus().equals("2")) {
			sysLoanValue.setStatus("0");
			sysLoanValue.setReason("");
		} else if (selectLoanByIdValue.getStatus().equals("1")) {
			userResult.setCode("erro");
			userResult.setMsg("审核已完成，请勿提交修改！");
			return userResult;
		} else {
			if (!progress.equals("0")) {
				userResult.setCode("erro");
				userResult.setMsg("审核中，请勿提交修改！");
				return userResult;
			}
			sysLoanValue.setStatus("0");
			sysLoanValue.setReason("");
			sysLoanValue.setManager("0");
			sysLoanValue.setManager_time(datetime);
			sysLoanValue.setManager_value("暂未审核，请等待！");
			sysLoanValue.setFinance("0");
			sysLoanValue.setFinance_time(datetime);
			sysLoanValue.setFinance_value("暂未审核，请等待！");
		}

		int updateLoanInt = updateMapper.updateLoan(sysLoanValue);
		if (updateLoanInt == 0) {
			userResult.setCode("erro");
			userResult.setMsg("修改失败！");
			return userResult;
		}

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(name);

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		title = "用户：" + name + ";于" + datetime + "修改了id：" + id + "的" + title;
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("修改借款申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public JsonResult updateUserbyUser(SysUserUpdateValue sysUserUpdateValue) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setMessage("修改成功！");

		String id = sysUserUpdateValue.getId();
		String oldPassword = sysUserUpdateValue.getOldPassword();

		int selectUserById = selectMapper.selectUserById(id);
		if (selectUserById == 0) {
			jsonResult.setState(0);
			jsonResult.setMessage("系统错误，查无此用户！");
			return jsonResult;
		}

		String password = selectMapper.selectUserPasswordById(id);

		if (!oldPassword.equals(password)) {
			jsonResult.setState(0);
			jsonResult.setMessage("旧密码错误，请确认！");
			return jsonResult;
		}

		int updateUserbyUser = updateMapper.updateUserbyUser(sysUserUpdateValue);
		if (updateUserbyUser == 0) {
			jsonResult.setState(0);
			jsonResult.setMessage("系统错误，修改失败！");
			return jsonResult;
		}

		// 插入存储操作记录
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = tempDate.format(new java.util.Date());
		String name = sysUserUpdateValue.getName();
		String username = sysUserUpdateValue.getUsername();

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(createTime);
		sysOperationValue.setName(name);

		int max = 999999999, min = 100000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);
		id = "SX_XSW_OPER" + ran3;
		String title = "用户：" + name + ";账号：" + username + ";于" + createTime + "执行修改用户信息操作";

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("修改用户");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			jsonResult.setState(0);
			jsonResult.setMessage("系统错误，修改失败！");
			return jsonResult;
		}

		return jsonResult;
	}

}
