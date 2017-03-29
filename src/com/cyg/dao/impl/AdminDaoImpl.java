package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cyg.dao.AdminDao;
import com.cyg.dao.BaseDao;
import com.cyg.models.Admin;

@Repository
public class AdminDaoImpl extends BaseDao<Admin> implements AdminDao {

	@Override
	public Admin getByUserName(String userName) {
		// TODO Auto-generated method stub
		List<Admin> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Admin.class);
		criteria.add(Restrictions.eq("userName", userName));
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (ret != null && ret.size() > 0) {
			return ret.get(0);
		}
		return null;
	}

}
