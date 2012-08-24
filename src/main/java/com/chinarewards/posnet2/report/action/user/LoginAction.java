package com.chinarewards.posnet2.report.action.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.Foo;
import com.chinarewards.posnet2.report.action.BaseAction;
import com.chinarewards.posnet2.report.domain.activity.Activity;
import com.chinarewards.posnet2.report.service.user.LoginService;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = -7279698782023245490L;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private LoginService loginService;
	
	private Foo foo;
	
	private List<Activity> activityList;
	
	public String userLogin() {
		logger.info("User Login: {1}, {2}", foo.getId(), foo.getName());
		return SUCCESS;
	}
	
	public String findAllActivity() {
		logger.info("action.findAllActivity();");
		activityList = loginService.findAllActivityList();
		return SUCCESS;
	}
	
	public Foo getFoo() {
		return foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

}
