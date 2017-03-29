package com.cyg.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cyg.dao.MaterialDao;
import com.cyg.enums.ErrorCodeEnum;
import com.cyg.models.Material;
import com.cyg.models.User;
import com.cyg.service.MaterialService;
import com.cyg.utils.DataWrapper;
import com.cyg.utils.FileUtils;
import com.cyg.utils.SessionManager;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService {
	@Autowired
	MaterialDao materialDao;

	@Override
	public DataWrapper<List<Material>> getMaterialList(Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		return materialDao.getMaterialList(numPerPage, pageNum);
	}

	@Override
	public DataWrapper<Void> uploadMaterial(String name, MultipartFile material, String token, HttpServletRequest request) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0 && name != null && material != null) {
			String filePath = FileUtils.saveFile(material, "material", request);
			if (filePath != null) {
				Material materialEntity = new Material();
				materialEntity.setId(null);
				materialEntity.setName(name);
				materialEntity.setUrl(filePath);
				materialEntity.setIcon("");
				materialEntity.setCreateTime(System.currentTimeMillis()/1000);
				if (!materialDao.addMaterial(materialEntity)) {
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
	public DataWrapper<Void> deleteMaterial(Long materialId, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0 && materialId != null) {
			if (!materialDao.deleteMaterial(materialId)) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<String> uploadFile(MultipartFile file, String token, HttpServletRequest request) {
		// TODO Auto-generated method stub
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0 && file != null) {
			String filePath = FileUtils.saveFile(file, "file", request);
			if (filePath != null) {
				dataWrapper.setData(filePath);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

}
