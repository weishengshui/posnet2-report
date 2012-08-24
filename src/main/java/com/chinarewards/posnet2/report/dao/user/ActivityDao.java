package com.chinarewards.posnet2.report.dao.user;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.dao.BaseDao;
import com.chinarewards.posnet2.report.domain.activity.Activity;

public class ActivityDao extends BaseDao<Activity> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public List<Activity> findAll() {
		logger.debug("damn u jrebel-2.1a");
		String hql = "FROM Activity a1";
		logger.trace(hql);
		Query q = getSession().createQuery(hql);
		return q.list();
	}
	
}
