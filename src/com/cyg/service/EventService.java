package com.cyg.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cyg.models.Event;
import com.cyg.utils.DataWrapper;

public interface EventService {
	DataWrapper<List<Event>> getEventList(String useTimeId,String rentalPlace,String startDate,String endDate,Integer status,String token,Integer numPerPage,Integer pageNum);
	DataWrapper<Void> addEvent(Event event,MultipartFile file,String useDateStr,String token, HttpServletRequest request);
	DataWrapper<Void> deleteEvent(Long eventId,String token);
	
	DataWrapper<Event> getById(Long eventId,String token);
	
	DataWrapper<Void> verify(Long eventId,Integer status,String token);
}
