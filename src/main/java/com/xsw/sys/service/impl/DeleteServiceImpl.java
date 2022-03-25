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
import com.xsw.sys.mapper.DeleteMapper;
import com.xsw.sys.mapper.InsertMapper;
import com.xsw.sys.mapper.SelectMapper;
import com.xsw.sys.mapper.UpdateMapper;
import com.xsw.sys.service.DeleteService;

@Service
public class DeleteServiceImpl implements DeleteService {

	@Autowired
	private SelectMapper selectMapper;

	@Autowired
	private DeleteMapper deleteMapper;

	@Autowired
	private UpdateMapper UpdateMapper;

	@Autowired
	private InsertMapper insertMapper;

	@Override
	public UserResult deleteUser(String id) {
		UserResult userResult = new UserResult();

		int userId = selectMapper.selectUserById(id);
		if (userId == 0) {
			userResult.setCode("erro");
			userResult.setMsg("删除失败，用户未查询到！");
			return userResult;
		}

		int userDelete = deleteMapper.deleteUser(id);
		if (userDelete == 0) {
			userResult.setCode("erro");
			userResult.setMsg("删除失败，用户未查询到！");
			return userResult;
		}

		return userResult;
	}

	@Override
	public UserResult deleteTask(String id) {
		UserResult result = new UserResult();
		result.setMsg("删除成功！");

		SysTaskValue sysTaskValue = selectMapper.selectTaskByIdValue(id);
		if (sysTaskValue.getStatus().equals("1") || sysTaskValue.getStatus() == null) {
			result.setCode("erro");
			result.setMsg("删除失败，申请已完成！");
			return result;
		}

		int userDelete = deleteMapper.deleteTask(id);
		if (userDelete == 0) {
			result.setCode("erro");
			result.setMsg("删除失败，系统错误！");
			return result;
		}

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(sysTaskValue.getName());

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		String title = "用户：" + sysTaskValue.getName() + ";于" + datetime + "删除了id：" + id + "的" + sysTaskValue.getTitle();
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("删除采购申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			result.setCode("erro");
			result.setMsg("日志记录失败，请确认！");
			return result;
		}

		return result;
	}

	@Override
	public UserResult deleteLoan(String id) {
		UserResult result = new UserResult();
		result.setMsg("删除成功！");

		SysLoanValue sysLoanValue = selectMapper.selectLoanByIdValue(id);
		if (sysLoanValue.getStatus().equals("1") || sysLoanValue.getStatus() == null) {
			result.setCode("erro");
			result.setMsg("删除失败，申请已完成！");
			return result;
		}

		int userDelete = deleteMapper.deleteLoan(id);
		if (userDelete == 0) {
			result.setCode("erro");
			result.setMsg("删除失败，系统错误！");
			return result;
		}

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(sysLoanValue.getName());

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		String title = "用户：" + sysLoanValue.getName() + ";于" + datetime + "删除了id：" + id + "的" + sysLoanValue.getTitle();
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("删除采购申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			result.setCode("erro");
			result.setMsg("日志记录失败，请确认！");
			return result;
		}

		return result;
	}

	@Override
	public UserResult deletePurch(String id) {
		UserResult result = new UserResult();
		result.setMsg("删除成功！");

		SysPurchValue sysPurchValue = selectMapper.selectPurchByIdValue(id);
		if (sysPurchValue.getStatus().equals("1") || sysPurchValue.getStatus() == null) {
			result.setCode("erro");
			result.setMsg("删除失败，申请已完成！");
			return result;
		}

		int userDelete = deleteMapper.deletePurch(id);
		if (userDelete == 0) {
			result.setCode("erro");
			result.setMsg("删除失败，系统错误！");
			return result;
		}

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(sysPurchValue.getName());

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		String title = "用户：" + sysPurchValue.getName() + ";于" + datetime + "删除了id：" + id + "的"
				+ sysPurchValue.getTitle();
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("删除采购申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			result.setCode("erro");
			result.setMsg("日志记录失败，请确认！");
			return result;
		}

		return result;
	}

	@Override
	public UserResult deleteReim(String id) {
		UserResult result = new UserResult();
		result.setMsg("删除成功！");

		SysReimValue sysReimValue = selectMapper.selectReimByIdValue(id);
		if (sysReimValue.getStatus().equals("1") || sysReimValue.getStatus() == null) {
			result.setCode("erro");
			result.setMsg("删除失败，申请已完成！");
			return result;
		}

		int userDelete = deleteMapper.deleteReim(id);
		if (userDelete == 0) {
			result.setCode("erro");
			result.setMsg("删除失败，系统错误！");
			return result;
		}

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(sysReimValue.getName());

		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);

		String title = "用户：" + sysReimValue.getName() + ";于" + datetime + "删除了id：" + id + "的" + sysReimValue.getTitle();
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("删除报销申请");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			result.setCode("erro");
			result.setMsg("日志记录失败，请确认！");
			return result;
		}

		return result;
	}

	@Override
	public UserResult deletePicSrc(String id) {
		UserResult result = new UserResult();
		result.setMsg("删除成功！");

		SysReimValue sysReimValue = selectMapper.selectReimByIdValue(id);
		if (sysReimValue.getStatus().equals("1") || sysReimValue.getStatus() == null) {
			result.setCode("erro");
			result.setMsg("删除失败，申请已完成！");
			return result;
		} else if (sysReimValue.getStatus().equals("0") && !sysReimValue.getProgress().equals("0")) {
			result.setCode("erro");
			result.setMsg("删除失败，申请进行中，请删除任务或联系审核人员！");
			return result;
		}

		String address = "/image/123.jpg";

		String status = sysReimValue.getStatus();
		String progress = "0";
		int userDelete = UpdateMapper.updataPictureSrcDelete(id, address, progress, status);
		if (userDelete == 0) {
			result.setCode("erro");
			result.setMsg("删除失败，系统错误！");
			return result;
		}

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		SysOperationValue sysOperationValue = new SysOperationValue();
		sysOperationValue.setCreateTime(datetime);
		sysOperationValue.setName(sysReimValue.getName());

		String title = "用户：" + sysReimValue.getName() + ";于" + datetime + "删除报销图片于id：" + sysReimValue.getId() + "的"
				+ sysReimValue.getTitle();
		int max = 99999999, min = 10000000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);
		id = "SX_XSW_OPER_" + ran3;

		sysOperationValue.setId(id);
		sysOperationValue.setTitle(title);
		sysOperationValue.setType("删除报销图片");
		int count = insertMapper.insertOperationValue(sysOperationValue);

		if (count == 0) {
			result.setCode("erro");
			result.setMsg("日志记录失败，请确认！");
			return result;
		}

		return result;
	}

}
