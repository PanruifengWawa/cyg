package com.cyg.dao;

import java.util.List;

import com.cyg.models.PhotoWall;
import com.cyg.utils.DataWrapper;

public interface PhotoWallDao {
	DataWrapper<List<PhotoWall>> getPhotoList(Integer year,Integer numPerPage,Integer pageNum);
	boolean addPhotoWall(PhotoWall photoWall);
	PhotoWall getById(Long id);
	boolean deletePhotoWall(PhotoWall photoWall);

}
