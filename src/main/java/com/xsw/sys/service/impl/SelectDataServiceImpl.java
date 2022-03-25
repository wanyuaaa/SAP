package com.xsw.sys.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysSelectValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.vo.UserResult;
import com.xsw.sys.mapper.SelectMapper;
import com.xsw.sys.service.SelectDataService;

@Service
public class SelectDataServiceImpl implements SelectDataService {

	@Autowired
	private SelectMapper selectMapper;

	@Override
	public UserResult selectTaskDataList(String name, Integer page, Integer limit, String startTime, String endTime,
			String username) {
		UserResult userResult = new UserResult();

		String Executive = selectMapper.selectExecutiveByName(name);
		String role = selectMapper.selectUserByNameValue(name).getRole();

		if (role.equals("1")) {
			if (Executive.equals("4")) {
				userResult.setCode("erro");
				userResult.setMsg("用户无权限查看！");
				return userResult;
			}
		}

		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		List<SysTaskValue> sysAllTaskValue = new ArrayList<SysTaskValue>();

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		if (startTime == null) {
			startTime = "2021-01-01 00:00:00";
			endTime = datetime;
		} else if (startTime == "" && endTime == "") {
			startTime = "2021-01-01 00:00:00";
			endTime = datetime;
		} else if (startTime == "") {
			startTime = "2021-01-01 00:00:00";
		} else if (endTime == "") {
			endTime = datetime;
		}

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		if (username == null || username == "") {
			List<String> nameList = selectMapper.selectAllUserName();

			for (String str : nameList) {
				sysSelectValue.setName(str);

				List<String> priceList = selectMapper.selectTaskTotalPriceByName(sysSelectValue);
				int TaskCount = selectMapper.selectTaskCount(sysSelectValue);
				String userName = selectMapper.selectUserName(str);

				double a = 0;
				for (String price : priceList) {
					BigDecimal a1 = new BigDecimal(a);
					a = a1.add(new BigDecimal(price)).doubleValue();
				}
				String title = "用户：" + str + ";	账户：" + userName + ";	从" + startTime + "到" + endTime + "的" + TaskCount
						+ "笔补助申请金额共计为" + a + "元";

				SysTaskValue taskValue = new SysTaskValue();
				taskValue.setGoTime(startTime);
				taskValue.setBackTime(endTime);
				taskValue.setTotalPrice(a + "");
				taskValue.setDay(TaskCount + "");
				taskValue.setTitle(title);
				taskValue.setName(str);
				taskValue.setId(userName);
				sysAllTaskValue.add(taskValue);
			}

		} else {
			int count = selectMapper.selectUserByName(username);
			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("查无此人，请确认！");
				return userResult;
			}

			List<String> priceList = selectMapper.selectTaskTotalPriceByName(sysSelectValue);
			int TaskCount = selectMapper.selectTaskCount(sysSelectValue);
			String userName = selectMapper.selectUserName(username);

			double a = 0;
			for (String price : priceList) {
				BigDecimal a1 = new BigDecimal(a);
				a = a1.add(new BigDecimal(price)).doubleValue();
			}
			String title = "用户：" + username + ";	账户：" + userName + ";	从" + startTime + "到" + endTime + "的"
					+ TaskCount + "笔补助申请金额共计为" + a + "元";

			SysTaskValue taskValue = new SysTaskValue();
			taskValue.setGoTime(startTime);
			taskValue.setBackTime(endTime);
			taskValue.setTotalPrice(a + "");
			taskValue.setDay(TaskCount + "");
			taskValue.setTitle(title);
			taskValue.setName(username);
			taskValue.setId(userName);
			sysAllTaskValue.add(taskValue);

			SysTaskValue taskValue2 = new SysTaskValue();
			taskValue2.setName("用户费用详细如下");
			taskValue2.setId("----");
			taskValue2.setDay("----");
			taskValue2.setGoTime("----");
			taskValue2.setBackTime("----");
			taskValue2.setTotalPrice("----");
			taskValue2.setTitle("----");
			sysAllTaskValue.add(taskValue2);

			if (startTime != null && startTime != "" && endTime != "") {
				List<SysTaskValue> sysTaskValue = selectMapper.selectAllTaskByTwoTime(sysSelectValue);

				for (SysTaskValue sysTaskValue2 : sysTaskValue) {
					SysTaskValue taskValue3 = new SysTaskValue();
					taskValue3.setName(username);
					taskValue3.setId(userName);
					taskValue3.setDay("1");
					taskValue3.setGoTime(sysTaskValue2.getGoTime());
					taskValue3.setBackTime(sysTaskValue2.getBackTime());
					taskValue3.setTotalPrice(sysTaskValue2.getTotalPrice());

					String title2 = "用户：" + username + ";	账户：" + userName + ";	从" + sysTaskValue2.getGoTime() + "到"
							+ sysTaskValue2.getBackTime() + "的一笔补助申请金额共计为" + sysTaskValue2.getTotalPrice() + "元";
					taskValue3.setTitle(title2);
					sysAllTaskValue.add(taskValue3);
				}

				userResult.setData(sysAllTaskValue);
				userResult.setCount(sysAllTaskValue.size());

				return userResult;

			} else if (startTime != "" && endTime == "") {
				List<SysTaskValue> sysTaskValue = selectMapper.selectAllTaskByStartTime(sysSelectValue);

				for (SysTaskValue sysTaskValue2 : sysTaskValue) {
					SysTaskValue taskValue3 = new SysTaskValue();
					taskValue3.setName(username);
					taskValue3.setId(userName);
					taskValue3.setDay("1");
					taskValue3.setGoTime(sysTaskValue2.getGoTime());
					taskValue3.setBackTime(sysTaskValue2.getBackTime());
					taskValue3.setTotalPrice(sysTaskValue2.getTotalPrice());

					String title2 = "用户：" + username + ";	账户：" + userName + ";	从" + sysTaskValue2.getGoTime() + "到"
							+ sysTaskValue2.getBackTime() + "的一笔补助申请金额共计为" + sysTaskValue2.getTotalPrice() + "元";
					taskValue3.setTitle(title2);
					sysAllTaskValue.add(taskValue3);
				}

				userResult.setData(sysAllTaskValue);
				userResult.setCount(sysAllTaskValue.size());

				return userResult;
			} else if (startTime == "" && endTime != "") {
				List<SysTaskValue> sysTaskValue = selectMapper.selectAllTaskByEndTime(sysSelectValue);

				for (SysTaskValue sysTaskValue2 : sysTaskValue) {
					SysTaskValue taskValue3 = new SysTaskValue();
					taskValue3.setName(username);
					taskValue3.setId(userName);
					taskValue3.setDay("1");
					taskValue3.setGoTime(sysTaskValue2.getGoTime());
					taskValue3.setBackTime(sysTaskValue2.getBackTime());
					taskValue3.setTotalPrice(sysTaskValue2.getTotalPrice());

					String title2 = "用户：" + username + ";	账户：" + userName + ";	从" + sysTaskValue2.getGoTime() + "到"
							+ sysTaskValue2.getBackTime() + "的一笔补助申请金额共计为" + sysTaskValue2.getTotalPrice() + "元";
					taskValue3.setTitle(title2);
					sysAllTaskValue.add(taskValue3);
				}

				userResult.setData(sysAllTaskValue);
				userResult.setCount(sysAllTaskValue.size());

				return userResult;
			}

			List<SysTaskValue> sysTaskValue = selectMapper.selectAllTask(sysSelectValue);

			for (SysTaskValue sysTaskValue2 : sysTaskValue) {
				SysTaskValue taskValue3 = new SysTaskValue();
				taskValue3.setName(username);
				taskValue3.setId(userName);
				taskValue3.setDay("1");
				taskValue3.setGoTime(sysTaskValue2.getGoTime());
				taskValue3.setBackTime(sysTaskValue2.getBackTime());
				taskValue3.setTotalPrice(sysTaskValue2.getTotalPrice());

				String title2 = "用户：" + username + ";	账户：" + userName + ";	从" + sysTaskValue2.getGoTime() + "到"
						+ sysTaskValue2.getBackTime() + "的一笔补助申请金额共计为" + sysTaskValue2.getTotalPrice() + "元";
				taskValue3.setTitle(title2);
				sysAllTaskValue.add(taskValue3);
			}

			userResult.setData(sysAllTaskValue);
			userResult.setCount(sysAllTaskValue.size());

			return userResult;

		}

		userResult.setData(sysAllTaskValue);
		userResult.setCount(sysAllTaskValue.size());

		return userResult;
	}

	@Override
	public UserResult selectReimDataList(String name, Integer page, Integer limit, String startTime, String endTime,
			String username) {
		UserResult userResult = new UserResult();

		String Executive = selectMapper.selectExecutiveByName(name);
		String role = selectMapper.selectUserByNameValue(name).getRole();

		if (role.equals("1")) {
			if (Executive.equals("4")) {
				userResult.setCode("erro");
				userResult.setMsg("用户无权限查看！");
				return userResult;
			}
		}

		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;

		List<SysTaskValue> sysAllTaskValue = new ArrayList<SysTaskValue>();

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		if (startTime == null) {
			startTime = "2021-01-01 00:00:00";
			endTime = datetime;
		} else if (startTime == "" && endTime == "") {
			startTime = "2021-01-01 00:00:00";
			endTime = datetime;
		} else if (startTime == "") {
			startTime = "2021-01-01 00:00:00";
		} else if (endTime == "") {
			endTime = datetime;
		}

		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		if (username == null || username == "") {
			List<String> nameList = selectMapper.selectAllUserName();

			for (String str : nameList) {
				sysSelectValue.setName(str);

				List<String> priceList = selectMapper.selectReimTotalPriceByName(sysSelectValue);
				int TaskCount = selectMapper.selectReimCount(sysSelectValue);
				String userName = selectMapper.selectUserName(str);

				double a = 0;
				for (String price : priceList) {
					BigDecimal a1 = new BigDecimal(a);
					a = a1.add(new BigDecimal(price)).doubleValue();
				}
				String title = "用户：" + str + ";	账户：" + userName + ";	 " + startTime + "到" + endTime + "的" + TaskCount
						+ "笔报销申请金额共计为" + a + "元";

				SysTaskValue taskValue = new SysTaskValue();
				taskValue.setGoTime(startTime);
				taskValue.setBackTime(endTime);
				taskValue.setTotalPrice(a + "");
				taskValue.setDay(TaskCount + "");
				taskValue.setTitle(title);
				taskValue.setName(str);
				taskValue.setId(userName);
				sysAllTaskValue.add(taskValue);
			}

		} else {
			int count = selectMapper.selectUserByName(username);
			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("查无此人，请确认！");
				return userResult;
			}

			List<String> priceList = selectMapper.selectReimTotalPriceByName(sysSelectValue);
			int TaskCount = selectMapper.selectReimCount(sysSelectValue);
			String userName = selectMapper.selectUserName(username);

			double a = 0;
			for (String price : priceList) {
				BigDecimal a1 = new BigDecimal(a);
				a = a1.add(new BigDecimal(price)).doubleValue();
			}
			String title = "用户：" + username + ";	账户：" + userName + ";	从" + startTime + "到" + endTime + "的"
					+ TaskCount + "笔报销申请金额共计为" + a + "元";

			SysTaskValue taskValue = new SysTaskValue();
			taskValue.setGoTime(startTime);
			taskValue.setBackTime(endTime);
			taskValue.setTotalPrice(a + "");
			taskValue.setDay(TaskCount + "");
			taskValue.setTitle(title);
			taskValue.setName(username);
			taskValue.setId(userName);
			sysAllTaskValue.add(taskValue);

			SysTaskValue taskValue2 = new SysTaskValue();
			taskValue2.setName("用户费用详细如下");
			taskValue2.setId("----");
			taskValue2.setDay("----");
			taskValue2.setGoTime("----");
			taskValue2.setBackTime("----");
			taskValue2.setTotalPrice("----");
			taskValue2.setTitle("----");
			sysAllTaskValue.add(taskValue2);

			if (startTime != null && startTime != "" && endTime != "") {
				List<SysReimValue> sysReimValue = selectMapper.selectAllReimByTwoTime(sysSelectValue);

				for (SysReimValue sysReimValue2 : sysReimValue) {
					SysTaskValue taskValue3 = new SysTaskValue();
					taskValue3.setName(username);
					taskValue3.setId(userName);
					taskValue3.setDay("1");
					taskValue3.setGoTime(sysReimValue2.getCreateTime());
					taskValue3.setBackTime(sysReimValue2.getUseTime());
					taskValue3.setTotalPrice(sysReimValue2.getPrice());

					String title2 = "用户：" + username + ";	账户：" + userName + ";	" + sysReimValue2.getCreateTime()
							+ "创建的用于报销" + sysReimValue2.getUseTime() + "使用的一笔关于" + sysReimValue2.getGoods()
							+ "的补助申请金额共计为" + sysReimValue2.getPrice() + "元";
					taskValue3.setTitle(title2);
					sysAllTaskValue.add(taskValue3);
				}

				userResult.setData(sysAllTaskValue);
				userResult.setCount(sysAllTaskValue.size());

				return userResult;
			} else if (startTime != "" && endTime == "") {
				List<SysReimValue> sysReimValue = selectMapper.selectAllReimByStartTime(sysSelectValue);
				for (SysReimValue sysReimValue2 : sysReimValue) {
					SysTaskValue taskValue3 = new SysTaskValue();
					taskValue3.setName(username);
					taskValue3.setId(userName);
					taskValue3.setDay("1");
					taskValue3.setGoTime(sysReimValue2.getCreateTime());
					taskValue3.setBackTime(sysReimValue2.getUseTime());
					taskValue3.setTotalPrice(sysReimValue2.getPrice());

					String title2 = "用户：" + username + ";	账户：" + userName + ";	" + sysReimValue2.getCreateTime()
							+ "创建的用于报销" + sysReimValue2.getUseTime() + "使用的一笔关于" + sysReimValue2.getGoods()
							+ "的补助申请金额共计为" + sysReimValue2.getPrice() + "元";
					taskValue3.setTitle(title2);
					sysAllTaskValue.add(taskValue3);
				}

				userResult.setData(sysAllTaskValue);
				userResult.setCount(sysAllTaskValue.size());

				return userResult;
			} else if (startTime == "" && endTime != "") {
				List<SysReimValue> sysReimValue = selectMapper.selectAllReimByEndTime(sysSelectValue);
				for (SysReimValue sysReimValue2 : sysReimValue) {
					SysTaskValue taskValue3 = new SysTaskValue();
					taskValue3.setName(username);
					taskValue3.setId(userName);
					taskValue3.setDay("1");
					taskValue3.setGoTime(sysReimValue2.getCreateTime());
					taskValue3.setBackTime(sysReimValue2.getUseTime());
					taskValue3.setTotalPrice(sysReimValue2.getPrice());

					String title2 = "用户：" + username + ";	账户：" + userName + ";	" + sysReimValue2.getCreateTime()
							+ "创建的用于报销" + sysReimValue2.getUseTime() + "使用的一笔关于" + sysReimValue2.getGoods()
							+ "的补助申请金额共计为" + sysReimValue2.getPrice() + "元";
					taskValue3.setTitle(title2);
					sysAllTaskValue.add(taskValue3);
				}

				userResult.setData(sysAllTaskValue);
				userResult.setCount(sysAllTaskValue.size());

				return userResult;
			}

			List<SysReimValue> sysReimValue = selectMapper.selectAllReim(sysSelectValue);
			for (SysReimValue sysReimValue2 : sysReimValue) {
				SysTaskValue taskValue3 = new SysTaskValue();
				taskValue3.setName(username);
				taskValue3.setId(userName);
				taskValue3.setDay("1");
				taskValue3.setGoTime(sysReimValue2.getCreateTime());
				taskValue3.setBackTime(sysReimValue2.getUseTime());
				taskValue3.setTotalPrice(sysReimValue2.getPrice());

				String title2 = "用户：" + username + ";	账户：" + userName + ";	" + sysReimValue2.getCreateTime()
						+ "创建的用于报销" + sysReimValue2.getUseTime() + "使用的一笔关于" + sysReimValue2.getGoods() + "的补助申请金额共计为"
						+ sysReimValue2.getPrice() + "元";
				taskValue3.setTitle(title2);
				sysAllTaskValue.add(taskValue3);
			}

			userResult.setData(sysAllTaskValue);
			userResult.setCount(sysAllTaskValue.size());

			return userResult;

		}

		userResult.setData(sysAllTaskValue);
		userResult.setCount(sysAllTaskValue.size());

		return userResult;
	}

	@Override
	public UserResult selectLoanDataList(String name, Integer page, Integer limit, String startTime, String endTime,
			String username) {
		UserResult userResult = new UserResult();

		String Executive = selectMapper.selectExecutiveByName(name);
		String role = selectMapper.selectUserByNameValue(name).getRole();

		if (role.equals("1")) {
			if (Executive.equals("4")) {
				userResult.setCode("erro");
				userResult.setMsg("用户无权限查看！");
				return userResult;
			}
		}

		int rowNumMin = (page - 1) * limit + 1;
		int rowNumMax = page * limit;
		List<SysTaskValue> sysAllTaskValue = new ArrayList<SysTaskValue>();

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		if (startTime == null) {
			startTime = "2021-01-01 00:00:00";
			endTime = datetime;
		} else if (startTime == "" && endTime == "") {
			startTime = "2021-01-01 00:00:00";
			endTime = datetime;
		} else if (startTime == "") {
			startTime = "2021-01-01 00:00:00";
		} else if (endTime == "") {
			endTime = datetime;
		}
		SysSelectValue sysSelectValue = new SysSelectValue();
		sysSelectValue.setName(username);
		sysSelectValue.setRowNumMin(rowNumMin);
		sysSelectValue.setRowNumMax(rowNumMax);
		sysSelectValue.setStartTime(startTime);
		sysSelectValue.setEndTime(endTime);

		if (username == null || username == "") {
			List<String> nameList = selectMapper.selectAllUserName();

			for (String str : nameList) {
				sysSelectValue.setName(str);

				List<String> priceList = selectMapper.selectLoanTotalPriceByName(sysSelectValue);
				int TaskCount = selectMapper.selectLoanCount(sysSelectValue);
				String userName = selectMapper.selectUserName(str);

				double a = 0;
				for (String price : priceList) {
					BigDecimal a1 = new BigDecimal(a);
					a = a1.add(new BigDecimal(price)).doubleValue();
				}
				String title = "用户：" + str + ";	账户：" + userName + ";	从" + startTime + "到" + endTime + "的" + TaskCount
						+ "笔借款申请金额共计为" + a + "元";

				SysTaskValue taskValue = new SysTaskValue();
				taskValue.setGoTime(startTime);
				taskValue.setBackTime(endTime);
				taskValue.setTotalPrice(a + "");
				taskValue.setDay(TaskCount + "");
				taskValue.setTitle(title);
				taskValue.setName(str);
				taskValue.setId(userName);
				sysAllTaskValue.add(taskValue);

			}

		} else {
			int count = selectMapper.selectUserByName(username);
			if (count == 0) {
				userResult.setCode("erro");
				userResult.setMsg("查无此人，请确认！");
				return userResult;
			}

			List<String> priceList = selectMapper.selectLoanTotalPriceByName(sysSelectValue);
			int TaskCount = selectMapper.selectLoanCount(sysSelectValue);
			String userName = selectMapper.selectUserName(username);

			double a = 0;
			for (String price : priceList) {
				BigDecimal a1 = new BigDecimal(a);
				a = a1.add(new BigDecimal(price)).doubleValue();
			}
			String title = "用户：" + username + ";	账户：" + userName + ";	从" + startTime + "到" + endTime + "的"
					+ TaskCount + "笔借款申请金额共计为" + a + "元";

			SysTaskValue taskValue = new SysTaskValue();
			taskValue.setGoTime(startTime);
			taskValue.setBackTime(endTime);
			taskValue.setTotalPrice(a + "");
			taskValue.setDay(TaskCount + "");
			taskValue.setTitle(title);
			taskValue.setName(username);
			taskValue.setId(userName);
			sysAllTaskValue.add(taskValue);

		}

		userResult.setData(sysAllTaskValue);
		userResult.setCount(sysAllTaskValue.size());

		return userResult;
	}

}
