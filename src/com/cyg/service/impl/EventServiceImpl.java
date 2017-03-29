package com.cyg.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cyg.dao.EventApplyDao;
import com.cyg.dao.EventDao;
import com.cyg.enums.ErrorCodeEnum;
import com.cyg.models.Event;
import com.cyg.models.User;
import com.cyg.service.EventService;
import com.cyg.utils.DataWrapper;
import com.cyg.utils.DateUtil;
import com.cyg.utils.FileUtils;
import com.cyg.utils.SessionManager;
import com.cyg.utils.UseTimeUtil;

@Service("eventService")
public class EventServiceImpl implements EventService {
	@Autowired
	EventDao eventDao;
	
	@Autowired
	EventApplyDao eventApplyDao;

	@Override
	public DataWrapper<List<Event>> getEventList(String useTimeId,String rentalPlace,String startDate,String endDate,Integer status,String token,Integer numPerPage,Integer pageNum) {
		// TODO Auto-generated method stub
		User user = SessionManager.getSession(token);
		if (user != null && user.getId() < 0) {
			DataWrapper<List<Event>> dataWrapper = eventDao.getEventList(useTimeId,rentalPlace,DateUtil.parse(startDate), status,numPerPage, pageNum);
			for(Event e: dataWrapper.getData()) {
				if (e.getId() == null) {
					e.setApplyCount(BigInteger.valueOf(0));
				} else {
					e.setApplyCount(eventApplyDao.getCount(e.getId()));
				}
				
			}
			return dataWrapper;
		} else if (user != null) {
			return eventDao.getEventList(DateUtil.parse(startDate), DateUtil.parse(endDate),status,user.getId());
		} else {
			return eventDao.getEventList(DateUtil.parse(startDate), DateUtil.parse(endDate),2,null);
		}

		
	}

	@Override
	public DataWrapper<Void> addEvent(Event event, MultipartFile file,String useDateStr, String token, HttpServletRequest request) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		if (user != null && event.getEventName() != null && !event.getEventName().equals("") && event.getContactPhone() != null && 
				!event.getContactPhone().equals("") && event.getContactName() != null && !event.getContactName().equals("") &&
				event.getUseTimeId() != null) {
			Date useDate = DateUtil.parse(useDateStr);
			String useTime = UseTimeUtil.parse(event.getUseTimeId());
			String photo = FileUtils.saveFile(file, "photo", request);
			if (useDate != null && useTime != null && !useTime.equals("") && photo != null) {
				event.setId(null);
				event.setUserId(user.getId());
				event.setUseDate(useDate);
				event.setUseTime(useTime);
				event.setPhoto(photo);
				event.setAppointmentTime(System.currentTimeMillis()/1000);
				event.setOtherEquipment(event.getOtherEquipment()==null? "":event.getOtherEquipment());
				event.setMobilePhone(event.getMobilePhone()==null? "":event.getMobilePhone());
				event.setStatus(1);
				if (!eventDao.addEvent(event)) {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteEvent(Long eventId, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		if (user != null) {
			Event event = eventDao.getById(eventId);
			if (event != null && event.getUserId().equals(user.getId())) {
				if (!eventDao.deleteEvent(event)) {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Event> getById(Long eventId, String token) {
		// TODO Auto-generated method stub
		Event event = eventDao.getById(eventId);
		DataWrapper<Event> dataWrapper = new DataWrapper<Event>();
		if (event != null) {
			if (event.getStatus() == 2) {
				dataWrapper.setData(event);
			} else {
				User user = SessionManager.getSession(token);
				if (user != null && ( user.getId() < 0 || user.getId().equals(event.getUserId()) ) ) {
					dataWrapper.setData(event);
				} else {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> verify(Long eventId, Integer status, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0 && status != null && status >= 1 && status <= 3) {
			Event event = eventDao.getById(eventId);
			if (event != null) {
				
				boolean flag = false;//no confict
				
				if (status == 2) {
					String[] useTimeId = event.getUseTimeId() != null ?  event.getUseTimeId().split(",") : null;
					
					List<Event> checkEvents = eventDao.getEventList(event.getUseDate(), event.getUseDate(), status,null).getData();
					if (checkEvents != null) {
						for(Event temp: checkEvents) {
							if (temp.getRentalPlace().equals(event.getRentalPlace()) && UseTimeUtil.checkConfict(useTimeId, temp.getUseTimeId())) {
								flag = true;
								break;
							}
						}
					}
					
					
				}
				
				if (!flag) {
					event.setStatus(status);
					if (!eventDao.updateEvent(event)) {
						dataWrapper.setErrorCode(ErrorCodeEnum.Error);
					}
				} else {
					dataWrapper.setErrorCode(ErrorCodeEnum.Confict);
				}
				

			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

}
