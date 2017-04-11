package com.cyg.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cyg.dao.PhotoWallDao;
import com.cyg.enums.ErrorCodeEnum;
import com.cyg.models.PhotoWall;
import com.cyg.models.User;
import com.cyg.service.PhotoWallService;
import com.cyg.utils.DataWrapper;
import com.cyg.utils.DateUtil;
import com.cyg.utils.FileUtils;
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
	public DataWrapper<Void> add(String content, MultipartFile file, String token,HttpServletRequest request) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && content != null && file != null) {
			String fileSrc = FileUtils.saveFile(file, "photoWall", request);
			if (fileSrc != null) {
				Date date = new Date();
				PhotoWall photoWall = new PhotoWall();
				photoWall.setId(null);
				photoWall.setContent(content);
				photoWall.setSrc(fileSrc);
				photoWall.setDate(date);
				photoWall.setYear(DateUtil.getYear(date));
				if (!photoWallDao.addPhotoWall(photoWall)) {
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
	public DataWrapper<Void> delete(Long photoWallId, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && photoWallId != null) {
			PhotoWall photoWall = photoWallDao.getById(photoWallId);
			if (photoWall != null) {
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
