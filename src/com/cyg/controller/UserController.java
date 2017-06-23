package com.cyg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyg.models.User;
import com.cyg.service.UserService;
import com.cyg.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	/**
	* @api {post} api/user/school/login 校内登录 
	* @apiName user_school_login
	* @apiGroup user
	*
	* @apiParam {Integer} identity * 用户身份:3老师 4学生 (必须,注意：这里的3、4被认为是校内登录时注册行为，不需要密码)
	* @apiParam {String} loginName * 用户名（必须，长度4-31）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "SUCCEED",
	*  		"errorCode": "No_Error",
	*  		"data": null,
	*  		"token": null,
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "FAILED",
	*  		"errorCode": "Error",
	*  		"data": null,
	*  		"token": null,
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="/school/login", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<User> updateUser(
			@ModelAttribute User user
			) {
		return userService.schoolLogin(user);

	}
	
	/**
	* @api {post} api/user/update 修改用户信息
	* @apiName user_update
	* @apiGroup user
	*
	* @apiParam {String} phone * 电话（非必须）
	* @apiParam {String} email * 邮箱（非必须）
	* @apiParam {String} intention * 意向（非必须，格式如： 了解现状、收集创意、入驻创业谷、其他合作）
	* @apiParam {String} companyName * 企业名称(企业)（非必须）
	* @apiParam {String} companyType * 企业类型（非必须）
	* @apiParam {String} contactName * 联系人姓名(企业)（非必须）
	* @apiParam {String} name * 姓名(个人)（非必须）
	* @apiParam {String} idNumber * 身份证号(个人)（非必须）
	* @apiParam {String} college * 大学（非必须）
	* @apiParam {String} major * 专业（非必须）
	* @apiParam {String} teacherTitle * 教师职称（非必须）
	* @apiParam {String} workUnit * 工作单位（非必须）
	* @apiParam {String} officeSector * 工作部门（非必须）
	* @apiParam {String} token * 身份凭证（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "SUCCEED",
	*  		"errorCode": "No_Error",
	*  		"data": null,
	*  		"token": null,
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "FAILED",
	*  		"errorCode": "Error",
	*  		"data": null,
	*  		"token": null,
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> update(
			@ModelAttribute User user,
			@RequestParam(value = "token", required = true) String token
			) {
		return userService.updateUser(user, token);

	}
	
	/**
	* @api {get} api/user/getById 管理员获取用户详情
	* @apiName user_getById
	* @apiGroup user
	*
	* @apiParam {Long} userId * （必须）
	* @apiParam {String} token  * 身份凭证（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	*	{
  	*		"callStatus": "SUCCEED",
  	*		"errorCode": "No_Error",
  	*		"data": {
    *			"id": 2091,
    *			"identity": 2,
    *			"loginName": "46937922",
    *			"password": null,
    *			"phone": "13761463756",
    *			"email": "46937922@qq.com",
    *			"intention": "了解现状",
    *			"companyName": "",
    *			"companyType": null,
    *			"contactName": "",
    *			"name": "潘瑞峰",
    *			"idNumber": "310227199310222819",
    *			"registTime": 1489488524000,
    *			"college": null,
    *			"major": null,
    *			"teacherTitle": null,
    *			"workUnit": null,
    *			"officeSector": null
  	*		},
  	*		"token": null,
  	*		"numberPerPage": 0,
  	*		"currentPage": 0,
  	*		"totalNumber": 0,
  	*		"totalPage": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	* 		"callStatus": "FAILED",
	*		"errorCode": "Error",
	*  		"data": null,
	*  		"token": null,
	* 		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="/getById", method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<User> getById(
			@RequestParam(value = "userId",required = true) Long userId,
			@RequestParam(value = "token", required = true) String token
			) {
		return userService.getById(userId, token);

	}
	
	/**
	* @api {get} api/user/getUserList 管理员获取用户列表
	* @apiName user_getUserList
	* @apiGroup user
	*
	* @apiParam {int} identity * 含义请查看注册接口（非必须）
	* @apiParam {String} phone * （非必须）
	* @apiParam {String} email * （非必须）
	* @apiParam {String} companyName * （非必须）
	* @apiParam {String} name * （非必须）
	* @apiParam {int} numPerPage * （非必须）
	* @apiParam {int} pageNum * （非必须）
	* @apiParam {String} token  * 身份凭证（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	*	{
  	*		"callStatus": "SUCCEED",
  	*		"errorCode": "No_Error",
  	*		"data": [
    *			{
    *  				"id": 2086,
    *  				"identity": 1,
    *  				"loginName": "dps123",
    *  				"password": null,
    *  				"phone": null,
    *  				"email": null,
    *  				"intention": null,
    *  				"companyName": null,
    *  				"companyType": null,
    *  				"contactName": null,
    *  				"name": null,
    *  				"idNumber": null,
    *  				"registTime": 1489595581000,
    *  				"college": null,
    *  				"major": null,
    *  				"teacherTitle": null,
    *  				"workUnit": null,
    *  				"officeSector": null
    *			}
  	*		],
  	*		"token": null,
  	*		"numberPerPage": 10,
  	*		"currentPage": 1,
  	*		"totalNumber": 1,
  	*		"totalPage": 1
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	* 		"callStatus": "FAILED",
	*		"errorCode": "Error",
	*  		"data": null,
	*  		"token": null,
	* 		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="/getUserList", method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<User>> getUserList(
			@RequestParam(value = "identity",required = false) Integer identity,
			@RequestParam(value = "phone",required = false) String phone,
			@RequestParam(value = "email",required = false) String email,
			@RequestParam(value = "companyName",required = false) String companyName,
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "numPerPage",required = false) Integer numPerPage,
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
			@RequestParam(value = "token", required = true) String token
			) {
		return userService.getUserList(identity, phone, email, companyName, name, numPerPage, pageNum, token);

	}
	
	/**
	* @api {post} api/user/login 登录
	* @apiName user_login
	* @apiGroup user
	*
	* @apiParam {String} loginName * 用户名（必须）
	* @apiParam {String} password * 密码（校内登录不需要密码）
	* @apiParam {Integer} type * 管理员登录-0，校内（学生和老师）- 1，校外（个人和企业） - 2
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*		"callStatus": "SUCCEED",
	*		"errorCode": "No_Error",
	*  		"data": {
    *			"id": 2091,
    *			"identity": 2,
    *			"loginName": "46937922",
    *			"password": null,
    *			"phone": "13761463756",
    *			"email": "46937922@qq.com",
    *			"intention": "投资项目、了解现状、收集创意、寻找对接项目、发布课题、入驻创业谷",
    *			"companyName": "",
    *			"companyType": null,
    *			"contactName": "",
    *			"name": "潘瑞峰",
    *			"idNumber": "310227199310222819",
    *			"registTime": 1489488524000,
    *			"college": null,
    *			"major": null,
    *			"teacherTitle": null,
    *			"workUnit": null,
    *			"officeSector": null
  	*		},
	*  		"token": "SK1d7a4fe3-c2cd-417f-8f6f-bf7412592996",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	* 		"callStatus": "FAILED",
	*		"errorCode": "Error",
	*  		"data": null,
	*  		"token": null,
	* 		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<User> login(
			@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "type", required = true) Integer type
			) {
		return userService.login(loginName, password, type);
	}
	
	
	/**
	* @api {post} api/user/register 注册 
	* @apiName user_regist
	* @apiGroup user
	*
	* @apiParam {Integer} identity * 用户身份:1企业 2个人 3老师 4学生 (必须,注意：这里的3、4被认为是校内登录时注册行为，不需要密码)
	* @apiParam {String} loginName * 用户名（必须，长度4-31）
	* @apiParam {String} password * 密码（校内不需要密码）
	* @apiParam {String} phone * 电话（非必须）
	* @apiParam {String} email * 邮箱（非必须）
	* @apiParam {String} intention * 意向（非必须，格式如： 了解现状、收集创意、入驻创业谷、其他合作）
	* @apiParam {String} companyName * 企业名称(企业)（非必须）
	* @apiParam {String} companyType * 企业类型（非必须）
	* @apiParam {String} contactName * 联系人姓名(企业)（非必须）
	* @apiParam {String} name * 姓名(个人)（非必须）
	* @apiParam {String} idNumber * 身份证号(个人)（非必须）
	* @apiParam {String} college * 大学（非必须）
	* @apiParam {String} major * 专业（非必须）
	* @apiParam {String} teacherTitle * 教师职称（非必须）
	* @apiParam {String} workUnit * 工作单位（非必须）
	* @apiParam {String} officeSector * 工作部门（非必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "SUCCEED",
	*  		"errorCode": "No_Error",
	*  		"data": null,
	*  		"token": null,
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "FAILED",
	*  		"errorCode": "Error",
	*  		"data": null,
	*  		"token": null,
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="/register", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> login(
			@ModelAttribute User user
			) {
		return userService.register(user);
	}
	
	/**
	* @api {post} api/user/deleteUser 删除
	* @apiName user_deleteUser
	* @apiGroup user
	*
	* @apiParam {Integer} userId * 
	* @apiParam {String} token * 
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "SUCCEED",
	*  		"errorCode": "No_Error",
	*  		"data": null,
	*  		"token": null,
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "FAILED",
	*  		"errorCode": "Error",
	*  		"data": null,
	*  		"token": null,
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> deleteUser(
			@RequestParam(value = "userId", required = true) Long userId,
			@RequestParam(value = "token", required = true) String token
			) {
		return userService.deleteUser(userId, token);
	}
	
	
}
