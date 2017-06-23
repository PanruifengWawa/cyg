package com.cyg.service;

import java.util.List;

import com.cyg.models.News;
import com.cyg.utils.DataWrapper;

public interface NoticeService {
	DataWrapper<List<News>> getNoticeList(Integer ifImage, Integer numPerPage,Integer pageNum);

	DataWrapper<Void> addNotice(News news,String token);
	
	DataWrapper<Void> deleteNotice(Long newsId,String token);
	
	DataWrapper<News> getById(Long newsId);
	
	
	DataWrapper<Void> update(News news,String token);

}
