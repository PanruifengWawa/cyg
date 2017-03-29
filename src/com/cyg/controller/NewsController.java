package com.cyg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyg.models.News;
import com.cyg.service.NewsService;
import com.cyg.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/news")
public class NewsController {
	
	@Autowired
	NewsService newsService;

	
	/**
	* @api {get} api/news/getNewsList 获取新闻列表
	* @apiName news_getNewsList
	* @apiGroup news
	*
	* @apiParam {int} ifImage * （非必须,1-必须有图片）
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
    *  				"id": 9046,
    *  				"title": "同济创业谷大讲堂启动暨首场讲座举行",
    *  				"content": "&lt;p&gt;&nbsp;&nbsp;&nbsp;&nbsp;5月2日，“同济创业谷大讲堂”启动仪式暨首场&lt;/p&gt;",
    *  				"date": 1385425321,
    *  				"pic": "themes/222/userfiles/images/2014/6/17/650/s8n6gc0klohy5fo.jpg", //也可能是"pic":"",代表无图片
    *  				"userName": "cygadmin",
    *  				"readPoint": 0,
    *	 	 		"updateDate": 0
    *			}
  	*		],
  	*		"token": null,
  	*		"numberPerPage": 4,
  	*		"currentPage": 64,
  	*		"totalNumber": 253,
  	*		"totalPage": 64
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
	@RequestMapping(value="getNewsList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<News>> getNewsList(
			@RequestParam(value = "ifImage",required = false) Integer ifImage,
			@RequestParam(value = "numPerPage",required = false) Integer numPerPage,
            @RequestParam(value = "pageNum",required = false) Integer pageNum
			){
		return newsService.getNewsList(ifImage,numPerPage, pageNum);
	}
	
	
	/**
	* @api {post} api/news/addNews 添加新闻
	* @apiName news_addNews
	* @apiGroup news
	*
	* @apiParam {String} title * 新闻标题（必须）
	* @apiParam {String} content * 新闻内容（必须）
	* @apiParam {String} pic * 新闻封面路径（非必须，若有，先调用文件上传接口，获取文件的路径，填至此）
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
	@RequestMapping(value="addNews",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> addNews(
			@ModelAttribute News news,
            @RequestParam(value = "token",required = true) String token
			){
		return newsService.addNews(news, token);
	}
	
	
	/**
	* @api {post} api/news/deleteNews 删除新闻
	* @apiName news_deleteNews
	* @apiGroup news
	*
	* @apiParam {Long} newsId * 新闻id（必须）
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
	@RequestMapping(value="deleteNews",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> deleteNews(
			@RequestParam(value = "newsId",required = true) Long newsId,
            @RequestParam(value = "token",required = true) String token
			){
		return newsService.deleteNews(newsId, token);
	}
	
	
	/**
	* @api {get} api/news/getById 根据id获取新闻
	* @apiName news_getById
	* @apiGroup news
	*
	* @apiParam {int} newsId * （必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	*	{
  	*		"callStatus": "SUCCEED",
  	*		"errorCode": "No_Error",
  	*		"data": {
    *			"id": 9475,
    *			"title": "同济大学•成都龙泉国际青年创业谷欧洲代表处签约揭牌",
    *			"content": "&lt;p&gt;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日前，同济原则和校外“一基地多园区”规划目标的重要体现。&lt;/p&gt;",
    *			"date": 1489626903，
    *			"pic": "themes/222/userfiles/images/2014/6/17/650/s8n6gc0klohy5fo.jpg", //也可能是"pic":"",代表无图片
    *			"userName": "cygadmin",
    *  			"readPoint": 0,
    *  			"updateDate": 0
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
	@RequestMapping(value="getById",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<News> getById(
			@RequestParam(value = "newsId",required = true) Long newsId
			){
		return newsService.getById(newsId);
	}
	
	/**
	* @api {post} api/news/update 修改新闻
	* @apiName news_update
	* @apiGroup news
	*
	* @apiParam {int} id * 新闻id（必须）
	* @apiParam {String} title * 新闻标题（非必须）
	* @apiParam {String} content * 新闻内容（非必须）
	* @apiParam {String} pic * 新闻封面路径（非必须，若有，先调用文件上传接口，获取文件的路径，填至此）
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
	@RequestMapping(value="update",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> update(
			@ModelAttribute News news,
            @RequestParam(value = "token",required = true) String token
			){
		return newsService.update(news, token);
	}
}
