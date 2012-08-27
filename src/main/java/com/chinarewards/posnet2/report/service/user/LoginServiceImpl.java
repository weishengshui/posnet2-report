package com.chinarewards.posnet2.report.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.dao.user.ActivityDao;
import com.chinarewards.posnet2.report.domain.activity.Activity;
import com.chinarewards.posnet2.report.exception.DaoLevelException;
import com.chinarewards.posnet2.report.exception.ServiceLevelException;


public class LoginServiceImpl implements LoginService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ActivityDao activityDao;

	@Override
	public List<Activity> findAllActivityList() throws ServiceLevelException {
		logger.debug("service.findAllActivityList();  fuck jrebel-2.1a");
		try {
			return activityDao.findAll();
		} catch (DaoLevelException e) {
			throw new ServiceLevelException(e);
		}
	}

	public ActivityDao getActivityDao() {
		return activityDao;
	}

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}
	
}
