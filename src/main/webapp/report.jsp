<%@page import="net.sf.jasperreports.j2ee.servlets.ImageServlet"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="
		java.io.*,
		net.sf.jasperreports.engine.*,
		net.sf.jasperreports.engine.util.*,
		java.util.*,java.sql.*,
		net.sf.jasperreports.engine.export.*,
		net.sf.jasperreports.engine.fill.*
"%>
<%@page import="com.chinarewards.jreport.common.MySqlDBConnection"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		MySqlDBConnection.initialize();
		Connection conn = MySqlDBConnection.getConnectionFromDatabase();
		try {
			File reportFile = new File(application.getRealPath("jasper"), 
					request.getParameter("jasper"));
			JasperReport jasperReport = (JasperReport) JRLoader
			        .loadObject(reportFile.getPath());
			Map<String, Object> parameters = new HashMap<String, Object>();
			for(Object key:request.getParameterMap().keySet()){
				parameters.put(String.valueOf(key), request.getParameter(String.valueOf(key)));
				System.out.println("key:"+ key + " , value:"+ request.getParameter(String.valueOf(key)));
			}
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(
			        jasperReport, parameters, conn);
			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
			
			request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, 
                    jasperPrint);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath()+"/servlet/image?image=");
						exporter.exportReport();
		} catch (Throwable e) {
			throw new ServletException(e);
		} finally {
			try{
				out.flush();
// 				MySqlDBConnection.release(conn);
				conn.close();
			}catch(Throwable e){
				e.printStackTrace();
			}
		}
	%>
</body>
</html>