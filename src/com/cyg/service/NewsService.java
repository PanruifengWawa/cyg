package com.cyg.service;

import java.util.List;

import com.cyg.models.News;
import com.cyg.utils.DataWrapper;

public interface NewsService {
	DataWrapper<List<News>> getNewsList(Integer ifImage,Integer numPerPage,Integer pageNum);

	DataWrapper<Void> addNews(News news,String token);
	
	DataWrapper<Void> deleteNews(Long newsId,String token);
	
	DataWrapper<News> getById(Long newsId);
	
	
	DataWrapper<Void> update(News news,String token);
}
