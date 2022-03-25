package com.xsw.sys.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysNumValue;
import com.xsw.common.entry.SysOperationValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysSelectValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.entry.SysUserValue;
import com.xsw.common.vo.JsonResult;
import com.xsw.common.vo.UserResult;
import com.xsw.sys.mapper.InsertMapper;
import com.xsw.sys.mapper.SelectMapper;
import com.xsw.sys.mapper.StatisticsMapper;
import com.xsw.sys.mapper.UpdateMapper;
import com.xsw.sys.service.SelectService;

@Service
public class SelectServiceImpl implements SelectService {

	@Autowired
	private SelectMapper selectMapper;

	@Autowired
	private InsertMapper insertMapper;

	@Autowired
	private StatisticsMapper statisticsMapper;

	@Autowired
	private UpdateMapper updateMapper;

	@Override
	public UserResult selectAllUser(Integer page, Integer limit, String name) {
		UserResult userResult = new UserResult();

		String Executive = selectMapper.selectExecutiveByName(name);

		if (!Executive.equals("0") && !Executive.equals("1")) {
			userResult.setCode("erro");
			userResult.setMsg("用户无权限查看！");
			return userResult;
		}

		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		List<SysUserValue> sysUserValue = selectMapper.selectAllUser(rowNumMin, rowNumMax);
		System.out.println(sysUserValue.toString());
		int allCount = selectMapper.selectAllUserCount();
		userResult.setData(sysUserValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectUserLogin(String username, String password, String ip, String address) {
		UserResult userResult = new UserResult();

		int selectUserByUsername = selectMapper.selectUserByUsername(username);
		if (selectUserByUsername == 0) {
			userResult.setCode("erro");
			userResult.setMsg("您输入的用户名不存在，请确认！");
			return userResult;
		}

		SysUserValue sysUserValue = selectMapper.selectUserByUsernameAll(username);

		String password2 = sysUserValue.getPassword();
		if (!password.equals(password2)) {
			userResult.setCode("erro");
			userResult.setMsg("您输入的密码错误，请确认！");
			return userResult;
		}

		String status = sysUserValue.getStatus();
		if (status.equals("1")) {
			userResult.setCode("erro");
			userResult.setMsg("您输入的账号已停用，请确认！");
			return userResult;
		}

		userResult.setData(sysUserValue);

		// 插入存储操作记录
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = tempDate.format(new java.util.Date());

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(createTime);
		sysOperationValue.setName(sysUserValue.getName());

		int max = 999999999, min = 100000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);
		String id = "SX_XSW_OPER" + ran3;
		String title = "用户：" + sysUserValue.getName() + ";账号：" + username + ";于" + createTime + "在" + address + ",ip:"
				+ ip + "执行登录操作";

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("用户登录");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			userResult.setCode("erro");
			userResult.setMsg("日志记录失败，请确认！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public UserResult selectAllTask(Integer page, Integer limit, String name, String startTime, String endTime) {
		UserResult userResult = new UserResult();

		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(name);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		SysUserValue selectUserByNameValue = selectMapper.selectUserByNameValue(name);
		String role = selectUserByNameValue.getRole();

		if (role.equals("0")) {
			List<SysTaskValue> sysTaskValue = selectMapper.selectAllTaskByRole(rowNumMin, rowNumMax);
			int allCount = selectMapper.selectAllTaskCountByRole(name);
			userResult.setData(sysTaskValue);
			userResult.setCount(allCount);

			return userResult;
		}

		if (startTime != null && startTime != "" && endTime != "") {
			List<SysTaskValue> sysTaskValue = selectMapper.selectAllTaskByTwoTime(sysSelectValue);
			int allCount = selectMapper.selectAllTaskCountByTwoTime(sysSelectValue);
			userResult.setData(sysTaskValue);
			userResult.setCount(allCount);
			return userResult;
		} else if (startTime != "" && endTime == "") {
			List<SysTaskValue> sysTaskValue = selectMapper.selectAllTaskByStartTime(sysSelectValue);
			int allCount = selectMapper.selectAllTaskCountByStartTime(sysSelectValue);
			userResult.setData(sysTaskValue);
			userResult.setCount(allCount);
			return userResult;
		} else if (startTime == "" && endTime != "") {
			List<SysTaskValue> sysTaskValue = selectMapper.selectAllTaskByEndTime(sysSelectValue);
			int allCount = selectMapper.selectAllTaskCountByEndTime(sysSelectValue);
			userResult.setData(sysTaskValue);
			userResult.setCount(allCount);
			return userResult;
		}

		List<SysTaskValue> sysTaskValue = selectMapper.selectAllTask(sysSelectValue);
		int allCount = selectMapper.selectAllTaskCount(name);
		userResult.setData(sysTaskValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectExecutiveTask(Integer page, Integer limit, String name, String startTime, String endTime,
			String username) {
		UserResult userResult = new UserResult();

		int allCount = 0;
		List<SysTaskValue> sysTaskValue = null;
		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		if (startTime == null) {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "" && endTime == "") {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "") {
			startTime = "2000-11-03 00:00:00";
		} else if (endTime == "") {
			endTime = "2050-11-03 00:00:00";
		}

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);
		System.out.println(sysSelectValue.toString());

		SysUserValue selectUserByNameValue = selectMapper.selectUserByNameValue(name);
		String role = selectUserByNameValue.getRole();

		if (role.equals("0")) {
			sysTaskValue = selectMapper.selectExecutiveTask(rowNumMin, rowNumMax);
			allCount = selectMapper.selectExecutiveTaskCount();

			userResult.setData(sysTaskValue);
			userResult.setCount(allCount);
			return userResult;
		}

		String Executive = selectMapper.selectExecutiveByName(name);

		if (Executive.equals("0")) {
			if (username == null || username == "") {
				sysTaskValue = selectMapper.selectExecutiveTaskByManager(sysSelectValue);
				allCount = selectMapper.selectExecutiveTaskCountByManager(sysSelectValue);
			} else {
				sysTaskValue = selectMapper.selectExecutiveTaskByManagerByName(sysSelectValue);
				allCount = selectMapper.selectExecutiveTaskCountByManagerByName(sysSelectValue);
			}
		} else if (Executive.equals("1")) {
			if (username == null || username == "") {
				sysTaskValue = selectMapper.selectExecutiveTaskByExecutive(sysSelectValue);
				allCount = selectMapper.selectExecutiveTaskCountByExecutive(sysSelectValue);
			} else {
				sysTaskValue = selectMapper.selectExecutiveTaskByExecutiveByName(sysSelectValue);
				allCount = selectMapper.selectExecutiveTaskCountByExecutiveByName(sysSelectValue);
			}

		} else if (Executive.equals("2")) {
			if (username == null || username == "") {
				sysTaskValue = selectMapper.selectExecutiveTaskByAccounting(sysSelectValue);
				allCount = selectMapper.selectExecutiveTaskCountByAccounting(sysSelectValue);
			} else {
				sysTaskValue = selectMapper.selectExecutiveTaskByAccountingByName(sysSelectValue);
				allCount = selectMapper.selectExecutiveTaskCountByAccountingByName(sysSelectValue);
			}
		} else if (Executive.equals("3")) {
			if (username == null || username == "") {
				sysTaskValue = selectMapper.selectExecutiveTaskByFinance(sysSelectValue);
				allCount = selectMapper.selectExecutiveTaskCountByFinance(sysSelectValue);
			} else {
				sysTaskValue = selectMapper.selectExecutiveTaskByFinanceByName(sysSelectValue);
				allCount = selectMapper.selectExecutiveTaskCountByFinanceByName(sysSelectValue);
			}
		} else {
			userResult.setCode("erro");
			userResult.setMsg("用户无权限查看！");
		}

		userResult.setData(sysTaskValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectExecutiveLoan(Integer page, Integer limit, String name, String startTime, String endTime,
			String username) {
		UserResult userResult = new UserResult();
		int allCount = 0;
		List<SysLoanValue> sysLoanValue = null;
		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		if (startTime == null) {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "" && endTime == "") {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "") {
			startTime = "2000-11-03 00:00:00";
		} else if (endTime == "") {
			endTime = "2050-11-03 00:00:00";
		}

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		SysUserValue selectUserByNameValue = selectMapper.selectUserByNameValue(name);
		String role = selectUserByNameValue.getRole();

		if (role.equals("0")) {
			sysLoanValue = selectMapper.selectExecutiveLoan(rowNumMin, rowNumMax);
			allCount = selectMapper.selectExecutiveLoanCount();

			userResult.setData(sysLoanValue);
			userResult.setCount(allCount);
			return userResult;
		}

		String Executive = selectMapper.selectExecutiveByName(name);

		if (Executive.equals("0")) {
			if (username == null || username == "") {
				sysLoanValue = selectMapper.selectExecutiveLoanByManager(sysSelectValue);
				allCount = selectMapper.selectExecutiveLoanCountByManager(sysSelectValue);
			} else {
				sysLoanValue = selectMapper.selectExecutiveLoanByManagerByName(sysSelectValue);
				allCount = selectMapper.selectExecutiveLoanCountByManagerByName(sysSelectValue);
			}
		} else if (Executive.equals("3")) {
			if (username == null || username == "") {
				sysLoanValue = selectMapper.selectExecutiveLoanByFinance(sysSelectValue);
				allCount = selectMapper.selectExecutiveLoanCountByFinance(sysSelectValue);
			} else {
				sysLoanValue = selectMapper.selectExecutiveLoanByFinanceByName(sysSelectValue);
				allCount = selectMapper.selectExecutiveLoanCountByFinanceByName(sysSelectValue);
			}
		} else {
			userResult.setCode("erro");
			userResult.setMsg("用户无权限查看！");
		}

		userResult.setData(sysLoanValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectExecutivePurch(Integer page, Integer limit, String name, String startTime, String endTime,
			String username) {
		UserResult userResult = new UserResult();
		int allCount = 0;
		List<SysPurchValue> sysPurchValue = null;
		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		if (startTime == null) {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "" && endTime == "") {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "") {
			startTime = "2000-11-03 00:00:00";
		} else if (endTime == "") {
			endTime = "2050-11-03 00:00:00";
		}

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);
		System.out.println(sysSelectValue.toString());

		SysUserValue selectUserByNameValue = selectMapper.selectUserByNameValue(name);
		String role = selectUserByNameValue.getRole();

		if (role.equals("0")) {
			sysPurchValue = selectMapper.selectExecutivePurch(rowNumMin, rowNumMax);
			allCount = selectMapper.selectExecutivePurchCount();

			userResult.setData(sysPurchValue);
			userResult.setCount(allCount);
			return userResult;
		}

		String Executive = selectMapper.selectExecutiveByName(name);

		if (Executive.equals("0")) {
			if (username == null || username == "") {
				sysPurchValue = selectMapper.selectExecutivePurchByManager(sysSelectValue);
				allCount = selectMapper.selectExecutivePurchCountByManager(sysSelectValue);
			} else {
				sysPurchValue = selectMapper.selectExecutivePurchByManagerByName(sysSelectValue);
				allCount = selectMapper.selectExecutivePurchCountByManagerByName(sysSelectValue);
			}
		} else if (Executive.equals("1")) {
			if (username == null || username == "") {
				sysPurchValue = selectMapper.selectExecutivePurchByExecutive(sysSelectValue);
				allCount = selectMapper.selectExecutivePurchCountByExecutive(sysSelectValue);
			} else {
				sysPurchValue = selectMapper.selectExecutivePurchByExecutiveByName(sysSelectValue);
				allCount = selectMapper.selectExecutivePurchCountByExecutiveByName(sysSelectValue);
			}

		} else {
			userResult.setCode("erro");
			userResult.setMsg("用户无权限查看！");
		}

		userResult.setData(sysPurchValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectExecutiveReim(Integer page, Integer limit, String name, String startTime, String endTime,
			String username) {
		UserResult userResult = new UserResult();
		int allCount = 0;
		List<SysReimValue> sysReimValue = null;
		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		if (startTime == null) {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "" && endTime == "") {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "") {
			startTime = "2000-11-03 00:00:00";
		} else if (endTime == "") {
			endTime = "2050-11-03 00:00:00";
		}

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		SysUserValue selectUserByNameValue = selectMapper.selectUserByNameValue(name);
		String role = selectUserByNameValue.getRole();

		if (role.equals("0")) {
			sysReimValue = selectMapper.selectExecutiveReim(rowNumMin, rowNumMax);
			allCount = selectMapper.selectExecutiveReimCount();

			userResult.setData(sysReimValue);
			userResult.setCount(allCount);
			return userResult;
		}

		String Executive = selectMapper.selectExecutiveByName(name);

		if (Executive.equals("0")) {
			if (username == null || username == "") {
				sysReimValue = selectMapper.selectExecutiveReimByManager(sysSelectValue);
				allCount = selectMapper.selectExecutiveReimCountByManager(sysSelectValue);
			} else {
				sysReimValue = selectMapper.selectExecutiveReimByManagerByName(sysSelectValue);
				allCount = selectMapper.selectExecutiveReimCountByManagerByName(sysSelectValue);
			}

		} else if (Executive.equals("2")) {
			if (username == null || username == "") {
				sysReimValue = selectMapper.selectExecutiveReimByAccounting(sysSelectValue);
				allCount = selectMapper.selectExecutiveReimCountByAccounting(sysSelectValue);
			} else {
				sysReimValue = selectMapper.selectExecutiveReimByAccountingByName(sysSelectValue);
				allCount = selectMapper.selectExecutiveReimCountByAccountingByName(sysSelectValue);
			}
		} else if (Executive.equals("3")) {
			if (username == null || username == "") {
				sysReimValue = selectMapper.selectExecutiveReimByFinance(sysSelectValue);
				allCount = selectMapper.selectExecutiveReimCountByFinance(sysSelectValue);
			} else {
				sysReimValue = selectMapper.selectExecutiveReimByFinanceByName(sysSelectValue);
				allCount = selectMapper.selectExecutiveReimCountByFinanceByName(sysSelectValue);
			}
		} else {
			userResult.setCode("erro");
			userResult.setMsg("用户无权限查看！");
		}

		userResult.setData(sysReimValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectAllReim(Integer page, Integer limit, String name, String startTime, String endTime) {
		UserResult userResult = new UserResult();
		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(name);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		SysUserValue selectUserByNameValue = selectMapper.selectUserByNameValue(name);
		String role = selectUserByNameValue.getRole();

		if (role.equals("0")) {
			List<SysReimValue> sysReimValue = selectMapper.selectAllReimByRole(rowNumMin, rowNumMax);
			int allCount = selectMapper.selectAllReimCountByRole(name);
			userResult.setData(sysReimValue);
			userResult.setCount(allCount);

			return userResult;
		}

		if (startTime != null && startTime != "" && endTime != "") {
			List<SysReimValue> sysReimValue = selectMapper.selectAllReimByTwoTime(sysSelectValue);
			int allCount = selectMapper.selectAllReimCountByTwoTime(sysSelectValue);
			userResult.setData(sysReimValue);
			userResult.setCount(allCount);
			return userResult;
		} else if (startTime != "" && endTime == "") {
			List<SysReimValue> sysReimValue = selectMapper.selectAllReimByStartTime(sysSelectValue);
			int allCount = selectMapper.selectAllReimCountByStartTime(sysSelectValue);
			userResult.setData(sysReimValue);
			userResult.setCount(allCount);
			return userResult;
		} else if (startTime == "" && endTime != "") {
			List<SysReimValue> sysReimValue = selectMapper.selectAllReimByEndTime(sysSelectValue);
			int allCount = selectMapper.selectAllReimCountByEndTime(sysSelectValue);
			userResult.setData(sysReimValue);
			userResult.setCount(allCount);
			return userResult;
		}

		List<SysReimValue> sysReimValue = selectMapper.selectAllReim(sysSelectValue);
		int allCount = selectMapper.selectAllReimCount(name);
		userResult.setData(sysReimValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectAllLoan(Integer page, Integer limit, String name, String startTime, String endTime) {
		UserResult userResult = new UserResult();
		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(name);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		if (startTime != null && startTime != "" && endTime != "") {
			List<SysLoanValue> sysLoanValue = selectMapper.selectAllLoanByTwoTime(sysSelectValue);
			int allCount = selectMapper.selectAllLoanCountByTwoTime(sysSelectValue);
			userResult.setData(sysLoanValue);
			userResult.setCount(allCount);
			return userResult;
		} else if (startTime != "" && endTime == "") {
			List<SysLoanValue> sysLoanValue = selectMapper.selectAllLoanByStartTime(sysSelectValue);
			int allCount = selectMapper.selectAllLoanCountByStartTime(sysSelectValue);
			userResult.setData(sysLoanValue);
			userResult.setCount(allCount);
			return userResult;
		} else if (startTime == "" && endTime != "") {
			List<SysLoanValue> sysLoanValue = selectMapper.selectAllLoanByEndTime(sysSelectValue);
			int allCount = selectMapper.selectAllLoanCountByEndTime(sysSelectValue);
			userResult.setData(sysLoanValue);
			userResult.setCount(allCount);
			return userResult;
		}

		List<SysLoanValue> sysLoanValue = selectMapper.selectAllLoan(sysSelectValue);
		int allCount = selectMapper.selectAllLoanCount(name);
		userResult.setData(sysLoanValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectAllPurch(Integer page, Integer limit, String name, String startTime, String endTime) {
		UserResult userResult = new UserResult();
		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(name);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		SysUserValue selectUserByNameValue = selectMapper.selectUserByNameValue(name);
		String role = selectUserByNameValue.getRole();

		if (role.equals("0")) {
			List<SysPurchValue> sysPurchValue = selectMapper.selectAllPurchByRole(rowNumMin, rowNumMax);
			int allCount = selectMapper.selectAllPurchCountByRole(name);
			userResult.setData(sysPurchValue);
			userResult.setCount(allCount);

			return userResult;
		}

		if (startTime != null && startTime != "" && endTime != "") {
			List<SysPurchValue> sysPurchValue = selectMapper.selectAllPurchByTwoTime(sysSelectValue);
			int allCount = selectMapper.selectAllPurchCountByTwoTime(sysSelectValue);
			userResult.setData(sysPurchValue);
			userResult.setCount(allCount);
			return userResult;
		} else if (startTime != "" && endTime == "") {
			List<SysPurchValue> sysPurchValue = selectMapper.selectAllPurchByStartTime(sysSelectValue);
			int allCount = selectMapper.selectAllPurchCountByStartTime(sysSelectValue);
			userResult.setData(sysPurchValue);
			userResult.setCount(allCount);
			return userResult;
		} else if (startTime == "" && endTime != "") {
			List<SysPurchValue> sysPurchValue = selectMapper.selectAllPurchByEndTime(sysSelectValue);
			int allCount = selectMapper.selectAllPurchCountByEndTime(sysSelectValue);
			userResult.setData(sysPurchValue);
			userResult.setCount(allCount);
			return userResult;
		}

		List<SysPurchValue> sysPurchValue = selectMapper.selectAllPurch(sysSelectValue);
		int allCount = selectMapper.selectAllPurchCount(name);
		userResult.setData(sysPurchValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public String showPhoto(String id) {

		SysReimValue reim = selectMapper.selectReimByIdValue(id);

		/*
		 * String str =
		 * "{\r\n\"title\": \"\", \r\n\"id\": 123,\r\n\"start\": 0,\r\n\"data\": [\r\n"
		 * + "{\r\n\"alt\": \"图片名\",\r\n\"pid\": 666,\r\n\"src\": \"" +
		 * reim.getAddress() + "\",\r\n\"thumb\": \"\"\r\n}\r\n]\r\n}";
		 */

		String str = "{\r\n\"title\": \"\",\r\n\"id\": 123,\r\n\"start\": 0,\r\n\"data\": [\r\n{\r\n"
				+ "\"alt\": \"图片1\",\r\n\"pid\": 666,\r\n\"src\": \"" + reim.getAddress() + "\",\r\n"
				+ "\"thumb\": \"\"\r\n},{\r\n\"alt\": \"图片2\",\r\n" + "\"pid\": 666,\r\n\"src\": \"" + reim.getAddress()
				+ "\",\r\n\"thumb\": \"\"\r\n}\r\n]\r\n}";
		return str;
	}

	@Override
	public JsonResult selectUser(String name) {
		JsonResult jsonResult = new JsonResult();

		int selectUserByName = selectMapper.selectUserByName(name);
		if (selectUserByName == 0) {
			jsonResult.setState(0);
			jsonResult.setMessage("查询失败，请联系管理员！");
		}

		SysUserValue sysUserValue = selectMapper.selectUserByNameValue(name);
		jsonResult.setData(sysUserValue);

		return jsonResult;
	}

	@Override
	public JsonResult selectNum(String name) {
		JsonResult jsonResult = new JsonResult();
		SysNumValue sysNumValue = new SysNumValue();

		int e_taskCount = selectMapper.selectTaskCountAll(0, 0, 0, 0);
		int a_taskCount = selectMapper.selectTaskCountAll(1, 0, 0, 0);
		int m_taskCount = selectMapper.selectTaskCountAll(1, 1, 0, 0);
		int f_taskCount = selectMapper.selectTaskCountAll(1, 1, 1, 0);
		sysNumValue.setE_taskCount(e_taskCount);
		sysNumValue.setA_taskCount(a_taskCount);
		sysNumValue.setM_taskCount(m_taskCount);
		sysNumValue.setF_taskCount(f_taskCount);

		int a_reimCount = selectMapper.selectReimCountAll(0, 0, 0);
		int m_reimCount = selectMapper.selectReimCountAll(1, 0, 0);
		int f_reimCount = selectMapper.selectReimCountAll(1, 1, 0);
		sysNumValue.setA_reimCount(a_reimCount);
		sysNumValue.setM_reimCount(m_reimCount);
		sysNumValue.setF_reimCount(f_reimCount);

		int m_purchCount = selectMapper.selectPurchCountAll(0, 0);
		int e_purchCount = selectMapper.selectPurchCountAll(1, 0);
		sysNumValue.setM_purchCount(m_purchCount);
		sysNumValue.setE_purchCount(e_purchCount);

		int m_LoanCount = selectMapper.selectLoanCountAll(0, 0);
		int f_LoanCount = selectMapper.selectLoanCountAll(1, 0);
		sysNumValue.setM_loanCount(m_LoanCount);
		sysNumValue.setF_loanCount(f_LoanCount);

		int taskCount = 0;
		int reimCount = 0;
		int purchCount = 0;
		int loanCount = 0;

		int userCount = selectMapper.selectAllUserCount();

		String Executive = selectMapper.selectExecutiveByName(name);
		if (Executive.equals("0")) {
			reimCount = selectMapper.selectExecutiveReimCountByManagerA();
			purchCount = selectMapper.selectExecutivePurchCountByManagerA();
			taskCount = selectMapper.selectExecutiveTaskCountByManagerA();
			loanCount = selectMapper.selectExecutiveLoanCountByManagerA();
		} else if (Executive.equals("1")) {
			purchCount = selectMapper.selectExecutivePurchCountByExecutiveA();
			purchCount = purchCount + selectMapper.selectExecutivePurchCountByExecutiveB();
			taskCount = selectMapper.selectExecutiveTaskCountByExecutiveA();
		} else if (Executive.equals("2")) {
			reimCount = selectMapper.selectExecutiveReimCountByAccountingA();
			taskCount = selectMapper.selectExecutiveTaskCountByAccountingA();
		} else if (Executive.equals("3")) {
			reimCount = selectMapper.selectExecutiveReimCountByFinanceA();
			taskCount = selectMapper.selectExecutiveTaskCountByFinanceA();
			loanCount = selectMapper.selectExecutiveLoanCountByFinanceA();
		}

		sysNumValue.setTaskCount(taskCount);
		sysNumValue.setReimCount(reimCount);
		sysNumValue.setPurchCount(purchCount);
		sysNumValue.setLoanCount(loanCount);
		sysNumValue.setUserCount(userCount);

		jsonResult.setData(sysNumValue);

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy/MM");
		String datetime = tempDate.format(new java.util.Date());

		int selectCountById = statisticsMapper.selectCountById(datetime);
		if (selectCountById == 0) {
			int allUserCount = selectMapper.selectAllUserCount();
			insertMapper.insertUserCount(allUserCount, datetime);
		} else {
			int allUserCount = selectMapper.selectAllUserCount();
			updateMapper.updateUserCount(allUserCount, datetime);
		}

		String[] arr = datetime.split("/");
		String a = arr[0];
		String b = arr[1];
		int aParseInt = Integer.parseInt(a) - 1;

		switch (b) {
		case "01":
			datetime = aParseInt + "/12";
			break;
		case "02":
			datetime = a + "/01";
			break;
		case "03":
			datetime = a + "/02";
			break;
		case "04":
			datetime = a + "/03";
			break;
		case "05":
			datetime = a + "/04";
			break;
		case "06":
			datetime = a + "/05";
			break;
		case "07":
			datetime = a + "/06";
			break;
		case "08":
			datetime = a + "/07";
			break;
		case "09":
			datetime = a + "/08";
			break;
		case "10":
			datetime = a + "/09";
			break;
		case "11":
			datetime = a + "/10";
			break;
		case "12":
			datetime = a + "/11";
			break;
		}

		double allTaskTitalprice = 0;
		double allReimTitalprice = 0;
		double allPurchTitalprice = 0;
		double allLoanTitalprice = 0;

		List<SysTaskValue> sysTaskValue = statisticsMapper.selectAllTaskLastMouth(datetime);
		List<SysReimValue> sysReimValue = statisticsMapper.selectAllReimLastMouth(datetime);
		List<SysPurchValue> sysPurchValue = statisticsMapper.selectAllPurchLastMouth(datetime);
		List<SysLoanValue> sysLoanValue = statisticsMapper.selectAllLoanLastMouth(datetime);

		for (int i = 0; i < sysTaskValue.size(); i++) {
			String totalPrice = sysTaskValue.get(i).getTotalPrice();
			double d1 = Double.valueOf(totalPrice);
			BigDecimal b1 = new BigDecimal(Double.toString(d1));
			BigDecimal b2 = new BigDecimal(Double.toString(allTaskTitalprice));
			allTaskTitalprice = b2.add(b1).doubleValue();
		}

		for (int i = 0; i < sysReimValue.size(); i++) {
			String totalPrice = sysReimValue.get(i).getPrice();
			double d1 = Double.valueOf(totalPrice);
			BigDecimal b1 = new BigDecimal(Double.toString(d1));
			BigDecimal b2 = new BigDecimal(Double.toString(allReimTitalprice));
			allReimTitalprice = b2.add(b1).doubleValue();
		}

		for (int i = 0; i < sysPurchValue.size(); i++) {
			String totalPrice = sysPurchValue.get(i).getPrice();
			double d1 = Double.valueOf(totalPrice);
			BigDecimal b1 = new BigDecimal(Double.toString(d1));
			BigDecimal b2 = new BigDecimal(Double.toString(allPurchTitalprice));
			allPurchTitalprice = b2.add(b1).doubleValue();
		}

		for (int i = 0; i < sysLoanValue.size(); i++) {
			String totalPrice = sysLoanValue.get(i).getPrice();
			double d1 = Double.valueOf(totalPrice);
			BigDecimal b1 = new BigDecimal(Double.toString(d1));
			BigDecimal b2 = new BigDecimal(Double.toString(allLoanTitalprice));
			allLoanTitalprice = b2.add(b1).doubleValue();
		}

		updateMapper.updataAllTitalprice(allTaskTitalprice, allReimTitalprice, allPurchTitalprice, allLoanTitalprice,
				datetime);

		return jsonResult;
	}

	@Override
	public UserResult selectAllTaskLast(String name, Integer page, Integer limit, String username, String startTime,
			String endTime) {
		UserResult userResult = new UserResult();

		String Executive = selectMapper.selectExecutiveByName(name);
		String role = selectMapper.selectUserByNameValue(name).getRole();

		if (Executive.equals("4") && role.equals("1")) {
			userResult.setCode("erro");
			userResult.setMsg("用户无权限查看！");
			return userResult;
		}

		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;
		List<SysTaskValue> sysTaskValue = null;
		int allCount = 0;
		if (startTime == null) {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "" && endTime == "") {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "") {
			startTime = "2000-11-03 00:00:00";
		} else if (endTime == "") {
			endTime = "2050-11-03 00:00:00";
		}

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		if (username == null || username == "") {
			sysTaskValue = selectMapper.selectAllTaskLast(sysSelectValue);
			allCount = selectMapper.selectAllTaskCountLast(sysSelectValue);
		} else {
			sysTaskValue = selectMapper.selectAllTaskLastByName(sysSelectValue);
			allCount = selectMapper.selectAllTaskCountLastByName(sysSelectValue);
		}

		userResult.setData(sysTaskValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectAllLoanLast(String name, Integer page, Integer limit, String username, String startTime,
			String endTime) {
		UserResult userResult = new UserResult();

		String Executive = selectMapper.selectExecutiveByName(name);
		String role = selectMapper.selectUserByNameValue(name).getRole();

		if (role.equals("1")) {
			if (Executive.equals("1") || Executive.equals("2") || Executive.equals("4")) {
				userResult.setCode("erro");
				userResult.setMsg("用户无权限查看！");
				return userResult;
			}
		}

		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		List<SysLoanValue> sysLoanValue = null;
		int allCount = 0;
		if (startTime == null) {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "" && endTime == "") {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "") {
			startTime = "2000-11-03 00:00:00";
		} else if (endTime == "") {
			endTime = "2050-11-03 00:00:00";
		}

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		if (username == null || username == "") {
			sysLoanValue = selectMapper.selectAllLoanLast(sysSelectValue);
			allCount = selectMapper.selectAllLoanCountLast(sysSelectValue);
		} else {
			sysLoanValue = selectMapper.selectAllLoanLastByName(sysSelectValue);
			allCount = selectMapper.selectAllLoanCountLastByName(sysSelectValue);
		}

		userResult.setData(sysLoanValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectAllPurchLast(String name, Integer page, Integer limit, String username, String startTime,
			String endTime) {
		UserResult userResult = new UserResult();

		String Executive = selectMapper.selectExecutiveByName(name);
		String role = selectMapper.selectUserByNameValue(name).getRole();

		if (role.equals("1")) {
			if (Executive.equals("2") || Executive.equals("4")) {
				userResult.setCode("erro");
				userResult.setMsg("用户无权限查看！");
				return userResult;
			}
		}

		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		List<SysPurchValue> sysPurchValue = null;
		int allCount = 0;
		if (startTime == null) {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "" && endTime == "") {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "") {
			startTime = "2000-11-03 00:00:00";
		} else if (endTime == "") {
			endTime = "2050-11-03 00:00:00";
		}

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		if (username == null || username == "") {
			sysPurchValue = selectMapper.selectAllPurchLast(sysSelectValue);
			allCount = selectMapper.selectAllPurchCountLast(sysSelectValue);
		} else {
			sysPurchValue = selectMapper.selectAllPurchLastByName(sysSelectValue);
			allCount = selectMapper.selectAllPurchCountLastByName(sysSelectValue);
		}

		userResult.setData(sysPurchValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectAllReimLast(String name, Integer page, Integer limit, String username, String startTime,
			String endTime) {
		UserResult userResult = new UserResult();

		String Executive = selectMapper.selectExecutiveByName(name);
		String role = selectMapper.selectUserByNameValue(name).getRole();

		if (role.equals("1")) {
			if (Executive.equals("1") || Executive.equals("4")) {
				userResult.setCode("erro");
				userResult.setMsg("用户无权限查看！");
				return userResult;
			}
		}

		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		List<SysReimValue> sysReimValue = null;
		int allCount = 0;
		if (startTime == null) {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "" && endTime == "") {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "") {
			startTime = "2000-11-03 00:00:00";
		} else if (endTime == "") {
			endTime = "2050-11-03 00:00:00";
		}

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		if (username == null || username == "") {
			sysReimValue = selectMapper.selectAllReimLast(sysSelectValue);
			allCount = selectMapper.selectAllReimCountLast(sysSelectValue);
		} else {
			sysReimValue = selectMapper.selectAllReimLastByName(sysSelectValue);
			allCount = selectMapper.selectAllReimCountLastByName(sysSelectValue);
		}

		userResult.setData(sysReimValue);
		userResult.setCount(allCount);

		return userResult;
	}

	@Override
	public UserResult selectOperationList(String name, Integer page, Integer limit, String startTime, String endTime,
			String username) {
		UserResult userResult = new UserResult();

		String Executive = selectMapper.selectExecutiveByName(name);
		String role = selectMapper.selectUserByNameValue(name).getRole();

		if (role.equals("1")) {
			if (!Executive.equals("0")) {
				userResult.setCode("erro");
				userResult.setMsg("用户无权限查看！");
				return userResult;
			}
		}

		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		List<SysOperationValue> sysOperationValue = null;
		int allCount = 0;
		if (startTime == null) {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "" && endTime == "") {
			startTime = "2000-11-03 00:00:00";
			endTime = "2050-11-03 00:00:00";
		} else if (startTime == "") {
			startTime = "2000-11-03 00:00:00";
		} else if (endTime == "") {
			endTime = "2050-11-03 00:00:00";
		}

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		if (username == null || username == "") {
			sysOperationValue = selectMapper.selectOperationList(sysSelectValue);
			allCount = selectMapper.selectOperationListCount(sysSelectValue);
		} else {
			sysOperationValue = selectMapper.selectOperationListByName(sysSelectValue);
			allCount = selectMapper.selectOperationListCountByName(sysSelectValue);
		}

		userResult.setData(sysOperationValue);
		userResult.setCount(allCount);

		return userResult;
	}

}
