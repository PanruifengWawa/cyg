package com.cyg.dao;

import java.util.List;

import com.cyg.models.Material;
import com.cyg.utils.DataWrapper;

public interface MaterialDao {
	DataWrapper<List<Material>> getMaterialList(Integer numPerPage, Integer pageNum);
	boolean addMaterial(Material material);
	boolean deleteMaterial(Long id);
}
