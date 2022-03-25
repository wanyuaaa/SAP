package com.xsw.sys.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xsw.common.entry.SysLoanValue;
import com.xsw.common.entry.SysPurchValue;
import com.xsw.common.entry.SysReimValue;
import com.xsw.common.entry.SysTaskValue;
import com.xsw.common.entry.SysUserValue;
import com.xsw.common.vo.UserResult;
import com.xsw.sys.mapper.SelectMapper;
import com.xsw.sys.service.InsertService;

@Controller
@RequestMapping("insert")
public class InsertController {

	@Autowired
	private InsertService insertService;

	@Autowired
	private SelectMapper selectMapper;

	@RequestMapping("insertTaskPrintCount")
	@ResponseBody
	@CrossOrigin
	public UserResult insertTaskPrintCount(@RequestBody String params) {
		SysTaskValue parseObject = JSON.parseObject(params, SysTaskValue.class);
		String id = parseObject.getId();

		insertService.insertTaskPrintCount(id);
		return new UserResult();
	}

	@RequestMapping("insertReimPrintCount")
	@ResponseBody
	@CrossOrigin
	public UserResult insertReimPrintCount(@RequestBody String params) {
		SysTaskValue parseObject = JSON.parseObject(params, SysTaskValue.class);
		String id = parseObject.getId();
		System.out.println(id);
		insertService.insertReimPrintCount(id);
		return new UserResult();
	}

	@RequestMapping("upload")
	@ResponseBody
	@CrossOrigin
	public UserResult upload(@Param("file") MultipartFile file, @Param("id") String id) throws Exception {
		UserResult userResult = new UserResult();
		SysReimValue selectReimByIdValue = selectMapper.selectReimByIdValue(id);

		String progress = selectReimByIdValue.getProgress();
		if (selectReimByIdValue.getStatus().equals("1")) {
			userResult.setCode("erro");
			userResult.setMsg("审核已完成，请勿提交修改！");
			return userResult;
		} else if (selectReimByIdValue.getStatus().equals("0")) {
			if (!progress.equals("0")) {
				userResult.setCode("erro");
				userResult.setMsg("审核中，请勿提交修改！");
				return userResult;
			}
		}

		String FileName = file.getOriginalFilename();
		if ("".equals(FileName))
			return null;

		String suffixName = FileName.substring(FileName.indexOf(".") + 1);

		long l = System.currentTimeMillis();
		FileName = l + "." + suffixName;

		String path = "E:\\pic_dir\\";

		File realPath = new File(path);
		if (!realPath.exists())
			realPath.mkdir();

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		selectReimByIdValue.setStatus("0");
		selectReimByIdValue.setProgress("0");
		selectReimByIdValue.setAddress("/image/" + FileName);
		selectReimByIdValue.setManager("0");
		selectReimByIdValue.setManager_time(datetime);
		selectReimByIdValue.setManager_value("尚未审核,请等待！");
		selectReimByIdValue.setAccounting_time(datetime);
		selectReimByIdValue.setAccounting_value("尚未审核,请等待！");
		selectReimByIdValue.setFinance_time(datetime);
		selectReimByIdValue.setFinance_value("尚未审核,请等待！");
		selectReimByIdValue.setExecutive("0");
		selectReimByIdValue.setAccounting("0");
		selectReimByIdValue.setFinance("0");
		selectReimByIdValue.setReason("");
		// 存入数据库id
		insertService.insertPictureSrc(selectReimByIdValue);

		InputStream is = file.getInputStream(); // 文件输入流
		OutputStream os = new FileOutputStream(new File(realPath, FileName)); // 文件输出流
		// 读取写出
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
			os.flush();
		}
		os.close();
		is.close();

		return userResult;
	}

	@RequestMapping("loanAdd")
	@ResponseBody
	@CrossOrigin
	public UserResult loanAdd(@RequestBody String params) {

		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = JSONObject.parseObject(params.split("=")[0]);
		SysLoanValue sysLoanValue = JSON.toJavaObject(jsonObject, SysLoanValue.class);

		String name = params.split("=")[1].split("&")[1];
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = tempDate.format(new java.util.Date());

		sysLoanValue.setCreateTime(createTime);
		sysLoanValue.setName(name);

		UserResult result = insertService.loanAdd(sysLoanValue);

		return result;
	}

	@RequestMapping("purchAdd")
	@ResponseBody
	@CrossOrigin
	public UserResult purchAdd(@RequestBody String params) {

		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = JSONObject.parseObject(params.split("=")[0]);
		SysPurchValue sysPurchValue = JSON.toJavaObject(jsonObject, SysPurchValue.class);

		String name = params.split("=")[1].split("&")[1];
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = tempDate.format(new java.util.Date());

		sysPurchValue.setCreateTime(createTime);
		sysPurchValue.setName(name);

		UserResult result = insertService.purchAdd(sysPurchValue);

		return result;
	}

	@RequestMapping("reimAdd")
	@ResponseBody
	@CrossOrigin
	public UserResult reimAdd(@RequestBody String params) {

		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = JSONObject.parseObject(params.split("=")[0]);
		SysReimValue sysReimValue = JSON.toJavaObject(jsonObject, SysReimValue.class);

		String name = params.split("=")[1].split("&")[1];
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = tempDate.format(new java.util.Date());

		sysReimValue.setCreateTime(createTime);
		sysReimValue.setName(name);

		System.out.println(sysReimValue.toString());

		UserResult result = insertService.reimAdd(sysReimValue);

		return result;
	}

	@RequestMapping("taskAdd")
	@ResponseBody
	@CrossOrigin
	public UserResult taskAdd(@RequestBody String params) {

		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println(params);

		JSONObject jsonObject = JSONObject.parseObject(params.split("=")[0]);
		SysTaskValue sysTaskValue = JSON.toJavaObject(jsonObject, SysTaskValue.class);

		String name = params.split("=")[1].split("&")[1];
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = tempDate.format(new java.util.Date());

		sysTaskValue.setName(name);
		sysTaskValue.setCreateTime(createTime);

		UserResult result = insertService.taskAdd(sysTaskValue);
		return result;
	}

	@RequestMapping("addUser")
	@ResponseBody
	@CrossOrigin
	public UserResult addUser(@RequestBody String params) {

		try {
			params = URLDecoder.decode(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = JSONObject.parseObject(params.split("=&")[0]);
		SysUserValue sysUserValue = JSON.toJavaObject(jsonObject, SysUserValue.class);
		String name = params.split("=&")[1].split("=")[0];

		String status = "1";
		String sex = "0";

		int max = 9999, min = 1000;
		long randomNum = System.currentTimeMillis();
		int ran3 = (int) (randomNum % (max - min) + min);
		String id = "SX_XSW_" + ran3;
		sysUserValue.setId(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updateTime = df.format(new Date());
		sysUserValue.setUpdateTime(updateTime);
		sysUserValue.setLogins("0");
		sysUserValue.setSex(sex);

		if (sysUserValue.getStatus() == null)
			sysUserValue.setStatus(status);

		UserResult result = insertService.addUser(sysUserValue, name);

		return result;
	}

}
