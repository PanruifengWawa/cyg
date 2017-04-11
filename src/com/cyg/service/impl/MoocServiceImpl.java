package com.cyg.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyg.dao.MoocDao;
import com.cyg.enums.ErrorCodeEnum;
import com.cyg.models.Mooc;
import com.cyg.models.User;
import com.cyg.service.MoocService;
import com.cyg.utils.DataWrapper;
import com.cyg.utils.QiNiuUtil;
import com.cyg.utils.SessionManager;

@Service("moocService")
public class MoocServiceImpl implements MoocService {
	@Autowired
	MoocDao moocDao;

	@Override
	public DataWrapper<String> getQiNiuToken(String token) {
		// TODO Auto-generated method stub
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0) {
			String upToken =  QiNiuUtil.getUploadToken();
			dataWrapper.setData(upToken);
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Mooc>> getMoocList(Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		return moocDao.getMoocList(numPerPage, pageNum);
	}

	@Override
	public DataWrapper<Void> add(String title, String src, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0 && title != null && src != null) {
			Mooc mooc = new Mooc();
			mooc.setId(null);
			mooc.setTitle(title);
			mooc.setSrc(src);
			mooc.setDate(new Date());
			if (!moocDao.addMooc(mooc)) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> delete(Long moocId, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0 && moocId != null) {
			Mooc mooc = moocDao.getById(moocId);
			if (mooc != null) {
				if (!moocDao.deleteMooc(mooc)) {
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
