package com.chinarewards.posnet2.report.action.report;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

/**
 * 报表输出的工具类，输出三种不同类型的报表格式，HTML、PDF、XLS。
 */
public class ReportExporter {
	
	// 输出PDF格式
	public static void exportPdf(HttpServletResponse response,
			JasperPrint jasperPrint) throws IOException, JRException {
		response.setContentType("application/pdf");
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		OutputStream ouputStream = response.getOutputStream();
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
		exporter.exportReport();
	}

	// 输出XLS格式
	public static void exportXls(HttpServletResponse response,
			JasperPrint jasperPrint, String nameXls) throws IOException,
			JRException {
		response.setContentType("application/xls");
		response.setHeader("Content-Disposition", "inline; filename=" + nameXls);
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		OutputStream ouputStream = response.getOutputStream();
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
		exporter.setParameter(
				JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporter.setParameter(
				JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);
		exporter.exportReport();
	}

	// 输出HTML格式
	public static void exportHtml(HttpServletResponse response,
			HttpServletRequest request, ServletContext context,
			JasperPrint jasperPrint) throws IOException, JRException {
		response.setContentType("text/html;charset=GB2312");
		request.setCharacterEncoding("GB2312");
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "GB2312");
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				response.getOutputStream());
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
				Boolean.FALSE);
		request.getSession().setAttribute(
				ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
				jasperPrint);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
				request.getContextPath() + "/servlet/image?image=");
		exporter.exportReport();
	}

}
