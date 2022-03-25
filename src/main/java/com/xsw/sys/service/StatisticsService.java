package com.xsw.sys.service;

import com.xsw.common.vo.UserResult;

public interface StatisticsService {

	UserResult selectAllstatistics();

	UserResult selectAllTotalpriceStatistics();

	UserResult selectAllTotalpriceStatisticsBySix();

}
