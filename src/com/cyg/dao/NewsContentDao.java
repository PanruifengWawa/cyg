package com.cyg.dao;

import com.cyg.models.NewsContent;

public interface NewsContentDao {
	boolean saveNewsContent(NewsContent newsContent);
	
	boolean updateReadPoint(Long id); 
	
	NewsContent getById(Long id);
	boolean updateNewsContent(NewsContent newsContent);
}
