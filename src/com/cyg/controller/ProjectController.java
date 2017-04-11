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

import com.cyg.models.Project;
import com.cyg.models.ProjectAll;
import com.cyg.models.ProjectButt;
import com.cyg.models.ProjectJieTi;
import com.cyg.models.ProjectMember;
import com.cyg.models.ProjectPeopleDemand;
import com.cyg.models.ProjectSchedule;
import com.cyg.models.ProjectYS;
import com.cyg.service.ProjectService;
import com.cyg.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/project")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	
	/**
	* @api {post} api/project/apply 项目申请
	* @apiName project_apply
	* @apiGroup project
	*
	* @apiParam {String} projectName * 项目名称（必须,下面的也全是必须的）
	* @apiParam {String} name * 姓名
	* @apiParam {String} phone * 手机
	* @apiParam {String} email * 邮箱
	* @apiParam {String} weixin * 微信
	* @apiParam {String} xiaoqu * 工作地点,如“四平校区”
	* @apiParam {String} grade * 年级，如“研究生”
	* @apiParam {String} zczj * 注册资金
	* @apiParam {String} tdcy * 团队成员
	* @apiParam {String} projectType * 项目类型，如“电商”
	* @apiParam {String} projectStatus * 项目状态，如“即将上线,开发中,初始阶段”，注意里面用的是英文的逗号
	* @apiParam {String} content * 项目内容
	* @apiParam {file} jhs * 计划书
	* @apiParam {String} token * 身份凭证
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
	@RequestMapping(value="apply",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> add(
			@RequestParam(value = "projectName",required = true) String projectName,
			@RequestParam(value = "xiaoqu",required = true) String xiaoqu,
			@RequestParam(value = "jhs", required = true) MultipartFile jhs,
			@ModelAttribute ProjectYS projectYS,
            @RequestParam(value = "token",required = true) String token,
            HttpServletRequest request
			) {
		return projectService.addProject(projectName, xiaoqu, projectYS, token,request,jhs);
	}
	
	/**
	* @api {get} api/project/getUserProjectList 获取某个用户申请的项目列表
	* @apiName project_getUserProjectList
	* @apiGroup project
	*
	* @apiParam {int} token * 身份凭证
	* @apiParam {int} numPerPage * （非必须）
	* @apiParam {int} pageNum * （非必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "SUCCEED",
	*  		"errorCode": "No_Error",
	*  		"data":  [
    *			{
    *  				"id": 359,
    *  				"userId": 1011,
    *  				"projectName": "吸附气凝胶",
    *  				"projectField": null,
    *  				"applyName": "阮蕾丹",
    *  				"applyCollege": "物理科学与工程学院",
    *  				"applyMajor": "应用物理学",
    *  				"studentId": "1354382",
    *  				"applyTeacherId": null,
    *  				"applyPhone": "18018506233",
    *  				"email": "490525109@qq.com",
    *  				"teacherName": "杜艾",
    *  				"teacherId": "12073",
    *  				"faculty": "物理科学与工程学院",
    *  				"teacherMajor": "应用物理学",
    *  				"title": "副教授",
    *  				"teacherPhone": "13636670568",
    *  				"projectType": "材料",
    * 	 			"projectProperty": "科技创新、创业实践、SITP、挑战杯项目、创新创业竞赛",
    *  				"isCompany": "否",
    *  				"companyMoney": "",
    *  				"projectIntro": null,
    *  				"projectBackGround": "吸附气凝胶具有高度功能可调性，通过对二氧化硅气凝胶修饰不同的化学基团可以开发不同的有针对性的产品，可以实现对不同物质的吸附来开拓使用前景，成为具备实用性和应用广泛性的纳米材料",
    *  				"projectBrief": "二氧化硅气凝胶具有良好的物理吸附特性，对于水中的有机染料、金属离子等有良好的吸附作用，可广泛运用于处理生态灾难、改善水质环境，特别的对于处理海洋溢油回收、废油处理、污水治理等有得天独厚的优势",
    *  				"slogan": "产业化的生产各种用途的气凝胶",
    *  				"projectTotalTime": "一年",
    *  				"type": 4,
    *  				"status": 2,
    *  				"applyTime": 1432458367,
    *  				"teacherEmail": "duai@tongji.edu.cn",
    *  				"teacherField": "新型纳米多孔材料",
    *  				"projectMarket": "目前市场上存在的吸附剂主要是活性炭，但其不具有选择吸附性，而且其吸附效率并不高。然而吸附气凝胶可以通过修改修饰基团来灵活调整其功能，且二氧化硅气凝胶能够吸附相对其质量30倍左右的油污。",
    *  				"projectBenefits": "（1）环境友好：纳米多孔气凝胶在吸油过程的物理与化学反应均中不会产生有害环境的物质，并且吸油后的纳米多孔气凝胶可作为有机燃料回收利用，燃烧产物为二氧化碳、水以及二氧化硅粉末等环境友好产物。\r\n（2）资源节约：气凝胶的制备过程中所需要的原料少，耗能少并且操作简单，在进行废水处理后能够二次回收利用。\r\n（3）高效、快速：通过我们课题组初步的研发，硅气凝胶吸附油污质量比已达到20倍以上。同时所采用的气凝胶制备工艺有利于工业大规模生产，有良好的技术背景和市场前景。\r\n",
    *  				"projectTeacher": "科研方面，主持国家自然科学基金、863 高技术项目子课题、上海航天科技\r\n创新基金重点项目、军口协作项目等纵向项目 8 项，作为科研骨干参与了国防重点专项（多种类别）、支撑计划（2 项）、民口 863 计划、军口 863 计划、上海航天创新基金、上海市科委、教育部等多项项目，上海市重点学科等多项项目（其中多项排名前 3 位）的科研工作。主要研究方向包括新型气凝胶的制备、溶胶-凝胶理论以及气凝胶在物理工程中的应用。发表论文 50 余篇（包括 1 篇 SCI 邀请综述和 3 篇中文综述，十余篇一作），申请专利 15 项（授权 4 项）。工作以来以”aerogel”为主题进行检索发文量排名 SCI 第 5 位、EI 第 3 位（2013 年 10 月24 日检索）。2011 年获得教育部自然科学奖二等奖，2011 年和 2007 年两度获得全国核靶技术交流大会优秀论文称号，作为第二完成人获得 2010 年军口863-804 主题”年度优秀课题”称号。中国物理学会和上海核学会会员，担任Langmuir、Soft Matter、Journal of Materials Chemistry、RSC Advance、New Journal of  Chemistry、Catalysis  Science  and  Technology、Food  Hydrocolloids、Letters  in Drug  Design  and  Discovery 等国际期刊的审稿人，2 次受邀参加国际会议并做invited  presentation，1 次受邀参加国内会议做大会报告，2 次受邀参加国内会议\r\n并做邀请报告。 \r\n",
    *  				"projectStaff": "有科研能力，有市场分析能力，能为本项目投资（其中一点即可）",
    *  				"projectReport": "word，一月一次",
    *  				"projectFunding": "资金主要用于研发和小规模投入生产",
    *  				"projectOther": "（无）",
    *  				"applyTeacherTitle": null,
    *  				"applyNumber": null,
    *  				"applyOfficeSector": null,
    *  				"applyWorkUnit": null,
    *  				"companyName": null,
    *  				"companyType": null,
    *  				"companyContact": null,
    *  				"officeSector": null,
    *  				"xqOtherRemark": "场地，规模生产设备",
    *  				"xiaoqu": null,
    *  				"jietiTime": null,
    *  				"talent": 0,
    *  				"talentNum": null,
    *  				"talentRemark": null,
    *  				"money": 1,
    *  				"moneyNum": "100000",
    *  				"moneyRemark": null,
    *  				"mentor": 0,
    *  				"mentorNum": null,
    *  				"mentorRemark": null
    *			}
  	*		]
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
	@RequestMapping(value="getUserProjectList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Project>> getUserProjectList(
			@RequestParam(value = "numPerPage",required = false) Integer numPerPage,
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "token",required = true) String token
			) {
		return projectService.getUserProjectList(numPerPage, pageNum, token);
	}
	
	
	/**
	* @api {get} api/project/getProjectList 获取项目列表
	* @apiName project_getProjectList
	* @apiGroup project
	*
	* @apiParam {int} status * 项目状态 在项目对接列表中，请将status设置为2 (非必须) 11-申请的项目审核中，2-申请的项目通过审核，21-申请结题的项目审核中，22-申请结题的项目通过审核，其他状态都是失败
	* @apiParam {String} demand * 需求，talent-找人才；mentor-找导师；money-找资金（非必须）
	* @apiParam {String} projectType * 项目类型，如“农林、畜牧、食品及相关产业”（非必须）
	* @apiParam {int} source * 来源，1企业，2个人，3导师，4学生（非必须）
	* @apiParam {int} numPerPage * （非必须）
	* @apiParam {int} pageNum * （非必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "SUCCEED",
	*  		"errorCode": "No_Error",
	*  		"data":  [
    *			{
    *  				"id": 359,
    *  				"userId": 1011,
    *  				"projectName": "吸附气凝胶",
    *  				"projectField": null,
    *  				"applyName": "阮蕾丹",
    *  				"applyCollege": "物理科学与工程学院",
    *  				"applyMajor": "应用物理学",
    *  				"studentId": "1354382",
    *  				"applyTeacherId": null,
    *  				"applyPhone": "18018506233",
    *  				"email": "490525109@qq.com",
    *  				"teacherName": "杜艾",
    *  				"teacherId": "12073",
    *  				"faculty": "物理科学与工程学院",
    *  				"teacherMajor": "应用物理学",
    *  				"title": "副教授",
    *  				"teacherPhone": "13636670568",
    *  				"projectType": "材料",
    * 	 			"projectProperty": "科技创新、创业实践、SITP、挑战杯项目、创新创业竞赛",
    *  				"isCompany": "否",
    *  				"companyMoney": "",
    *  				"projectIntro": null,
    *  				"projectBackGround": "吸附气凝胶具有高度功能可调性，通过对二氧化硅气凝胶修饰不同的化学基团可以开发不同的有针对性的产品，可以实现对不同物质的吸附来开拓使用前景，成为具备实用性和应用广泛性的纳米材料",
    *  				"projectBrief": "二氧化硅气凝胶具有良好的物理吸附特性，对于水中的有机染料、金属离子等有良好的吸附作用，可广泛运用于处理生态灾难、改善水质环境，特别的对于处理海洋溢油回收、废油处理、污水治理等有得天独厚的优势",
    *  				"slogan": "产业化的生产各种用途的气凝胶",
    *  				"projectTotalTime": "一年",
    *  				"type": 4,
    *  				"status": 2,
    *  				"applyTime": 1432458367,
    *  				"teacherEmail": "duai@tongji.edu.cn",
    *  				"teacherField": "新型纳米多孔材料",
    *  				"projectMarket": "目前市场上存在的吸附剂主要是活性炭，但其不具有选择吸附性，而且其吸附效率并不高。然而吸附气凝胶可以通过修改修饰基团来灵活调整其功能，且二氧化硅气凝胶能够吸附相对其质量30倍左右的油污。",
    *  				"projectBenefits": "（1）环境友好：纳米多孔气凝胶在吸油过程的物理与化学反应均中不会产生有害环境的物质，并且吸油后的纳米多孔气凝胶可作为有机燃料回收利用，燃烧产物为二氧化碳、水以及二氧化硅粉末等环境友好产物。\r\n（2）资源节约：气凝胶的制备过程中所需要的原料少，耗能少并且操作简单，在进行废水处理后能够二次回收利用。\r\n（3）高效、快速：通过我们课题组初步的研发，硅气凝胶吸附油污质量比已达到20倍以上。同时所采用的气凝胶制备工艺有利于工业大规模生产，有良好的技术背景和市场前景。\r\n",
    *  				"projectTeacher": "科研方面，主持国家自然科学基金、863 高技术项目子课题、上海航天科技\r\n创新基金重点项目、军口协作项目等纵向项目 8 项，作为科研骨干参与了国防重点专项（多种类别）、支撑计划（2 项）、民口 863 计划、军口 863 计划、上海航天创新基金、上海市科委、教育部等多项项目，上海市重点学科等多项项目（其中多项排名前 3 位）的科研工作。主要研究方向包括新型气凝胶的制备、溶胶-凝胶理论以及气凝胶在物理工程中的应用。发表论文 50 余篇（包括 1 篇 SCI 邀请综述和 3 篇中文综述，十余篇一作），申请专利 15 项（授权 4 项）。工作以来以”aerogel”为主题进行检索发文量排名 SCI 第 5 位、EI 第 3 位（2013 年 10 月24 日检索）。2011 年获得教育部自然科学奖二等奖，2011 年和 2007 年两度获得全国核靶技术交流大会优秀论文称号，作为第二完成人获得 2010 年军口863-804 主题”年度优秀课题”称号。中国物理学会和上海核学会会员，担任Langmuir、Soft Matter、Journal of Materials Chemistry、RSC Advance、New Journal of  Chemistry、Catalysis  Science  and  Technology、Food  Hydrocolloids、Letters  in Drug  Design  and  Discovery 等国际期刊的审稿人，2 次受邀参加国际会议并做invited  presentation，1 次受邀参加国内会议做大会报告，2 次受邀参加国内会议\r\n并做邀请报告。 \r\n",
    *  				"projectStaff": "有科研能力，有市场分析能力，能为本项目投资（其中一点即可）",
    *  				"projectReport": "word，一月一次",
    *  				"projectFunding": "资金主要用于研发和小规模投入生产",
    *  				"projectOther": "（无）",
    *  				"applyTeacherTitle": null,
    *  				"applyNumber": null,
    *  				"applyOfficeSector": null,
    *  				"applyWorkUnit": null,
    *  				"companyName": null,
    *  				"companyType": null,
    *  				"companyContact": null,
    *  				"officeSector": null,
    *  				"xqOtherRemark": "场地，规模生产设备",
    *  				"xiaoqu": null,
    *  				"jietiTime": null,
    *  				"talent": 0,
    *  				"talentNum": null,
    *  				"talentRemark": null,
    *  				"money": 1,
    *  				"moneyNum": "100000",
    *  				"moneyRemark": null,
    *  				"mentor": 0,
    *  				"mentorNum": null,
    *  				"mentorRemark": null
    *			}
  	*		]
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
	@RequestMapping(value="getProjectList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Project>> getProjectList(
			@RequestParam(value = "demand",required = false) String demand,
			@RequestParam(value = "projectType",required = false) String type,
			@RequestParam(value = "source",required = false) Integer source,
			@RequestParam(value = "status",required = false) Integer status,
			@RequestParam(value = "numPerPage",required = false) Integer numPerPage,
            @RequestParam(value = "pageNum",required = false) Integer pageNum
			) {
		return projectService.getProjectList(demand, type, source, status,numPerPage, pageNum);
	}
	
	/**
	* @api {get} api/project/getProjectDetails 获取项目详情
	* @apiName project_getProjectDetails
	* @apiGroup project
	*
	* @apiParam {Long} projectId *
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"callStatus": "SUCCEED",
	*  		"errorCode": "No_Error",
	*  		"data":  {
    *			"project": {
    *  				"id": 412,
    *  				"userId": 1347,
    *  				"projectName": "智慧小树培养计划",
    *  				"projectField": null,
    *  				"applyName": "秦敏",
    *  				"applyCollege": null,
    *  				"applyMajor": null,
    *  				"studentId": null,
    *  				"applyTeacherId": null,
    *  				"applyPhone": "13611742200",
    *  				"email": "158116419@qq.com",
    *  				"teacherName": null,
    *  				"teacherId": null,
    *  				"faculty": null,
    *  				"teacherMajor": null,
    *  				"title": null,
    *  				"teacherPhone": null,
    *  				"projectType": "",
    *  				"projectProperty": "创业实践",
    *  				"isCompany": "否",
    *  				"companyMoney": "",
    *  				"projectIntro": null,
    *  				"projectBackGround": "现在作为80后的父母，本来就是独生子女，对于孩子的教育既对老人们的教育观念有些批判，又不知如何才是更有效的教育，所以大多数父母要么在实行“本本主义”指导下的实践摸索，要么臣服于于早教机构精美包装下的糖衣炮弹。同样作为80后的父母，我又是一个教育工作者，有着长期的教育理论实践，我能为孩子们弥补在幼儿园实现不了的家长互动体验的活动；也有教育机构等地方望尘莫及的科学的课程资源和专业课程团队；也有着平民的价格。",
    *  				"projectBrief": "为了让每一个孩子都得到快乐的童年，为了让每一个孩子都得到充分和谐的发展，为了让家长更科学的陪伴孩子成长，现在推出亲子互动体验系列活动————智慧小树体验营。",
    *  				"slogan": "为3-8岁的孩子打造一个快乐的童年",
    *  				"projectTotalTime": "三个月",
    *  				"type": 2,
    *  				"status": 2,
    *  				"applyTime": 1452573356,
    *  				"teacherEmail": null,
    *  				"teacherField": null,
    *  				"projectMarket": "上海的现在的儿童的主力家长是80后，这个群体的家长特别重视孩子的早期教育，愿意为孩子在教育方面投入。他们也经常考察教育机构，但是教育机构在狭小的闹市区的高楼中严重脱离了社会与自然的环境，幼儿园又是老师负责管理，不能照顾到所有孩子的个性。本方案就是为了弥补教育机构和幼儿园实现不了的家长亲子体验活动课程的市场缺口。",
    *  				"projectBenefits": "1.集合早教机构的优势资源，给孩子呈现不一样的互动体验课程；此外，项目成员在闵行地区拥有其他机构无法比拟的教育资源（大、中、小学）。\r\n2.项目成员分别分别是大学、中学、幼儿园老师，其中两个硕士研究生，一个本科生，不论是教育教学理论还是实践方面都具有很好的专业支持。\r\n3.目标客户群体巨大，闵行地区儿童的人口数量巨大，而且据调查，他们都有着强烈的教育投入意向，但是却没有合适先进的教育方向。\r\n",
    *  				"projectTeacher": "1.幼儿教师\r\n2.外教\r\n3.体验培训师\r\n4.艺术启蒙师",
    *  				"projectStaff": "项目推广营销策划",
    *  				"projectReport": "视频，会议，文档等",
    *  				"projectFunding": "活动课程相关材料",
    *  				"projectOther": "",
    *  				"applyTeacherTitle": null,
    *  				"applyNumber": "",
    *  				"applyOfficeSector": "",
    *  				"applyWorkUnit": "",
    *  				"companyName": null,
    *  				"companyType": null,
    *  				"companyContact": null,
    *  				"officeSector": null,
    *  				"xqOtherRemark": "最好能有个APP作为平台",
    * 			 	"xiaoqu": null,
    *  				"jietiTime": null,
    *  				"talent": 1,
    *  				"talentNum": null,
    *  				"talentRemark": null,
    *  				"money": 1,
    *  				"moneyNum": "2",
    *  				"moneyRemark": null,
    *  				"mentor": 0,
    *  				"mentorNum": null,
    *  				"mentorRemark": null,
    *  				"tdkzpzj": "1"
    *			},
    *			"projectYS": {
    *  				"id": 189,
    *  				"projectId": 565,
    *  				"userId": 2086,
    *  				"name": "小潘",
    *  				"phone": "1376",
    *  				"email": "123@qq.com",
    *  				"weixin": "123",
    *  				"grade": "研究生",
    *  				"zczj": "1",
    *  				"tdcy": "无成员",
    *  				"projectType": "电商",
    *  				"projectStatus": "即将上线,开发中,初始阶段",
    *  				"content": "啊哈哈哈",
    *  				"type": 2,
    *  				"inputTime": 1491921294,
    *  				"liuyan": null
    *			},
    *			"projectJHS": {
    *  				"id": 283,
    *  				"projectId": 565,
    *  				"name": "copy.png",
    *  				"url": "jhs/28f13d03aeb550c1dd3101e5c4f5a7b8.png",
    *  				"inputTime": 1491921294
    *			},
    *			"projectButts": [//人才对接就数里面有几个1，导师企业对接相同，资金对接就是里面money求和
    *  				{
    *    				"id": 75,
    *    				"projectId": 412,
    *    				"userId": 1385,
    *    				"demandType": 1,
    *    				"name": "赵安铭",
    *    				"college": "其他",
    *    				"major": "计算机科学与技术",
    *    				"teacherTitle": null,
    *    				"companyName": null,
    *    				"post": null,
    *    				"userNub": null,
    *    				"phone": "18801930335",
    *    				"email": "i@vistart.name",
    *    				"qq": "40924455",
    *    				"applyPost": "业务架构师",
    *    				"specialty": "业务架构规划，社交网络推广",
    *    				"certificate": "",
    *    				"content": "",
    *    				"url": "",
    *    				"other": "建议业务经营线上化。",
    *    				"research": null,
    *    				"mode": null,
    *    				"cooperation": null,
    *    				"ckOther": null,
    *    				"buttTime": 1456847212,
    *    				"status": 1,
    *    				"money": null
    *  				}
    *			],
    *			"projectJieTis": [
    *				{
    *    				"id": 29,
    *    				"projectId": 565,
    *    				"name": "公交车拥挤程度0.jpg",
    *    				"url": "jieti/efe7fde9e5869dc69bc34f9ea9c4c886.jpg",
    *    				"inputTime": 1491926291,
    *    				"isImg": 1
    *  				},
    *  				{
    *    				"id": 30,
    *    				"projectId": 565,
    *    				"name": "公交车拥挤程度0.jpg",
    *    				"url": "jieti/4f91b9f0fc2ee413d6929537e598e751.jpg",
    *    				"inputTime": 1491927977,
    *    				"isImg": 1
    *  				}
    *  			],
    *			"projectMembers": [//项目成员
    *  				{
    *    				"id": 3458,
    *    				"projectId": 412,
    *    				"memberName": "李永久",
    *    				"memberCollege": null,
    *    				"memberMajor": null,
    *    				"memberWorkUnit": "",
    *    				"memberOfficeSector": "",
    *    				"memberId": null,
    *    				"memberPhone": "13764549890",
    *    				"memberEmail": "",
    *    				"function": null,
    *    				"memberField": null,
    *    				"isHas": 1,
    *    				"memberType": 3,
    *    				"gwlx": "大学老师"
    *  				}
    *			],
    *			"projectPeopleDemands": [//需求人才
    *  				{
    *    				"id": 2280,
    *    				"leixing": "1",
    *    				"num": "若干",
    *   	 			"xingshi": 0,
    *    				"projectId": 412,
    *    				"gwlx": "营销",
    *    				"addTime": 1452836541000,
    *    				"dslx": null
    *  				}
    *			],
    *			"projectSchedules": [
    *  				{
    *    				"id": 2948,
    *    				"projectId": 412,
    *    				"time": "2016-01-12",
    *    				"taskIntro": "项目试运营阶段",
    *    				"scheduleType": 3,
    *    				"endTime": "2016-03-01"
    *  				}
    *			]
  	*		},
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
	@RequestMapping(value="getProjectDetails",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<ProjectAll> getProjectDetails(
			@RequestParam(value = "projectId",required = true) Long projectId
			) {
		return projectService.getProjectDetails(projectId);
	}
	
	
	/**
	* @api {post} api/project/updateYSProject 用户修改正在申请中的项目信息
	* @apiName project_updateYSProject
	* @apiGroup project
	*
	* @apiParam {String} projectIdToUpdate * 需要更新的项目id
	* @apiParam {String} projectName * 项目名称（必须）
	* @apiParam {String} name * 姓名
	* @apiParam {String} phone * 手机
	* @apiParam {String} email * 邮箱
	* @apiParam {String} weixin * 微信
	* @apiParam {String} xiaoqu * 工作地点,如“四平校区”
	* @apiParam {String} grade * 年级，如“研究生”
	* @apiParam {String} zczj * 注册资金
	* @apiParam {String} tdcy * 团队成员
	* @apiParam {String} projectType * 项目类型，如“电商”
	* @apiParam {String} projectStatus * 项目状态，如“即将上线,开发中,初始阶段”，注意里面用的是英文的逗号
	* @apiParam {String} content * 项目内容
	* @apiParam {file} jhs * 计划书
	* @apiParam {String} token * 身份凭证
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
	@RequestMapping(value="updateYSProject",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> updateYSProject(
			@RequestParam(value = "projectIdToUpdate",required = true) Long projectIdToUpdate,
			@RequestParam(value = "projectName",required = true) String projectName,
			@RequestParam(value = "xiaoqu",required = true) String xiaoqu,
			@RequestParam(value = "jhs", required = true) MultipartFile jhs,
			@ModelAttribute ProjectYS projectYS,
            @RequestParam(value = "token",required = true) String token,
            HttpServletRequest request
			) {
		return projectService.updateYSProject(projectIdToUpdate,projectName, xiaoqu, projectYS, token,request,jhs);
	}
	
	
	/**
	* @api {post} api/project/delete 删除项目
	* @apiName project_delete
	* @apiGroup project
	*
	* @apiParam {Long} projectIdToDelete * id（必须）
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
	public DataWrapper<Void> deleteProject(
			@RequestParam(value = "projectIdToDelete",required = true) Long projectIdToDelete,
            @RequestParam(value = "token",required = true) String token
			) {
		return projectService.deleteProject(projectIdToDelete, token);
	}
	
	
	/**
	* @api {post} api/project/updateProject 用户修改通过申请的项目信息
	* @apiName project_updateProject
	* @apiGroup project
	* 
	*
	* @apiParam {String} projectIdToUpdate * 需要更新的项目id （下面的，除非有特殊说明，否则都是非必须）
	* @apiParam {String} projectName * 项目名称
	* @apiParam {String} projectField * 项目领域
	* @apiParam {String} applyName * 立项人姓名
	* @apiParam {String} applyCollege * 立项人学院
	* @apiParam {String} applyMajor * 立项人专业
	* @apiParam {String} studentId * 立项人学号
	* @apiParam {String} applyTeacherId * 立项人老师工号
	* @apiParam {String} applyPhone * 立项人联系电话
	* @apiParam {String} email * 立项人Email
	* @apiParam {String} teacherName * 教导老师姓名
	* @apiParam {String} teacherId * 教导老师工号
	* @apiParam {String} faculty * 教师所在院系
	* @apiParam {String} teacherMajor * 教导老师专业
	* @apiParam {String} title * 教导老师职称
	* @apiParam {String} teacherPhone * 教导老师联系电话
	* @apiParam {String} projectType * 项目类型
	* @apiParam {String} projectProperty * 项目属性
	* @apiParam {String} isCompany * 是否成立公司
	* @apiParam {String} companyMoney * 公司资金
	* @apiParam {String} projectIntro * 项目介绍
	* @apiParam {String} projectBackGround * 项目背景
	* @apiParam {String} projectBrief * 项目简介(个性宣传)
	* @apiParam {String} slogan * 口号(项目目标)
	* @apiParam {String} projectTotalTime * 项目总时间
	* @apiParam {String} teacherEmail * 教导老师邮箱
	* @apiParam {String} teacherField * 教导老师领域
	* @apiParam {String} projectMarket * 项目市场
	* @apiParam {String} projectBenefits * 项目优势
	* @apiParam {String} projectTeacher * 项目导师
	* @apiParam {String} projectStaff * 项目人员需求详情
	* @apiParam {String} projectReport * 项目汇报模式
	* @apiParam {String} projectFunding * 资金、资源开放情况及用途
	* @apiParam {String} projectOther * 项目其他
	* @apiParam {String} applyTeacherTitle * 立项人老师职称 (若立项人为老师)
	* @apiParam {String} applyNumber * 立项人身份证
	* @apiParam {String} applyOfficeSector * 立项人任职部门
	* @apiParam {String} applyWorkUnit * 立项人工作单位
	* @apiParam {String} companyName * 企业名称
	* @apiParam {String} companyType * 企业类型
	* @apiParam {String} companyContact * 企业联系人
	* @apiParam {String} officeSector * 企业部门
	* @apiParam {String} xqOtherRemark * 需求其他描述
	* @apiParam {String} xiaoqu * 校区
	* @apiParam {int} talent * 找人才, 1-找人才，0-不找人才
	* @apiParam {String} talentNum * 找人才数量
	* @apiParam {String} talentRemark * 找人才备注
	* @apiParam {String} money * 找资金
	* @apiParam {String} moneyNum * 找资金数量(需要投入的资金)
	* @apiParam {String} moneyRemark * 找资金备注
	* @apiParam {String} mentor * 找导师
	* @apiParam {String} mentorNum * 找导师数量
	* @apiParam {String} mentorRemark * 找导师备注
	* @apiParam {String} tdkzpzj * 团队可支配资金
	* 
	* 
	* @apiParam {String} token * 身份凭证
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
	@RequestMapping(value="updateProject",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> updateProject(
			@RequestParam(value = "projectIdToUpdate",required = true) Long projectIdToUpdate,
			@ModelAttribute Project project,
            @RequestParam(value = "token",required = true) String token
			) {
		return projectService.updateProject(projectIdToUpdate, project, token);
	}
	
	/**
	* @api {post} api/project/addProjectMember 增加项目成员
	* @apiName project_addProjectMember
	* @apiGroup project
	*
	* @apiParam {Long} projectId * 项目id （必须）
	* @apiParam {String} memberName 成员姓名 (必须)
	* @apiParam {String} memberCollege 成员学院 (非必须) 
	* @apiParam {String} memberMajor 成员专业 (非必须)
	* @apiParam {String} memberWorkUnit 成员工作单位 (非必须)
	* @apiParam {String} memberOfficeSector 成员任职部门 (非必须) 
	* 
	* @apiParam {String} memberId 成员学号/工号 (非必须) 
	* @apiParam {String} memberPhone 成员手机 (非必须) 
	* @apiParam {String} memberEmail 成员Email (非必须) 
	* @apiParam {String} function 职能，职称 (非必须) 
	* @apiParam {String} memberField 成员领域 (非必须) 
	* @apiParam {int} isHas 成员任职部门 (必须)  0为导师，1为成员
	* @apiParam {int} memberType 成员类型 (非必须)  4学生,3导师,2个人,1企业
	* @apiParam {String} gwlx (必须)  如“内部管理”
	* 
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
	@RequestMapping(value="addProjectMember",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> addProjectMember(
			@ModelAttribute ProjectMember projectMember,

            @RequestParam(value = "token",required = true) String token
			) {
		return projectService.addProjectMember(projectMember,token);
	}
	
	/**
	* @api {post} api/project/addProjectSchedule 增加进度安排
	* @apiName project_addProjectSchedule
	* @apiGroup project
	*
	* @apiParam {Long} projectId * 项目id （必须）
	* @apiParam {String} time * 开始时间 (必须) 如 “2016-07-08”
	* @apiParam {String} taskIntro * 任务介绍(必须) 
	* @apiParam {String} scheduleType * 类型 (非必须) 1企业，2个人，3导师，4学生
	* @apiParam {String} endTime * 结束时间 (非必须) 如“2015-10-23”
	* 
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
	@RequestMapping(value="addProjectSchedule",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> addProjectSchedule(
			@ModelAttribute ProjectSchedule projectSchedule,

            @RequestParam(value = "token",required = true) String token
			) {
		return projectService.addProjectSchedule(projectSchedule, token);
	}
	
	/**
	* @api {post} api/project/addProjectButt 其他用户对接项目
	* @apiName project_addProjectButt
	* @apiGroup project
	*
	* @apiParam {Long} projectId * 项目id （必须）
	* @apiParam {String} demandType * 对接类型 (必须) 对接类型：1人才，2导师，3企业
	* @apiParam {String} name * 姓名(必须) 
	* @apiParam {String} college * 学院 (非必须)
	* @apiParam {String} major * 专业 (非必须)
	* 
	* @apiParam {String} teacherTitle * 导师职称 (非必须)
	* @apiParam {String} companyName * 公司名称 (非必须)
	* @apiParam {String} post * 职务 (非必须)
	* @apiParam {String} userNub * 工号学号 (非必须)
	* @apiParam {String} phone * 联系电话 (非必须)
	* @apiParam {String} email * 邮箱 (非必须)
	* @apiParam {String} qq * QQ (非必须)
	* @apiParam {String} applyPost * 应聘职位 (必须)
	* @apiParam {String} specialty * 特长 (非必须)
	* @apiParam {String} certificate * 证书 (非必须)
	* @apiParam {String} content * 工作经历，知道内容，合作领域 (非必须)
	* @apiParam {String} other * 其他 (非必须)
	* @apiParam {String} research * 研究方向 (非必须)
	* @apiParam {String} mode * 指导（合作）方式 (非必须)
	* @apiParam {String} cooperation * 合作内容 (非必须)
	* @apiParam {String} ckOther * 多选其他 (非必须)
	* @apiParam {String} money * 对接资金 (非必须)
	* 
	* @apiParam {file} personalWork * 个人作品 (非必须)
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
	@RequestMapping(value="addProjectButt",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> addProjectButt(
			@ModelAttribute ProjectButt projectButt,
			@RequestParam(value = "personalWork", required = true) MultipartFile personalWork,
            @RequestParam(value = "token",required = true) String token,
            HttpServletRequest request
			) {
		return projectService.addProjectButt(projectButt, personalWork, token, request);
	}
	
	
	/**
	* @api {post} api/project/addProjectPeopleDemand 增加项目需求人才
	* @apiName project_addProjectPeopleDemand
	* @apiGroup project
	*
	* @apiParam {Long} projectId * 项目id （必须）
	* @apiParam {String} leixing * 人员类型 (必须) 类型:1人才,2导师
	* @apiParam {String} num * 数量 (必须) 
	* @apiParam {String} xingshi * 形式 (必须) 形式,0:需要,1已有
	* @apiParam {String} gwlx * 岗位类型 (必须) 当leixing=1时，此列有意义， 当leixing=2时，此列为空
	* @apiParam {String} dslx * 导师类型 (必须) 当leixing=2时，此列有意义， 当leixing=1时，此列为空，dslx和gwlx不能同时有值
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
	@RequestMapping(value="addProjectPeopleDemand",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> addProjectPeopleDemand(
			@ModelAttribute ProjectPeopleDemand projectPeopleDemand,
            @RequestParam(value = "token",required = true) String token
			) {
		return projectService.addProjectPeopleDemand(projectPeopleDemand, token);
	}
	
	/**
	* @api {post} api/project/jieti 项目结题
	* @apiName project_jieti
	* @apiGroup project
	*
	* @apiParam {Long} projectId * 项目id （必须）
	* @apiParam {file} jieti 结题文件 (必须) 
	* @apiParam {int} isImg 是否为图片 (必须) 是否为图片资料，0否，1是
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
	@RequestMapping(value="jieti",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> jieti(
			@ModelAttribute ProjectJieTi projectJieTi,
			@RequestParam(value = "jieti", required = true) MultipartFile jieti,
            @RequestParam(value = "token",required = true) String token,
            HttpServletRequest request
			) {
		return projectService.jieti(projectJieTi, jieti, token, request);
	}
	
	
	/**
	* @api {post} api/project/verify 管理员审核某个报名
	* @apiName project_verify
	* @apiGroup project
	*
	* @apiParam {Long} projectId * 项目id （必须）
	* @apiParam {Integer} status * 状态（必须） 11-申请的项目审核中，2-申请的项目通过审核，21-申请结题的项目审核中，22-申请结题的项目通过审核，其他状态都是失败（这是因为他们的数据库设计得很乱，我们在这里设定 0 为失败状态，但是你在显示项目列表的时候，如果状态不是0，也不是已知的状态，就认为是失败）
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
	public DataWrapper<Void> verify(
			@RequestParam(value = "projectId", required = true) Long projectId,
			@RequestParam(value = "status", required = true) Integer status,
            @RequestParam(value = "token",required = true) String token
			) {
		return projectService.verify(projectId, status,token);
	}

}
