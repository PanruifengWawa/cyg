package com.cyg.dao;


import java.util.List;

import com.cyg.models.News;
import com.cyg.utils.DataWrapper;

public interface NewsDao {
	DataWrapper<List<News>> getNewsList(Integer ifImage,Integer numPerPage, Integer pageNum);
	boolean deleteNews(String table,Long id);
	News getById(Long id);
	int getCount(Integer ifImage);
	
	DataWrapper<List<News>> getNoticeList(Integer ifImage, Integer numPerPage, Integer pageNum);
	News getNoticeById(Long id);

}
