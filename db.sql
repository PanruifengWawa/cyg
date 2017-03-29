create database tjcygnew;
use tjcygnew;


DROP TABLE IF EXISTS m_chuangyegu_login;
CREATE TABLE `m_chuangyegu_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identity` tinyint(3) DEFAULT NULL COMMENT '用户身份:1企业 2个人 3老师 4学生',
  `login_name` varchar(32) DEFAULT NULL COMMENT '登录名',
  `password` varchar(32) DEFAULT NULL COMMENT '登录密码',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(64) DEFAULT NULL COMMENT 'Email',
  `intention` varchar(64) DEFAULT NULL COMMENT '意向',
  `company_name` varchar(128) DEFAULT NULL COMMENT '企业名称(企业)',
  `company_type` varchar(255) DEFAULT NULL,
  `contact_name` varchar(32) DEFAULT NULL COMMENT '联系人姓名(企业)',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名(个人)',
  `id_number` varchar(32) DEFAULT NULL COMMENT '身份证号(个人)',
  `regist_time` datetime DEFAULT NULL COMMENT '注册时间',
  `classid` int(11) DEFAULT NULL,
  `site_id` int(11) DEFAULT NULL,
  `listorder` int(11) DEFAULT '0',
  `college` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `teacher_title` varchar(255) DEFAULT NULL,
  `work_unit` varchar(255) DEFAULT NULL,
  `office_sector` varchar(255) DEFAULT NULL,
  `integral` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2084 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS cms_member;
CREATE TABLE `cms_member` (
  `userid` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `username` char(40) NOT NULL DEFAULT '',
  `email` char(40) NOT NULL,
  `groupid` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `disabled` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `pwd` char(32) NOT NULL DEFAULT '' COMMENT '密码',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `empno` varchar(15) DEFAULT NULL COMMENT '员工号',
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=141 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;




DROP TABLE IF EXISTS cms_content;
CREATE TABLE `cms_content` (
  `contentid` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `catid` int(11) unsigned NOT NULL DEFAULT '0',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '3',
  `certification` char(1) NOT NULL DEFAULT '0' COMMENT '是否被认证；默认：0—不被认证；1—需要认证',
  `areaid` smallint(5) unsigned NOT NULL DEFAULT '0',
  `title` varchar(255) NOT NULL DEFAULT '',
  `pic` varchar(255) NOT NULL DEFAULT '' COMMENT '当前选中的图片',
  `pic_title` varchar(255) NOT NULL,
  `style` char(255) NOT NULL DEFAULT '',
  `thumb` char(100) NOT NULL DEFAULT '',
  `keywords` char(255) NOT NULL DEFAULT '',
  `description` char(255) NOT NULL DEFAULT '',
  `posids` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `url` char(100) NOT NULL DEFAULT '',
  `listorder` tinyint(3) NOT NULL DEFAULT '0',
  `userid` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `username` char(20) NOT NULL DEFAULT '',
  `input_time` int(10) unsigned NOT NULL DEFAULT '0',
  `update_time` int(10) unsigned NOT NULL DEFAULT '0',
  `searchid` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `islink` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `prefix` char(20) NOT NULL DEFAULT '',
  `mtype` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '模块类别',
  `site_id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `has_video` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否含有视频',
  `has_audio` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否含有音频',
  `has_img` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否含有图片',
  `short_title` varchar(255) DEFAULT NULL,
  `is_loginshow` tinyint(1) DEFAULT '1' COMMENT '0可见，1登陆后可见',
  PRIMARY KEY (`contentid`)
) ENGINE=MyISAM AUTO_INCREMENT=9469 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS cms_c_news;
CREATE TABLE `cms_c_news` (
  `contentid` int(11) unsigned NOT NULL DEFAULT '0',
  `template` varchar(30) NOT NULL DEFAULT '',
  `titleintact` varchar(200) NOT NULL DEFAULT '',
  `content` mediumtext NOT NULL,
  `groupids_view` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `readpoint` smallint(5) unsigned NOT NULL DEFAULT '0',
  `author` varchar(30) NOT NULL DEFAULT '',
  `copyfrom` varchar(100) NOT NULL DEFAULT '',
  `paginationtype` tinyint(1) NOT NULL DEFAULT '0',
  `maxcharperpage` mediumint(6) NOT NULL DEFAULT '10000',
  `site_id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`contentid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;



CREATE TABLE `download` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `icon` varchar(255) NOT NULL,
  `certification` char(1) NOT NULL DEFAULT '0' COMMENT '是否认证；默认：0—不需认证；1—需要认证',
  `url_type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT 'url类型 1表示上传文件url，2表示外部url',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '文件地址',
  `link` varchar(255) NOT NULL DEFAULT '' COMMENT '链接文件地址',
  `description` text,
  `create_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '插入时间',
  `listorder` double(6,3) unsigned NOT NULL DEFAULT '0.000' COMMENT '排序',
  `site_id` tinyint(3) unsigned DEFAULT NULL COMMENT '站点id',
  `classid` smallint(5) unsigned DEFAULT NULL COMMENT '栏目ID',
  `hits` int(11) unsigned DEFAULT '0' COMMENT '下载次数',
  `status` tinyint(1) DEFAULT '0',
  `scan_count` int(11) DEFAULT '1',
  `cate_id` int(11) unsigned NOT NULL DEFAULT '0',
  `password` varchar(64) DEFAULT NULL,
  `is_loginshow` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1687 DEFAULT CHARSET=utf8;


CREATE TABLE `m_chuangyegu_place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `apply_unit` varchar(128) DEFAULT NULL,
  `contact_name` varchar(32) DEFAULT NULL,
  `contact_phone` varchar(16) DEFAULT NULL,
  `mobile_phone` varchar(16) DEFAULT NULL,
  `use_date` date DEFAULT NULL COMMENT '场地使用日期',
  `use_time` varchar(128) DEFAULT NULL COMMENT '场地使用时间段',
  `use_time_id` varchar(32) DEFAULT NULL,
  `event_name` varchar(128) DEFAULT NULL,
  `event_content` text,
  `campus` tinyint(3) DEFAULT NULL COMMENT '校区 1:四平校区 2:嘉定校区',
  `rental_place` varchar(16) DEFAULT NULL COMMENT '场地类型',
  `event_equipment` varchar(64) DEFAULT NULL,
  `other_equipment` varchar(128) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `photo_status` tinyint(4) DEFAULT '0' COMMENT '是否启用海报',
  `appointment_time` int(11) DEFAULT NULL,
  `status` tinyint(3) DEFAULT '1' COMMENT '状态:1审核中 2已通过 3不通过',
  `classid` int(11) DEFAULT NULL,
  `site_id` int(11) DEFAULT NULL,
  `listorder` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1329 DEFAULT CHARSET=utf8;



CREATE TABLE `m_chuangyegu_signup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) DEFAULT NULL COMMENT '活动ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `state` tinyint(3) DEFAULT '0' COMMENT '状态0:审核中 1:已审核',
  `registration_time` int(11) DEFAULT NULL COMMENT '报名时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
