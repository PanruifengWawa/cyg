package com.cyg.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyg.models.Event;
import com.cyg.models.EventApply;
import com.cyg.service.EventApplyService;
import com.cyg.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/eventApply")
public class EventApplyController {
	
	@Autowired
	EventApplyService eventApplyService;
	
	
	/**
	* @api {post} api/eventApply/add 报名参加活动
	* @apiName eventApply_add
	* @apiGroup eventApply
	*
	* @apiParam {Long} eventId * 活动id（必须）
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
	public DataWrapper<Void> addEventApply(
			@RequestParam(value = "eventId",required = true) Long eventId,
            @RequestParam(value = "token",required = true) String token
			){
		return eventApplyService.addEventApply(eventId, token);
	}
	
	
	/**
	* @api {post} api/eventApply/verify 管理员审核某个报名
	* @apiName eventApply_verify
	* @apiGroup eventApply
	*
	* @apiParam {Long} eventApplyId * 报名参加的id（必须,注意不是eventid，是通过getEventApplyList获取的数据里面的id）
	* @apiParam {Integer} state * 状态（必须,状态0:审核中 1:已审核）
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
	@RequestMapping(value="verify",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> verifyEventApply(
			@RequestParam(value = "eventApplyId",required = true) Long eventApplyId,
			@RequestParam(value = "state",required = true) Integer state,
            @RequestParam(value = "token",required = true) String token
			){
		return eventApplyService.verifyEventApply(eventApplyId, state, token);
	}
	
	
	
	/**
	* @api {get} api/eventApply/getEventApplyList 管理员获取报名参加活动的用户(仅包含用户的id和用户的登录名)
	* @apiName eventApply_getEventApplyList
	* @apiGroup eventApply
	*
	* @apiParam {Long} eventId * 活动id（必须）
	* @apiParam {Integer} state * 状态（非必须,状态0:审核中 1:已审核）
	* @apiParam {String} token * 身份凭证（必须，管理员验证）
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
    *  				"id": 70,
    *  				"eventId": 1330,
    *  				"userId": 2086,
    *  				"state": 0,
    *  				"registrationTime": 1490715627,
    *  				"userName": "46937922"
    *			},
    *			{
    *  				"id": 69,
    *  				"eventId": 1330,
    *  				"userId": 2040,
    *  				"state": 0,
    *  				"registrationTime": 1490715585,
    *  				"userName": "1641478"
    *			}
  	*		],
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
	@RequestMapping(value="getEventApplyList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<EventApply>> getEventApplyList(
			@RequestParam(value = "eventId",required = true) Long eventId,
			@RequestParam(value = "state",required = false) Integer state,
            @RequestParam(value = "token",required = true) String token,
            @RequestParam(value = "numPerPage",required = false) Integer numPerPage,
            @RequestParam(value = "pageNum",required = false) Integer pageNum
			){
		return eventApplyService.getEventApplyList(eventId, state, token,numPerPage,pageNum);
	}
	
	
	
	/**
	* @api {get} api/eventApply/getAppliedEventList 获取用户报名过的活动
	* @apiName eventApply_getAppliedEventList
	* @apiGroup eventApply
	*
	* @apiParam {int} numPerPage * （非必须）
	* @apiParam {int} pageNum * （非必须）
	* @apiParam {String} token * 身份凭证
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
    *  				"applyCount": null
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
	@RequestMapping(value="getAppliedEventList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Event>> getAppliedEventList(
            @RequestParam(value = "token",required = true) String token,
            @RequestParam(value = "numPerPage",required = false) Integer numPerPage,
            @RequestParam(value = "pageNum",required = false) Integer pageNum
			){
		return eventApplyService.getAppliedEventList(token,numPerPage,pageNum);
	}
	

}
