package com.cyg.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cyg.models.Material;
import com.cyg.utils.DataWrapper;

public interface MaterialService {
	DataWrapper<List<Material>> getMaterialList(Integer numPerPage,Integer pageNum);
	DataWrapper<Void> uploadMaterial(String name,MultipartFile material,String token, HttpServletRequest request);
	DataWrapper<Void> deleteMaterial(Long materialId,String token);
	
	DataWrapper<String> uploadFile(MultipartFile file,String token, HttpServletRequest request);
}
