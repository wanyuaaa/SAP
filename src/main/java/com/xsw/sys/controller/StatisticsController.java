package com.xsw.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsw.common.vo.UserResult;
import com.xsw.sys.service.StatisticsService;

@Controller
@RequestMapping("statistics")
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;

	@RequestMapping("selectAllStatistics")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllstatistics() {

		UserResult userResult = statisticsService.selectAllstatistics();

		return userResult;

	}

	@RequestMapping("selectAllTotalpriceStatistics")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllTotalpriceStatistics() {

		UserResult userResult = statisticsService.selectAllTotalpriceStatistics();

		return userResult;

	}

	@RequestMapping("selectAllTotalpriceStatisticsBySix")
	@ResponseBody
	@CrossOrigin
	public UserResult selectAllTotalpriceStatisticsBySix() {

		UserResult userResult = statisticsService.selectAllTotalpriceStatisticsBySix();

		return userResult;

	}
}
