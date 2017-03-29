package com.cyg.service;

import java.util.List;

import com.cyg.models.Event;
import com.cyg.models.EventApply;
import com.cyg.utils.DataWrapper;

public interface EventApplyService {
	DataWrapper<Void> addEventApply(Long eventId,String token);
	DataWrapper<Void> verifyEventApply(Long eventApplyId,Integer state,String token);
	DataWrapper<List<EventApply>> getEventApplyList(Long eventId,Integer state,String token,Integer numPerPage, Integer pageNum);
	
	DataWrapper<List<Event>> getAppliedEventList(String token,Integer numPerPage, Integer pageNum);

}
