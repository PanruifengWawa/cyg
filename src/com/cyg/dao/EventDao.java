package com.cyg.dao;

import java.util.Date;
import java.util.List;

import com.cyg.models.Event;
import com.cyg.utils.DataWrapper;

public interface EventDao {
	DataWrapper<List<Event>> getEventList(Date startDate, Date endDate,Integer status,Long userId);
	DataWrapper<List<Event>> getEventList(String useTimeId,String rentalPlace,Date useDate,Integer status,Integer numPerPage,Integer pageNum);
	boolean addEvent(Event event);
	boolean deleteEvent(Event event);
	boolean updateEvent(Event event);
	Event getById(Long id);
}
