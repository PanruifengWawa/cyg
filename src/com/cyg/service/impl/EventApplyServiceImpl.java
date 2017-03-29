package com.cyg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyg.dao.EventApplyDao;
import com.cyg.enums.ErrorCodeEnum;
import com.cyg.models.Event;
import com.cyg.models.EventApply;
import com.cyg.models.User;
import com.cyg.service.EventApplyService;
import com.cyg.utils.DataWrapper;
import com.cyg.utils.SessionManager;

@Service("eventApplyService")
public class EventApplyServiceImpl implements EventApplyService {
	
	@Autowired
	EventApplyDao eventApplyDao;

	@Override
	public DataWrapper<Void> addEventApply(Long eventId, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		if (user != null && eventId != null) {
			
			EventApply eventApply = eventApplyDao.getByUserIdEventId(user.getId(), eventId);
			if (eventApply == null) {
				eventApply = new EventApply();
				eventApply.setId(null);
				eventApply.setState(0);
				eventApply.setUserId(user.getId());
				eventApply.setRegistrationTime(System.currentTimeMillis()/1000);
				eventApply.setEventId(eventId);
				if (!eventApplyDao.addEventApply(eventApply)) {
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
	public DataWrapper<Void> verifyEventApply(Long eventApplyId, Integer state, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0 && eventApplyId != null && state != null && state >=0 && state <= 1) {
			if (!eventApplyDao.updateEventApply(eventApplyId, state)) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<EventApply>> getEventApplyList(Long eventId, Integer state, String token,Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		
		DataWrapper<List<EventApply>> dataWrapper = null;
		User user = SessionManager.getSession(token);
		if (user != null && eventId != null) {
			dataWrapper = eventApplyDao.getEventApplyList(eventId, state, numPerPage, pageNum);
		} else {
			dataWrapper = new  DataWrapper<List<EventApply>>();
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Event>> getAppliedEventList(String token, Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		DataWrapper<List<Event>> dataWrapper = null;
		User user = SessionManager.getSession(token);
		if (user != null) {
			dataWrapper = eventApplyDao.getAppliedEventList(user.getId(), numPerPage, pageNum);
		} else {
			dataWrapper = new  DataWrapper<List<Event>>();
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

}
