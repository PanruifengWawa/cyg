package com.cyg.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyg.dao.PhotoWallDao;
import com.cyg.enums.ErrorCodeEnum;
import com.cyg.models.PhotoWall;
import com.cyg.models.User;
import com.cyg.service.PhotoWallService;
import com.cyg.utils.DataWrapper;
import com.cyg.utils.DateUtil;
import com.cyg.utils.QiNiuUtil;
import com.cyg.utils.SessionManager;

@Service("photoWallService")
public class PhotoWallServiceImpl implements PhotoWallService {
	@Autowired
	PhotoWallDao photoWallDao;

	@Override
	public DataWrapper<List<PhotoWall>> getPhotoList(Integer year, Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		return photoWallDao.getPhotoList(year, numPerPage, pageNum);
	}

	@Override
	public DataWrapper<Void> add(String content, String src,Integer width,Integer height, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && content != null && src != null && width != null && height != null) {
			
			PhotoWall photoWall = new PhotoWall();
			Date date = new Date();
			photoWall.setId(null);
			photoWall.setContent(content);
			photoWall.setSrc(src);
			photoWall.setDate(date);
			photoWall.setYear(DateUtil.getYear(date));
			
			
			String smallSrc = src + "?imageView2/1/w/" + (int)(width/(height/250)) + "/h/" + 250;
			photoWall.setSmallSrc(smallSrc);
			
			if (!photoWallDao.addPhotoWall(photoWall)) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Entity_not_Saved);
			}
			
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Auth_Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> delete(Long photoWallId, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && photoWallId != null) {
			PhotoWall photoWall = photoWallDao.getById(photoWallId);
			if (photoWall != null) {
				String key = photoWall.getSrc().split("/")[photoWall.getSrc().split("/").length-1];
				QiNiuUtil.deleteFile(key);
				
				if (!photoWallDao.deletePhotoWall(photoWall)) {
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
