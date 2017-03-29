package com.cyg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cyg.models.Event;
import com.cyg.service.EventService;
import com.cyg.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/event")
public class EventController {
	@Autowired
	EventService eventService;
	
	
	/**
	* @api {post} api/event/add 添加活动
	* @apiName event_add
	* @apiGroup event
	*
	* @apiParam {String} applyUnit * 申请单位（必须）
	* @apiParam {String} contactName * 联系人（必须）
	* @apiParam {String} contactPhone * 联系电话（必须）
	* @apiParam {String} mobilePhone * 手机（非必须）
	* @apiParam {String} useDateStr * 活动日期（必须，格式'yyyy-mm-dd',这个是用于服务器接受，注意，在获取活动的时候，字段名称为useDate）
	* @apiParam {String} useTimeId * 活动时间段（必须，格式如：3,4,5,6  ；  其中1=08:00-10:00,2=10:00-12:00,3=12:00-13:30,4=13:30-15:00,5=15:00-17:00,6=17:00-18:30,7=18:30-20:00,8=20:00-22:00）
	* @apiParam {String} eventName * 活动名称（必须）
	* @apiParam {String} eventContent * 活动内容（必须）
	* @apiParam {String} campus * 校区（必须，1:四平校区 2:嘉定校区）
	* @apiParam {String} rentalPlace * 租借场地类型（必须）
	* @apiParam {String} eventEquipment * 活动所需器材（必须,格式如：会议桌、多媒体投影仪）
	* @apiParam {String} otherEquipment * 活动所需要的其他器材（非必须）
	* @apiParam {file} file * 活动海报（必须，最终海报存的路径为photo字段，可在返回列表接口看到）
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
	public DataWrapper<Void> addEvent(
			@ModelAttribute Event event,
			@RequestParam(value = "file",required = true) MultipartFile file,
			@RequestParam(value = "useDateStr",required = true) String useDateStr,
            @RequestParam(value = "token",required = true) String token,
            HttpServletRequest request
			) {
		return eventService.addEvent(event, file,useDateStr, token,request);
	}
	
	
	/**
	* @api {post} api/event/verify 管理员审核活动
	* @apiName event_verify
	* @apiGroup event
	*
	* @apiParam {Long} eventId * 活动id（必须）
	* @apiParam {Integer} status * 状态:1审核中 2已通过 3不通过（必须）
	* @apiParam {String} token * 身份凭证（必须，管理员验证）
	* @apiParam {String} note  * 备注: 同一天(useDate)同一时间段(useTimeId)的同一个场地（rentalPlace），如果数据库中已经有审核通过的活动(status 2)，那么通过其他活动的时候，接口会返回冲突Confict错误
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
	*  		"errorCode": "Confict", //还有Error
	*  		"data": null,
	*  		"token": null,
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="verify",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> verify(
			@RequestParam(value = "eventId",required = true) Long eventId,
			@RequestParam(value = "status",required = true) Integer status,
            @RequestParam(value = "token",required = true) String token
			){
		return eventService.verify(eventId, status, token);
	}
	
	
	/**
	* @api {post} api/event/delete 删除活动
	* @apiName event_delete
	* @apiGroup event
	*
	* @apiParam {Long} eventId * 活动id（必须）
	* @apiParam {String} token * 身份凭证（必须，只有活动的创建者才能删除）
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
	public DataWrapper<Void> deleteEvent(
			@RequestParam(value = "eventId",required = true) Long eventId,
            @RequestParam(value = "token",required = true) String token
			){
		return eventService.deleteEvent(eventId, token);
	}
	
	
	/**
	* @api {get} api/event/getById 获取活动详情
	* @apiName event_getById
	* @apiGroup event
	*
	* @apiParam {Long} eventId * 活动id（必须）
	* @apiParam {String} token * 身份凭证（非必须，游客和普通用户只能看到status为2的活动详情，活动拥有者和管理员 能看到任何status的活动详情）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "SUCCEED",
	*  		"errorCode": "No_Error",
	*  		"data": {
    *			"id": 1363,
    *			"userId": 2086,
    *			"applyUnit": "测试单位",
    *			"contactName": "测试人",
    *			"contactPhone": "1356789",
    *			"mobilePhone": "",
    *			"useDate": "2017-05-01",
    *			"useTime": "17:00-18:30",
    *			"useTimeId": "6",
    *			"eventName": "测试活动",
    *			"eventContent": "活动内容",
    *			"campus": 1,
    *			"rentalPlace": "会议室4",
    *			"eventEquipment": "会议桌、多媒体投影仪",
    *			"otherEquipment": "",
    *			"photo": "photo/374491cdf43e8829b42ca21d8739084d.jpg",
    *			"appointmentTime": 1489999542,
    *			"status": 3
  	*		}
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
	@RequestMapping(value="getById",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<Event> getById(
			@RequestParam(value = "eventId",required = true) Long eventId,
            @RequestParam(value = "token",required = false) String token
			){
		return eventService.getById(eventId, token);
	}
	
	/**
	* @api {get} api/event/getEventList 获取活动列表
	* @apiName event_getEventList
	* @apiGroup event
	*
	* @apiParam {String} useTimeId * 使用时间，只能选一个
	* @apiParam {String} rentalPlace * 地点
	* @apiParam {Date} startDate * 开始日期（必须,格式'yyyy-mm-dd',对应字段useDate）
	* @apiParam {Date} endDate * 结束日期（必须,格式'yyyy-mm-dd',对应字段useDate）
	* @apiParam {int} status * （非必须,状态:1审核中 2已通过 3不通过,备注：未登录状态下，后端默认此参数为2；登录状态下，可以进行status筛选）
	* @apiParam {String} token  * 身份凭证（非必须，未登录只能看到已被通过的活动；管理员身份可以看到所有的活动；用户能看到本人创建的活动。）
	* @apiParam {int} numPerPage * （非必须）
	* @apiParam {int} pageNum * （非必须）
	* @apiParam {String} note1  * 这不是一个参数，只是给你看的备注:一个useDate可能有多条记录，代表某一天有多个活动（如例子中3-20日有两个活动）；而一个活动有多个useTimeId,代表一个活动有多个时间段（如例子中的‘总监例会’），所以前端需要做处理（如按照usetimeid去对一天的活动排序）。
	* @apiParam {String} note2  * 未登录：?startDate=2017-05-01&endDate=2017-05-03 ; 用户查看本人创建的活动:?startDate=2017-05-01&endDate=2017-05-03&token=xxxx; 管理员:token=SK49ab9f76-f7c0-4e7a-93ae-7ce86c5b2017&startDate=2017-05-01&useTimeId=3&status=2&numPerPage=10&pageNum=1&rentalPlace=会议室4
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	*	{
  	*		"callStatus": "SUCCEED",
  	*		"errorCode": "No_Error",
  	*		"data": [
    *			{
    *  				"id": 1322,
    *  				"userId": 1943,
    *  				"applyUnit": "同济大学创业谷",
    *  				"contactName": "苏宇轩",
    *  				"contactPhone": "18121136906",
    *  				"mobilePhone": "",
    *  				"useDate": "2017-03-20",
    *  				"useTime": "10:00-12:00,12:00-13:30",
    *  				"useTimeId": "2,3",
    *  				"eventName": "总监例会",
    *  				"eventContent": "总监例会",
    *  				"campus": 1,
    *  				"rentalPlace": "会议室1",
    *  				"eventEquipment": null,
    *  				"otherEquipment": "",
    *  				"photo": "",//海报，如果无海报，则为""
    *  				"appointmentTime": 1489320882,
    *  				"status": 2,
    *  				"applyCount": 0
    *			},
    *			{
    *  				"id": 1346,
    * 				 "userId": 2097,
    *  				"applyUnit": "同济大学创业谷",
    *  				"contactName": "肖昊坪",
    *  				"contactPhone": "13829537548",
    *  				"mobilePhone": "",
    *  				"useDate": "2017-03-20",
    *  				"useTime": "13:30-15:00",
    *  				"useTimeId": "4",
    *  				"eventName": "导师问诊",
    *  				"eventContent": "导师问诊",
    *  				"campus": 1,
    *  				"rentalPlace": "会议室1",
    *  				"eventEquipment": null,
    *  				"otherEquipment": "",
    * 				"photo": "",
    *  				"appointmentTime": 1489828472,
    *  				"status": 2,
    *  				"applyCount": 0
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
	@RequestMapping(value="getEventList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Event>> getEventList(
			@RequestParam(value = "useTimeId",required = false) String useTimeId,
			@RequestParam(value = "rentalPlace",required = false) String rentalPlace,
			
			
			@RequestParam(value = "startDate",required = false) String startDate,
            @RequestParam(value = "endDate",required = false) String endDate,
            @RequestParam(value = "status",required = false) Integer status,
            @RequestParam(value = "token",required = false) String token,
            @RequestParam(value = "numPerPage",required = false) Integer numPerPage,
            @RequestParam(value = "pageNum",required = false) Integer pageNum
			){
		return eventService.getEventList(useTimeId,rentalPlace,startDate, endDate,status, token,numPerPage,pageNum);
	}

}
