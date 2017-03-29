package com.cyg.dao;

import java.math.BigInteger;
import java.util.List;

import com.cyg.models.Event;
import com.cyg.models.EventApply;
import com.cyg.utils.DataWrapper;

public interface EventApplyDao {
	EventApply getByUserIdEventId(Long userId,Long eventId);
	boolean addEventApply(EventApply eventApply);
	boolean updateEventApply(Long eventApplyId,Integer state);
	DataWrapper<List<EventApply>> getEventApplyList(Long eventId, Integer state,Integer numPerPage,Integer pageNum);
	
	DataWrapper<List<Event>> getAppliedEventList(Long userId, Integer numPerPage, Integer pageNum);
	
	BigInteger getCount(Long eventId);
	
	int getcount(Long eventId, Integer state,Long userId); 

}
