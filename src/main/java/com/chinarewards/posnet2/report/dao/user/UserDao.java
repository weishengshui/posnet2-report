//package com.chinarewards.posnet2.report.dao.user;
//
//import org.hibernate.Query;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.chinarewards.posnet2.report.dao.BaseDao;
//import com.chinarewards.posnet2.report.domain.Report_User;
//import com.chinarewards.posnet2.report.exception.DaoLevelException;
//
//public class UserDao  extends BaseDao<Report_User>{
//
//	private Logger logger = LoggerFactory.getLogger(getClass());
//	
//	public Report_User getUserByUsername(String username) throws DaoLevelException {
//		logger.debug("call getUserByUsername()");
//		String hsql = "from Report_User u where u.username=:username ";
//		logger.trace(hsql);
//		try {
//			Query q = getSession().createQuery(hsql);
//			q.setParameter("username", username);
//			return (Report_User)q.uniqueResult();
//		} catch (Throwable e) {
//			throw new DaoLevelException(e);
//		}
//	}
//}
