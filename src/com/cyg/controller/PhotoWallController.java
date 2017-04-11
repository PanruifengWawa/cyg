package com.cyg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cyg.models.PhotoWall;
import com.cyg.service.PhotoWallService;
import com.cyg.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/photoWall")
public class PhotoWallController {
	
	@Autowired
	PhotoWallService photoWallService;

	/**
	* @api {get} api/photoWall/getPhotoList 获取图片墙图片列表
	* @apiName photoWall_getPhotoList
	* @apiGroup photoWall
	*
	* @apiParam {Integer} year * 年份过滤（非必须）
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
    *  				"content": "我是内容啦2",
    *  				"src": "photoWall/df212a8e1091b0e1f40145e28be95e70.jpg",
    *  				"date": "2017-03-30",
    *  				"year": 2017
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
	@RequestMapping(value="getPhotoList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<PhotoWall>> getPhotoList(
			@RequestParam(value = "year",required = false) Integer year,
			@RequestParam(value = "numPerPage",required = false) Integer numPerPage,
            @RequestParam(value = "pageNum",required = false) Integer pageNum
			){
		return photoWallService.getPhotoList(year, numPerPage, pageNum);
	}
	
	
	/**
	* @api {post} api/photoWall/add 添加图片墙图片
	* @apiName photoWall_add
	* @apiGroup photoWall
	*
	* @apiParam {String} content * 图片墙图片内容（必须）
	* @apiParam {file} file * 图片（必须）
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
			@RequestParam(value = "content",required = true) String content,
			@RequestParam(value = "file",required = true) MultipartFile file,
			@RequestParam(value = "token",required = true) String token,
			HttpServletRequest request
			){
		return photoWallService.add(content, file,token,request);
	}
	
	/**
	* @api {post} api/photoWall/delete 删除图片墙图片
	* @apiName photoWall_delete
	* @apiGroup photoWall
	*
	* @apiParam {Long} photoWallId * id（必须）
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
			@RequestParam(value = "photoWallId",required = true) Long photoWallId,
			@RequestParam(value = "token",required = true) String token,
			HttpServletRequest request
			){
		return photoWallService.delete(photoWallId, token);
	}
}
