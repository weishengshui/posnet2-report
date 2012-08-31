package com.chinarewards.posnet2.report.action.user;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.json.annotations.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.posnet2.report.Foo;
import com.chinarewards.posnet2.report.action.BaseAction;
import com.chinarewards.posnet2.report.action.report.ReportExporter;
import com.chinarewards.posnet2.report.domain.Activity;
import com.chinarewards.posnet2.report.exception.ServiceLevelException;
import com.chinarewards.posnet2.report.service.user.LoginService;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = -7279698782023245490L;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private LoginService loginService;
	
	private Foo foo;
	
	private List<Activity> activityList;
	
	private Boolean isJson;
	
	private Map<String, Integer> jsonMap;
	
	public String userLogin() {
		logger.info("User Login: {}, {}", foo.getId(), foo.getName());
		return SUCCESS;
	}
	
	public String findAllActivity() {
		logger.info("action.findAllActivity(); fuck jrebel_2.1a");
		try {
			activityList = loginService.findAllActivityList();
		} catch (ServiceLevelException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String testJson1(){
		isJson = true;
		return SUCCESS;
	}
	
	public String testJson2(){
		jsonMap = new HashMap<String, Integer>();
		jsonMap.put("01", 100);
		jsonMap.put("02", 100);
		jsonMap.put("03", 100);
		return SUCCESS;
	}
	
	public String testReport1() throws Exception {
		logger.info("action.testReport1();");
		activityList = loginService.findAllActivityList();
		
		try {
			String reportFileName = application.getRealPath("/WEB-INF/jasper/Activity.jasper");
			File reportFile = new File(reportFileName);
			if (!reportFile.exists())
				throw new Exception("指定的模板文件不存在!.");
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("SUBREPORT_DIR", application.getRealPath("/WEB-INF/jasper/") + "/");
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					reportFileName, parameters, new JRBeanCollectionDataSource(activityList));
			String path = this.request.getRequestURI();
			if(path.indexOf("/test_report1XLS.action") != -1){
				String nameXls = "ExchangeStatusDetail.xls";
				ReportExporter.exportXls(response, jasperPrint, nameXls);
			}
			if(path.indexOf("/test_report1PDF.action") != -1){
				ReportExporter.exportPdf(response, jasperPrint);
			}
			if(path.indexOf("/test_report1HTML.action") != -1){
				ReportExporter.exportHtml(response, request, application, jasperPrint);
			}
			
		} catch (JRException e) {
			logger.error("查询开票明细报表时出错",e);
			ActionContext ctx = ActionContext.getContext();
			ctx.put("errMsg", "查询开票明细报表时出错,请联系管理员");
			throw e;
		} catch (Exception e) {
			logger.error("查询开票明细报表时发生未知异常",e);
			ActionContext ctx = ActionContext.getContext();
			ctx.put("errMsg", "查询开票明细报表时发生未知异常,请联系管理员");
			throw e;
		}
		return SUCCESS;
	}
	
	@JSON(serialize = false)
	public Foo getFoo() {
		return foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}
	
	@JSON(serialize = false)
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@JSON(serialize = false)
	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}
	
	public Boolean getIsJson() {
		return isJson;
	}

	public void setIsJson(Boolean isJson) {
		this.isJson = isJson;
	}
	
	public Map<String, Integer> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Integer> jsonMap) {
		this.jsonMap = jsonMap;
	}

}
