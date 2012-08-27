package com.chinarewards.posnet2.report.service.user;

import java.util.List;

import com.chinarewards.posnet2.report.domain.activity.Activity;
import com.chinarewards.posnet2.report.exception.ServiceLevelException;

public interface LoginService {
	
	public List<Activity> findAllActivityList() throws ServiceLevelException;
	
}
