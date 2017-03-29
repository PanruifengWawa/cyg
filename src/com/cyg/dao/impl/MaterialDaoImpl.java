package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.MaterialDao;
import com.cyg.models.Material;
import com.cyg.utils.DaoUtils;
import com.cyg.utils.DataWrapper;

@Repository
public class MaterialDaoImpl extends BaseDao<Material> implements MaterialDao {

	@Override
	public DataWrapper<List<Material>> getMaterialList(Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		DataWrapper<List<Material>> dataWrapper = new DataWrapper<List<Material>>();
		List<Material> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Material.class);
		
		if (numPerPage == null) {
			numPerPage = 10;
		}
        if (pageNum == null) {
			pageNum = 1;
		}
        
        
        criteria.setProjection(Projections.rowCount());
        int totalltemNum = ((Long) criteria.uniqueResult()).intValue();
        int totalPageNum = DaoUtils.getTotalPageNum(totalltemNum, numPerPage);
        
        criteria.setProjection(null);
        criteria.addOrder(Order.desc("createTime"));
        if (numPerPage > 0 && pageNum > 0) {
            criteria.setMaxResults(numPerPage);
            criteria.setFirstResult((pageNum - 1) * numPerPage);
        }
        
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		dataWrapper.setCurrentPage(pageNum);
		dataWrapper.setNumberPerPage(numPerPage);
		dataWrapper.setTotalPage(totalPageNum);
		dataWrapper.setTotalNumber(totalltemNum);
        dataWrapper.setData(ret);
        return dataWrapper;
	}

	@Override
	public boolean addMaterial(Material material) {
		// TODO Auto-generated method stub
		return save(material);
	}

	@Override
	public boolean deleteMaterial(Long id) {
		// TODO Auto-generated method stub
		return delete(get(id));
	}

}
