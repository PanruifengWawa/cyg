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

import com.cyg.models.Material;
import com.cyg.service.MaterialService;
import com.cyg.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/file")
public class FileController {
	
	@Autowired
	MaterialService materialService;
	
	
	/**
	* @api {get} api/file/material/getMaterialList 获取下载资料列表
	* @apiName file_getMaterialList
	* @apiGroup file
	*
	* @apiParam {int} numPerPage * （非必须）
	* @apiParam {int} pageNum * （非必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	*	{
  	*		"callStatus": "SUCCEED",
  	*		"errorCode": "No_Error",
  	*		"data": [
    *			{
    *  				"id": 1687,
    *  				"name": "2017卓越工程师法国行报名表",
    *  				"url": "themes/222/userfiles/download/2017/3/13/xjit9i0bos4un13.doc",
    *  				"createTime": 1489412753
    *			}
  	*		],
  	*		"token": null,
  	*		"numberPerPage": 1,
  	*		"currentPage": 1,
  	*		"totalNumber": 20,
  	*		"totalPage": 20
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
	@RequestMapping(value="material/getMaterialList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Material>> getMaterialList(
			@RequestParam(value = "numPerPage",required = false) Integer numPerPage,
            @RequestParam(value = "pageNum",required = false) Integer pageNum
			){
		return materialService.getMaterialList(numPerPage, pageNum);
	}
	
	
	/**
	* @api {post} api/file/material/upload 管理员上传资料
	* @apiName file_material_upload
	* @apiGroup file
	*
	* @apiParam {String} name * 资料名称名称（必须）
	* @apiParam {file} material * 资料文件（必须）
	* @apiParam {String} token * 身份验证（必须）
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
	@RequestMapping(value="material/upload",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> uploadMaterial(
			@RequestParam(value = "name",required = true) String name,
			@RequestParam(value = "material", required = true) MultipartFile material,
            @RequestParam(value = "token",required = true) String token,
            HttpServletRequest request
			){
		return materialService.uploadMaterial(name, material, token,request);
	}
	
	
	/**
	* @api {post} api/file/material/delete 管理员删除资料
	* @apiName file_material_delete
	* @apiGroup file
	*
	* @apiParam {Long} materialId * id（必须）
	* @apiParam {String} token * 身份验证（必须）
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
	@RequestMapping(value="material/delete",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> deleteMaterial(
			@RequestParam(value = "materialId",required = true) Long materialId,
            @RequestParam(value = "token",required = true) String token,
            HttpServletRequest request
			){
		return materialService.deleteMaterial(materialId, token);
	}
	
	/**
	* @api {post} api/file/upload 管理员上传文件
	* @apiName file_upload
	* @apiGroup file
	*
	* @apiParam {file} file * 文件（必须，本接口的目的：当管理员添加新闻时，可能会上传新闻封面图片，或者对新闻内容进行富文本编辑的时候，会在文本内有图片；本接口提供上传文件，并返回路径，前端可以直接使用此路径）
	* @apiParam {String} token * 身份验证（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "SUCCEED",
	*  		"errorCode": "No_Error",
	*  		"data": "aaaaa.jpg",
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
	@RequestMapping(value="upload",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<String> upload(
			@RequestParam(value = "file",required = true) MultipartFile file,
            @RequestParam(value = "token",required = true) String token,
            HttpServletRequest request
			){
		return materialService.uploadFile(file, token, request);
	}

}
