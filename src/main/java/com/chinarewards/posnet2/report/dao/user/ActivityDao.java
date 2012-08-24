package com.chinarewards.posnet2.report.dao.user;

import java.util.List;

import org.hibernate.Query;

import com.chinarewards.posnet2.report.dao.BaseDao;
import com.chinarewards.posnet2.report.domain.activity.Activity;

public class ActivityDao extends BaseDao<Activity> {
	
	public List<Activity> findAll() {
		String hql = "FROM Activity a1";
		logger.trace(hql);
		Query q = getSession().createQuery(hql);
		return q.list();
	}
	
}
