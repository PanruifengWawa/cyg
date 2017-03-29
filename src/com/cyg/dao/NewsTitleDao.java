package com.cyg.dao;

import com.cyg.models.NewsTitle;

public interface NewsTitleDao {
	boolean addNewsTite(NewsTitle newsTitle);
	NewsTitle getById(Long id);
	
	boolean updateNewsTitle(NewsTitle newsTitle);
}
