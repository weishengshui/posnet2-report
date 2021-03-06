package com.chinarewards.posnet2.report.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import com.chinarewards.posnet2.report.domain.User;
import com.chinarewards.posnet2.report.exception.ServiceLevelException;
import com.chinarewards.posnet2.report.service.user.LoginService;

public class ValidateCodeUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private boolean postOnly = true;  
    private boolean allowEmptyValidateCode = false;  
    private String sessionvalidateCodeField = DEFAULT_SESSION_VALIDATE_CODE_FIELD;  
    private String validateCodeParameter = DEFAULT_VALIDATE_CODE_PARAMETER;  
    public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";  
    // session中保存的验证码  
    public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "rand";  
    // 输入的验证码  
    public static final String DEFAULT_VALIDATE_CODE_PARAMETER = "code";  
	 
    private LoginService loginService;
	 
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		if (postOnly && !request.getMethod().equals("POST")) {  
            throw new AuthenticationServiceException(  
                    "Authentication method not supported: "  
                            + request.getMethod());  
        }  

		String username = obtainUsername(request);  
        String password = obtainPassword(request);  
        if (username == null) {  
            username = "";  
        }  
        if (password == null) {  
            password = "";  
        }  
        username = username.trim();  
  
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(  
                username, password);  
  
        // Place the last username attempted into HttpSession for views  
        HttpSession session = request.getSession(false);  
  
        if (session != null || getAllowSessionCreation()) {  
            request.getSession().setAttribute(  
                    SPRING_SECURITY_LAST_USERNAME_KEY,  
                    TextEscapeUtils.escapeEntities(username));  
        }  
  
        // Allow subclasses to set the "details" property  
        setDetails(request, authRequest);  
        // check validate code  
        if (!isAllowEmptyValidateCode())  
            checkValidateCode(request);  
        // 根据用户和密码查询  
        User user = null;
        try {
        	user= loginService.getUserByUsernamePwd(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("User",  
                user);  
        String userLoginIP = request.getRemoteAddr();
		logger.info("!Login attempt detected. Username={}, IP={}",
				new Object[] { username, userLoginIP });
		request.getSession().setAttribute("Login", "OK");
        return this.getAuthenticationManager().authenticate(authRequest);  
    }  
  
    /** 
     *  
     * <li>比较session中的验证码和用户输入的验证码是否相等</li> 
     *  
     */  
    protected void checkValidateCode(HttpServletRequest request) {  
    	//session里面的验证码
        String sessionValidateCode = obtainSessionValidateCode(request);  
        //用户输入的验证码
        String validateCodeParameter = obtainValidateCodeParameter(request);  
        if (StringUtils.isEmpty(validateCodeParameter)  
                || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {  
            throw new AuthenticationServiceException("验证码错误！");  
        }  
    }  
  
    private String obtainValidateCodeParameter(HttpServletRequest request) {  
        return request.getParameter(validateCodeParameter);  
    }  
  
    protected String obtainSessionValidateCode(HttpServletRequest request) {  
        Object obj = request.getSession()  
                .getAttribute(sessionvalidateCodeField);  
        return null == obj ? "" : obj.toString();  
    }  
  
    public boolean isPostOnly() {  
        return postOnly;  
    }  
  
    @Override  
    public void setPostOnly(boolean postOnly) {  
        this.postOnly = postOnly;  
    }  
  
    public String getValidateCodeName() {  
        return sessionvalidateCodeField;  
    }  
  
    public void setValidateCodeName(String validateCodeName) {  
        this.sessionvalidateCodeField = validateCodeName;  
    }  
  
    public boolean isAllowEmptyValidateCode() {  
        return allowEmptyValidateCode;  
    }  
  
    public void setAllowEmptyValidateCode(boolean allowEmptyValidateCode) {  
        this.allowEmptyValidateCode = allowEmptyValidateCode;  
    }  
  
}  