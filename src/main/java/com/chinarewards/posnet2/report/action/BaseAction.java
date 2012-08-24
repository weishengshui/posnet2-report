package com.chinarewards.posnet2.report.action;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Serializable, Preparable {
	
	private static final long serialVersionUID = -6855114700602349075L;
	/**
	 * Instance of logger for logging message.
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpServletRequest httpServletRequest;

	protected HttpServletResponse httpServletResponse;

	protected HttpSession session;

	protected String msg;

	public BaseAction() {
		super();
	}

	@Override
	public void prepare() throws Exception {
		logger.info("getRequest:" + ServletActionContext.getRequest());
		logger.info("getResponse:" + ServletActionContext.getResponse());
		this.httpServletRequest = ServletActionContext.getRequest();
		this.httpServletResponse = ServletActionContext.getResponse();
		this.session = this.httpServletRequest.getSession();
	}

	/**
	 * description：获取contextPath
	 * @time 2011-6-1   下午04:18:35
	 * @author Seek
	 */
	protected String getContextPath() {
		return this.httpServletRequest.getContextPath();
	}
	
	/**
	 * description：获取当前URI
	 * @time 2011-6-10   下午01:29:54
	 * @author Seek
	 */
	protected String getCurrentPath() {
		StringBuffer urlBuff = new StringBuffer(this.httpServletRequest.getRequestURI());
		if (this.httpServletRequest.getQueryString() != null) {
			urlBuff.append("?");
			urlBuff.append(this.httpServletRequest.getQueryString());
		}
		logger.debug("getCurrentPath()   currentURL:"+urlBuff);
		return urlBuff.toString();
	}
	
}
