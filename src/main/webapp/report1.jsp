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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World!</h1>
	<table>
		<tr>
			<td><a href="report1.jsp?page=1">1</a></td>
			<td><a href="report1.jsp?page=2">2</a></td>
		</tr>
	</table>
	<%
		File reportFile = new File(application
		        .getRealPath("jasper/date_count.jasper"));
		JasperReport jasperReport = (JasperReport) JRLoader
		        .loadObject(reportFile.getPath());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("date_start", "2012-07-30 00:00:00");
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
		        "jdbc:mysql://119.146.223.2:3306/posnet", "report", "posnet");
// 		文件缓存
// 		JRFileVirtualizer virtualizer = new JRFileVirtualizer(2, reportFile.getParentFile() + "\\cacheDir");
// 		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(
		        jasperReport, parameters, conn);
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		
// 		分页
		String p = request.getParameter("page");
		exporter.setParameter(JRExporterParameter.PAGE_INDEX, Integer.parseInt(p));
		exporter.exportReport();
		out.flush();
		conn.close();
	%>
</body>
</html>