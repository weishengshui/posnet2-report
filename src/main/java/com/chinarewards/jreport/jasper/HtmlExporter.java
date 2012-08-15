package com.chinarewards.jreport.jasper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import com.chinarewards.jreport.common.MySqlDBConnection;

/**
 * Servlet implementation class HtmlExporter  //TODO 作废，servlet出不来报表
 */
public class HtmlExporter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HtmlExporter() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MySqlDBConnection.initialize();
		Connection conn = MySqlDBConnection.getConnection();
		
		ServletContext application = request.getSession().getServletContext();
		PrintWriter out= response.getWriter();
		
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
//			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
//			在生成html形式的报表的代码中设置一个参数：
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "/servlet/image?image=");
//			如果浏览器发生图片缓存，还应加上一句：
			exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
			exporter.exportReport();
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			out.flush();
			MySqlDBConnection.release(conn);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
	}

}
