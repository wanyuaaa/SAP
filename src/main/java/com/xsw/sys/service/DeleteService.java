package com.xsw.sys.service;

import com.xsw.common.vo.UserResult;

public interface DeleteService {

	UserResult deleteUser(String id);

	UserResult deleteTask(String id);

	UserResult deletePurch(String id);

	UserResult deleteReim(String id);

	UserResult deletePicSrc(String id);

	UserResult deleteLoan(String id);

}
