"use strict";
layui.define([], function (exprots) {

	var strCookie=document.cookie;
    var arr = strCookie.split("; ");  
    var name = window.decodeURIComponent(window.atob(arr[1].substring(5)));

    let okMock = {
        api: {
            login: "http://192.168.1.10/sap/select/selectUserLogin.do#",
            listUser: "http://192.168.1.10/sap/select/selectAllUser.do?name="+name,
            listTask: "http://192.168.1.10/sap/select/selectAllTask.do?name="+name,
            listTaskLast: "http://192.168.1.10/sap/select/selectAllTaskLast.do?name="+name,
            listPurchLast: "http://192.168.1.10/sap/select/selectAllPurchLast.do?name="+name,
            listLoanLast: "http://192.168.1.10/sap/select/selectAllLoanLast.do?name="+name,
            listReimLast: "http://192.168.1.10/sap/select/selectAllReimLast.do?name="+name,
            listTaskExamine:"http://192.168.1.10/sap/select/selectExecutiveTask.do?name="+name,
            listTaskData:"http://192.168.1.10/sap/selectData/selectTaskDataList.do?name="+name,
            listReimData:"http://192.168.1.10/sap/selectData/selectReimDataList.do?name="+name,
            listLoanData:"http://192.168.1.10/sap/selectData/selectLoanDataList.do?name="+name,
            listPurchExamine:"http://192.168.1.10/sap/select/selectExecutivePurch.do?name="+name,
            listReimExamine:"http://192.168.1.10/sap/select/selectExecutiveReim.do?name="+name,
            listReim: "http://192.168.1.10/sap/select/selectAllReim.do?name="+name,
            listPurch: "http://192.168.1.10/sap/select/selectAllPurch.do?name="+name,
            listLoan: "http://192.168.1.10/sap/select/selectAllLoan.do?name="+name,
            listLoanExamine:"http://192.168.1.10/sap/select/selectExecutiveLoan.do?name="+name,
            operationList:"http://192.168.1.10/sap/select/selectOperationList.do?name="+name,

            menu: {
                list: "https://easy-mock.com/mock/5d0ce725424f15399a6c2068/okadmin/menu/list"
            },
            user: {
                list: "http://rap2api.taobao.org/app/mock/233041/user/list",
            },
            role: {
                list: "http://rap2api.taobao.org/app/mock/233041/role/list"
            },
            permission: {
                list: "http://rap2api.taobao.org/app/mock/233041/permission/list",
            },
            article: {
                list: "http://rap2api.taobao.org/app/mock/233041/article/list"
            },
            task: {
                list: "http://rap2api.taobao.org/app/mock/233041/task/list"
            },
            link: {
                list: "http://rap2api.taobao.org/app/mock/233041/link/list"
            },
            product: {
                list: "http://rap2api.taobao.org/app/mock/233041/product/list"
            },
            log: {
                list: "https://easy-mock.com/mock/5d0ce725424f15399a6c2068/okadmin/log/list"
            },
            message: {
                list: "http://rap2api.taobao.org/app/mock/233041/message/list"
            },
            download: {
                list: "http://rap2api.taobao.org/app/mock/233041/download/list"
            },
            bbs: {
                list: "http://rap2api.taobao.org/app/mock/233041/bbs/list"
            }
        }
    };
    exprots("okMock", okMock);
});
