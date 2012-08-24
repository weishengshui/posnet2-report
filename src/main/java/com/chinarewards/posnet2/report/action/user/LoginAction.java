package com.chinarewards.posnet2.report.action.user;

import com.chinarewards.posnet2.report.Foo;
import com.chinarewards.posnet2.report.action.BaseAction;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = -7279698782023245490L;
	
	private Foo foo;
	
	public Foo getFoo() {
		return foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}

	public String userLogin() {
		logger.info("User Login: {1}, {2}", foo.getId(), foo.getName());
		return SUCCESS;
	}
	
}
