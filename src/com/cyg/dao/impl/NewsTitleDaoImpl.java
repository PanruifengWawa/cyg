package com.cyg.dao.impl;

import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.NewsTitleDao;
import com.cyg.models.NewsTitle;

@Repository
public class NewsTitleDaoImpl extends BaseDao<NewsTitle> implements NewsTitleDao {

	@Override
	public boolean addNewsTite(NewsTitle newsTitle) {
		// TODO Auto-generated method stub
		return save(newsTitle);
	}

	@Override
	public NewsTitle getById(Long id) {
		// TODO Auto-generated method stub
		return get(id);
	}

	@Override
	public boolean updateNewsTitle(NewsTitle newsTitle) {
		// TODO Auto-generated method stub
		return update(newsTitle);
	}

}
