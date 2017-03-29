package com.cyg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyg.dao.NewsContentDao;
import com.cyg.dao.NewsDao;
import com.cyg.dao.NewsTitleDao;
import com.cyg.enums.ErrorCodeEnum;
import com.cyg.models.News;
import com.cyg.models.NewsContent;
import com.cyg.models.NewsTitle;
import com.cyg.models.User;
import com.cyg.service.NoticeService;
import com.cyg.utils.DataWrapper;
import com.cyg.utils.SessionManager;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	NewsDao newsDao;
	
	@Autowired
	NewsContentDao newsContentDao;
	

	@Autowired
	NewsTitleDao newsTitleDao;

	@Override
	public DataWrapper<List<News>> getNoticeList(Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		return newsDao.getNoticeList(numPerPage, pageNum);
	}

	@Override
	public DataWrapper<Void> addNotice(News news, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0 && news.getTitle() != null && news.getContent() != null && !news.getTitle().equals("") && !news.getContent().equals("")) {
			NewsContent newsContent = new NewsContent();
			NewsTitle newsTitle = new NewsTitle();
			newsTitle.setId(null);
			newsTitle.setTitle(news.getTitle());
			newsTitle.setCatId(new Long(6599));
			newsTitle.setStatus(new Long(99));
			newsTitle.setPic(news.getPic()==null? "": news.getPic());
			newsTitle.setPicTitle("");
			newsTitle.setDate(System.currentTimeMillis()/1000);
			newsTitle.setUserName(admin.getLoginName());
			newsTitle.setUpdateDate(new Long(0));
			if (newsTitleDao.addNewsTite(newsTitle)) {
				newsContent.setId(newsTitle.getId());
				newsContent.setContent(news.getContent());
				newsContent.setReadPoint(new Long(0));
				if (!newsContentDao.saveNewsContent(newsContent)) {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteNotice(Long newsId, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0 && newsId != null) {
			if (!newsDao.deleteNews("cms_content",newsId) || !newsDao.deleteNews("cms_c_news", newsId)) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<News> getById(Long newsId) {
		// TODO Auto-generated method stub
		DataWrapper<News> dataWrapper = new DataWrapper<News>();
		newsContentDao.updateReadPoint(newsId);
		dataWrapper.setData(newsDao.getNoticeById(newsId));
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> update(News news, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0 && news.getId() != null) {
			NewsContent newsContent = newsContentDao.getById(news.getId());
			NewsTitle newsTitle = newsTitleDao.getById(news.getId());
			
			
			if (newsContent != null && newsTitle != null) {
				if (news.getContent() != null && !news.getContent().equals("")) {
					newsContent.setContent(news.getContent());
				}
				if (news.getTitle() != null && !news.getTitle().equals("")) {
					newsTitle.setTitle(news.getTitle());
				}
				if (news.getPic() != null && !news.getPic().equals("")) {
					newsTitle.setPic(news.getPic());
				}
				newsTitle.setUpdateDate(System.currentTimeMillis()/1000);
				
				if (!newsContentDao.updateNewsContent(newsContent) || !newsTitleDao.updateNewsTitle(newsTitle)) {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

}
