package com.cyg.dao;

import java.util.List;

import com.cyg.models.Mooc;
import com.cyg.utils.DataWrapper;

public interface MoocDao {
	boolean addMooc(Mooc mooc);
	boolean deleteMooc(Mooc mooc);
	Mooc getById(Long id);
	DataWrapper<List<Mooc>> getMoocList(Integer numPerPage, Integer pageNum);

}
