package com.cyg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyg.models.Mooc;
import com.cyg.service.MoocService;
import com.cyg.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/mooc")
public class MoocController {
	@Autowired
	MoocService moocService;
	
	
	/**
	* @api {get} api/mooc/getQiNiuToken 获取七牛uptoken
	* @apiName mooc_getQiNiuToken
	* @apiGroup mooc
	*
	* @apiParam {String} token * 身份凭证（必须，管理员验证）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "SUCCEED",
	*  		"errorCode": "No_Error",
	*  		"data": "2EkHs4sPHlelB-JYR5WuDp3jp9spsqyxIkluejva:7qWjUWr2RZTC71Xv3_tUwGWfh_E=:eyJzY29wZSI6ImN5Z2ZpbGUiLCJkZWFkbGluZSI6MTQ5MDg5NDE1NX0=",
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
	@RequestMapping(value="getQiNiuToken",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<String> getQiNiuToken(
            @RequestParam(value = "token",required = true) String token
			){
		return moocService.getQiNiuToken(token);
	}
	
	
	/**
	* @api {get} api/mooc/getMoocList 获取慕课视频列表
	* @apiName mooc_getMoocList
	* @apiGroup mooc
	*
	* @apiParam {int} numPerPage * （非必须）
	* @apiParam {int} pageNum * （非必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "SUCCEED",
	*  		"errorCode": "No_Error",
	*  		"data": [
    *			{
    *  				"id": 3,
    *  				"title": "演示视频1",
    *  				"src": "http://onktd2a1f.bkt.clouddn.com/li7AxWsjYdihZZdvAIi1pdxKYwsf",
    *  				"date": "2017-03-30"
    *			}
  	*		],
	*  		"token": null,
	*  		"numberPerPage": 10,
	*  		"currentPage": 1,
	*  		"totalNumber": 1,
	*  		"totalPage": 1
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
	@RequestMapping(value="getMoocList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Mooc>> getMoocList(
			@RequestParam(value = "numPerPage",required = false) Integer numPerPage,
            @RequestParam(value = "pageNum",required = false) Integer pageNum
			){
		return moocService.getMoocList(numPerPage, pageNum);
	}
	
	
	/**
	* @api {post} api/mooc/add 添加慕课视频
	* @apiName mooc_add
	* @apiGroup mooc
	*
	* @apiParam {String} title * 视频标题（必须）
	* @apiParam {String} src * 视频路径（必须，该路径是上传到七牛存储后的路径,形如http://onktd2a1f.bkt.clouddn.com/li7AxWsjYdihZZdvAIi1pdxKYwsf，获取方法问我）
	* @apiParam {String} token * 身份凭证（必须，管理员验证）
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
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> add(
			@RequestParam(value = "title",required = true) String title,
            @RequestParam(value = "src",required = true) String src,
            @RequestParam(value = "token",required = true) String token
			){
		return moocService.add(title, src, token);
	}
	
	
	/**
	* @api {post} api/mooc/delete 删除慕课视频
	* @apiName mooc_delete
	* @apiGroup mooc
	*
	* @apiParam {Long} moocId * id（必须）
	* @apiParam {String} token * 身份凭证（必须，管理员验证）
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
	@RequestMapping(value="delete",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> delete(
			@RequestParam(value = "moocId",required = true) Long moocId,
            @RequestParam(value = "token",required = true) String token
			){
		return moocService.delete(moocId, token);
	}
	

}
