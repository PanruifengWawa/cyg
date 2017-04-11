package com.cyg.service;

import java.util.List;

import com.cyg.models.Mooc;
import com.cyg.utils.DataWrapper;

public interface MoocService {
	DataWrapper<String> getQiNiuToken(String token);
	
	DataWrapper<List<Mooc>> getMoocList(Integer numPerPage,Integer pageNum);

	DataWrapper<Void> add(String title,String src,String token);
	
	DataWrapper<Void> delete(Long moocId,String token);
}
