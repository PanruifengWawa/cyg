package com.cyg.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cyg.models.PhotoWall;
import com.cyg.utils.DataWrapper;

public interface PhotoWallService {
	DataWrapper<List<PhotoWall>> getPhotoList(Integer year,Integer numPerPage,Integer pageNum);
	DataWrapper<Void> add(String content,String src, Integer width,Integer height,String token);
	
	DataWrapper<Void> delete(Long photoWallId,String token);
}
