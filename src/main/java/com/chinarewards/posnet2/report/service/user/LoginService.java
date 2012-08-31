package com.chinarewards.posnet2.report.service.user;

import java.util.List;

import com.chinarewards.posnet2.report.domain.Activity;
import com.chinarewards.posnet2.report.exception.ServiceLevelException;

public interface LoginService {
	
	public List<Activity> findAllActivityList() throws ServiceLevelException;
	
	public boolean isRightUsernamePwd(String username, String password)throws ServiceLevelException;
	
}
